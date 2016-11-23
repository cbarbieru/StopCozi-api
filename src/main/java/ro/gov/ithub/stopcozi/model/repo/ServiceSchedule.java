package ro.gov.ithub.stopcozi.model.repo;

import lombok.Data;
import ro.gov.ithub.stopcozi.api.broadcast.model.DaysOfWeek;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "services")
public class ServiceSchedule {

    @Enumerated(EnumType.STRING)
    private DaysOfWeek dayOfWeek;

    @Temporal(TemporalType.TIME)
    private OffsetDateTime start;

    @Temporal(TemporalType.TIME)
    private OffsetDateTime end;

}
