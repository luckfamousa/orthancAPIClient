/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author felixnensa
 */
public class DcmTag {
  
  private static final transient Pattern pattern = Pattern.compile("^([0-9A-Fa-f]+),([0-9A-Fa-f]+)$");
  private final transient String group;
  private final transient String element;

  public DcmTag(String s) {    
    Matcher matcher = pattern.matcher(s);        
    // do not check the result of find() but let it run into an Exception instead
    matcher.find();
    group = matcher.group(1);
    element = matcher.group(2);
  }

  public String getGroup() {
    return group;
  }

  public String getElement() {
    return element;
  }

  @Override
  public String toString() {
    return group + "," + element;
  }
}
