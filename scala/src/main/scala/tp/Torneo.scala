package tp

case class Equipo(vikingos: List[Vikingo]) {
  def rearmarse(vikingosNuevos: List[Vikingo]): Equipo =
    Equipo(vikingosNuevos.filter(v => vikingos.contains(v)))

}

trait Torneo[T] {
  def postas: List[Posta]

  def dragones: List[Dragon]

  def jugadores: List[T]

  def jugarTorneo: Option[T] = {
    jugarPostas(postas, jugadores) match {
      case Nil => None
      case x :: Nil => Option(x)
      case finalistas => Option(elegirGanador(finalistas))
    }
  }

  def jugarPostas(postas: List[Posta], jugadores: List[T]): List[T] = {
    val posta = postas.head
    val ganadoresPosta = jugarPosta(posta, jugadores)
    if (postas.size == 1 || ganadoresPosta.size <= 1) ganadoresPosta else jugarPostas(postas.tail, ganadoresPosta)
  }

  def jugarPosta(posta: Posta, jugadores: List[T]): List[T]

  def obtenerGanadores(vikingos: List[Vikingo], posta: Posta): List[Vikingo] = {
    val participantesPreparados = prepararParticipantes(vikingos, dragones, posta)
    val resultadoPosta = posta.hacerParticipar(participantesPreparados)
    eliminarParticipantesLuegoDePosta(resultadoPosta).map {
      case Jinete(vikingo, _) => vikingo
      case vikingo: Vikingo => vikingo
    }
  }

  def eliminarParticipantesLuegoDePosta(resultadoPosta: List[Participante]): List[Participante] =
    resultadoPosta.take(resultadoPosta.size / 2)

  def prepararParticipantes(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Participante] = {
    var dragonesDisponibles = dragones

    vikingos.map { vikingo =>
      val dragonElegido = vikingo.mejorMontura(dragonesDisponibles, posta)

      dragonElegido.map { dragon =>
        dragonesDisponibles = dragonesDisponibles.filterNot(_.equals(dragon))
        vikingo.montar(dragon)
      }.getOrElse(vikingo)
    }
  }
  def elegirGanador(jugadores: List[T]): T
}

class TorneoEstandar(override val postas: List[Posta], override val dragones: List[Dragon], override val jugadores: List[Vikingo])
  extends Torneo[Vikingo] {

  override def elegirGanador(jugadores: List[Vikingo]): Vikingo = jugadores.head

  override def jugarPosta(posta: Posta, jugadores: List[Vikingo]): List[Vikingo] = this.obtenerGanadores(jugadores, posta)

}

case class TorneoConEliminacion(override val postas: List[Posta], override val dragones: List[Dragon], override val jugadores: List[Vikingo], cantidadAEliminar: Int)
  extends TorneoEstandar(postas, dragones, jugadores) {
  override def eliminarParticipantesLuegoDePosta(resultadoPosta: List[Participante]): List[Participante] =
    resultadoPosta.dropRight(cantidadAEliminar)
}

case class TorneoInverso(override val postas: List[Posta], override val dragones: List[Dragon], override val jugadores: List[Vikingo])
extends TorneoEstandar(postas, dragones, jugadores) {
  override def eliminarParticipantesLuegoDePosta(resultadoPosta: List[Participante]): List[Participante] =
    resultadoPosta.drop(resultadoPosta.size / 2)

  override def elegirGanador(jugadores: List[Vikingo]): Vikingo = jugadores.head
}

case class TorneoConVeto(override val postas: List[Posta], override val dragones: List[Dragon], override val jugadores: List[Vikingo], condicion: Dragon => Boolean)
  extends TorneoEstandar(postas, dragones, jugadores) {
  override def prepararParticipantes(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Participante] = {
    val nuevosDragones = dragones.filter(condicion)
    super.prepararParticipantes(vikingos, nuevosDragones, posta)
  }
}

case class TorneoHandicap(override val postas: List[Posta], override val dragones: List[Dragon], override val jugadores: List[Vikingo])
  extends TorneoEstandar(postas, dragones, jugadores) {
  override def prepararParticipantes(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Participante] = {
    val nuevosVikingos = vikingos.reverse
    super.prepararParticipantes(nuevosVikingos, dragones, posta)
  }
}

case class TorneoPorEquipos(override val postas: List[Posta], override val dragones: List[Dragon], override val jugadores: List[Equipo]) extends Torneo[Equipo] {
  override def jugarPosta(posta: Posta, jugadores: List[Equipo]): List[Equipo] = {
    val participantes = jugadores.flatMap(_.vikingos)
    val vikingosFinalistas = obtenerGanadores(participantes, posta)
    jugadores.map(_.rearmarse(vikingosFinalistas))
  }

  override def elegirGanador(jugadores: List[Equipo]): Equipo =
    jugadores.sortWith((e1, e2) => e1.vikingos.size >= e2.vikingos.size).head
}


