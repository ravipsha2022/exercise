package com.atlassian.votingApp.repository;

import com.atlassian.votingApp.models.Candidate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SomeRepository {

   static Map<String , Candidate> scores ;

    static {
        scores = new ConcurrentHashMap<>();
    }

    public static Map<String , Candidate> retrieve(){
       return scores;
    }
}
