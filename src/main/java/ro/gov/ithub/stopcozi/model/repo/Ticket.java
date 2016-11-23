package ro.gov.ithub.stopcozi.model.repo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "tickets")
public class Ticket extends Identity {

    private String ticketNo;

    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime issueDateTime;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "serviceId")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "deskId")
    private Desk desk;
}
