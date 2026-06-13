package org.example.helpdesk.repository;

import org.example.helpdesk.entity.AppUser;
import org.example.helpdesk.entity.Ticket;
import org.example.helpdesk.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUser(AppUser user);

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByUserUsername(String username);
}