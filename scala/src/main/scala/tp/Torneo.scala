package tp

import tp.CriterioAdmision.CriterioAdmision

case class Torneo(postas: List[Posta], dragones: List[Dragon], participantes: List[Participante])

trait Posta {
  def puedeParticipar(participante: Participante): Boolean = {
    !participante.participarEnPosta(this).quedariaHambriento && criterioAdmision.exists(_.apply(participante))
  }
  def obtenerGanador: Option[Participante]
  def criterioAdmision: Option[CriterioAdmision]
  def hacerParticipar(participantes: List[Participante]): List[Participante] = participantes
      .filter(participante => puedeParticipar(participante))
      .map(participante => participante.participarEnPosta(this))
      .sortBy(participante => this.criterioGanador(participante)).reverse
  def compararParticipantes(participante: Participante, otroParticipante: Participante): Participante
  def criterioGanador(participante: Participante): Double
  def costoParticipacion: Int
}

case class Carrera(km: Int, criterioAdmision: Option[CriterioAdmision]) extends Posta {
  override def obtenerGanador: Option[Participante] = ???

  override def compararParticipantes(participante: Participante, otroParticipante: Participante): Participante = //todo mejorar
    if(participante.velocidad >= otroParticipante.velocidad) participante else otroParticipante

  override def criterioGanador(participante: Participante): Double = participante.velocidad

  override def costoParticipacion: Int = km
}

case class Combate() extends Posta {
  override def obtenerGanador: Option[Participante] = ???

  override def criterioAdmision: Option[CriterioAdmision] = ???

  override def compararParticipantes(participante: Participante, otroParticipante: Participante): Participante =
    if(participante.danio >= otroParticipante.danio) participante else otroParticipante

  override def criterioGanador(participante: Participante): Double = participante.danio

  override def costoParticipacion: Int = 10
}

case class Pesca(criterioAdmision: Option[CriterioAdmision]) extends Posta {

  override def puedeParticipar(participante: Participante): Boolean = ???

  override def obtenerGanador: Option[Participante] = ???

  override def criterioGanador(participante: Participante): Double = participante.maximaCargaDePescado

  override def compararParticipantes(participante: Participante, otroParticipante: Participante): Participante = //todo mejorar
    if(participante.maximaCargaDePescado >= otroParticipante.maximaCargaDePescado) participante else otroParticipante

  override def costoParticipacion: Int = 5
}
