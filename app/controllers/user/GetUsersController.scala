package controllers.user

import javax.inject._
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import play.api.db._
import play.api.mvc._

import model.User

class GetUsersController @Inject()(val cc: ControllerComponents, db: Database) extends AbstractController(cc) {

  def get = Action async { implicit request: Request[AnyContent] =>
    Future {
      val members = ListBuffer.empty[User]
      val conn = db.getConnection()
      db.withConnection { _ =>
        val stmt   = conn.createStatement
        val result = stmt.executeQuery("select id, last_name, first_name, age, address from user")
        while(result.next) {
          val id        = result.getInt("id")
          val lastName  = result.getString("last_name")
          val firstName = result.getString("first_name")
          val age       = result.getInt("age")
          val address   = result.getString("address")
          members append User(Some(id), lastName, firstName, Some(age), Some(address))
        }
      }
      Ok(views.html.index(members))
    }
  }
}
