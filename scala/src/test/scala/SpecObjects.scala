import tp.{Arma, BarbaridadMinima, Carrera, Combate, Comestible, Dragon, FuriaNocturna, Item, NadderMortifero, Participante, Pesca, PesoMinimo, Posta, SistemaDeVuelo, SuficienteBarbaridad, TengaItem, Vikingo}

object SpecObjects {
  val hipo: Vikingo = Vikingo(peso = 100, velocidad = 35, barbarosidad = 5, nivelDeHambre = 90, item = SistemaDeVuelo)
  val astrid: Vikingo = Vikingo(peso = 90, velocidad = 40, barbarosidad = 6, nivelDeHambre = 0, item = Arma(30))
  val patan: Vikingo = Vikingo(peso = 79, velocidad = 30, barbarosidad = 3, nivelDeHambre = 0, item = Arma(100))
  val patapez: Patapez.type = Patapez

  val combate: Combate = Combate(Some(BarbaridadMinima(3)))
  val pesca: Pesca = Pesca(Some(PesoMinimo(50)))
  val carrera: Carrera = Carrera(12)

  val chimuelo: Dragon = Dragon(170, FuriaNocturna(130, 250, List(TengaItem(SistemaDeVuelo))))
  val dragonNadder: Dragon = Dragon(70, NadderMortifero(80, List(SuficienteBarbaridad(5))))

  val dragones = List(chimuelo, dragonNadder)

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
}
