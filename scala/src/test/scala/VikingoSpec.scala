import SpecObjects._
import org.scalatest.{FreeSpec, Matchers}
import tp._

class VikingoSpec extends FreeSpec with Matchers {

  "Vikingo spec" - {
    "un vikingo que cumple con los requsitos del dragon" - {
      val razaFuriaNocturna = FuriaNocturna(30, 10, List(TengaItem(SistemaDeVuelo)))
      val chimuelo = Dragon(raza = razaFuriaNocturna)
      "deberia generar un nuevo jinete" in {
        hipo.montar(chimuelo) shouldBe Jinete(hipo, chimuelo)
      }
    }
    "un vikingo que no cumple con los requsitos del dragon" - {
      val razaFuriaNocturna = FuriaNocturna(30, 10, List(TengaItem(SistemaDeVuelo)))
      val chimuelo = Dragon(raza = razaFuriaNocturna)
      "deberia retornar solo el vikingo" in {
        astrid.montar(chimuelo) shouldBe astrid
      }
    }
    "un vikingo que elige la mejor montura para una posta" - {
      "elige al dragon nadder porque con chimuelo no puede participar y solo hace menos danio" in {
        val dragonElegido = astrid.mejorMontura(dragones, combate).get
        dragonElegido shouldBe dragonNadder
      }
      "participa como vikingo si no cumple con el requisito de ninguno de los dragones disponibles" in {
        val dragonElegido = astrid.mejorMontura(List(chimuelo), combate)
        dragonElegido shouldBe None
      }
      "elige al dragon que mas danio haga en un combate y que pueda montar" in {
        val dragonElegido = hipo.mejorMontura(dragones, combate).get
        dragonElegido shouldBe chimuelo
      }
    }
  }
}
