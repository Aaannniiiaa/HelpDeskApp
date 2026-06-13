package org.example.helpdesk.strategy;

import org.springframework.stereotype.Component;

@Component
public class LowPriorityStrategy implements PriorityStrategy {

    @Override
    public int getResponseTimeInHours() {
        return 72;
    }

    @Override
    public String getDescription() {
        return "Low priority ticket should be handled within 72 hours";
    }
}