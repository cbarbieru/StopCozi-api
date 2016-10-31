package ro.gov.ithub.stopcozi.model.repo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "appointments")
public class Appointment extends Identity {

  private Date start;

  private String name;

  private String phone;

  @ManyToOne
  @JoinColumn(name = "deskId")
  private Desk desk;

}

