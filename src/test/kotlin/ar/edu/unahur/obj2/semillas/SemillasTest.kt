package ar.edu.unahur.obj2.semillas



import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec ({
    // hay una clase Planta que tiene por atributos
    // anioSemilla, altura
    describe("Creación de las plantas") {
        /*si tenemos una quinoa que ocupa 0.2 m2 y su semilla de origen es
        de 2010, se trata de una planta que da semillas.
        si tenemos una planta que ocupa 0.9 m2 pero cuya semilla de origen es de 2006,
        también da semillas.*/
        val menta = Menta(1.0, 2021)
        val mentita = Menta(0.3, 2021)
        val soja = Soja(0.6, 2009)
        val quinoaEsFuerte = Quinoa(1.0, 2010, 0.2)
        val quinoaAnioDeObtencion = Quinoa(1.0, 2006, 0.9)
        val sojaTransgenica = SojaTransgenica(0.6, 2009)
        val peperina = Peperina(0.3, 2021)

        it("probamos los atributos altura  y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioSemilla.shouldBe(2021)
        }

        it("verificar si da semillas") {
            menta.daSemillas().shouldBeTrue()
            mentita.daSemillas().shouldBeFalse()
            soja.daSemillas().shouldBeFalse()
            quinoaEsFuerte.daSemillas().shouldBeTrue()
            quinoaAnioDeObtencion.daSemillas().shouldBeTrue()
            sojaTransgenica.daSemillas().shouldBeFalse()
        }

        it("es fuerte") {
            menta.esFuerte().shouldBeFalse()
            soja.esFuerte().shouldBeFalse()
            quinoaEsFuerte.esFuerte().shouldBeTrue()
            quinoaAnioDeObtencion.esFuerte().shouldBeFalse()
            sojaTransgenica.esFuerte().shouldBeFalse()
        }

        it("espacio") {
            menta.espacio().shouldBe(2.0)
            mentita.espacio().shouldBe(1.3)
            soja.espacio().shouldBe(0.3)
            peperina.espacio().shouldBe(2.6)
            sojaTransgenica.espacio().shouldBe(0.3)
        }
        it("verifico la suma de varias") {
            val superficie = mutableListOf(
                soja.espacio(),
                menta.espacio(),
                mentita.espacio()
            ).sum()
            Math.ceil(superficie).shouldBe(4)
        }
        describe("Parcela"){
            /*Una parcela de 20 metros de ancho por 1 metro de largo que recibe 10 horas de sol por día,
            tiene una superficie de 20 metros cuadrados y la cantidad máxima de plantas que tolera es 4.
            Si a esa parcela le plantamos 4 plantas de soja de más de 1 metro (que toleran 12 horas de sol),
            no tendría complicaciones. Si intentaramos agregar una quinta planta, se superaría la cantidad máxima y
            nos arrojaría un error.*/
            val parcela = Parcela(20.0,1.0,10, mutableListOf<Planta>())
            val sojaParaParcela = Soja(1.2,2011)
            val plantaQueSuperaLaCantidadMax = Menta(0.3,2012)

            parcela.plantarUnaPlanta(sojaParaParcela)
            parcela.plantarUnaPlanta(sojaParaParcela)
            parcela.plantarUnaPlanta(sojaParaParcela)
            parcela.plantarUnaPlanta(sojaParaParcela)

            it("Superficie")
            {
                parcela.superficie().shouldBe(20)
            }
            it("Cantidad maxima de plantas")
            {
                parcela.cantidadMaximaDePlantas().shouldBe(4)
            }
            it("complicaciones")
            {
                parcela.tieneComplicaciones().shouldBeFalse()
            }


        }
    }
})