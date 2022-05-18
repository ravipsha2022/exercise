package com.atlassian.votingApp.services;

import com.atlassian.votingApp.models.Candidate;
import com.atlassian.votingApp.repository.SomeRepository;
import com.atlassian.votingApp.utils.ResolveTie;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SomeService {

    Object lock1 = new Object();

    Object lock2 = new Object();

   public  void processRequest(List<String> votes){
       Map<String , Candidate> scores = SomeRepository.retrieve();

           for (String vote : votes) {
               synchronized (lock1) {
                   if (!scores.containsKey(vote)) {
                       scores.put(vote, new Candidate(vote));
                   }
               }

               synchronized (lock2) {
                   scores.get(vote).getScore().getAndAdd(1);
               }
           }


        return;
    }


   public Map<String ,Candidate>  publishScore(){
       return SomeRepository.retrieve();
    }


    public Candidate  publishWinner(){

        Map<String ,Candidate> scores = SomeRepository.retrieve();
        List<Candidate>sortedOutput ;

        /** sort the map*/
        sortedOutput=  scores.entrySet().stream().map(Map.Entry::getValue).sorted(
                        Comparator.comparingInt(a -> a.getScore().get()))
                .collect(Collectors.toList());


        Collections.reverse(sortedOutput);

        Candidate winner = sortedOutput.get(0);
        Candidate mayBeWinner = sortedOutput.get(1);

        if(winner.getScore().get() == mayBeWinner.getScore().get()){
            //resolve the tie
            winner = ResolveTie.resolveTieWhenScoresAreEqual(winner, mayBeWinner);

        }

       return winner;

    }


}
