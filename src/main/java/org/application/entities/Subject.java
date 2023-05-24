package org.application.entities;


public class Subject {
  private long subjectId;
  private String name;
  private String description;
  private boolean closed;


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
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


  public boolean getClosed() {
    return closed;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  @Override
  public String toString() {
    return "Subject{subjectId: " + this.subjectId + ", name: '" + this.name + "', description: '"
            + this.description + "', closed: " + this.closed + "}";
  }

  public Subject(long subjectId, String name, String description, boolean closed) {
    this.subjectId = subjectId;
    this.name = name;
    this.description = description;
    this.closed = closed;
  }

  public Subject(String name, String description, boolean closed) {
    this.name = name;
    this.description = description;
    this.closed = closed;
  }

  public Subject() {
  }
}
