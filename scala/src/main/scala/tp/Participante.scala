package tp

case class Vikingo(peso: Int, velocidad: Int, barbarosidad: Int, nivelDeHambre: Int, item: Item) extends Participante {
  def maximaCargaDePescado: Double = peso * 0.5 + barbarosidad * 2

  def puedeCargar(cantidadPescado: Int): Boolean =
    cantidadPescado <= maximaCargaDePescado

  def danio: Int = barbarosidad + item.danio

  def montar(dragon: Dragon): Participante =
    if (dragon.puedeSerMontadoPor(this)) Jinete(this, dragon) else this

}

case class Patapez(itemComestible: Comestible) extends Participante {

  override def maximaCargaDePescado: Double = ???

  override def nivelDeHambre: Int = ???
}

case class Jinete(vikingo: Vikingo, dragon: Dragon) extends Participante {
  def maximaCargaDePescado: Double = vikingo.peso - dragon.maximaCarga
  def danio: Int = vikingo.danio + dragon.danio
  def velocidad: Double = dragon.velocidad - vikingo.peso
  def nivelDeHambre: Int = vikingo.nivelDeHambre
}

trait Participante {
  def maximaCargaDePescado: Double
  def nivelDeHambre: Int
  def quedariaHambriento: Boolean = nivelDeHambre >= 100
  def participarEnPosta(posta: Posta): Unit = ???
}
