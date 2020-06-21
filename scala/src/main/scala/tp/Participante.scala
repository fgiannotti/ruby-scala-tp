package tp

case class Vikingo(peso: Int, velocidad: Double, barbarosidad: Int, nivelDeHambre: Int, item: Item) extends Participante {
  def maximaCargaDePescado: Double = peso * 0.5 + barbarosidad * 2

  def puedeCargar(cantidadPescado: Int): Boolean =
    cantidadPescado <= maximaCargaDePescado

  def danio: Int = barbarosidad + item.danio

  def montar(dragon: Dragon): Participante =
    if (dragon.puedeSerMontadoPor(this)) Jinete(this, dragon) else this

  def participarEnPosta(posta: Posta): Participante = aumentarHambre(posta.costoParticipacion)

  def aumentarHambre(porcentaje: Int): Vikingo = copy(nivelDeHambre = nivelDeHambre + porcentaje)

  def mejorMontura(dragones: List[Dragon], posta: Posta): Option[Dragon] = {
    posta.hacerParticipar(dragones.filter(_.puedeSerMontadoPor(this)).map(montar) :+ this).head match {
      case Jinete(_, dragon) => Some(dragon)
      case _ => None
    }
  }

}

case object Patapez extends Participante {

  override def maximaCargaDePescado: Double = ???

  var nivelDeHambre: Int = 0

  override def peso: Int = ???

  override def velocidad: Double = ???

  override def barbarosidad: Int = ???

  override def item: Item = Comestible(???)

  override def danio: Int = ???

  def participarEnPosta(posta: Posta): Participante = aumentarHambre(posta.costoParticipacion * 2)

  def aumentarHambre(porcentaje: Int): Participante = {
    nivelDeHambre = nivelDeHambre + porcentaje
    this
  }
}

case class Jinete(vikingo: Vikingo, dragon: Dragon) extends Participante {
  def maximaCargaDePescado: Double = vikingo.peso - dragon.maximaCarga

  override def danio: Int = vikingo.danio + dragon.danio

  override def velocidad: Double = dragon.velocidad - peso

  override def peso: Int = vikingo.peso

  override def nivelDeHambre: Int = vikingo.nivelDeHambre

  override def barbarosidad: Int = vikingo.barbarosidad

  override def item: Item = vikingo.item

  override def participarEnPosta(posta: Posta): Participante = copy(vikingo.aumentarHambre(5))
}

trait Participante {
  def peso: Int

  def velocidad: Double

  def barbarosidad: Int

  def maximaCargaDePescado: Double

  def nivelDeHambre: Int

  def quedariaHambriento: Boolean = nivelDeHambre >= 100

  def item: Item

  def danio: Int

  def esMejorQue(participante: Participante)(posta: Posta): Boolean = {
    if (posta.compararParticipantes(this, participante) == this) true else false
  }

  def participarEnPosta(posta: Posta): Participante
}
