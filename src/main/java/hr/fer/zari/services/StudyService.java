package hr.fer.zari.services;

import hr.fer.zari.OrthancException;
import hr.fer.zari.OrthancService;
import hr.fer.zari.models.id.*;
import hr.fer.zari.models.Study;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eugen on 13/05/2017.
 */
public class StudyService extends BaseService {

    public StudyService(OrthancService service) {
        super(service);
    }

    public List<Study> getStudiesForPatient(PatientId patientId) throws IOException, OrthancException {
        Call<List<Study>> call = service.getStudiesForPatient(patientId);
        return checkResponse(call);
    }

    public List<String> getStudiesIds() throws IOException, OrthancException {
        Call<List<String>> call = service.getStudies();
        return checkResponse(call);
    }

    public Study getStudy(StudyId studyId) throws IOException, OrthancException {
        Call<Study> call = service.getStudy(studyId);
        return checkResponse(call);
    }

    public List<Study> getStudies() throws IOException, OrthancException {
        List<String> studiesIds = getStudiesIds();
        List<Study> studies = new ArrayList<>();
        for (String id : studiesIds) {
            studies.add(getStudy(new StudyId(id)));
        }
        return studies;
    }

    public void downloadStudyArchive(StudyId studyId, String filePath) throws IOException, OrthancException {
        Call<ResponseBody> call = service.getStudyZipData(studyId);
        ResponseBody response = checkResponse(call);
        writeResponseBodyToDisk(response, filePath);
    }
}
