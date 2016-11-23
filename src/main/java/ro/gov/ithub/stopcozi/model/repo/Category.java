package ro.gov.ithub.stopcozi.model.repo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table(name = "categories")
public class Category {

  private String id = null;

  private String name = null;

}
