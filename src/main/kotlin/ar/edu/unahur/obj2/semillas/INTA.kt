package ar.edu.unahur.obj2.semillas

object INTA {
    val parcelas = mutableListOf<Parcela>()

    fun agregarParcela(parcelaNueva: Parcela) {
        parcelas.add(parcelaNueva)
    }

    fun promedioDePlantas(): Int {
        return parcelas.sumBy { it.plantas.size } / parcelas.size
    }

    fun parcelaMasAutosustentable(): Parcela? {
        return parcelas.filter{it.plantas.size >= 4}.maxByOrNull{ unaParcela -> unaParcela.plantas.count{ unaParcela.seAsociaBien(it) } / unaParcela.plantas.size }
    }
}