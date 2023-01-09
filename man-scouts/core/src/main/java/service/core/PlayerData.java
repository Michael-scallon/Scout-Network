package service.core;

public class PlayerData {
  String region;
  Integer minutes;
  String nationality;
  Integer appearances;
  Integer goals;
  Integer assists;
  Integer concessions;

  public void setRegion(String region) {
    this.region = region;
  }

  public String getRegion() {
    return region;
  }

  public void setMinutes(Integer minutes) {
    this.minutes = minutes;
  }

  public Integer getMinutes() {
    return minutes;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public String getNationality() {
    return nationality;
  }

  public void setAppearances(Integer appearances) {
    this.appearances = appearances;
  }

  public Integer getAppearances() {
    return appearances;
  }

  public void setGoals(Integer goals) {
    this.goals = goals;
  }

  public Integer getGoals() {
    return this.goals;
  }

  public void setAssists(Integer assists) {
    this.assists = assists;
  }

  public Integer getAssists() {
    return this.assists;
  }

  public void setConcessions(Integer concessions) {
    this.concessions = concessions;
  }

  public Integer getConcessions() {
    return this.concessions;
  }

}
