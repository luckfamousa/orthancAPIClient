package hr.fer.zari.services;

import hr.fer.zari.OrthancException;
import hr.fer.zari.OrthancService;
import hr.fer.zari.models.Instance;
import hr.fer.zari.models.id.*;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

/**
 * Created by eugen on 13/05/2017.
 */
public class InstanceService extends BaseService {

    public InstanceService(OrthancService service) {
        super(service);
    }

    public List<Instance> getInstacesForSeries(OrthancId seriesId) throws IOException, OrthancException {
        Call<List<Instance>> call = service.getInstancesForSeries(seriesId);
        return checkResponse(call);
    }

    public List<String> getInstances() throws IOException, OrthancException {
        Call<List<String>> call = service.getInstances();
        return checkResponse(call);
    }

    public Instance getInstance(InstanceId instanceId) throws IOException, OrthancException {
        Call<Instance> call = service.getInstance(instanceId);
        return checkResponse(call);
    }

    public void downloadInstanceDicom(InstanceId instanceId, String filePath) throws IOException, OrthancException {
        Call<ResponseBody> call = service.getInstanceDicomData(instanceId);
        ResponseBody body = checkResponse(call);
        writeResponseBodyToDisk(body, filePath);
    }

    public List<Instance> getInstancesForPatient(PatientId patientId) throws IOException, OrthancException {
        Call<List<Instance>> call = service.getPatientInstances(patientId);
        return checkResponse(call);
    }

    public List<String> getInstanceContent(InstanceId instanceId) throws IOException, OrthancException {
        Call<List<String>> call = service.getInstanceContent(instanceId);
        return checkResponse(call);
    }
}
