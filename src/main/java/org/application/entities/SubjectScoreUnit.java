package org.application.entities;


public class SubjectScoreUnit {
  private Subject subject;
  private int score;


  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }


  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public String toString() {
    return "SubjectScoreUnit{subject: " + subject + ", score: " + score + "}";
  }

  public SubjectScoreUnit(Subject subject, int score) {
    this.subject = subject;
    this.score = score;
  }

  public SubjectScoreUnit() {
  }
}
