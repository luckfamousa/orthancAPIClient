/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.models.qr;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author felixnensa
 */
public class CMove {
  
  // level of this C-FIND
  private final InformationModel level;  
  // params attributes
  private final JSONArray resources = new JSONArray();
  
  public CMove(InformationModel level) { 
    this.level = level;
  }
  
  public CMove addStudy(String studyInstanceUID) {
    JSONObject params = new JSONObject();
    params.put("StudyInstanceUID", studyInstanceUID);
    resources.add(params);
    return this;
  }
  
  public CMove addSeries(String studyInstanceUID, String seriesInstanceUID) {
    JSONObject params = new JSONObject();
    params.put("StudyInstanceUID", studyInstanceUID);
    params.put("SeriesInstanceUID", seriesInstanceUID);
    resources.add(params);
    return this;
  }
  
  public CMove addInstance(String studyInstanceUID, String seriesInstanceUID, String sopInstanceUID) {
    JSONObject params = new JSONObject();
    params.put("StudyInstanceUID", studyInstanceUID);
    params.put("SeriesInstanceUID", seriesInstanceUID);
    params.put("SOPInstanceUID", sopInstanceUID);
    resources.add(params);
    return this;
  }
  
  /**
   * Format more or less specified in Tests
   * https://bitbucket.org/sjodogne/orthanc-tests/src/76747a374affdea27d0e8c2958eae7d0678ac652/Tests/Tests.py?at=default&fileviewer=file-view-default#Tests.py-3000
   * @return 
   */
  @Override
  public String toString() {    
    // query.put("SpecificCharacterSet", "ISO_IR 192");    
    JSONObject jsnObj = new JSONObject(); 
    jsnObj.put("Level", level.toString());    
    jsnObj.put("Resources", resources);    
    return jsnObj.toJSONString();
  }
  
}

