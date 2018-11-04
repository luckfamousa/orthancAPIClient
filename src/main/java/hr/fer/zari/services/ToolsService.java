/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.services;

import hr.fer.zari.OrthancException;
import hr.fer.zari.OrthancService;
import hr.fer.zari.models.tools.Lookup;
import java.io.IOException;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 *
 * @author felixnensa
 */
public class ToolsService extends BaseService {
  
  public ToolsService(OrthancService service) {
    super(service);
  }
  
  public List<Lookup> toolsLookup(String id) throws IOException, OrthancException {
    Call<List<Lookup>> call = service.toolsLookup(RequestBody.create(MediaType.parse("text/plain"), id));
    return checkResponse(call);
  }
}
