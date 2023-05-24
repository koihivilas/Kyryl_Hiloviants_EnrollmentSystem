package org.application.entities;


import java.util.List;

public class Specialization {
  private long specializationId;
  private int code;
  private String name;
  private String description;
  private int limit;
  private List<SubjectWeightUnit> subjectWeightUnits;


  public long getSpecializationId() {
    return specializationId;
  }

  public void setSpecializationId(long specializationId) {
    this.specializationId = specializationId;
  }


  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }


  public List<SubjectWeightUnit> getSubjectWeightUnits() {
    return subjectWeightUnits;
  }

  public void setSubjectWeightUnits(List<SubjectWeightUnit> subjectWeightUnits) {
    this.subjectWeightUnits = subjectWeightUnits;
  }

  @Override
  public String toString() {
    return "Specialization{specializationId: " + this.specializationId + ", code: '"
            + this.code + "', name: '" + this.name + "', description: '"
            + this.description + "', limit: " + this.limit + "}";
  }

  public Specialization(long specializationId, int code, String name, String description, int limit) {
    this.specializationId = specializationId;
    this.code = code;
    this.name = name;
    this.description = description;
    this.limit = limit;
  }

  public Specialization(int code, String name, String description, int limit) {
    this.code = code;
    this.name = name;
    this.description = description;
    this.limit = limit;
  }

  public Specialization() {
  }
}
