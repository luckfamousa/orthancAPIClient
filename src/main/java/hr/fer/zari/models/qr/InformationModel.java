/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.fer.zari.models.qr;

public class InformationModel {
  
  // available levels
  public final static InformationModel PatientLevel  = new InformationModel("Patient");
  public final static InformationModel StudyLevel    = new InformationModel("Study");
  public final static InformationModel SeriesLevel   = new InformationModel("Series");
  public final static InformationModel InstanceLevel = new InformationModel("Instance");
  
  final String identifier;
  InformationModel(String identifier) {
    this.identifier = identifier;
  }
  @Override
  public String toString() {
    return identifier;
  }
};