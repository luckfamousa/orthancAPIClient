/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.models;

/**
 *
 * @author felixnensa
 */
public class DcmAttribute {
  
  private String Name;
  private String Type;
  private String Value;

  public String getName() {
    return Name;
  }

  public String getType() {
    return Type;
  }

  public String getValue() {
    return Value;
  }

  @Override
  public String toString() {
    return "DcmAttribute{" + "Name=" + Name + ", Type=" + Type + ", Value=" + Value + '}';
  }
  
}
