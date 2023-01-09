package service.core;

public class PlayerDataFactory {
  public static PlayerData playerDataFactory(String region, int minutes, String nationality, int appearances, int goals, int assists, int concessions) {
    PlayerData playerData = new PlayerData();
    playerData.setRegion(region);
    playerData.setMinutes(minutes);
    playerData.setNationality(nationality);
    playerData.setAppearances(appearances);
    playerData.setGoals(goals);
    playerData.setAssists(assists);
    playerData.setConcessions(concessions);
    return playerData;
  }
}
