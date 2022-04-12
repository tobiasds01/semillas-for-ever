package ar.edu.unahur.obj2.semillas

//1) y 4)
abstract class Planta(var altura: Double, val anioSemilla: Int) {
    open fun esFuerte(): Boolean { return horasDeSolToleradas() > 9 }

    open fun horasDeSolToleradas(): Int { return 7 }

    abstract fun esParcelaIdeal(parcela: Parcela): Boolean
}

open class Menta(altura: Double, anioSemilla: Int): Planta(altura, anioSemilla) {
    open fun espacio(): Double {
        return altura + 1.0
    }

    fun daSemillas(): Boolean {
        return altura > 0.4
    }

    override fun esParcelaIdeal(parcela: Parcela): Boolean { return parcela.superficie() > 6 }
}

open class Soja(altura: Double, anioSemilla: Int): Planta(altura, anioSemilla) {
    fun espacio(): Double {
        return altura / 2
    }

    open fun daSemillas(): Boolean {
        return anioSemilla > 2007 && (altura in 0.75..0.9)
    }

    override fun horasDeSolToleradas(): Int {
        return if (altura < 0.5){6} else if (altura < 1.0){8} else {12}
    }

    override fun esParcelaIdeal(parcela: Parcela): Boolean { return parcela.horasDeSolDiarias == this.horasDeSolToleradas() }
}

class Quinoa(altura: Double, anioSemilla: Int, var espacio: Double): Planta(altura, anioSemilla) {
    fun espacio(): Double { return espacio }

    override fun horasDeSolToleradas(): Int {
        return if(espacio < 0.3){10} else super.horasDeSolToleradas()
    }

    fun daSemillas(): Boolean {
        return anioSemilla in 2001..2008
    }

    override fun esParcelaIdeal(parcela: Parcela): Boolean { return parcela.plantas.all { it.altura < 1.5 } }
}

// 2)
class SojaTransgenica(altura: Double, anioSemilla: Int): Soja(altura, anioSemilla) {
    override fun daSemillas(): Boolean { return false }

    override fun esParcelaIdeal(parcela: Parcela): Boolean { return parcela.cantidadMaxima() == 1 }
}

class Peperina(altura: Double, anioSemilla: Int): Menta(altura, anioSemilla){
    override fun espacio(): Double { return super.espacio() * 2 }
}