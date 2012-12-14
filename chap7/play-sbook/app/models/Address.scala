package models

import play.api.Play.current

import play.api.db.DB

import anorm._
import anorm.SqlParser._

case class Address(
  internalId:Pk[Long] = NotAssigned,
  fullStreet:String,
  county:String,
  country:String
) {

  def save =
    DB.withTransaction { implicit conn =>
      SQL(
        """
          SELECT
            internal_id
          FROM
            address
          WHERE
            full_street = {fullStreet}
            AND county = {county}
            AND country = {country}
        """
      ).on(
        'fullStreet -> fullStreet,
        'county -> county,
        'country -> country
      )
      .as(get[Long]("internal_id") singleOpt) match {
        case None => create
        case Some(id) => this.copy(internalId=Id(id)).update
      }
    }

  def create = DB.withTransaction { implicit conn =>
    val id = scala.util.Random.nextLong
    SQL(
      """
        INSERT INTO address(
          full_street,
          county,
          country,
          internal_id
        )
        VALUES (
          {fullStreet},
          {county},
          {country},
          {addressId}
        )
      """
    ).on(
      'fullStreet -> fullStreet,
      'county -> county,
      'country -> country,
      'addressId -> id
    ).executeUpdate()
    this.copy(internalId = Id(id))
  }

  def update = DB.withConnection { implicit conn =>
    SQL(
      """
        UPDATE address
        SET
          full_street = {fullStreet},
          county = {county},
          country = {country},
          internal_id = {addressId}
        WHERE
          internal_id = {addressId}
      """
    ).on(
      'fullStreet -> fullStreet,
      'county -> county,
      'country -> country,
      'addressId -> internalId.get //still assuming that address has been stored
    ).executeUpdate()
    this
  }
}

object Address {
  import anorm.SqlParser._

  /**
   * Parse an Address from a ResultSet
   */
  val simple = {
    get[Pk[Long]]("address.internal_id") ~
    get[String]("address.full_street") ~
    get[String]("address.county") ~
    get[String]("address.country") map {
      case internalId~fullStreet~county~country =>
        Address(internalId, fullStreet, county, country)
    }
  }

  def load(addressId:Long):Option[Address] =
    DB.withConnection { implicit conn =>
      SQL("""
        SELECT
          *
        FROM
          address
        WHERE
         internal_id = {addressId}
      """).on(
        'addressId -> addressId
      ).as(Address.simple.singleOpt)
    }

  def all:Seq[Address] =
    DB.withConnection { implicit conn =>
      SQL("""
        SELECT
          *
        FROM
          address
      """).as(Address.simple *)
    }


}