package service.core;

public class PlayerReportFactory {
  public static PlayerReport playerReportFactory(String name, PlayerInfo playerInfo, PlayerData playerData) {
    PlayerReport playerReport = new PlayerReport();
    playerReport.setName(name);
    playerReport.setPlayerInfo(playerInfo);
    playerReport.setPlayerData(playerData);
    return playerReport;
  }
}
