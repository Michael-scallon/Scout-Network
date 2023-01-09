package service.fieldscout;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Main {
  private static RestTemplate restTemplate = new RestTemplate();

  private static Player getPlayer(String name) {
      ResponseEntity<Player> response = Main.restTemplate.getForEntity(
              "http://asia-oceania-scout:8084/player/find/" + name,
              Player.class
      );
      return response.getBody();
  }

  private static void updatePlayer(Player player) {
    HttpEntity<Player> playerUpdate = new HttpEntity<>(player);
    Main.restTemplate.exchange(
            "http://asia-oceania-scout:8084/player/update/" + player.getName(),
            HttpMethod.PUT,
            playerUpdate,
            Player.class
    );
  }

  public static void main(String[] args) throws InterruptedException {
    Player player = getPlayer("Tahith_Chong");

    int iterations = 5;
    for (int i=0; i < iterations; i++) {
      player.setGoals(player.getGoals() + 1);
      updatePlayer(player);
      Thread.sleep(10 * 1000);
    }
  }
}
