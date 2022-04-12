package ar.edu.unahur.obj2.semillas

//3)
abstract class Parcela(var ancho: Double, var largo: Double, var horasDeSolDiarias: Int, var plantas: MutableList<Planta>) {
    fun superficie(): Double { return ancho * largo }

    fun cantidadMaxima(): Int {
        return if(ancho > largo) {(this.superficie() / 5).toInt()}
                else { (this.superficie() / 3 + largo).toInt() }
    }

    fun tieneComplicaciones(): Boolean {
        return plantas.any { it.horasDeSolToleradas() < horasDeSolDiarias }
    }

    fun plantar(planta: Planta) {
        if (plantas.size == cantidadMaxima() || planta.horasDeSolToleradas() + 2.0 <= horasDeSolDiarias) {
            error("No se puede plantar esa planta en esta parcela")
        }
        else {
            plantas.add(planta)
        }
    }

    abstract fun seAsociaBien(planta: Planta): Boolean
}

//5)
class ParcelaEcologica(ancho: Double, largo: Double, horasDeSolDiarias: Int, plantas: MutableList<Planta>): Parcela(ancho, largo, horasDeSolDiarias, plantas) {
    override fun seAsociaBien(planta: Planta): Boolean { return this.tieneComplicaciones() && planta.esParcelaIdeal(this) }
}

class ParcelaIndustrial(ancho: Double, largo: Double, horasDeSolDiarias: Int, plantas: MutableList<Planta>): Parcela(ancho, largo, horasDeSolDiarias, plantas) {
    override fun seAsociaBien(planta: Planta): Boolean { return planta.esFuerte() && this.plantas.size <= 2 }
}