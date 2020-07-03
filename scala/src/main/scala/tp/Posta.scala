package tp

import tp.CriterioAdmision.CriterioAdmision

trait Posta extends Hambrienta {
  def puedeParticipar(participante: Participante): Boolean = {
    !efectoSobreParticipante(participante, costoParticipacion).quedariaHambriento && criterioAdmision.forall(_.apply(participante))
  }
  def criterioAdmision: Option[CriterioAdmision]
  def hacerParticipar(participantes: List[Participante]): List[Participante] = participantes
      .filter(puedeParticipar)
      .map(efectoSobreParticipante(_, costoParticipacion))
      .sortBy(criterioGanador).reverse
  def esMejorQue(participante: Participante, otroParticipante: Participante): Boolean
  def criterioGanador(participante: Participante): Double
  def costoParticipacion: Int
}

case class Carrera(km: Int, criterioAdmision: Option[CriterioAdmision] = None) extends Posta {
  override def esMejorQue(participante: Participante, otroParticipante: Participante): Boolean =
    participante.velocidad >= otroParticipante.velocidad

  override def criterioGanador(participante: Participante): Double = participante.velocidad

  override def costoParticipacion: Int = km
}

case class Combate(criterioDeCombate: CriterioAdmision) extends Posta {
  override def criterioAdmision: Option[CriterioAdmision] = Option(criterioDeCombate)

  override def esMejorQue(participante: Participante, otroParticipante: Participante): Boolean =
    participante.danio >= otroParticipante.danio

  override def criterioGanador(participante: Participante): Double =
    participante.danio

  override def costoParticipacion: Int = 10
}

case class Pesca(criterioAdmision: Option[CriterioAdmision] = None) extends Posta {

  override def criterioGanador(participante: Participante): Double = participante.maximaCargaDePescado

  override def esMejorQue(participante: Participante, otroParticipante: Participante): Boolean =
    participante.maximaCargaDePescado >= otroParticipante.maximaCargaDePescado

  override def costoParticipacion: Int = 5
}

trait Hambrienta {
  def efectoSobreParticipante(participante: Participante, costoParticipacion: Int): Participante =
    participante match {
      case jinete: Jinete => jinete.aumentarHambre()
      case vikingo: Vikingo => vikingo.aumentarHambre(costoParticipacion)
    }
}
