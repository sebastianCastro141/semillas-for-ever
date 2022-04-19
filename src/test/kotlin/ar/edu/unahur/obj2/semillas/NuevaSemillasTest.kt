package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrowMessage
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class NuevaSemillasTest : DescribeSpec
    ({
        describe("Creo variables necesarias")
        {
            val menta = Menta(1.0, 2021)
            val mentita = Menta(0.3, 2021)
            val soja = Soja(0.6, 2009)
            val sojaAltaFuerte = Soja(1.1,2009)
            val sojaFuerte = Soja(0.8, 2009)
            val sojaNoCumple = Soja(0.5, 1999)
            val quinoaEsFuerte = Quinoa(1.0, 2010, 0.2)
            val quinoaAnioDeObtencion = Quinoa(1.0, 2006, 0.9)
            val quinoaNoEsFuerte = Quinoa(0.8, 1900, 0.5)
            val sojaTransgenica = SojaTransgenica(0.6, 2009)
            val sojaTransgenicaSoporta12Hs = SojaTransgenica(1.2, 2009)
            val peperina = Peperina(0.3, 2021)


            describe("Probando metodos de la menta")
            {
                describe("metodo da semillas")
                {
                    it("Si da semillas, por que la menta tiene mas de 0.4 de altura")
                    {
                        menta.daSemillas().shouldBeTrue()
                    }
                    it("No da semillas, por que la menta tiene menos de 0.4 de altura")
                    {
                        mentita.daSemillas().shouldBeFalse()
                    }
                }
                describe("metodo esFuerte")
                {
                    it("Si es fuerte, por que la menta tiene mas de 0.4 de altura")
                    {
                        menta.daSemillas().shouldBeTrue()
                    }
                    it("No no es fuerte, por que la menta tiene menos de 0.4 de altura y tolera menos de 9hs de sol")
                    {
                        mentita.daSemillas().shouldBeFalse()
                    }
                }
                describe("metodo espacio")
                {
                    it("Espacio que ocupa la menta de mas de 0.4 de altura")
                    {
                        menta.espacio().shouldBe(2.0)
                    }
                    it("Espacio que ocupa la menta de menos de 0.4 de altura")
                    {
                        mentita.espacio().shouldBe(1.3)
                    }
                }

            }
            describe("Probando metodos de la soja")
            {
                describe("metodo da semillas")
                {
                    it("Si da semillas, por que miden mas de un metro, por ende son fuertes")
                    {
                        sojaAltaFuerte.daSemillas().shouldBeTrue()
                    }
                    it("Si da semillas, por que a pesar de no ser fuerte por su altura, esta esta entre 0.75-0.9 y es posterior a 2007")
                    {
                        sojaFuerte.daSemillas().shouldBeTrue()
                    }
                    it("No da semillas, por que la soja no esta entre 0.75 y 0.9, por mas de que su anio de obtencionsea posterior")
                    {
                        soja.daSemillas().shouldBeFalse()
                    }
                }
                describe("metodo esFuerte")
                {
                    it("Si es fuerte, por que mide mas de 1 metro y su anio de obtencion es posterior a 2007")
                    {
                        menta.daSemillas().shouldBeTrue()
                    }
                    it("No no es fuerte, por que mide menos de 0.75, ademas se obtuvo antes del 2007 y no tolera mas de 9hs")
                    {
                        soja.daSemillas().shouldBeFalse()
                    }
                }
                describe("metodo espacio")
                {
                    it("Espacio que ocupa la menta de mas de 0.4 de altura")
                    {
                        soja.espacio().shouldBe(0.3)
                    }
                    it("Espacio que ocupa la menta de menos de 0.4 de altura")
                    {
                        sojaFuerte.espacio().shouldBe(0.4)
                    }
                }
            }
            describe("Probando metodos de la quinoa")
            {
                describe("metodo da semillas")
                {
                    it("Si da semillas, por que el anio de obtencion esta entre 2001 y 2008")
                    {
                        quinoaAnioDeObtencion.daSemillas().shouldBeTrue()
                    }
                    it("Si da semillas, por que cumple con ser fuerte, por las horas que tolera")
                    {
                        sojaFuerte.daSemillas().shouldBeTrue()
                    }
                    it("No da semillas, por que mide mas de 0.3 y ademas se obtuvo en 1999")
                    {
                        sojaNoCumple.daSemillas().shouldBeFalse()
                    }
                }
                describe("metodo esFuerte")
                {
                    it("Si es fuerte, por que el espacio que ocupa es menos de 0.3 y tolera la horas de sol suficiente")
                    {
                        quinoaEsFuerte.daSemillas().shouldBeTrue()
                    }
                    it("No no es fuerte, por que el espacio que ocupa es mayor a 0.3")
                    {
                        quinoaNoEsFuerte.daSemillas().shouldBeFalse()
                    }
                }
                ////////////////////////////////////////////////
                describe("metodo espacio")
                {
                    it("Espacio que ocupa la menta de mas de 0.4 de altura")
                    {
                        quinoaEsFuerte.espacio().shouldBe(0.2)
                    }

                }
            }
            describe("Probando los metodos de la soja transgenica")
            {
                describe("metodo da semillas") {
                    it("La soja transgenica nunca da semillas") {
                        sojaTransgenica.daSemillas().shouldBeFalse()
                    }
                    it("No da semillas ni aunque se cumpla la condicion general"){
                        sojaTransgenicaSoporta12Hs.daSemillas().shouldBeFalse()
                    }
                }
            }
            describe("Probando los metodos de la peperina")
            {
                describe("metodo espacio") {
                    it("Espacio que ocupa la peperina") {
                        peperina.espacio().shouldBe(2.6)
                    }

                }
            }
            describe("Probando metodos de la parcela"){

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