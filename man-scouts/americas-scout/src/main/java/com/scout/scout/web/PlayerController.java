package com.scout.scout.web;

import com.scout.scout.data.models.Player;
import com.scout.scout.data.payloads.request.PlayerRequest;
import com.scout.scout.data.payloads.response.MessageResponse;
import com.scout.scout.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.core.PlayerReport;
import static service.core.PlayerDataFactory.playerDataFactory;
import static service.core.PlayerReportFactory.playerReportFactory;
import static service.core.PlayerInfoFactory.playerInfoFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")

public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/all")
    public ResponseEntity<List<PlayerReport>> getAllPlayer() {
        List<Player> playersList = playerService.getAllPlayer();
        List<PlayerReport> playerReports = new ArrayList();
        for (Player player: playersList) {
            playerReports.add(
                    playerReportFactory(
                        player.getName(),
                        playerInfoFactory(
                            player.getAge(),
                            player.getPosition(),
                            player.getGoals_per(),
                            player.getAssist_per(),
                            player.getConceded_per()
                        ),
                        playerDataFactory(
                            player.getRegion(),
                            player.getMinutes(),
                            player.getNationality(),
                            player.getAppearances(),
                            player.getGoals(),
                            player.getAssists(),
                            player.getConceded()
                        )
                    )
            );
        }
        return new ResponseEntity<>(playerReports, HttpStatus.OK);
    }
    @GetMapping("/find/{Name}")
    public ResponseEntity<Player> getPlayerId (@PathVariable("Name") String Name) {
        Player player = playerService.getASinglePlayer(Name);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addPlayer( @RequestBody PlayerRequest player) {
        MessageResponse newplayer= playerService.createPlayer(player);
        return new ResponseEntity<>(newplayer, HttpStatus.CREATED);
    }
//    @PutMapping("/update/{Name}")
//        public ResponseEntity<MessageResponse> updatePlayer( @PathVariable String Name, @RequestBody PlayerRequest player) {
//        MessageResponse updatePlayer = playerService.updatePlayer(Name, player);
//        return new ResponseEntity<>(updatePlayer, HttpStatus.OK);
//    }

    @PutMapping("/update/{Name}")
    public Optional<Player> updatePlayer(@PathVariable String Name, @RequestBody PlayerRequest player) {
        return playerService.updatePlayer(Name, player);

    }
    @DeleteMapping("/delete/{Name}")
    public ResponseEntity<?> deletePlayer(@PathVariable("Name") String Name) {
        playerService.deletePlayer(Name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
