package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import static play.data.validation.Constraints.*;
import javax.validation.*;
import com.avaje.ebean.*;

import java.io.File;

@Entity
public class Image extends Model {

  public static enum ImageType {
    GIF("image/gif"),
    PNG("image/png"),
    JPEG("image/jpeg");

    public String contentType;

    private ImageType(String contentType) {
      this.contentType = contentType;
    }

    public static ImageType get(String contentType) {
      for (ImageType type: values()) {
        if (type.contentType.equals(contentType)) {
          return type;
        }
      }
      return null;
    }
  }

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  public Long internalId;

  @MaxLength(140)
  public String caption;

  //memoization of pic
  @Transient
  public File pic;

  public String filePath;

  @OneToOne
  public User user;


  public static Finder<Long, Image> find =
    new Finder<Long, Image>(Long.class, Image.class);

  public Image() {}

  public File pic() {
    if (filePath != null) {
      if (pic == null || !pic.getPath().equals(filePath)) {
        pic = new File(filePath);
      }
    }
    return pic;
  }
}