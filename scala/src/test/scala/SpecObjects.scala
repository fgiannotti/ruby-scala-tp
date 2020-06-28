import tp.CriterioAdmision.CriterioAdmision
import tp.{Arma, BarbaridadMinima, Carrera, Combate, Comestible, Dragon, EsJinete, FuriaNocturna, Gronkle, Item, NadderMortifero, NoSuperaPesoMaximo, Participante, Pesca, PesoMinimo, Posta, PuedeCargarSuPeso, SistemaDeVuelo, SuficienteBarbaridad, TengaItem, TieneArma, TorneoEstandar, Vikingo}

object SpecObjects {
  val hipo: Vikingo = Vikingo(peso = 100, velocidad = 35, barbarosidad = 5, nivelDeHambre = 90, item = SistemaDeVuelo)
  val balti: Vikingo = Vikingo(peso = 96, velocidad = 35, barbarosidad = 5, nivelDeHambre = 0, item = SistemaDeVuelo)
  val astrid: Vikingo = Vikingo(peso = 90, velocidad = 40, barbarosidad = 6, nivelDeHambre = 0, item = Arma(30))
  val astridPesada: Vikingo = Vikingo(peso = 120, velocidad = 40, barbarosidad = 6, nivelDeHambre = 0, item = Arma(40))
  val patan: Vikingo = Vikingo(peso = 79, velocidad = 30, barbarosidad = 3, nivelDeHambre = 0, item = Arma(100))
  val patapez: Patapez.type = Patapez

  val combate: Combate = Combate(Some(BarbaridadMinima(3)))
  val combateConArma: Combate = Combate(Some(TieneArma()))
  val pesca: Pesca = Pesca(Some(PesoMinimo(50)))
  val carrera: Carrera = Carrera(12)
  val carreraConCriterioMontura: Carrera = Carrera(10,Some(EsJinete()))


  val chimuelo: Dragon = Dragon(100, FuriaNocturna(peso= 500, 250, List(TengaItem(SistemaDeVuelo))))
  val chimueloRapido: Dragon = Dragon(1000, FuriaNocturna(peso= 500, 250, List(TengaItem(SistemaDeVuelo))))
  val dragonNadder: Dragon = Dragon(70, NadderMortifero(500, List(SuficienteBarbaridad(5))))

  val gronkle:Dragon = Dragon(80,Gronkle(600,List(NoSuperaPesoMaximo(97))))
  val dragones = List(chimuelo, dragonNadder,gronkle)

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
  val jugadores = List(hipo,astrid,balti,astridPesada)

}

