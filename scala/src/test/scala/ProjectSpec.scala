import org.scalatest.{FreeSpec, Matchers}
import tp._

class ProjectSpec extends FreeSpec with Matchers {

  "TP-FuncionalObjetos" - {
    val hipo = Vikingo(peso = 100, velocidad = 35, barbarosidad =  5, nivelDeHambre = 90, item = SistemaDeVuelo)
    val astrid = Vikingo(peso = 90, velocidad = 40, barbarosidad = 6, nivelDeHambre = 0, item = Arma(30))
    val patan = Vikingo(peso = 79, velocidad = 30, barbarosidad = 3, nivelDeHambre = 0, item = Arma(100))
    val patapez = Patapez

    val combate = Combate(Some(BarbaridadMinima(3)))
    val pesca = Pesca(Some(PesoMinimo(50)))
    val carrera = Carrera(12)

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
      "cuando cumple el criterio de admision y no queda totalmente ambriento" in {
        pesca.puedeParticipar(astrid) shouldBe true
      }
      "cuando no queda totalmente ambriento y no hay criterio de admision" in {
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
        participantesLuegoDelCombate.head.peso shouldBe astrid.peso
        participantesLuegoDelCombate.filter(_.peso == hipo.peso) shouldBe List.empty
      }
    }
  }

}
case object Patapez extends Participante {

  override def maximaCargaDePescado: Double = ???

  var nivelDeHambre: Int = 0

  override def peso: Int = ???

  override def velocidad: Double = ???

  override def barbarosidad: Int = ???

  override def item: Item = Comestible(6)

  override def danio: Int = ???

  def participarEnPosta(posta: Posta): Participante = aumentarHambre(posta.costoParticipacion * 2)

  def aumentarHambre(porcentaje: Int): Participante = {
    nivelDeHambre = nivelDeHambre + porcentaje
    this
  }
}