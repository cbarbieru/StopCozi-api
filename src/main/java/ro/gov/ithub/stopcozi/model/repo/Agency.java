package ro.gov.ithub.stopcozi.model.repo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "agencies")
public class Agency extends Identity {

  private String name;

  private String countyCode;

}

