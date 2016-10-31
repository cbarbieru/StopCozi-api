package ro.gov.ithub.stopcozi.model.repo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "desks")
public class Desk extends Identity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "officeId")
    private Office office;

}
