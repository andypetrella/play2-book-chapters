package comparison;

public class IfElse {
  //NOTE: we don't use return within branches for
  // bes-practices reasons

  //176 chars
  public static String category(int age) {
    String result = null;
    if (age < 18) {
      result = "Young";
    } else {
      result = "Old";
    }
    return result;
  }

  //272 chars
  public static String finestCategory(int age) {
    String result = null;
    if (age < 5) {
      result = "Kid";
    } else if (age < 18) {
      result = "Young";
    } else {
      result = "Old";
    }
    return result;
  }

  //446 chars
  public static String qualified(int age, short kind) {
    String q = null;
    if (age < 1) {
      q = "Baby ";
    } else if (age < 3) {
      q = "Kid ";
    } else if (age < 7) {
      q = "Young ";
    } else {
      q = "";
    }
    String g = null;
    if (kind == 1) {
      g = "Girl";
    } else if (kind == 2) {
      g = "Boy";
    } else if (kind == 3) {
      g = "Dog";
    } else {
      g = "Cat";
    }
    return q + g;
  }

}