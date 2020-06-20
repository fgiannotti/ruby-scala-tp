package tp

import tp.CriterioAdmision.CriterioAdmision

case class Torneo(postas: List[Posta], dragones: List[Dragon], participantes: List[Participante])

trait Posta {
  def puedeParticipar(participante: Participante): Boolean = {
    !hacerParticipar(participante).quedariaHambriento && criterioAdmision.exists(_.apply(participante))
  }
  def obtenerGanador: Option[Participante]
  def criterioAdmision: Option[CriterioAdmision]
  def hacerParticipar(participante: Participante): Participante = ???
  def compararParticipantes(participante: Participante, otroParticipante: Participante): Participante
}

case class Carrera(km: Int, criterioAdmision: Option[CriterioAdmision]) extends Posta {
  override def obtenerGanador: Option[Participante] = ???

  override def compararParticipantes(participante: Participante, otroParticipante: Participante): Participante = //todo mejorar
    if(participante.velocidad >= otroParticipante.velocidad) participante else otroParticipante

}

case class Combate() extends Posta {
  override def obtenerGanador: Option[Participante] = ???

  override def criterioAdmision: Option[CriterioAdmision] = ???

  override def compararParticipantes(participante: Participante, otroParticipante: Participante): Participante =
    if(participante.danio >= otroParticipante.danio) participante else otroParticipante
}

case class Pesca(criterioAdmision: Option[CriterioAdmision]) extends Posta {
  override def hacerParticipar(participante: Participante) = ???
  override def puedeParticipar(participante: Participante): Boolean = ???

  override def obtenerGanador: Option[Participante] = ???

  override def compararParticipantes(participante: Participante, otroParticipante: Participante): Participante = //todo mejorar
    if(participante.maximaCargaDePescado >= otroParticipante.maximaCargaDePescado) participante else otroParticipante
}
