package org.application.entities;


public class Role {
  private long roleId;
  private String code;
  private String name;


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
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
    return "Role{roleId: " + this.roleId + ", code: '" + this.code + "', name: '" + this.name + "'}";
  }

  public Role(long roleId, String code, String name) {
    this.roleId = roleId;
    this.code = code;
    this.name = name;
  }

  public Role(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Role() {
  }
}
