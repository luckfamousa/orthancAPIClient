/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.models;

import java.util.UUID;

/**
 *
 * @author felixnensa
 */
public class OrthancId {
  
  private transient UUID uuid;

  public OrthancId(String s) {
    uuid = UUID.fromString(s);
  }
  
  @Override
  public String toString() {
    return uuid==null ? null : uuid.toString().toUpperCase();
  }
  
}
