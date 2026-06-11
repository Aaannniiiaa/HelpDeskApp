package org.example.helpdesk.controller;

import org.example.helpdesk.entity.Ticket;
import org.example.helpdesk.enums.TicketStatus;
import org.example.helpdesk.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public Ticket findById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @GetMapping("/{id}/response-time")
    public int getResponseTime(@PathVariable Long id) {
        return ticketService.getResponseTimeForTicket(id);
    }

    @PostMapping
    public Ticket create(@RequestBody Ticket ticket) {
        return ticketService.create(ticket);
    }

    @PutMapping("/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.update(id, ticket);
    }

    @PatchMapping("/{id}/status")
    public Ticket changeStatus(@PathVariable Long id, @RequestParam TicketStatus status) {
        return ticketService.changeStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ticketService.delete(id);
    }
}