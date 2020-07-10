package tp

trait Participante {
  def peso: Int

  def velocidad: Double

  def barbarosidad: Int

  def maximaCargaDePescado: Double

  def nivelDeHambre: Int

  def quedariaHambriento: Boolean = nivelDeHambre >= 100

  def item: Item

  def danio: Int

  def esMejorQue(participante: Participante)(posta: Posta): Boolean =
    posta.esMejorQue(this, participante)

  def aumentarHambre(porcentaje: Int): Participante

}

case class Vikingo(peso: Int, velocidad: Double, barbarosidad: Int, nivelDeHambre: Int, item: Item) extends Participante {
  def maximaCargaDePescado: Double = peso * 0.5 + barbarosidad * 2

  def puedeCargar(cantidadPescado: Int): Boolean =
    cantidadPescado <= maximaCargaDePescado

  def danio: Int =
    barbarosidad + item.danio

  def montar(dragon: Dragon): Participante =
    if (dragon.puedeSerMontadoPor(this)) Jinete(this, dragon) else this

  override def aumentarHambre(porcentaje: Int): Vikingo =
    copy(nivelDeHambre = nivelDeHambre + porcentaje)

  def mejorMontura(dragones: List[Dragon], posta: Posta): Option[Dragon] = {
    val dragonesQuePuedenSerMontados = dragones.filter(_.puedeSerMontadoPor(this))
    posta.hacerParticipar(dragonesQuePuedenSerMontados.map(montar) :+ this).headOption match {
      case Some(Jinete(_, dragon)) => Some(dragon)
      case _ => None
    }
  }

  override def equals(obj: Any): Boolean = {
    val vikingo = obj.asInstanceOf[Vikingo]
    this.barbarosidad.equals(vikingo.barbarosidad) & this.item.equals(vikingo.item) & this.peso.equals(vikingo.peso) & this.velocidad.equals(vikingo.velocidad)
  }
}

case class Jinete(vikingo: Vikingo, dragon: Dragon) extends Participante {
  override def maximaCargaDePescado: Double = vikingo.peso - dragon.maximaCarga

  override def danio: Int = vikingo.danio + dragon.danio

  override def velocidad: Double = dragon.velocidad - peso

  override def peso: Int = vikingo.peso

  override def nivelDeHambre: Int = vikingo.nivelDeHambre

  override def barbarosidad: Int = vikingo.barbarosidad

  override def item: Item = vikingo.item

  override def aumentarHambre(porcentaje: Int): Participante = copy(vikingo.aumentarHambre(5))
}

