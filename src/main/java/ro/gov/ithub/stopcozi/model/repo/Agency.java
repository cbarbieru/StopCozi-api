package ro.gov.ithub.stopcozi.model.repo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "agencies")
public class Agency extends Identity {

  private String id;
  private String name;
  private String description;
  private String address;
  //private Location location;
  @OneToMany()
  private List<Category> categories;
  private List<Service> services;
  private List<Desk> desks;

}

