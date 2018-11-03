package hr.fer.zari.models.qr;

import hr.fer.zari.models.OrthancId;

public class Query {
  
  private OrthancId ID;
  private String Path;

  public OrthancId getID() {
    return ID;
  }

  public String getPath() {
    return Path;
  }

  @Override
  public String toString() {
    return "Query{" + "ID=" + ID + ", Path=" + Path + '}';
  }
}
