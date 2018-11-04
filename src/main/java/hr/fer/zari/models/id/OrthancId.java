/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.models.id;

import java.util.Objects;

/**
 *
 * @author felixnensa
 */
public class OrthancId {
  
  private final transient String uuid;

  public OrthancId(String s) {
    uuid = s.toUpperCase();
  }
  
  @Override
  public String toString() {
    return uuid;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 83 * hash + Objects.hashCode(this.uuid);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    return Objects.equals(this.uuid, ((OrthancId)obj).uuid);
  }
  
}
