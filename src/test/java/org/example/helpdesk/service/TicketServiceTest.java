package org.example.helpdesk.service;

import org.example.helpdesk.entity.Ticket;
import org.example.helpdesk.enums.TicketPriority;
import org.example.helpdesk.enums.TicketStatus;
import org.example.helpdesk.repository.TicketRepository;
import org.example.helpdesk.strategy.PriorityStrategy;
import org.example.helpdesk.strategy.PriorityStrategyFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private PriorityStrategyFactory priorityStrategyFactory;

    @Mock
    private PriorityStrategy priorityStrategy;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void shouldCreateTicketWithNewStatusAndCreatedAt() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Login problem");
        ticket.setDescription("User cannot log in");
        ticket.setPriority(TicketPriority.HIGH);

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.create(ticket);

        assertEquals(TicketStatus.NEW, result.getStatus());
        assertNotNull(result.getCreatedAt());
        verify(ticketRepository).save(ticket);
    }

    @Test
    void shouldChangeTicketStatus() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setStatus(TicketStatus.NEW);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.changeStatus(1L, TicketStatus.IN_PROGRESS);

        assertEquals(TicketStatus.IN_PROGRESS, result.getStatus());
        verify(ticketRepository).save(ticket);
    }

    @Test
    void shouldReturnResponseTimeForHighPriorityTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setPriority(TicketPriority.HIGH);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(priorityStrategyFactory.getStrategy(TicketPriority.HIGH)).thenReturn(priorityStrategy);
        when(priorityStrategy.getResponseTimeInHours()).thenReturn(4);

        int result = ticketService.getResponseTimeForTicket(1L);

        assertEquals(4, result);
    }
}