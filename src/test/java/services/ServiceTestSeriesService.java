package services;

import hr.fer.zari.OrthancException;
import hr.fer.zari.RestClient;
import hr.fer.zari.models.Header;
import hr.fer.zari.models.id.*;
import hr.fer.zari.models.Patient;
import hr.fer.zari.models.Series;
import hr.fer.zari.models.Statistics.SeriesStatistics;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by eugen on 14/05/2017.
 */
public class ServiceTestSeriesService extends BaseServiceTest {

    @org.junit.Test
    public void testSeriesModule() throws IOException {
        RestClient client = MockClientConstructor.getSeriesModule();
        try {
            Map<String, Header> module = client.getSeriesService().getSeriesModule(new SeriesId("a69a10d7-068c5263-8aab53fe-de7af5f2-373a74bd"));
            Header header = module.get("0008,0031");
            assertEquals(header.getType(), "String");
            assertEquals(header.getName(), "SeriesTime");
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testSeriesForPatient() throws IOException {
        RestClient client = MockClientConstructor.getSeriesForPatient();
        try {
            List<Series> series = client.getSeriesService().getSeriesForPatient(new PatientId("da39a3ee-5e6b4b0d-3255bfef-95601890-afd80709"));
            assertEquals(series.get(0).getStatus(), "Unknown");
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testSeries() throws IOException {
        RestClient client = MockClientConstructor.getSeriesSingle();
        try {
            Series series = client.getSeriesService().getSeries(new SeriesId("6b9e19d9-62094390-5f9ddb01-4a191ae7-9766b715"));
            assertEquals(new InstanceId(series.getInstances().get(0)), new InstanceId("38b2a146-1a11768b-4b140230-21f7de01-19873143"));
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testListOfSeries() throws IOException {
        RestClient client = MockClientConstructor.getSeriesIds();
        try {
            List<String> studiesIds = client.getSeriesService().getSeries();
            assertEquals(studiesIds.size(), 23);
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testSeriesPatient() throws IOException {
        RestClient client = MockClientConstructor.getPatient();
        try {
            Patient patient = client.getSeriesService().getSeriesPatient(new SeriesId("da39a3ee-5e6b4b0d-3255bfef-95601890-afd80709"));
            checkPatient(patient);
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testSeriesSharedTags() throws IOException {
        RestClient client = MockClientConstructor.getSeriesSharedTags();
        try {
            Map<String, Header> module = client.getSeriesService().getSeriesSharedTags(new SeriesId("a69a10d7-068c5263-8aab53fe-de7af5f2-373a74bd"));
            Header header = module.get("0008,0005");
            assertEquals(header.getType(), "String");
            assertEquals(header.getName(), "SpecificCharacterSet");
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }

    @org.junit.Test
    public void testPatientStatistics() throws IOException {
        RestClient client = MockClientConstructor.getSeriesStatistics();
        try {
            SeriesStatistics statistics = client.getSeriesService().getSeriesStatistics(new SeriesId("a69a10d7-068c5263-8aab53fe-de7af5f2-373a74bd"));
            assertEquals(statistics.getDiskSize(), "11081078");
            assertEquals(statistics.getDiskSizeMB(), 10);
            assertEquals(statistics.getCountInstances(), 227);
            assertEquals(statistics.getUncompressedSize(), "11081078");
            assertEquals(statistics.getUncompressedSizeMB(), 10);
        } catch (OrthancException e) {
            handleOrthancException(e);
        }
    }
}
