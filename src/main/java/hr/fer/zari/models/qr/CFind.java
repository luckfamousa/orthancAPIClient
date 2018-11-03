/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.models.qr;

import org.json.simple.JSONObject;

/**
 *
 * @author felixnensa
 */
public class CFind {
  
  // level of this C-FIND
  private final InformationModel level;
  // wildcard for empty values
  private final String wildcard;
  
  // params attributes
  private final JSONObject params;
  
  public CFind(InformationModel level, String wildcard) {
    this.level = level;
    this.wildcard = wildcard;
    params = new JSONObject();
  }
  
  public CFind(InformationModel level) {
    this(level, "*");
  }
  
  public CFind add(String returnParameter) {
    params.put(returnParameter, wildcard);
    return this;
  }
  
  public CFind add(String searchParameter, String value) {
    params.put(searchParameter, value);
    return this;
  }
  
  @Override
  public String toString() {    
    // params.put("SpecificCharacterSet", "ISO_IR 192");    
    JSONObject jsnObj = new JSONObject();
    jsnObj.put("Level", level.toString());
    jsnObj.put("Query", params);    
    return jsnObj.toJSONString();
  }
  
}

