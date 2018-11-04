package hr.fer.zari.models.qr;

import hr.fer.zari.models.id.QueryId;

public class Query {
  
  private QueryId ID;
  private String Path;

  public QueryId getID() {
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
