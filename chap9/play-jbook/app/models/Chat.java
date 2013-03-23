package models;

import org.joda.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import play.data.*;
import play.data.validation.Constraints.*;
import javax.validation.Valid;

import javax.persistence.*;

@Entity
public class Chat extends play.db.ebean.Model {

  @Id
  @GeneratedValue
  public Long internalId;

  @Required
  public String topic;

  @Required
  public LocalDate date;

  @Required
  public int occurrence;

  @Valid
  @OneToMany(cascade=CascadeType.ALL)
  @OrderBy("timestamp")
  @JoinColumn(name="CHAT_ID", referencedColumnName="internal_id")
  public List<Item> items = new ArrayList<Item>();

  @Valid
  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name="CHAT_ID", referencedColumnName="internal_id")
  public List<Image> images = new ArrayList<Image>();

  public Chat() {
  }

  public Chat(LocalDate date, int occurrence, List<Item> items, List<Image> images) {
      this.date       = date;
      this.occurrence = occurrence;
      this.items      = items;
      this.images     = images;
  }

  public static Finder<Long,Chat> find = new Finder<Long,Chat>(
      Long.class, Chat.class
  );

  public static int occurrencesFor(LocalDate date) {
    return find.where("date = :date").setParameter("date", date).findRowCount();
  }

}