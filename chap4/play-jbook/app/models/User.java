package models;

import java.util.List;
import play.libs.F.Option;

import play.data.*;
import play.data.validation.Constraints.*;
import javax.validation.Valid;

import javax.persistence.*;

@Entity
public class User extends play.db.ebean.Model {
  @Required
  public String name;

  @Required
  @Email
  @Id
  public String email;

  @Required
  @Min(0)
  @Max(150)
  public Integer age;

  @Required
  public Boolean female;

  @Valid
  @Required
  @ManyToOne(cascade=CascadeType.ALL)
  public Address address = new Address();

  @Valid
  public List<User> friends;

  @Valid
  public Option<User> spouse;

  public static Finder<String,User> find = new Finder<String,User>(
    String.class, User.class
  );
}