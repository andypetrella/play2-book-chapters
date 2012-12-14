package models;

import org.joda.time.LocalTime;

import play.data.*;
import play.data.validation.Constraints.*;
import javax.validation.Valid;

import javax.persistence.*;

@Entity
public class Item extends play.db.ebean.Model implements ChatEntry {
  @Id
  @GeneratedValue
  public Long internalId;

  @OneToOne
  public User user;

  public LocalTime timestamp = LocalTime.now();

  @Required
  @MaxLength(140)
  public String message;

  public Item() {

  }

  public Item(User user, LocalTime timestamp, String message) {
      this.user = user;
      this.timestamp = timestamp;
      this.message = message;
  }

  public LocalTime timestamp() {
    return timestamp;
  }


}