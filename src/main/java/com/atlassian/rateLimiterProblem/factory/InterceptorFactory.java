package com.atlassian.rateLimiterProblem.factory;

import com.atlassian.rateLimiterProblem.configs.ConfigService;
import com.atlassian.rateLimiterProblem.rateLimiters.FixedWindowRateLimiters;
import com.atlassian.rateLimiterProblem.rateLimiters.Interceptor;
import com.atlassian.rateLimiterProblem.rateLimiters.LeakyBucketRateLimiter;

public class InterceptorFactory {

    ConfigService configService = new ConfigService();


    boolean isFixedWindowRateLimiterEnabled = configService.getFixedWindowStatus();
    boolean isLeakyBucktRateLimniterEnabled = configService.getLeakyBuckeyWindowStatus();


   public Interceptor getOrCreateInterceptor(){
        Interceptor interceptor = new Interceptor();
        if(isFixedWindowRateLimiterEnabled){
            interceptor.setRateLimiterStrategy(new FixedWindowRateLimiters());
        }

        if(isLeakyBucktRateLimniterEnabled){
            interceptor.setRateLimiterStrategy(new LeakyBucketRateLimiter());
        }

        return  interceptor;
    }

}
