package st003.ticketing.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import st003.ticketing.data.entities.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Optional<Ticket> findByNumber(String number);
}
