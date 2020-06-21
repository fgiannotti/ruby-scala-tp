package tp

trait ReglaTorneo {
  def eliminarParticipantesLuegoDePosta(resultadoPosta: List[Participante]): List[Participante]

  def prepararParticipantes(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Participante]
}

class Estandar extends ReglaTorneo {
  override def prepararParticipantes(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Participante] = {
    val participantesPreparados: List[Participante] = List()
    var dragonesDisponibles = dragones

    vikingos.foreach { vikingo =>
      val dragonElegido = vikingo.mejorMontura(dragonesDisponibles, posta)

      val participante: Participante = dragonElegido.map { dragon =>
        dragonesDisponibles = dragonesDisponibles.filterNot(_.equals(dragon))
        vikingo.montar(dragon)
      }.getOrElse(vikingo)

      participantesPreparados :+ participante
    }
    participantesPreparados
  }

  override def eliminarParticipantesLuegoDePosta(resultadoPosta: List[Participante]): List[Participante] = {
    resultadoPosta.slice(0, resultadoPosta.size / 2)
  }
}

object Eliminacion extends Estandar {}

object PorEquipos extends ReglaTorneo {

}

case class Torneo(postas: List[Posta], dragones: List[Dragon], vikingos: List[Vikingo], regla: ReglaTorneo) {
  def jugarTorneo = {
    postas.foreach { posta =>
      val participantesPreparados = regla.prepararParticipantes(vikingos, dragones, posta)
      val resultadoPosta = posta.hacerParticipar(participantesPreparados)
      regla.eliminarParticipantesLuegoDePosta(resultadoPosta)
    }
  }
}

