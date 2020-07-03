import SpecObjects._
import org.scalatest.{FreeSpec, Matchers}
import tp._

class TorneoSpec extends FreeSpec with Matchers {

  "Torneo spec" - {

    "un torneo con 1 postas de combate" - {
      val torneo1: TorneoEstandar = new TorneoEstandar(List(combateConArma), dragones, List(hipo, balti, astrid))

      "deberia ganar astrid porque es la unica que tiene arma" in {
        torneo1.jugarTorneo shouldBe Some(astrid.copy(nivelDeHambre = astrid.nivelDeHambre + 5))
      }
    }

    "un torneo con 1 postas de carrera" - {
      val torneo2: TorneoEstandar = new TorneoEstandar(List(carrera), List(chimueloRapido), List(hipo, balti, astrid))

      "deberia ganar hipo porque monta en el chimueloRapido" in {
        torneo2.jugarTorneo shouldBe Some(hipo.copy(nivelDeHambre = hipo.nivelDeHambre + 5))
      }
    }

    "un torneo estandar con 3 postas de carrera" - {
      val torneo3: TorneoEstandar = new TorneoEstandar(List(pesca, combateConArma, carrera, carrera), List(chimueloRapido), List(hipo, balti, astrid, astridPesada))

      "deberia ganar astridPesada luego de la primer carrera porque tiene arma y es veloz" in {
        torneo3.jugarTorneo shouldBe Some(astridPesada.aumentarHambre(15))
      }
    }

    "un torneo estandar con 2 vikingos llenos" - {
      val torneo4: TorneoEstandar = new TorneoEstandar(List(pesca, combateConArma, carrera), List(chimueloRapido), List(vikingoLleno, otroVikingoLleno))

      "no deberia tener ganador porque ninguno puede participar" in {
        torneo4.jugarTorneo shouldBe None
      }
    }

    "un torneo de eliminación de los ultimos 2" - {
      val torneoEliminacion1: TorneoConEliminacion = TorneoConEliminacion(List(combate, carrera, combateConArma), List(chimueloRapido), List(ragnar, ivar, hipo, patan, astrid, astridPesada), 2)
      "deberia ganar ragnar en la ronda final" in {
        torneoEliminacion1.jugarTorneo shouldBe Some(ragnar.aumentarHambre(22))
      }
    }

    "un torneo de eliminación de los ultimos 10" - {
      val torneoEliminacion2: TorneoConEliminacion = TorneoConEliminacion(List(combate, carrera, combateConArma), List(chimueloRapido), List(ragnar, ivar, hipo, patan, astrid, astridPesada), 10)
      "no deberia tener ganador porque el n es grande y elimina a todos" in {
        torneoEliminacion2.jugarTorneo shouldBe None
      }
    }

    "un torneo inverso con carrera y combate" - {
      val torneoInverso: TorneoInverso = TorneoInverso(List(carrera, combate), List(chimueloRapido), List(hipo, ragnar, patan, ivar))
      "deberia ganar ivar porque es el peor" in {
        torneoInverso.jugarTorneo shouldBe Some(ivar.aumentarHambre(22))
      }
    }

    "un torneo con handicap con carrera y un dragón disponible" - {
      val torneoHandicap1: TorneoHandicap = TorneoHandicap(List(carrera), List(furiaNocturna), List(hipo, patan, astrid, balti, ivar))
      "deberia ganar ivar es el último de la lista entonces se sube al dragón" in {
        torneoHandicap1.jugarTorneo shouldBe Some(ivar.aumentarHambre(5))
      }
    }

    "un torneo con handicap con carrera, combate y un dragón disponible" - {
      val torneoHandicap2: TorneoHandicap = TorneoHandicap(List(carrera, combate), List(furiaNocturna), List(hipo, patan, astrid, balti, ivar))
      "deberia ganar astrid porque es la última en pasar la carrera y entonces se sube al dragón para el combate" in {
        torneoHandicap2.jugarTorneo shouldBe Some(astrid.aumentarHambre(17))
      }
    }

    "un torneo con veto con combate y un dragón disponible que es vetado" - {
      val torneoConVeto: TorneoConVeto = TorneoConVeto(List(combate), List(furiaNocturaSuperDanio), List(hipo, patan, astrid, balti, ivar), (dragon => dragon.danio < 1000))
      "deberia ganar patan porque es el mejor guerrero a pie" in {
        torneoConVeto.jugarTorneo shouldBe Some(patan.aumentarHambre(10))
      }
    }

    "un torneo por equipos con el equipoPanzaLlena" - {
      val torneoPorEquipos: TorneoPorEquipos = TorneoPorEquipos(List(pesca), List(furiaNocturaSuperDanio), List(equipoPanzaLlena, Equipo(List(vikingoReLleno))))
      "no debería tener ganador porque no pueden competir" in {
        torneoPorEquipos.jugarTorneo shouldBe Some(Equipo(List()))
      }
    }

    "un torneo por equipos con el equipoKattegat y el equipoPanzaLlena" - {
      val torneoPorEquipos: TorneoPorEquipos = TorneoPorEquipos(List(combate), List(dragonNadder, furiaNocturna), List(equipoKattegat, equipoBerg))
      "debería ganar el equipoKattegat en orden ragnar, floki y bjorn" in {
        torneoPorEquipos.jugarTorneo shouldBe Some(Equipo(List(ragnar.aumentarHambre(5),floki.aumentarHambre(6), bjorn.aumentarHambre(5))))
      }
    }
  }
}