package controllers.user

import javax.inject._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.db._
import play.api.mvc._
import play.api.libs.json._

import model.User

class PostUserController @Inject()(val cc: ControllerComponents, db: Database)
  extends AbstractController(cc) {

  def add = Action async { implicit request =>
    Future {
      request.body.asJson match {
        case Some(json) =>
          json.validate[User] match {
            case JsSuccess(user, _) =>
              db.withConnection { conn =>
                val stmt = conn.createStatement
                stmt.executeQuery(s"INSERT INTO user (last_name, first_name) VALUES ($user.lastName, $user.firstName)")
              }
              NoContent
            case JsError(e) => BadRequest
          }
        case None       => BadRequest
      }
    }
  }
}
