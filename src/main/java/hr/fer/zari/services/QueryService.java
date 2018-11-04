/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.services;

import hr.fer.zari.OrthancException;
import hr.fer.zari.OrthancService;
import hr.fer.zari.models.DcmAttribute;
import hr.fer.zari.models.DcmTag;
import hr.fer.zari.models.id.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 *
 * @author felixnensa
 */
public class QueryService extends BaseService {
  
  public QueryService(OrthancService service) {
    super(service);
  }
  
  public List<OrthancId> getQueries() throws IOException, OrthancException {
    Call<List<OrthancId>> call = service.getQueries();
    return checkResponse(call);
  }
  
  public List<String> getQuery(QueryId id) throws IOException, OrthancException {
    Call<List<String>> call = service.getQuery(id);
    return checkResponse(call);
  }
  
  public List<Integer> getQueryAnswers(QueryId id) throws IOException, OrthancException {
    Call<List<Integer>> call = service.getQueryAnswers(id);
    return checkResponse(call);
  }
  
  public Map<DcmTag, DcmAttribute> getQueryAnswer(QueryId id, int index) throws IOException, OrthancException {
    Call<Map<DcmTag, DcmAttribute>> call = service.getQueryAnswer(id, index);
    return checkResponse(call);
  }
  
  public Map<String, String> getQuerySimplifiedAnswer(QueryId id, int index) throws IOException, OrthancException {
    Call<Map<String, String>> call = service.getQuerySimplifiedAnswer(id, index);
    return checkResponse(call);
  }
  
  public Object retrieveAnswer(QueryId id, int index, String targetAET) throws IOException, OrthancException {
    Call<Object> call = service.retrieveAnswer(id, index, RequestBody.create(MediaType.parse("text/plain"), targetAET));
    return checkResponse(call);
  }
}
