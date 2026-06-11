package org.example.helpdesk.strategy;

import org.example.helpdesk.enums.TicketPriority;
import org.springframework.stereotype.Component;

@Component
public class PriorityStrategyFactory {

    private final LowPriorityStrategy lowPriorityStrategy;
    private final MediumPriorityStrategy mediumPriorityStrategy;
    private final HighPriorityStrategy highPriorityStrategy;

    public PriorityStrategyFactory(LowPriorityStrategy lowPriorityStrategy,
                                   MediumPriorityStrategy mediumPriorityStrategy,
                                   HighPriorityStrategy highPriorityStrategy) {
        this.lowPriorityStrategy = lowPriorityStrategy;
        this.mediumPriorityStrategy = mediumPriorityStrategy;
        this.highPriorityStrategy = highPriorityStrategy;
    }

    public PriorityStrategy getStrategy(TicketPriority priority) {
        if (priority == TicketPriority.LOW) {
            return lowPriorityStrategy;
        }

        if (priority == TicketPriority.MEDIUM) {
            return mediumPriorityStrategy;
        }

        if (priority == TicketPriority.HIGH) {
            return highPriorityStrategy;
        }

        throw new RuntimeException("Unknown ticket priority");
    }
}