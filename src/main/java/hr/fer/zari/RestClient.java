package hr.fer.zari;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import hr.fer.zari.models.DcmTag;
import hr.fer.zari.models.id.*;
import java.util.Base64;
import hr.fer.zari.services.*;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import javax.net.ssl.SSLSession;

/**
 * Created by eugen on 01/12/2016.
 */
public class RestClient {

    private SystemService systemService;
    private PatientService patientService;
    private StudyService studyService;
    private SeriesService seriesService;
    private InstanceService instanceService;
    private ModalityService modalityService;
    private QueryService queryService;
    
    public RestClient(String apiUrl, String username, String password, boolean enableLogging) {
      this(apiUrl, username, password, enableLogging, true);
    }
    
    public RestClient(String apiUrl, String username, String password, boolean enableLogging, boolean sslVerifyHost) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (enableLogging) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);
        }

        if (!sslVerifyHost) {
            httpClient.hostnameVerifier((String hostname, SSLSession session) -> true);
        }
        
        if (username != null && password != null) {
            httpClient.addInterceptor(createInterceptor(username, password));
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(createGsonConverterFactory())
                .baseUrl(apiUrl)
                .client(client)
                .build();
        OrthancService service = retrofit.create(OrthancService.class);
        createServices(service);
    }

    private static GsonConverterFactory createGsonConverterFactory() {
      // serialize UUIDs to upper-case Strings because Orthanc seems to make
      // case-sensitive comparisons        
      Gson gson = new GsonBuilder()
        
        // hr.fer.zari.models.id.PatientId      
        .registerTypeAdapter(PatientId.class, (JsonSerializer<PatientId>) (PatientId t, Type type, JsonSerializationContext jsc) 
          -> t==null ? null : new JsonPrimitive(t.toString()))
        .registerTypeAdapter(PatientId.class, (JsonDeserializer<PatientId>) (JsonElement json, Type typeOfT, JsonDeserializationContext context) 
          -> new PatientId(json.getAsJsonPrimitive().getAsString())) 
              
        // hr.fer.zari.models.id.StudyId      
        .registerTypeAdapter(StudyId.class, (JsonSerializer<StudyId>) (StudyId t, Type type, JsonSerializationContext jsc) 
          -> t==null ? null : new JsonPrimitive(t.toString()))
        .registerTypeAdapter(StudyId.class, (JsonDeserializer<StudyId>) (JsonElement json, Type typeOfT, JsonDeserializationContext context) 
          -> new StudyId(json.getAsJsonPrimitive().getAsString()))       
              
        // hr.fer.zari.models.id.SeriesId      
        .registerTypeAdapter(SeriesId.class, (JsonSerializer<SeriesId>) (SeriesId t, Type type, JsonSerializationContext jsc) 
          -> t==null ? null : new JsonPrimitive(t.toString()))
        .registerTypeAdapter(SeriesId.class, (JsonDeserializer<SeriesId>) (JsonElement json, Type typeOfT, JsonDeserializationContext context) 
          -> new SeriesId(json.getAsJsonPrimitive().getAsString()))      
              
        // hr.fer.zari.models.id.InstanceId      
        .registerTypeAdapter(InstanceId.class, (JsonSerializer<InstanceId>) (InstanceId t, Type type, JsonSerializationContext jsc) 
          -> t==null ? null : new JsonPrimitive(t.toString()))
        .registerTypeAdapter(InstanceId.class, (JsonDeserializer<InstanceId>) (JsonElement json, Type typeOfT, JsonDeserializationContext context) 
          -> new InstanceId(json.getAsJsonPrimitive().getAsString())) 
              
        // hr.fer.zari.models.id.QueryId      
        .registerTypeAdapter(QueryId.class, (JsonSerializer<QueryId>) (QueryId t, Type type, JsonSerializationContext jsc) 
          -> t==null ? null : new JsonPrimitive(t.toString()))
        .registerTypeAdapter(QueryId.class, (JsonDeserializer<QueryId>) (JsonElement json, Type typeOfT, JsonDeserializationContext context) 
          -> new QueryId(json.getAsJsonPrimitive().getAsString()))       
              
        // hr.fer.zari.models.DcmTag
        .registerTypeAdapter(DcmTag.class, (JsonSerializer<DcmTag>) (DcmTag t, Type type, JsonSerializationContext jsc) 
          -> t==null ? null : new JsonPrimitive(t.toString()))
        .registerTypeAdapter(DcmTag.class, (JsonDeserializer<DcmTag>) (JsonElement json, Type typeOfT, JsonDeserializationContext context) 
          -> new DcmTag(json.getAsJsonPrimitive().getAsString()))
        .create();
      
      return GsonConverterFactory.create(gson);
    }
    
    private Interceptor createInterceptor(String username, String password) {
        final String credentials = username + ":" + password;
        final String basic = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", basic)
                        .header("Accept", "application/json")
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    private void createServices(OrthancService service) {
        systemService = new SystemService(service);
        patientService = new PatientService(service);
        studyService = new StudyService(service);
        seriesService = new SeriesService(service);
        instanceService = new InstanceService(service);
        modalityService = new ModalityService(service);
        queryService = new QueryService(service);
    }

    public SystemService getSystemService() {
        return systemService;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public StudyService getStudyService() {
        return studyService;
    }

    public SeriesService getSeriesService() {
        return seriesService;
    }

    public InstanceService getInstanceService() {
        return instanceService;
    }
    
    public ModalityService getModalityService() {
        return modalityService;
    }
    
    public QueryService getQueryService() {
        return queryService;
    }
}
