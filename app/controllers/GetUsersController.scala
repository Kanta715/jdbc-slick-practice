package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db._

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

// User class
case class User(id: Int, name: String) {}

/**
 * Get users
 */
@Singleton
class GetUsersController @Inject()(val cc: ControllerComponents, db: Database) extends AbstractController(cc) {

  def get() = Action async { implicit request: Request[AnyContent] =>
    Future {
      val members = ListBuffer.empty[User]
      db.withConnection { conn =>                                  // Connection to database
        val stmt   = conn.createStatement                          // Create statement
        val result = stmt.executeQuery("select id, last_name from user") // Execute sql query
        while(result.next) {
          val id   = result.getInt("id")             // Get id at Int type
          val name = result.getString("last_name")   // Get last name at String type
          members append User(id, name)                          // Add user instance to List[User]
        }
      }
      Ok(views.html.index(members))
    }
  }
}
