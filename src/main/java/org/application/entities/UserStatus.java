package org.application.entities;


public class UserStatus {
  private long userStatusId;
  private String code;
  private String name;


  public long getUserStatusId() {
    return userStatusId;
  }

  public void setUserStatusId(long userStatusId) {
    this.userStatusId = userStatusId;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "UserStatus{userStatusId: " + this.userStatusId + ", code: '" + this.code + "', name: '" + this.name + "'}";
  }

  public UserStatus(long userStatusId, String code, String name) {
    this.userStatusId = userStatusId;
    this.code = code;
    this.name = name;
  }

  public UserStatus(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public UserStatus() {
  }
}
