package hr.fer.zari;

import hr.fer.zari.models.id.*;
import hr.fer.zari.models.*;
import hr.fer.zari.models.Header;
import hr.fer.zari.models.Statistics.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;

/**
 * Created by eugen on 01/12/2016.
 */
public interface OrthancService {

    /* SYSTEM */
    @GET("system")
    Call<SystemInfo> getSystemInfo();
    @GET("statistics")
    Call<SystemStatistics> getStatistics();

    /* PATIENTS */
    @GET("patients")
    Call<List<String>> getPatients();
    @GET("patients/{id}")
    Call<Patient> getPatient(@Path("id") PatientId patientId);
    /* Create ZIP */
    @Streaming
    @GET("patients/{id}/archive")
    Call<ResponseBody> getPatientZipData(@Path("id") PatientId patientId);
    /* Retrieve all the instances of this patient in a single REST call */
    @GET("patients/{id}/instances")
    Call<List<Instance>> getPatientInstances(@Path("id") PatientId patientId);
    /* Create a ZIP archive for media storage with DICOMDIR */
    @Streaming
    @GET("patients/{id}/media")
    Call<ResponseBody> getPatientMedia(@Path("id") PatientId patientId);
    /* */
    @GET("patients/{id}/module")
    Call<Map<String, Header>> getPatientModule(@Path("id") PatientId patientId);
    /* Retrieve all the series of this patient in a single REST call */
    @GET("patients/{id}/series")
    Call<List<Series>> getPatientSeries(@Path("id") PatientId patientId);
    /* "?simplify" argument to simplify output */
    @GET("patients/{id}/shared-tags")
    Call<Map<String, Header>> getPatientSharedTags(@Path("id") PatientId patientId);
    /* */
    @GET("patients/{id}/statistics")
    Call<PatientStatistics> getPatientStatistics(@Path("id") PatientId patientId);
    /* Retrieve all the studies of this patient in a single REST call */
    @GET("patients/{id}/studies")
    Call<List<Study>> getStudiesForPatient(@Path("id") PatientId patientId);

    /* PLUGINS */
    /* Get the list of all the registered plugins */
    @GET("plugins")
    Call<List<String>> getPlugins();

    /* SERIES */
    @GET("series")
    Call<List<String>> getSeries();
    @GET("series/{id}")
    Call<Series> getSeries(@Path("id") SeriesId seriesId);
    /* Create a ZIP archive for media storage with DICOMDIR */
    @Streaming
    @GET("series/{id}/archive")
    Call<ResponseBody> getSeriesZipData(@Path("id") SeriesId seriesId);
    /* Retrieve all the instances of this series in a single REST call */
    @GET("series/{id}/instances")
    Call<List<Instance>> getInstancesForSeries(@Path("id") SeriesId seriesId);
    /* Create archives for media storage with DICOMDIR */
    @GET("series/{id}/media")
    Call<List<Instance>> getSeriesMedia(@Path("id") SeriesId seriesId);
    /* "?simplify" argument to simplify output */
    @GET("series/{id}/module")
    Call<Map<String, Header>> getSeriesModule(@Path("id") SeriesId seriesId);
    /* Retrieve the parent study of this series */
    @GET("series/{id}/patient")
    Call<Patient> getSeriesPatient(@Path("id") SeriesId seriesId);
    /* "?simplify" argument to simplify output */
    @GET("series/{id}/shared-tags")
    Call<Map<String, Header>> getSeriesSharedTags(@Path("id") SeriesId seriesId);
    /* */
    @GET("series/{id}/statistics")
    Call<SeriesStatistics> getSeriesStatistics(@Path("id") SeriesId seriesId);

    /* STUDIES */
    @GET("studies")
    Call<List<String>> getStudies();
    @GET("studies/{id}")
    Call<Study> getStudy(@Path("id") StudyId studyId);
    /* Create ZIP */
    @Streaming
    @GET("studies/{id}/archive")
    Call<ResponseBody> getStudyZipData(@Path("id") StudyId studyId);
    /* Retrieve all the instances of this patient in a single REST call */
    @GET("studies/{id}/instances")
    Call<List<Instance>> getStudyInstances(@Path("id") StudyId studyId);
    /* Create a ZIP archive for media storage with DICOMDIR */
    @GET("studies/{id}/media")
    Call<ResponseBody> getStudyMedia(@Path("id") StudyId studyId);
    /* "?simplify" argument to simplify output */
    @GET("studies/{id}/module")
    Call<Map<String, Header>> getStudyModule(@Path("id") StudyId studyId);
    /* "?simplify" argument to simplify output */
    @GET("studies/{id}/module-patient")
    Call<Map<String, Header>> getStudyModulePatient(@Path("id") StudyId studyId);
    /* "Retrieve the parent patient of this study */
    @GET("studies/{id}/patient")
    Call<Patient> getStudyPatient(@Path("id") StudyId studyId);
    /* Retrieve all the series of this study in a single REST call */
    @GET("studies/{id}/series")
    Call<List<Series>> getSeriesForStudy(@Path("id") StudyId studyId);
    /* "?simplify" argument to simplify output */
    @GET("studies/{id}/shared-tags")
    Call<Map<String, Header>> getStudySharedTags(@Path("id") StudyId studyId);
    /* */
    @GET("study/{id}/statistics")
    Call<StudyStatistics> getStudyStatistics(@Path("id") StudyId studyId);

    /* INSTANCES */
    @GET("instances")
    Call<List<String>> getInstances();
    @GET("instances/{id}")
    Call<Instance> getInstance(@Path("id") InstanceId instanceId);
    @Streaming
    @GET("instances/{id}/file")
    Call<ResponseBody> getInstanceDicomData(@Path("id") InstanceId instanceId);
    /* List the first-level DICOM tags */
    @GET("instances/{id}/content")
    Call<List<String>> getInstanceContent(@Path("id") InstanceId instanceId);
    /* Raw access to the value of DICOM tags (comprising the padding character) */
    @GET("instances/{id}/content/{group}-{element}")
    Call<List<String>> getInstanceContent(@Path("id") InstanceId instanceId,
                                          @Path("group") String group,
                                          @Path("element") String element);
    /* List the frames */
    @GET("instances/{id}/frames")
    Call<List<Integer>> getInstanceFrames(@Path("id") InstanceId instanceId);
    /* Truncated image to the [-32768;32767] range */
    @GET("instances/{id}/frames/{frameNumber}/image-int16")
    Call<ResponseBody> getInstanceFrameImageInt16(@Path("id") InstanceId instanceId,
                                                  @Path("frameNumber") int frameNumber);
    /* Truncated image to the [0;65535] range */
    @GET("instances/{id}/frames/{frameNumber}/image-uint16")
    Call<ResponseBody> getInstanceFrameImageUInt16(@Path("id") InstanceId instanceId,
                                                   @Path("frameNumber") int frameNumber);
    /* Truncated image to the [0;255] range */
    @GET("instances/{id}/frames/{frameNumber}/image-uint8")
    Call<ResponseBody> getInstanceFrameImageUInt8(@Path("id") InstanceId instanceId,
                                                  @Path("frameNumber") int frameNumber);
    /* a = eval(urlread('http://localhost:8042/instances/.../frames/0/matlab')) */
    @GET("instances/{id}/frames/{frameNumber}/matlab")
    Call<ResponseBody> getInstanceFrameMatlab(@Path("id") InstanceId instanceId,
                                              @Path("frameNumber") int frameNumber);
    /* Rescaled image (so that all the range [0;255] is used) */
    @GET("instances/{id}/frames/{frameNumber}/preview")
    Call<ResponseBody> getInstanceFramePreview(@Path("id") InstanceId instanceId,
                                               @Path("frameNumber") int frameNumber);
    /* Access to the raw content of one frame (bypass image decoding) */
    @GET("instances/{id}/frames/{frameNumber}/raw")
    Call<ResponseBody> getInstanceFrameRaw(@Path("id") InstanceId instanceId,
                                           @Path("frameNumber") int frameNumber);

    /* Get the meta information (header) of the DICOM file, "?simplify" argument to simplify output */
    @GET("instances/{id}/header")
    Call<Map<String, Header>> getInstanceHeader(@Path("id") InstanceId instanceId);

    /* Truncated image to the [-32768;32767] range */
    @GET("instances/{id}/image-int16")
    Call<ResponseBody> getInstanceImageInt16(@Path("id") InstanceId instanceId);
    /* Truncated image to the [0;65535] range */
    @GET("instances/{id}/image-uint16")
    Call<ResponseBody> getInstanceImageUInt16(@Path("id") InstanceId instanceId);
    /* Truncated image to the [0;255] range */
    @GET("instances/{id}/image-uint8")
    Call<ResponseBody> getInstanceImageUInt8(@Path("id") InstanceId instanceId);
    /* a = eval(urlread('http://localhost:8042/instances/.../frames/0/matlab')) */
    @GET("instances/{id}/matlab")
    Call<ResponseBody> getInstanceMatlab(@Path("id") InstanceId instanceId);
    /* Rescaled image (so that all the range [0;255] is used) */
    @GET("instances/{id}/preview")
    Call<ResponseBody> getInstancePreview(@Path("id") InstanceId instanceId);
    /* Return the encapsulated PDF inside the DICOM file, if any */
    @GET("instances/{id}/pdf")
    Call<ResponseBody> getInstancePDF(@Path("id") InstanceId instanceId);
    /* */
    @GET("instances/{id}/module")
    Call<Map<String, Header>> getInstanceModule(@Path("id") InstanceId instanceId);
    /* Retrieve the parent patient of this instance */
    @GET("instances/{id}/patient")
    Call<Patient> getInstancePatient(@Path("id") InstanceId instanceId);
    /* Retrieve the parent series of this instance */
    @GET("instances/{id}/series")
    Call<Series> getInstanceSeries(@Path("id") InstanceId instanceId);
    /* */
    @GET("instances/{id}/simplified-tags")
    Call<Map<String, String>> getInstanceSimplifiedTags(@Path("id") InstanceId instanceId);
    /* */
    @GET("instances/{id}/statistics")
    Call<Statistics> getInstanceStatistics(@Path("id") InstanceId instanceId);
    /* Retrieve the parent study of this instance */
    @GET("instances/{id}/study")
    Call<Study> getInstanceStudy(@Path("id") InstanceId instanceId);
    /* "?simplify" argument to simplify output (same as "simplified-tags") */
    @GET("instances/{id}/tags")
    Call<Map<String, Header>> getInstanceTags(@Path("id") InstanceId instanceId);

    /* QUERIES */
    @GET("queries")
    Call<List<QueryId>> getQueries();
    
    @GET("queries/{id}")
    Call<List<String>> getQuery(@Path("id") QueryId queryId);
    
    @GET("/queries/{id}/answers")
    Call<List<Integer>> getQueryAnswers(@Path("id") QueryId queryId);
    
    @GET("/queries/{id}/answers/{index}/content")
    Call<Map<DcmTag, DcmAttribute>> getQueryAnswer(@Path("id") QueryId queryId, @Path("index") int index);
    
    @GET("/queries/{id}/answers/{index}/content?simplify")
    Call<Map<String, String>> getQuerySimplifiedAnswer(@Path("id") QueryId queryId, @Path("index") int index);
    
    @Headers("application/x-www-form-urlencoded; charset=UTF-8")
    @POST("/queries/{id}/answers/{index}/retrieve")
    Call<Object> retrieveAnswer(@Path("id") QueryId queryId, @Path("index") int index, @Body RequestBody aet);
    
    
    /* MODALITIES */
    @GET("modalities")
    Call<List<String>> getModalities();

    //@Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8")
    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("modalities/{dicom}/query")
    Call<hr.fer.zari.models.qr.Query> queryModality(@Path("dicom") String modality, @Body RequestBody cfind);
    
    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("modalities/{dicom}/move")
    Call<Object> moveModality(@Path("dicom") String modality, @Body RequestBody cmove);
    
    
    /* not implemented */
    /* Protection against recycling: "0" means unprotected, "1" protected */
//    @GET("patients/{id}/protected")
//    Call<Integer> getPatientProtected(@Path("id") PatientId patientId);
}
