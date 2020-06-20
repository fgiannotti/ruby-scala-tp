package tp

trait Item {
  def danio = 0
}

case class Arma(override val danio: Int) extends Item

case object SistemaDeVuelo extends Item

case class Comestible(porcentajeSaciedad: Int) extends Item

object Patapez extends Vikingo(23, 50, 3, 55, Comestible(porcentajeSaciedad = 6))
