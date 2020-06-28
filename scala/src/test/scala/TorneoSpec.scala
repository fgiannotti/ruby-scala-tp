import SpecObjects._
import org.scalatest.{FreeSpec, Matchers}
import tp.{Jinete, TorneoEstandar}

class TorneoSpec extends FreeSpec with Matchers {

  "Torneo spec" - {

    "un torneo con 1 postas de combate" - {
      val torneo1: TorneoEstandar = new TorneoEstandar(List(combateConArma), dragones, List(hipo, balti, astrid))

      "deberia ganar astrid porque es la unica que tiene arma" in {
        torneo1.jugarTorneo shouldBe Some(astrid.copy(nivelDeHambre = astrid.nivelDeHambre + 5))
      }

      "un torneo con 1 postas de combate" - {
        val torneo2: TorneoEstandar = new TorneoEstandar(List(carrera), List(chimueloRapido), List(hipo, balti, astrid))

        "deberia ganar astrid porque es la unica que tiene arma" in {
          torneo2.jugarTorneo shouldBe Some(hipo.copy(nivelDeHambre = hipo.nivelDeHambre + 5))
        }
      }
    }
  }
}