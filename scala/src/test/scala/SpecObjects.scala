import tp._

object SpecObjects {
  val hipo: Vikingo = Vikingo(peso = 100, velocidad = 35, barbarosidad = 5, nivelDeHambre = 90, item = SistemaDeVuelo)
  val balti: Vikingo = Vikingo(peso = 96, velocidad = 35, barbarosidad = 5, nivelDeHambre = 0, item = SistemaDeVuelo)
  val astrid: Vikingo = Vikingo(peso = 90, velocidad = 40, barbarosidad = 6, nivelDeHambre = 0, item = Arma(30))
  val astridPesada: Vikingo = Vikingo(peso = 120, velocidad = 40, barbarosidad = 6, nivelDeHambre = 0, item = Arma(40))
  val patan: Vikingo = Vikingo(peso = 79, velocidad = 30, barbarosidad = 3, nivelDeHambre = 0, item = Arma(100))
  val vikingoLleno: Vikingo = Vikingo(peso = 150, velocidad = 10, barbarosidad = 10, nivelDeHambre = 99, item = Arma(25))
  val otroVikingoLleno: Vikingo = Vikingo(peso = 155, velocidad = 11, barbarosidad = 15, nivelDeHambre = 99, item = Arma(30))
  val ragnar: Vikingo = Vikingo(peso = 110, velocidad = 140, barbarosidad = 30, nivelDeHambre = 0, item = Arma(200))
  val ivar: Vikingo = Vikingo(peso = 60, velocidad = 13, barbarosidad = 3, nivelDeHambre = 1, item = Arma(10))
  val floki: Vikingo = Vikingo(peso = 80, velocidad = 45, barbarosidad = 300, nivelDeHambre = 0, item = Arma(100))
  val bjorn: Vikingo = Vikingo(peso = 90, velocidad = 100, barbarosidad = 30, nivelDeHambre = 0, item = Arma(200))
  val vikingoReLleno: Vikingo = Vikingo(peso = 100, velocidad = 1, barbarosidad = 10, nivelDeHambre = 99, item = Arma(10))

  val equipoPanzaLlena: Equipo = Equipo(List(vikingoLleno, otroVikingoLleno))
  val equipoKattegat: Equipo = Equipo(List(ragnar, bjorn, floki))
  val equipoBerg: Equipo = Equipo(List(hipo, astrid, patan, balti))
  val equipoPanzaLlena2: Equipo = Equipo(List(hipo, otroVikingoLleno))

  val combate: Combate = Combate(BarbaridadMinima(3))
  val combateConArma: Combate = Combate(TieneArma())
  val pesca: Pesca = Pesca(Some(PesoMinimo(50)))
  val carrera: Carrera = Carrera(12)
  val carreraConCriterioMontura: Carrera = Carrera(10, Some(EsJinete()))


  val chimuelo: Dragon = Dragon(100, FuriaNocturna(peso = 500, 250, List(TengaItem(SistemaDeVuelo))))
  val chimueloRapido: Dragon = Dragon(1000, FuriaNocturna(peso = 500, 250, List(TengaItem(SistemaDeVuelo))))
  val dragonNadder: Dragon = Dragon(70, NadderMortifero(500, List(SuficienteBarbaridad(5))))
  val furiaNocturna: Dragon = Dragon(1000, FuriaNocturna(600, 500, List(SuficienteBarbaridad(1))))
  val furiaNocturaSuperDanio = Dragon(1000, FuriaNocturna(650, 3000, List(TengaItem(SistemaDeVuelo))))

  val gronkle: Dragon = Dragon(80, Gronkle(600, List(NoSuperaPesoMaximo(97))))
  val dragones = List(chimuelo, dragonNadder, gronkle)

  val jugadores = List(hipo, astrid, balti, astridPesada)

}

