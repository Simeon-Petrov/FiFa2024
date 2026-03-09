package com.sirma.service;

import com.sirma.model.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerPairService {

    private List<Record> records;

    // key "p1-p2" / value allminutes
    private Map<String, Integer> pairTotalTimes = new HashMap<>();

    //                  value:List[matchid, minutes]
    private Map<String, List<int[]>> pairMatchDetails = new HashMap<>();

    public PlayerPairService(List<Record> records) {
        this.records = records;
    }

    public void calculate() {
        //{1: [Record, Record, 2: [Record, Record, ...]
        Map<Integer, List<Record>> recordsByMatch = groupRecordsByMatch();

        for (Map.Entry<Integer, List<Record>> entry : recordsByMatch.entrySet()) {
            int matchId = entry.getKey();
            List<Record> matchRecord = entry.getValue();

            for (int i = 0; i < matchRecord.size(); i++) { // no repeat
                for (int j = i + 1; j < matchRecord.size(); j++) {
                    Record r1 = matchRecord.get(i);
                    Record r2 = matchRecord.get(j);

                    int overlap = calculateOverlap(r1, r2);

                    if (overlap > 0) {
                        String key = Math.min(r1.getPlayerId(), r2.getPlayerId()) + "-" +
                                     Math.max(r1.getPlayerId(), r2.getPlayerId());

                        pairTotalTimes.put(key, pairTotalTimes.getOrDefault(key, 0) + overlap);

                        if (!pairMatchDetails.containsKey(key)) {
                            pairMatchDetails.put(key, new ArrayList<>());
                        }
                        pairMatchDetails.get(key).add(new int[]{matchId, overlap});
                    }
                }
            }
        }
    }

    //Groups all records by matchId
    //{1: [all players in match 1 ]}
    private Map<Integer, List<Record>> groupRecordsByMatch() {
        Map<Integer, List<Record>> result = new HashMap<>();
        for (Record record : records) {
            int matchId = record.getMatchId();
            if (!result.containsKey(matchId)) {
                result.put(matchId, new ArrayList<>());
            }
            result.get(matchId).add(record);
        }
        return result;
    }

    // overlap      = overlapEnd - overlapStart (ако > 0)
    // r1: 0  → 90
    // r2: 46 → 90
    // overlap = min(90,90) - max(0,46) = 90 - 46 = 44
    private int calculateOverlap(Record r1, Record r2) {
        int overlapStart = Math.max(r1.getFromMinutes(), r2.getFromMinutes());
        int overlapEnd = Math.min(r1.getToMinutes(), r2.getToMinutes());
        return Math.max(0, overlapEnd - overlapStart);
    }

    //max total time
    public int getMaxTime() {
        int max = 0;
        for (int time : pairTotalTimes.values()) {
            if (time > max) max = time;
        }
        return max;
    }

    public List<String> getTopPairs() {
        int maxTime = getMaxTime();
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : pairTotalTimes.entrySet()) {
            if (entry.getValue() == maxTime) {
                String key = entry.getKey();
                String[] ids = key.split("-");

                StringBuilder sb = new StringBuilder();
                //first row: id1, id2, allminutes
                sb.append(ids[0]).append(", ").append(ids[1]).append(", ").append(maxTime).append("\n");
                //next rows: matchId, minutes together
                for (int[] detail : pairMatchDetails.get(key)) {
                    sb.append(detail[0]).append(", ").append(detail[1]).append("\n");
                }

                result.add(sb.toString());
            }
        }
        return result;
    }

    // only one pair with max time
  //public String getTopPair() {
  //    int getMaxTime = 0;
  //    String result = "";
  //    for (Map.Entry<String, Integer> entry : pairTotalTimes.entrySet()) {
  //        if (entry.getValue() > getMaxTime) {
  //            getMaxTime = entry.getValue();
  //            String key = entry.getKey();
  //            String[] ids = key.split("-");

  //            // Първи ред: играч1, играч2, общо минути
  //            StringBuilder sb = new StringBuilder();
  //            sb.append(ids[0]).append(", ").append(ids[1])
  //                    .append(", ").append(getMaxTime).append("\n");

  //            for (int[] detail : pairMatchDetails.get(key)) {
  //                sb.append(detail[0]).append(", ").append(detail[1]).append("\n");
  //            }

  //            result = sb.toString();
  //        }
  //    }
  //    return result;
  //}
}
