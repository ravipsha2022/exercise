package com.atlassian.rateLimiterProblem.rateLimiters;

import com.atlassian.rateLimiterProblem.configs.ConfigService;
import com.atlassian.rateLimiterProblem.interfaces.RateLimiter;
import com.atlassian.rateLimiterProblem.models.Customer;
import com.atlassian.rateLimiterProblem.repository.SomeRepository;

import java.time.Duration;

public class FixedWindowRateLimiters  implements RateLimiter {

    SomeRepository cache = new SomeRepository();
    ConfigService configService = new ConfigService();

    Duration windowSize = configService.getWindowSize();
    int maxRequestAllowed = configService.getMaxRequestCount();
    int maxAllowedCreditsPerWindow = configService.getMaxAllowedCreditsPerWindow();

    @Override
    public void preHandle(String api_dev_key) {
        if(api_dev_key.isEmpty()){
            // throw some exception
        }
        int currentWindow = (int) (System.currentTimeMillis()/windowSize.toMillis());
        Customer customer = cache.getOrCreateCustomer(api_dev_key, currentWindow);

        if(customer.getCurrentWindow() > currentWindow){
            //reset
        }


        if(customer.getRequestCount().get() > maxRequestAllowed){

            // throw exception
        }


        customer.getQuota().tryAcquire();
        //allow

        //reset

    }
}
