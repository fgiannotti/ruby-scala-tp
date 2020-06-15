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

case class Carrera(km: Int, requisito: Option[CriterioDeAdmision]) extends Posta



case class Pesca() extends Posta{
  def hacerParticipar(participante: Participante) = ???
  def puedeParticipar(participante: Participante): Boolean = {

  }

}
