
trait Item {
  def danio = 0
}

case class Arma(override val danio: Int) extends Item

case class SistemaDeVuelo() extends Item

case class Comestible(override val danio: Int = 0, porcentajeSaciedad: Int) extends Item

object Patapez extends Vikingo(23, 50, 3, 55, Comestible(porcentajeSaciedad = 6))
