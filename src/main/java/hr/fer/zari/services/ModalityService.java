/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.services;

import hr.fer.zari.OrthancException;
import hr.fer.zari.OrthancService;
import hr.fer.zari.models.qr.CFind;
import hr.fer.zari.models.qr.CMove;
import hr.fer.zari.models.qr.Query;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 *
 * @author felixnensa
 */
public class ModalityService extends BaseService {
  
  public ModalityService(OrthancService service) {
    super(service);
  }
  
  public Query queryModality(String modality, CFind cfind) throws IOException, OrthancException {
    Call<Query> call = service.queryModality(modality, RequestBody.create(MediaType.parse("application/json"), cfind.toString()));
    return checkResponse(call);
  }
  
  public Query moveModality(String modality, CMove cmove) throws IOException, OrthancException {
    Call<Query> call = service.moveModality(modality, RequestBody.create(MediaType.parse("application/json"), cmove.toString()));
    return checkResponse(call);
  }
}
