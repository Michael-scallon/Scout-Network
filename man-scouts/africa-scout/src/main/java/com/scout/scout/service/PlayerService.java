package com.scout.scout.service;
import com.scout.scout.data.models.Player;
import com.scout.scout.data.payloads.request.PlayerRequest;
import com.scout.scout.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface PlayerService {
    MessageResponse createPlayer(PlayerRequest playerRequest);

    Optional<Player> updatePlayer(String playerName, PlayerRequest playerRequest);
    void deletePlayer(String playerName);
    Player getASinglePlayer(String playerName);
    List<Player> getAllPlayer();

}
