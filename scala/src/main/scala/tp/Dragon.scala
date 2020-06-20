package tp

import tp.Requisito.Requisito

case class Dragon(velocidadBase: Int = 60, raza: Raza, requisitosExtra: List[Requisito]) {
  def velocidad: Double = raza.velocidad(velocidadBase)

  def danio: Int = raza.danio

  def maximaCarga: Double = raza.peso * 0.2

  def puedeSerMontadoPor(vikingo: Vikingo): Boolean = {
    Requisito.PuedeCargarSuPeso().apply(vikingo, this) &&
      requisitosExtra.forall(_.apply(vikingo, this)) &&
      raza.requisitos.forall(_.apply(vikingo, this))
  }
}

trait Raza {
  def peso: Int

  def danio: Int

  def velocidad(velocidadBase: Int): Double

  def requisitos: List[Requisito]
}

case class NadderMortifero(peso: Int, requisitos: List[Requisito]) extends Raza {
  def velocidad(velocidadBase: Int): Double = velocidadBase - peso

  def danio = 150
}

case class FuriaNocturna(peso: Int, danio: Int, requisitos: List[Requisito]) extends Raza {
  def velocidad(velocidadBase: Int): Double = 3 * (velocidadBase - peso)
}

case class Gronkle(peso: Int, requisitos: List[Requisito]) extends Raza {
  def velocidad(velocidadBase: Int): Double = (velocidadBase / 2) - peso

  def danio: Int = peso * 5
}
