package com.atlassian.rateLimiterProblem.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Builder
public class Customer {
    String userId;
    AtomicInteger requestCount;
    int currentWindow;
    AtomicInteger credits;
    Semaphore quota;


    reset(){

    }

}
