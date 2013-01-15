package models
import play.api.Play.current
import play.api.db.DB
import anorm._

case class User(
  name:String,
  email:Pk[String] = NotAssigned,
  age:Int,
  female:Boolean,
  address:Address,
  spouse:Option[User]=None,
  friends:Seq[User]=Seq()
) {
  def save =
    DB.withTransaction { implicit conn =>
      email.flatMap { e => User.load(e) } match {
        case None => create
        case _ => update
      }
    }

  def create = DB.withConnection { implicit conn =>
    SQL(
      """
        INSERT INTO user(
          name,
          email,
          age,
          female,
          address_internal_id
        )
        VALUES (
          {name},
          {email},
          {age},
          {female},
          {addressId}
        )
      """
    ).on(
      'name -> name,
      'email -> email.get,
      'age -> age,
      'female -> female,
      'addressId -> address.internalId.get
    ).executeUpdate()
    this
  }

  def update = DB.withConnection { implicit conn =>
    SQL(
      """
        UPDATE user
        SET
          name = {name},
          age = {age},
          female = {female},
          address_internal_id = {addressId}
        WHERE
          email = {email}
      """
    ).on(
      'name -> name,
      'email -> email.get,
      'age -> age,
      'female -> female,
      'addressId -> address.internalId.get    //still assuming that address has been stored
    ).executeUpdate()
    this
  }
}

object User {
  import anorm.SqlParser._

  /**
   * Parse a User from a ResultSet
   */
  val withAddress = {
    get[Pk[String]]("user.email") ~
    get[String]("user.name") ~
    get[Int]("user.age") ~
    get[Boolean]("user.female") ~
    Address.simple map {
      case email~name~age~female~address =>
        User(name, email, age, female, address)
    }
  }

  def load(email:String):Option[User] =
    DB.withConnection { implicit conn =>
      SQL("""
        SELECT
          *
        FROM
          user,
          address
        WHERE
          user.address_internal_id = address.internal_id
          AND user.email = {email}
      """).on(
        'email -> email
      ).as(User.withAddress.singleOpt)
    }

  def all:Seq[User] =
    DB.withConnection { implicit conn =>
      SQL("""
        SELECT
          *
        FROM
          user,
          address
        WHERE
          user.address_internal_id = address.internal_id
      """).as(User.withAddress *)
    }

}