package service.core;

public class PlayerInfoFactory {
  public static PlayerInfo playerInfoFactory(int age, String position, double goalsPer, double assistsPer, double concessionsPer) {
    PlayerInfo playerInfo = new PlayerInfo();
    playerInfo.setAge(age);
    playerInfo.setPosition(position);
    playerInfo.setGoalsPer(goalsPer);
    playerInfo.setAssistsPer(assistsPer);
    playerInfo.setConcessionsPer(concessionsPer);
    return playerInfo;
  }
}
