package ar.edu.unahur.obj2.semillas



import io.kotest.assertions.throwables.shouldThrowMessage
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec ({

    describe("Creación de las plantas") {

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
            it("supera la cantidad maxima"){
                shouldThrowMessage("Ya no hay lugar en esta parcela"){parcela.plantarUnaPlanta(plantaQueSuperaLaCantidadMax)}
            }


        }
    }
})