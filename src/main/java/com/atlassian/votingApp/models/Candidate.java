package com.atlassian.votingApp.models;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Candidate {
    String name;
    AtomicInteger score;

   public Candidate(String name){
        this.name = name;
        this.score = new AtomicInteger(0);
    }

}
