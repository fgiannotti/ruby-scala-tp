package tp

case class Vikingo(peso: Int, velocidad: Double, barbarosidad: Int, nivelDeHambre: Int, item: Item) extends Participante {
  def maximaCargaDePescado: Double = peso * 0.5 + barbarosidad * 2

  def puedeCargar(cantidadPescado: Int): Boolean =
    cantidadPescado <= maximaCargaDePescado

  def danio: Int = barbarosidad + item.danio

  def montar(dragon: Dragon): Participante =
    if (dragon.puedeSerMontadoPor(this)) Jinete(this, dragon) else this
}

object Patapez extends Participante {

  override def maximaCargaDePescado: Double = ???

  override def nivelDeHambre: Int = ???

  override def peso: Int = ???

  override def velocidad: Double = ???

  override def barbarosidad: Int = ???

  override def item: Item = Comestible(???)

  override def danio: Int = ???
}

case class Jinete(vikingo: Vikingo, dragon: Dragon) extends Participante {
  def maximaCargaDePescado: Double = vikingo.peso - dragon.maximaCarga
  override def danio: Int = vikingo.danio + dragon.danio
  override def velocidad: Double = dragon.velocidad - peso
  override def peso: Int = vikingo.peso
  override def nivelDeHambre: Int = vikingo.nivelDeHambre

  override def barbarosidad: Int = vikingo.barbarosidad

  override def item: Item = vikingo.item
}

trait Participante {
  def peso: Int
  def velocidad: Double
  def barbarosidad: Int
  def maximaCargaDePescado: Double
  def nivelDeHambre: Int
  def quedariaHambriento: Boolean = nivelDeHambre >= 100
  def participarEnPosta(posta: Posta): Unit = ???
  def item: Item
  def danio: Int

  def esMejorQue(participante: Participante)(posta: Posta): Boolean = {
    if(posta.compararParticipantes(this, participante) == this) true else false
  }
}
