package tp

trait ReglaTorneo {
  def eliminarParticipantesLuegoDePosta(resultadoPosta: List[Participante]): List[Participante]

  def prepararParticipantes(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Participante]

  def elegirGanador(vikingos: List[Vikingo]): Vikingo
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

  override def elegirGanador(vikingos: List[Vikingo]): Vikingo =
    vikingos.head
}

case class Eliminacion(cantidadAEliminar: Int) extends Estandar {
  override def eliminarParticipantesLuegoDePosta(resultadoPosta: List[Participante]): List[Participante] =
    resultadoPosta.dropRight(cantidadAEliminar)
}

case class TorneoInverso() extends Estandar {
  override def eliminarParticipantesLuegoDePosta(resultadoPosta: List[Participante]): List[Participante] =
    resultadoPosta.slice(resultadoPosta.size / 2, resultadoPosta.size)

  override def elegirGanador(vikingos: List[Vikingo]): Vikingo =
    vikingos.last
}

case class ConVeto(condicion: Dragon => Boolean) extends Estandar {
  override def prepararParticipantes(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Participante] = {
    val nuevosDragones = dragones.filter(condicion)
    super.prepararParticipantes(vikingos, nuevosDragones, posta)
  }
}

case class Handicap() extends Estandar {
  override def prepararParticipantes(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Participante] = {
    val nuevosVikingos = vikingos.reverse
    super.prepararParticipantes(nuevosVikingos, dragones, posta)
  }
}

/*object PorEquipos extends ReglaTorneo {

}*/

case class Torneo(postas: List[Posta], dragones: List[Dragon], vikingos: List[Vikingo], regla: ReglaTorneo) {
  def jugarTorneo: Option[Vikingo] = {
    jugarPostas(postas, vikingos) match {
      case Nil => None
      case x :: Nil => Some(x)
      case finalistas => Option(regla.elegirGanador(finalistas))
    }
  }

  def jugarPostas(postas: List[Posta], vikingos: List[Vikingo]): List[Vikingo] = {
    val posta = postas.head
    val ganadoresPosta = jugarPosta(posta, vikingos)
    if (postas.size == 1 || ganadoresPosta.size <= 1) ganadoresPosta else jugarPostas(postas.tail, ganadoresPosta)
  }

  private def jugarPosta(posta: Posta, vikingos: List[Vikingo]): List[Vikingo] = {
    val participantesPreparados = regla.prepararParticipantes(vikingos, dragones, posta)
    val resultadoPosta = posta.hacerParticipar(participantesPreparados)
    regla.eliminarParticipantesLuegoDePosta(resultadoPosta).map {
      case Jinete(vikingo, _) => vikingo
      case vikingo: Vikingo => vikingo
    }
  }
}

