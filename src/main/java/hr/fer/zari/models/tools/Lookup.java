/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.models.tools;

import hr.fer.zari.models.id.OrthancId;

/**
 *
 * @author felixnensa
 */
public class Lookup {

  private OrthancId ID;
  private String Path;
  private String Type;
  
  public OrthancId getID() {
    return ID;
  }

  public String getPath() {
    return Path;
  }

  public String getType() {
    return Type;
  }

  @Override
  public String toString() {
    return "Lookup{" + "ID=" + ID + ", Path=" + Path + ", Type=" + Type + '}';
  }
  
}
