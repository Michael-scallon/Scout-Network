package service.core;

import java.io.Serializable;

public class PlayerInfo implements Serializable {
  int age;
  String position;
  double goalsPer;
  double assistsPer;
  double concessionsPer;

  public void setAge(int age) {
    this.age = age;
  }

  public int getAge() {
    return this.age;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getPosition(){
    return this.position;
  }

  public void setGoalsPer(double goalsPer) {
    this.goalsPer = goalsPer;
  }
  public double getGoalsPer() {
    return this.goalsPer;
  }
  public void setAssistsPer(double assistsPer) {
    this.assistsPer = assistsPer;
  }
  public double getAssistsPer() {
    return this.assistsPer;
  }
  public void setConcessionsPer(double concessionsPer) {
    this.concessionsPer = concessionsPer;
  }
  public double getConcessionsPer() {
    return this.concessionsPer;
  }
}
