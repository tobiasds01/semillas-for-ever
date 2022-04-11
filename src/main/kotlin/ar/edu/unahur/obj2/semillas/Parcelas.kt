package ar.edu.unahur.obj2.semillas

//3)
class Parcelas(var ancho: Double, var largo: Double, var horasDeSolDiarias: Int, var plantas: MutableSet<Planta>) {
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
}