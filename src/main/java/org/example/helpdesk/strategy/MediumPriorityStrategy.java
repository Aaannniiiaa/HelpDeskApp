package org.example.helpdesk.strategy;

import org.springframework.stereotype.Component;

@Component
public class MediumPriorityStrategy implements PriorityStrategy {

    @Override
    public int getResponseTimeInHours() {
        return 24;
    }

    @Override
    public String getDescription() {
        return "Medium priority ticket should be handled within 24 hours";
    }
}