package tp

case class Torneo(postas: Set[Posta], dragones: List[Dragon], participantes: List[Participante])

trait CriterioDeAdmision {
  def seCumple(participante: Participante): Boolean
}

trait Posta {
  def puedeParticipar(participante: Participante): Boolean = {
    !hacerParticipar(participante).quedariaHambriento && requisito.exists(_.seCumple(participante))
  }
  def obtenerGanador: Option[Participante]
  def requisito: Option[CriterioDeAdmision]
  def hacerParticipar(participante: Participante): Participante = ???
}

case class Carrera(km: Int, requisito: Option[CriterioDeAdmision]) extends Posta {
  override def obtenerGanador: Option[Participante] = ???
}



case class Pesca() extends Posta{
  override def hacerParticipar(participante: Participante) = ???
  override def puedeParticipar(participante: Participante): Boolean = ???

  override def obtenerGanador: Option[Participante] = ???

  override def requisito: Option[CriterioDeAdmision] = ???
}
