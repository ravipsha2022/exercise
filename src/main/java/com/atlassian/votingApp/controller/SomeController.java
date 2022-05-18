package com.atlassian.votingApp.controller;

import com.atlassian.votingApp.models.Candidate;
import com.atlassian.votingApp.services.SomeService;

import java.util.List;
import java.util.Map;

public class SomeController {
    SomeService someService = new SomeService();


    public void processBatchOfVotes(List<String> votes){
        someService.processRequest(votes);
    }

   public Map<String , Candidate> publishScore(){
       return someService.publishScore();
    }


   public Candidate publishWinner(){
       return someService.publishWinner();
    }

}



/*
* Election
*
* casteVote(String name)
*
* process(vote)
*
*
* input is list of strings : list of names
*  - one batch only
*
* If votes are equal:
*   - popularity
*   -
*
* multithreaded?
* - can be single for now
*
*
*
*
* figure out winner .
*
*
*
*
*
*
*
*
* */