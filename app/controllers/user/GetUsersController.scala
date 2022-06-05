package controllers.user

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.mvc._

import model.User
import persistence.UserRepository

class GetUsersController @Inject()(val cc: ControllerComponents) extends AbstractController(cc) {

  def get = Action async { implicit request: Request[AnyContent] =>
    for {
      users <- UserRepository.getAll()
    } yield users match {
      case seq: Seq[User] => Ok(views.html.index(users))
      case _              => NoContent
    }
  }
}
