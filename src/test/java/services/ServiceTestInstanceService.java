package services;

import hr.fer.zari.OrthancException;
import hr.fer.zari.RestClient;
import hr.fer.zari.models.Instance;
import hr.fer.zari.models.id.*;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by eugen on 14/05/2017.
 */
public class ServiceTestInstanceService extends BaseServiceTest {

    @org.junit.Test
    public void testListOfInstances() throws IOException {
        RestClient client = MockClientConstructor.getInstancesIds();
        try {
            List<String> studiesIds = client.getInstanceService().getInstances();
            assertEquals(studiesIds.size(), 2352);
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testInstance() throws IOException {
        RestClient client = MockClientConstructor.getInstance();
        try {
            Instance instance = client.getInstanceService().getInstance(new InstanceId("38b2a146-1a11768b-4b140230-21f7de01-19873143"));
            assertEquals(instance.getMainDicomTags().get("ImageIndex"), "39");
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testInstanceForPatient() throws IOException {
        RestClient client = MockClientConstructor.getInstancesForPatient();
        try {
            List<Instance> instances = client.getInstanceService().getInstancesForPatient(new PatientId("da39a3ee-5e6b4b0d-3255bfef-95601890-afd80709"));
            assertEquals(instances.get(0).getFileSize(), 526864);
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testInstanceContent() throws IOException {
        RestClient client = MockClientConstructor.getInstanceContent();
        try {
            List<String> content = client.getInstanceService().getInstanceContent(new InstanceId("38b2a146-1a11768b-4b140230-21f7de01-19873143"));
            assertEquals(content.size(), 82);
            assertEquals(content.get(1), "0008-0008");
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }
}
