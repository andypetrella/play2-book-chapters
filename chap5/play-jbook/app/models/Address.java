package models;

import play.data.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Address extends play.db.ebean.Model {

    @Id
    @GeneratedValue
    public Long internalId;

    //CUSTOM :: Sample implementation of Hard Coded data
    public enum Country {
        ARDA("Arda", "AR"),
        BELGIUM("Belgium", "BE"),
        SMURFS_LAND("Smurfs Land", "SL");

        public String name;
        public String id;
        private Country(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public static Country getById(String id) {
            for (Country c: values()) {
                if (c.id.equals(id)) {
                    return c;
                }
            }
            throw new IllegalArgumentException("Country not found => Bad id {"+id+"}");
        }
    }



    @Required
    @Pattern(
        value="[A-Z]{1}\\w*, [0-9]+",
        message="A street starts with an upper case, and ends with a number after a comma"
    )
    public String fullStreet;

    @Required
    public String county;

    @Required
    @MaxLength(2)
    public String country;

    //CUSTOM :: validation rules
    public String validate() {
        try {
            Country.getById(country);
            return null;
        } catch (IllegalArgumentException e) {
            return "Bad country : " +country;
        }
    }

    public static Finder<Long,Address> find = new Finder<Long,Address>(
        Long.class, Address.class
    );
}