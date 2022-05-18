package com.atlassian.votingApp.utils;

import com.atlassian.votingApp.models.Candidate;

import java.util.Random;

public class ResolveTie {




   public static Candidate resolveTieWhenScoresAreEqual(Candidate first , Candidate second){

     int random =   new Random(2).nextInt();
     Candidate winner= second;

     if(random==1){
         winner = first;
     }

        return winner;
    }



    public static Candidate resolveTieWhenScoresAreEqual_v2(Candidate first , Candidate second){

        int random =   new Random(2).nextInt();
        Candidate winner= second;

        if(random==1){
            winner = first;
        }

        return winner;
    }



}
