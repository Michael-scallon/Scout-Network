package com.scout.scout.service;
import com.scout.scout.data.models.Player;
import com.scout.scout.data.payloads.request.PlayerRequest;
import com.scout.scout.data.payloads.response.MessageResponse;
import com.scout.scout.data.repository.PlayerRepository;
import com.scout.scout.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public MessageResponse createPlayer(PlayerRequest playerRequest) {
        Player newplayer = new Player();
        newplayer.setName(playerRequest.getName());
        newplayer.setAge(playerRequest.getAge());
        newplayer.setPosition(playerRequest.getPosition());
        newplayer.setRegion(playerRequest.getRegion());
        newplayer.setMinutes(playerRequest.getMinutes());
        newplayer.setNationality(playerRequest.getNationality());
        newplayer.setAppearances(playerRequest.getAppearances());
        newplayer.setGoals(playerRequest.getGoals());
        newplayer.setAssists(playerRequest.getAssists());
        newplayer.setConceded(playerRequest.getConceded());
        newplayer.setAssist_per(playerRequest.getAssist_per());
        newplayer.setGoals_per(playerRequest.getGoals_per());
        newplayer.setConceded_per(playerRequest.getConceded_per());
        playerRepository.save(newplayer);
        return new MessageResponse("New Player created successfully");

    }

    @Override
    public Optional<Player> updatePlayer(String playerName, PlayerRequest playerRequest)  throws ResourceNotFoundException{
        Optional<Player> player = playerRepository.findById(playerName);
        if (!player.isPresent()){
            throw new ResourceNotFoundException("Player", "Name", playerName);
        }
        else
            player.get().setName(playerRequest.getName());
        player.get().setAge(playerRequest.getAge());
        player.get().setPosition(playerRequest.getPosition());
        player.get().setRegion(playerRequest.getRegion());
        player.get().setMinutes(playerRequest.getMinutes());
        player.get().setNationality(playerRequest.getNationality());
        player.get().setAppearances(playerRequest.getAppearances());
        player.get().setGoals(playerRequest.getGoals());
        player.get().setAssists(playerRequest.getAssists());
        player.get().setConceded(playerRequest.getConceded());
        player.get().setAssist_per(playerRequest.getAssist_per());
        player.get().setGoals_per(playerRequest.getGoals_per());
        player.get().setConceded_per(playerRequest.getConceded_per());
        playerRepository.save(player.get());
        return player;
    }

    @Override
    public Player getASinglePlayer(String playerName) throws ResourceNotFoundException{
        return playerRepository.findById(playerName).orElseThrow(() -> new ResourceNotFoundException("Player", "Name", playerName));
    }

    @Override
    public List<Player> getAllPlayer() {
        return playerRepository.findAll();
    }
    @Override
   public void deletePlayer(String playerName) throws ResourceNotFoundException {
      if (playerRepository.getById(playerName).getName().equals(playerName)){playerRepository.deleteById(playerName);
        }
       else throw new ResourceNotFoundException("Player", "Name", playerName);
   }
}
