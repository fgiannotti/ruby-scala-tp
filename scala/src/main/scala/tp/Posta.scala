package tp

import tp.CriterioAdmision.CriterioAdmision

trait Posta {
  def puedeParticipar(participante: Participante): Boolean = {
    !participante.participarEnPosta(this).quedariaHambriento && criterioAdmision.exists(_.apply(participante))
  }
  def criterioAdmision: Option[CriterioAdmision]
  def hacerParticipar(participantes: List[Participante]): List[Participante] = participantes
      .filter(participante => puedeParticipar(participante))
      .map(participante => participante.participarEnPosta(this))
      .sortBy(participante => this.criterioGanador(participante)).reverse
  def esMejorQue(participante: Participante, otroParticipante: Participante): Boolean
  def criterioGanador(participante: Participante): Double
  def costoParticipacion: Int
}

case class Carrera(km: Int, criterioAdmision: Option[CriterioAdmision]) extends Posta {
  override def esMejorQue(participante: Participante, otroParticipante: Participante): Boolean =
    participante.velocidad >= otroParticipante.velocidad

  override def criterioGanador(participante: Participante): Double = participante.velocidad

  override def costoParticipacion: Int = km
}

case class Combate(criterioAdmision: Option[CriterioAdmision]) extends Posta {
  override def esMejorQue(participante: Participante, otroParticipante: Participante): Boolean =
    participante.danio >= otroParticipante.danio

  override def criterioGanador(participante: Participante): Double =
    participante.danio

  override def costoParticipacion: Int = 10
}

case class Pesca(criterioAdmision: Option[CriterioAdmision]) extends Posta {

  override def criterioGanador(participante: Participante): Double = participante.maximaCargaDePescado

  override def esMejorQue(participante: Participante, otroParticipante: Participante): Boolean =
    participante.maximaCargaDePescado >= otroParticipante.maximaCargaDePescado

  override def costoParticipacion: Int = 5
}
