package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec ({
    // hay una clase Planta que tiene por atributos
    // anioSemilla, altura
    val menta = Menta(1.0, 2021)
    val mentita = Menta(0.3, 2021)
    val soja = Soja(0.6, 2009)
    val quinoa = Quinoa(0.2, 2010, 1.0)
    val quinoaGrande = Quinoa(0.9, 2006, 0.2)

    val sojaAlta = Soja(1.5, 2010)
    val plantasParcela1 = mutableListOf<Planta>(sojaAlta, sojaAlta, sojaAlta, sojaAlta)

    //Agregando tests
    val parcela1 = ParcelaEcologica(20.0, 1.0, 10, plantasParcela1)

    describe("Creación de las plantas") {

        it("probamos los atributos altura  y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioSemilla.shouldBe(2021)
        }

        it("verificar si da semillas") {
            menta.daSemillas().shouldBeTrue()
            mentita.daSemillas().shouldBeFalse()
            soja.daSemillas().shouldBeFalse()
            quinoa.daSemillas().shouldBeFalse()
            quinoaGrande.daSemillas().shouldBeTrue()
        }

        it("es fuerte") {
            menta.esFuerte().shouldBeFalse()
            soja.esFuerte().shouldBeFalse()
            quinoa.esFuerte().shouldBeFalse()
            quinoaGrande.esFuerte().shouldBeTrue()
        }

        it("espacio") {
            menta.espacio().shouldBe(2.0)
            mentita.espacio().shouldBe(1.3)
            soja.espacio().shouldBe(0.3)
        }

        it("verifico la suma de varias") {
            val superficie = mutableListOf(
                soja.espacio(),
                menta.espacio(),
                mentita.espacio()
            ).sum()
            Math.ceil(superficie).shouldBe(4)
        }
    }

    //Agregando tests
    describe("Parcelas") {
        it("Superficie") {
            parcela1.superficie().shouldBe(20.0)
        }

        it("Máximo de plantas") {
            parcela1.cantidadMaxima().shouldBe(4)
        }

        it("tiene Complicaciones este se da si alguna de sus plantas tolera menos sol del que recibe la parcela") {
            parcela1.tieneComplicaciones().shouldBeFalse()
        }

        //it("Intentamos plantar otra planta") {
        //    parcela1.plantar(menta).sh
        //}

        it("Parcelas ideales") {
            menta.esParcelaIdeal(parcela1).shouldBeTrue()
            soja.esParcelaIdeal(parcela1).shouldBeFalse()
        }
    }

    describe("INTA") {
        val parcela2 = ParcelaEcologica(25.0, 10.0, 7, mutableListOf(menta, menta, menta, sojaAlta))
        val parcela3 = ParcelaIndustrial(2.0, 2.5, 12, mutableListOf(menta, sojaAlta))

        INTA.agregarParcela(parcela2)
        INTA.agregarParcela(parcela3)

        it("Promedio este se calcula como la suma de plantas que hay en cada parcela dividido por la cantidad de parcelas que existen") {
            INTA.promedioDePlantas().shouldBe(3)
        }

        it("Parcela más autosustentable es la que tiene mas de 4 plantas y la que tenga mayor porcentaje de plantas bien asociadas") {
            INTA.parcelaMasAutosustentable().shouldBe(parcela2)
        }
    }
})