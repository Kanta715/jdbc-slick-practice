package persistence.db

import slick.jdbc.MySQLProfile
import slick.lifted._
import slick.sql.SqlProfile.ColumnOption.SqlType

import model.User

class UserTable(tag: Tag) extends MySQLProfile.Table[User](tag, "user") {
  import MySQLProfile.api._

  def id        = column[Option[Int]]   ("id",         O.PrimaryKey, O.AutoInc, SqlType("int(11)"))
  def lastName  = column[String]        ("last_name",  SqlType("varchar(50)"))
  def firstName = column[String]        ("first_name", SqlType("varchar(50)"))
  def age       = column[Option[Int]]   ("age",        SqlType("int(3)"), SqlType("unsigned"))
  def address   = column[Option[String]]("address",    SqlType("text"))
  def gender    = column[Option[Int]]   ("gender",     SqlType("int(1)"))

  def * = (id, lastName, firstName, age, address, gender) <> ((User.apply _).tupled, User.unapply _)
}

object UserTable {
  lazy val users = TableQuery[UserTable]
}
