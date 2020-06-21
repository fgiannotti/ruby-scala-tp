package tp

import tp.CriterioAdmision.CriterioAdmision

object CriterioAdmision {
  type CriterioAdmision = Participante => Boolean
}

case class PesoMinimo(min: Int) extends CriterioAdmision {
  override def apply(participante: Participante): Boolean =
    participante.maximaCargaDePescado >= min
}

case class BarbaridadMinima(min: Int) extends CriterioAdmision {
  override def apply(participante: Participante): Boolean = participante.barbarosidad >= min
}

case class TieneArma() extends CriterioAdmision {
  override def apply(participante: Participante): Boolean = participante.item.isInstanceOf[Arma]
}

case class EsJinete() extends CriterioAdmision {
  override def apply(participante: Participante): Boolean = participante.isInstanceOf[Jinete]
}

