package tp

import tp.Requisito.Requisito

object Requisito {
  type Requisito = (Vikingo, Dragon) => Boolean
}

case class TengaItem(item: Item) extends Requisito {
  override def apply(vikingo: Vikingo, dragon: Dragon): Boolean = {
    vikingo.item.equals(item)
  }
}

case class SuficienteBarbaridad(minimo: Int) extends Requisito {
  override def apply(vikingo: Vikingo, dragon: Dragon): Boolean = {
    vikingo.barbarosidad >= minimo
  }
}

case class PuedeCargarSuPeso() extends Requisito {
  override def apply(vikingo: Vikingo, dragon: Dragon): Boolean = {
    dragon.maximaCarga >= vikingo.peso
  }
}

case class NoSuperaPesoMaximo(maximo:Int) extends Requisito{
  override def apply(vikingo: Vikingo, dragon: Dragon): Boolean = vikingo.peso <= maximo
}