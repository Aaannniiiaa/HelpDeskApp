package org.example.helpdesk.strategy;

import org.example.helpdesk.enums.TicketPriority;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriorityStrategyTest {

    @Test
    void shouldReturnLowPriorityResponseTime() {
        LowPriorityStrategy strategy = new LowPriorityStrategy();

        assertEquals(72, strategy.getResponseTimeInHours());
    }

    @Test
    void shouldReturnMediumPriorityResponseTime() {
        MediumPriorityStrategy strategy = new MediumPriorityStrategy();

        assertEquals(24, strategy.getResponseTimeInHours());
    }

    @Test
    void shouldReturnHighPriorityResponseTime() {
        HighPriorityStrategy strategy = new HighPriorityStrategy();

        assertEquals(4, strategy.getResponseTimeInHours());
    }

    @Test
    void shouldReturnCorrectStrategyFromFactory() {
        LowPriorityStrategy low = new LowPriorityStrategy();
        MediumPriorityStrategy medium = new MediumPriorityStrategy();
        HighPriorityStrategy high = new HighPriorityStrategy();

        PriorityStrategyFactory factory = new PriorityStrategyFactory(low, medium, high);

        assertEquals(72, factory.getStrategy(TicketPriority.LOW).getResponseTimeInHours());
        assertEquals(24, factory.getStrategy(TicketPriority.MEDIUM).getResponseTimeInHours());
        assertEquals(4, factory.getStrategy(TicketPriority.HIGH).getResponseTimeInHours());
    }

    @Test
    void shouldReturnPriorityDescriptions() {
        LowPriorityStrategy low = new LowPriorityStrategy();
        MediumPriorityStrategy medium = new MediumPriorityStrategy();
        HighPriorityStrategy high = new HighPriorityStrategy();

        assertEquals("Low priority ticket should be handled within 72 hours", low.getDescription());
        assertEquals("Medium priority ticket should be handled within 24 hours", medium.getDescription());
        assertEquals("High priority ticket should be handled within 4 hours", high.getDescription());
    }

    @Test
    void shouldThrowExceptionForUnknownPriority() {
        LowPriorityStrategy low = new LowPriorityStrategy();
        MediumPriorityStrategy medium = new MediumPriorityStrategy();
        HighPriorityStrategy high = new HighPriorityStrategy();

        PriorityStrategyFactory factory = new PriorityStrategyFactory(low, medium, high);

        assertThrows(RuntimeException.class, () -> factory.getStrategy(null));
    }
}