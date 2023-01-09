package service.core;

import java.io.Serializable;

public class PlayerReport implements Serializable {
  String name;
  PlayerInfo playerInfo;
  PlayerData playerData;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setPlayerInfo(PlayerInfo playerInfo) {
    this.playerInfo = playerInfo;
  }

  public PlayerInfo getPlayerInfo() {
    return playerInfo;
  }

  public void setPlayerData(PlayerData playerData) {
    this.playerData = playerData;
  }

  public PlayerData getPlayerData() {
    return playerData;
  }
}
