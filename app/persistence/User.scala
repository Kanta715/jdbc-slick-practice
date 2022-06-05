package persistence

import scala.concurrent.Future
import slick.jdbc.JdbcBackend
import slick.lifted._
import slick.jdbc.MySQLProfile

import db.UserTable
import model.User

class UserRepository extends ExtensionMethodConversions {
  import MySQLProfile.api._

  val users = UserTable.users
  // https://github.com/slick/slick/blob/b528b87bb2096e5d385e0b4f8cc6920a75710ba3/slick/src/main/scala/slick/jdbc/JdbcBackend.scala#L129
  val db    = JdbcBackend.Database.forURL(
    url      = "jdbc:mysql://localhost:3306/kidsna_db?characterEncoding=UTF8&autoReconnect=true&useSSL=false",
    driver   = "slick.jdbc.MySQLProfile",
    user     = "kidsna",
    password = "nb"
  )

  def getAll(): Future[Seq[User]] = {
    // https://github.com/slick/slick/blob/b528b87bb2096e5d385e0b4f8cc6920a75710ba3/slick/src/main/scala/slick/basic/BasicBackend.scala#L74
    db.run(users.result)
  }
}

object UserRepository extends UserRepository
