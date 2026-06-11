package org.example.helpdesk.strategy;

import org.springframework.stereotype.Component;

@Component
public class HighPriorityStrategy implements PriorityStrategy {

    @Override
    public int getResponseTimeInHours() {
        return 4;
    }

    @Override
    public String getDescription() {
        return "High priority ticket should be handled within 4 hours";
    }
}