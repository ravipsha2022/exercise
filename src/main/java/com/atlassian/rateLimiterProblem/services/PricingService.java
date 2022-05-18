package com.atlassian.rateLimiterProblem.services;

import java.util.concurrent.atomic.AtomicInteger;

public class PricingService {


public AtomicInteger getCredits(String api_dev_key){

    return new AtomicInteger(50);
    }
}
