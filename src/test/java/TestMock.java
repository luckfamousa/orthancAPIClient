import hr.fer.zari.OrthancException;
import hr.fer.zari.RestClient;
import hr.fer.zari.models.Patient;
import hr.fer.zari.models.Study;
import hr.fer.zari.models.SystemInfo;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by eugen on 12/05/2017.
 */
public class TestMock {

    @org.junit.Test
    public void testListOfPatients() throws IOException {
        RestClient client = MockClientConstructor.getPatientsIds();
        try {
            List<String> patientsIds = client.getPatientsIds();
            assertEquals(patientsIds.size(), 7);
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testListOfStudies() throws IOException {
        RestClient client = MockClientConstructor.getStudiesIds();
        try {
            List<String> studiesIds = client.getStudiesIds();
            assertEquals(studiesIds.size(), 7);
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testListOfSeries() throws IOException {
        RestClient client = MockClientConstructor.getSeriesIds();
        try {
            List<String> studiesIds = client.getSeries();
            assertEquals(studiesIds.size(), 23);
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testListOfInstances() throws IOException {
        RestClient client = MockClientConstructor.getInstancesIds();
        try {
            List<String> studiesIds = client.getInstances();
            assertEquals(studiesIds.size(), 2352);
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testSystemInfo() throws IOException {
        RestClient client = MockClientConstructor.getSystemInfo();
        try {
            SystemInfo systemInfo = client.getSystemInfo();
            assertEquals(systemInfo.getName(), "Orthanc Demo");
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testPatient() throws IOException {
        RestClient client = MockClientConstructor.getPatient();
        try {
            Patient patient = client.getPatient("1");
            assertEquals(patient.getID(), "da39a3ee-5e6b4b0d-3255bfef-95601890-afd80709");
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testStudy() throws IOException {
        RestClient client = MockClientConstructor.getStudy();
        try {
            Study study = client.getStudy("1");
            assertEquals(study.getType(), "Study");
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    private void handleOrthancException(OrthancException e) {
        e.printStackTrace();
        fail(e.getMessage());
    }
}
