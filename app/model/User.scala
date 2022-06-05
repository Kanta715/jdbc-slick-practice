package model

import play.api.libs.json._

/**
 * Model: User
 *
 * @param id       id of user.
 * @param lastName last name of user.
 * @param fistName fist name of user.
 * @param age      age of user.
 * @param address  address of user.
 * @param gender   gender of user.
 */
case class User(
  id:        Option[Int]    = None,
  lastName:  String,
  firstName: String,
  age:       Option[Int]    = None,
  address:   Option[String] = None,
  gender:    Option[Int]    = None
) {

  /**
   * Get full name
   */
  def fullName: String = lastName + firstName
}

/**
 * Companion object
 */
object User {

  /**
   * Create new object
   */
  def create(
    lastName:  String,
    firstName: String,
    age:       Option[Int],
    address:   Option[String],
    gender:    Option[Int]
  ): User =
    User(
      lastName  = lastName,
      firstName = firstName,
      age       = age,
      address   = address,
      gender    = gender
    )

  /**
   * Json parser
   */
  implicit val format = Json.format[User]
}
