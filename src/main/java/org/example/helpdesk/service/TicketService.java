package org.example.helpdesk.service;

import org.example.helpdesk.strategy.PriorityStrategy;
import org.example.helpdesk.strategy.PriorityStrategyFactory;
import org.example.helpdesk.entity.Ticket;
import org.example.helpdesk.enums.TicketStatus;
import org.example.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final PriorityStrategyFactory priorityStrategyFactory;
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository, PriorityStrategyFactory priorityStrategyFactory) {
        this.ticketRepository = ticketRepository;
        this.priorityStrategyFactory = priorityStrategyFactory;
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public Ticket create(Ticket ticket) {
        ticket.setStatus(TicketStatus.NEW);
        ticket.setCreatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public Ticket update(Long id, Ticket updatedTicket) {
        Ticket existingTicket = findById(id);

        existingTicket.setTitle(updatedTicket.getTitle());
        existingTicket.setDescription(updatedTicket.getDescription());
        existingTicket.setPriority(updatedTicket.getPriority());
        existingTicket.setCategory(updatedTicket.getCategory());

        return ticketRepository.save(existingTicket);
    }

    public Ticket changeStatus(Long id, TicketStatus status) {
        Ticket existingTicket = findById(id);

        existingTicket.setStatus(status);

        return ticketRepository.save(existingTicket);
    }

    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    public int getResponseTimeForTicket(Long id) {
        Ticket ticket = findById(id);

        PriorityStrategy strategy = priorityStrategyFactory.getStrategy(ticket.getPriority());

        return strategy.getResponseTimeInHours();
    }
}