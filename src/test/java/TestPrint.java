import hr.fer.zari.models.id.*;
import hr.fer.zari.OrthancException;
import hr.fer.zari.RestClient;
import hr.fer.zari.models.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by eugen on 02/12/2016.
 */
public class TestPrint {

    private static final String API_BASE_URL = "http://demo.orthanc-server.com";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static final RestClient client = new RestClient(API_BASE_URL, USERNAME, PASSWORD, false);

    public static void main(String[] args) {
        PatientId patientId = new PatientId("da39a3ee-5e6b4b0d-3255bfef-95601890-afd80709");
        StudyId studyId = new StudyId("27f7126f-4f66fb14-03f4081b-f9341db2-53925988");
        SeriesId seriesId = new SeriesId("a69a10d7-068c5263-8aab53fe-de7af5f2-373a74bd");
        InstanceId instancesId = new InstanceId("38b2a146-1a11768b-4b140230-21f7de01-19873143");

        printSystemInfo();

        printListOfPatients();
        printListOfStudies();
        printListOfStudies(patientId);
        printListOfSeries(studyId);
        printListOfInstancesForSeries(seriesId);

//        downloadInstance(instancesId);
//        downloadPatientArchive(patientId);
    }

    private static void printSystemInfo() {
        try {
            SystemInfo info = client.getSystemService().getSystemInfo();
            System.out.println(info.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OrthancException e) {
            e.printStackTrace();
        }
    }

    private static void printListOfPatients() {
        try {
            List<Patient> patients = client.getPatientService().getPatients();
            System.out.println("NUMBER OF PATINETS: " + patients.size());
            for (Patient patient : patients) {
                System.out.println(patient.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OrthancException e) {
            e.printStackTrace();
        }
    }

    private static void printListOfStudies() {
        try {
            List<Study> studies = client.getStudyService().getStudies();
            System.out.println("NUMBER OF STUDIES: " + studies.size());
            for (Study study : studies) {
                System.out.println(study.toString());
            }
        } catch (IOException  e) {
            e.printStackTrace();
        } catch (OrthancException e) {
            e.printStackTrace();
        }
    }

    private static void downloadInstance(InstanceId instancesId) {
        try {
            client.getInstanceService().downloadInstanceDicom(instancesId, "");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OrthancException e) {
            e.printStackTrace();
        }
    }

    private static void downloadPatientArchive(PatientId patientId) {
        try {
            client.getPatientService().downloadPatientArchive(patientId, "" + patientId + ".zip");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OrthancException e) {
            e.printStackTrace();
        }
    }

    private static void printListOfStudies(PatientId patientId) {
        try {
            List<Study> studies = client.getStudyService().getStudiesForPatient(patientId);
            for (Study study : studies) {
                System.out.println(study.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OrthancException e) {
            e.printStackTrace();
        }
    }
    
    private static void printListOfSeries(StudyId studyId) {
        try {
            List<Series> series = client.getSeriesService().getSeriesForStudy(studyId);
            for (Series serie : series) {
                System.out.println(serie.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OrthancException e) {
            e.printStackTrace();
        }
    }

    private static void printListOfInstancesForSeries(SeriesId seriesId) {
        try {
            List<Instance> instances = client.getInstanceService().getInstacesForSeries(seriesId);
            for (Instance instance : instances) {
                System.out.println(instance.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OrthancException e) {
            e.printStackTrace();
        }
    }
}
