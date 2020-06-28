import SpecObjects._
import org.scalatest.{FreeSpec, Matchers}
import tp._

class VikingoSpec extends FreeSpec with Matchers {

  "Vikingo spec" - {
    "un vikingo que cumple con los requsitos del dragon" - {
      "deberia generar un nuevo jinete" in {
        hipo.montar(chimuelo) shouldBe Jinete(hipo, chimuelo)
      }
    }

    "un vikingo que no cumple con los requsitos del dragon" - {
      "deberia retornar solo el vikingo" in {
        astrid.montar(chimuelo) shouldBe astrid
      }
        "deberia retornar jinete" in {
          astrid.montar(dragonNadder) shouldBe Jinete(astrid, dragonNadder)
        }
    }

    "un vikingo que elige la mejor montura para una posta" - {
      "elige al dragon nadder porque con chimuelo no puede participar y solo hace menos danio" in {
        val dragonElegido = astrid.mejorMontura(List(dragonNadder,chimuelo), combate).get
        dragonElegido shouldBe dragonNadder
      }
      "participa como vikingo si no cumple con el requisito de ninguno de los dragones disponibles" in {
        val dragonElegido = astridPesada.mejorMontura(List(gronkle,chimuelo), combate)
        dragonElegido shouldBe None
      }
      "elige al dragon que mas danio haga en un combate y que pueda montar" in {
        val dragonElegido = hipo.mejorMontura(List(chimuelo,gronkle), combate).get
        dragonElegido shouldBe chimuelo
      }
      "elige a gronkle que hace mas danio hace y puede montar" in {
        val dragonElegido = balti.mejorMontura(dragones, combate).get
        dragonElegido shouldBe gronkle
      }
    }
  }
}
