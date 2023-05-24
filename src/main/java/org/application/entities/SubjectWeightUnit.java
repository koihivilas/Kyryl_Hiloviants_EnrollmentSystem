package org.application.entities;


public class SubjectWeightUnit {
  private Subject subject;
  private double subjectWeight;


  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }


  public double getSubjectWeight() {
    return subjectWeight;
  }

  public void setSubjectWeight(double subjectWeight) {
    this.subjectWeight = subjectWeight;
  }

  @Override
  public String toString() {
    return "SubjectWeightUnit{subject: " + this.subject + ", subjectWeight: " + this.subjectWeight + "}";
  }

  public SubjectWeightUnit(Subject subject, double subjectWeight) {
    this.subject = subject;
    this.subjectWeight = subjectWeight;
  }

  public SubjectWeightUnit() {
  }
}
