import org.scalatest.{FreeSpec, Matchers}
import tp._
import SpecObjects._

class PostasSpec extends FreeSpec with Matchers {
  "Postas spec" - {

    "un vikingo es mejor que otro" - {
      "cuando produce mas danio en un combate" in {
        astrid.esMejorQue(hipo)(combate) shouldBe true
      }
      "cuando levanta mas pescado en una pesca" in {
        hipo.esMejorQue(astrid)(pesca) shouldBe true
      }
      "cuando tiene mayor velocidad en una carrera" in {
        astrid.esMejorQue(patan)(carrera) shouldBe true
      }
    }
    "un vikingo no puede participar en una posta" - {
      "cuando no cumple el criterio de admision" in {
        pesca.puedeParticipar(patan) shouldBe false
      }
      "cuando quedaria totalmente hambriento" in {
        combate.puedeParticipar(hipo) shouldBe false
      }
    }
    "un vikingo puede participar en una posta" - {
      "cuando cumple el criterio de admision y no queda totalmente hambriento" in {
        pesca.puedeParticipar(astrid) shouldBe true
      }
      "cuando no queda totalmente hambriento y no hay criterio de admision" in {
        carrera.puedeParticipar(astrid) shouldBe true
      }
    }
    "una posta que se lleva a cabo" - {
      "devuelve los participantes y afectados si cumplen los criterios" in {
        val astridLuegoDeLaPesca = pesca.hacerParticipar(List(astrid)).head
        astridLuegoDeLaPesca.nivelDeHambre shouldBe 5
      }
      "devuelve lista vacia si los participantes no cumplen los criterios" in {
        val listaDeParticipantesLuegoDelCombate = combate.hacerParticipar(List(hipo))
        listaDeParticipantesLuegoDelCombate shouldBe List.empty
      }
      "devuelve los participantes ordenados por el criterio ganador de la posta" in {
        val participantesLuegoDelCombate = combate.hacerParticipar(List(patan, astrid, hipo))
        participantesLuegoDelCombate.head.peso shouldBe patan.peso
        participantesLuegoDelCombate.filter(_.peso == hipo.peso) shouldBe List.empty
      }
    }
  }

}
