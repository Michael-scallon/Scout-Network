package broker.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;
import service.core.*;

import java.time.Instant;
import java.util.*;

@RestController
public class BrokerService {
  Jedis jedis = new Jedis(new HostAndPort("redis", 6379));

  @Autowired
  private EurekaClient eurekaClient;

  private ArrayList<PlayerReport> getPlayersWithPosition(ArrayList<PlayerReport> playerReports, String position) {
    ArrayList<PlayerReport> returnList = new ArrayList<PlayerReport>();
    for (PlayerReport playerReport : playerReports) {
      if (playerReport.getPlayerInfo().getPosition().equals(position)) {
        returnList.add(playerReport);
      }
    }
    return returnList;
  }

  private ArrayList<PlayerReport> getPlayersYoungerThan(ArrayList<PlayerReport> playerReports, int age) {
    ArrayList<PlayerReport> returnList = new ArrayList<PlayerReport>();
    for (PlayerReport playerReport : playerReports) {
      if (playerReport.getPlayerInfo().getAge() <= age) {
        returnList.add(playerReport);
      }
    }
    return returnList;
  }

  private ArrayList<PlayerReport> getPerGreaterThan(ArrayList<PlayerReport> playerReports, double perValue, String value) {
    ArrayList<PlayerReport> returnList = new ArrayList<PlayerReport>();
    for (PlayerReport playerReport : playerReports) {
      if (value.equals("goals")) {
        if (playerReport.getPlayerInfo().getGoalsPer() >= perValue) {
          returnList.add(playerReport);
        }
      }
      else if(value.equals("assists")) {
        if (playerReport.getPlayerInfo().getAssistsPer() >= perValue) {
          returnList.add(playerReport);
        }
      }
    }
    return returnList;
  }

  private ArrayList<PlayerReport> getPerLessThan(ArrayList<PlayerReport> playerReports, double perValue) {
    ArrayList<PlayerReport> returnList = new ArrayList<PlayerReport>();
    for (PlayerReport playerReport : playerReports) {
      if (playerReport.getPlayerInfo().getConcessionsPer() <= perValue){
        returnList.add(playerReport);
      }
    }
    return returnList;
  }

  private ArrayList<PlayerReport> getPlayerReports() {
    RestTemplate restTemplate = new RestTemplate();
    ArrayList<PlayerReport> playerReports = new ArrayList<>();
    for (Application application: eurekaClient.getApplications().getRegisteredApplications()) {
      try {
        String appName = application.getInstances().get(0).getAppName();
        if (appName.equals("\"SCOUT\"")) {
          for (int i = 0; i < application.getInstances().size(); i++) {
            InstanceInfo service = application.getInstances().get(i);
            String endpoint = "http://" + service.getHostName() + ":" + service.getPort() + "/player/all";
            ResponseEntity<ArrayList<PlayerReport>> response = restTemplate.exchange(endpoint, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<ArrayList<PlayerReport>>() {
            });
            ArrayList<PlayerReport> scoutReports = response.getBody();
            for (PlayerReport scoutReport : scoutReports) playerReports.add(scoutReport);
          }
        }

      } catch (RestClientException e) {
        e.printStackTrace();
      }
    }
    return playerReports;
  }

  private void saveToRedis(List<PlayerReport> reports) {
    for (PlayerReport report: reports) {
      Map<String, String> cacheMap = new HashMap<>();
      cacheMap.put("name", report.getName());
      cacheMap.put("age", Integer.toString(report.getPlayerInfo().getAge()));
      cacheMap.put("position", report.getPlayerInfo().getPosition());
      cacheMap.put("goalsPer", Double.toString(report.getPlayerInfo().getGoalsPer()));
      cacheMap.put("assistsPer", Double.toString(report.getPlayerInfo().getAssistsPer()));
      cacheMap.put("concessionsPer", Double.toString(report.getPlayerInfo().getConcessionsPer()));
      cacheMap.put("region", report.getPlayerData().getRegion());
      cacheMap.put("minutes", Integer.toString(report.getPlayerData().getMinutes()));
      cacheMap.put("nationality", report.getPlayerData().getNationality());
      cacheMap.put("appearances", Integer.toString(report.getPlayerData().getAppearances()));
      cacheMap.put("goals", Integer.toString(report.getPlayerData().getGoals()));
      cacheMap.put("assists", Integer.toString(report.getPlayerData().getAssists()));
      cacheMap.put("concessions", Integer.toString(report.getPlayerData().getConcessions()));
      jedis.hset("playerReport:" + report.getName().toLowerCase(), cacheMap);
    }
  }

  private ArrayList<PlayerReport> retrieveFromRedis() {
    System.out.println("Broker retrieving from redis.");
    ArrayList<PlayerReport> reportsList = new ArrayList<>();

    ScanParams params = new ScanParams().count(1000).match("playerReport:*");
    ScanResult<String> scanResult = jedis.scan("0", params);
    List<String> scanKeys = scanResult.getResult();

    for (String scanKey: scanKeys) {
      Map<String, String> values = jedis.hgetAll(scanKey);
      PlayerReport report = convertToPlayerReport(values);
      reportsList.add(report);
    }
    return reportsList;
  }

  private PlayerReport convertToPlayerReport(Map<String, String> cacheValues) {
    PlayerReport report = new PlayerReport();
    report.setName(cacheValues.get("name"));

    PlayerInfo info = new PlayerInfo();
    info.setAge(Integer.parseInt(cacheValues.get("age")));
    info.setPosition(cacheValues.get("position"));
    info.setGoalsPer(Double.parseDouble(cacheValues.get("goalsPer")));
    info.setAssistsPer(Double.parseDouble(cacheValues.get("assistsPer")));
    info.setConcessionsPer(Double.parseDouble(cacheValues.get("concessionsPer")));
    report.setPlayerInfo(info);

    PlayerData data = new PlayerData();
    data.setRegion(cacheValues.get("region"));
    data.setMinutes(Integer.parseInt(cacheValues.get("minutes")));
    data.setNationality(cacheValues.get("nationality"));
    data.setAppearances(Integer.parseInt(cacheValues.get("appearances")));
    data.setGoals(Integer.parseInt(cacheValues.get("goals")));
    data.setAssists(Integer.parseInt(cacheValues.get("assists")));
    data.setConcessions(Integer.parseInt(cacheValues.get("concessions")));
    report.setPlayerData(data);

    return report;
  }

  private Boolean shouldUpdate(long currentTime) {
    if (jedis.scan("0").getResult().size() == 0) return true;
    int freshnessSecs = 60;
    return currentTime - Long.parseLong(jedis.get("lastUpdated")) > freshnessSecs;
  }

  @CrossOrigin
  @RequestMapping(value="/players", method= RequestMethod.GET)
  @ResponseBody
  public ArrayList<PlayerReport> getPlayers(@RequestParam(required = false) String position, Integer age, Double goalsPer, Double assistsPer, Double concessionsPer) {
    ArrayList<PlayerReport> returnList = new ArrayList<>();

    if (shouldUpdate(Instant.now().getEpochSecond())) {
      System.out.println("Broker calling the scouts.");
      returnList = getPlayerReports();
      saveToRedis(returnList);
      jedis.set("lastUpdated", Long.toString(Instant.now().getEpochSecond()));
    }
    else returnList = retrieveFromRedis();

    if (position != null) {
      returnList = getPlayersWithPosition(returnList, position);
    }
    if (age != null) {
      returnList = getPlayersYoungerThan(returnList, age);
    }
    if (goalsPer != null) {
      returnList = getPerGreaterThan(returnList, goalsPer, "goals");
    }
    if (assistsPer != null) {
      returnList = getPerGreaterThan(returnList, assistsPer, "assists");
    }
    if (concessionsPer != null) {
      returnList = getPerLessThan(returnList, concessionsPer);
    }
    return returnList;
  }
}
