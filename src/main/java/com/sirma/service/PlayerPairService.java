package com.sirma.service;

import com.sirma.model.Match;
import com.sirma.model.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerPairService {

    private List<Match> matches;
    private List<Record> records;

    // "p1-p2" : общо минути
    private Map<String, Integer> pairTotalTimes = new HashMap<>();

    //"p1-p2" : [matchId, minutes]
    private Map<String, List<int[]>> pairMatchDetails = new HashMap<>();

    public PlayerPairService(List<Match> matches, List<Record> records) {
        this.matches = matches;
        this.records = records;
    }

    public void calculate() {
        Map<Integer, List<Record>> recordsByMatch = groupRecordsByMatch();

        for (Map.Entry<Integer, List<Record>> entry : recordsByMatch.entrySet()) {
            int matchId = entry.getKey();
            List<Record> matchRecord = entry.getValue();

            for (int i = 0; i < matchRecord.size(); i++) {
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

    private int calculateOverlap(Record r1, Record r2) {
        int overlapStart = Math.max(r1.getFromMinutes(), r2.getFromMinutes());
        int overlapEnd = Math.min(r1.getToMinutes(), r2.getToMinutes());
        return Math.max(0, overlapEnd - overlapStart);
    }

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
                sb.append(ids[0]).append(", ").append(ids[1]).append(", ").append(maxTime).append("\n");

                for (int[] detail: pairMatchDetails.get(key)) {
                    sb.append(detail[0]).append(", ").append(detail[1]).append("\n");
                }

                result.add(sb.toString());
            }
        }
        return result;
    }
}
