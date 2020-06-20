import org.scalatest.{FreeSpec, Matchers}
import tp.{Arma, Comestible, Dragon, FuriaNocturna, Jinete, Patapez, SistemaDeVuelo, TengaItem, Vikingo}

class ProjectSpec extends FreeSpec with Matchers {

  "TP-FuncionalObjetos" - {
    val hipo = Vikingo(100, 35, 5, 0, SistemaDeVuelo)
    val astrid = Vikingo(95, 40, 6, 0, Arma(30))
    val patan = Vikingo(120, 30, 3, 0, Arma(100))
    val patapez = Patapez(Comestible(6))
    "un vikingo que cumple con los requsitos del dragon" - {
      val razaFuriaNocturna = FuriaNocturna(30,10, List(TengaItem(SistemaDeVuelo)))
      val chimuelo = Dragon(raza = razaFuriaNocturna)
      "deberia generar un nuevo jinete" in {
        hipo.montar(chimuelo) shouldBe Jinete(hipo, chimuelo)
      }
    }
    "un vikingo que no cumple con los requsitos del dragon" - {
      val razaFuriaNocturna = FuriaNocturna(30,10, List(TengaItem(SistemaDeVuelo)))
      val chimuelo = Dragon(raza = razaFuriaNocturna)
      "deberia retornar solo el vikingo" in {
        astrid.montar(chimuelo) shouldBe astrid
      }
    }
  }

}
