package ar.edu.unahur.obj2.semillas

abstract class Planta(var altura: Double, val anioSemilla: Int) {
    object Constantes{val UMBRAL_DE_HORAS = 7}
    open fun horasDeSolQueTolera() = Constantes.UMBRAL_DE_HORAS
    open fun esFuerte() = horasDeSolQueTolera()>9
    open fun daSemillas() = this.esFuerte()
    abstract fun espacio(): Double
}

open class Menta(altura: Double,  anioSemilla: Int) : Planta(altura, anioSemilla)
{
    override fun daSemillas() =  super.daSemillas() || altura > 0.4
    override fun espacio() = altura + 1.0
    fun esParcelaIdeal(unaParcela: Parcela) = unaParcela.superficie()>6.0
}

open class Soja( altura: Double, anioSemilla: Int) : Planta(altura, anioSemilla)
{
    override fun horasDeSolQueTolera(): Int  {
        return  when {
            altura < 0.5  -> 6
            altura < 1    -> 8
            else          -> 12
        }
    }
    override fun espacio() = altura/2
    override fun daSemillas() = super.esFuerte() or (this.semillaPosteriorA2007() && (altura>0.75 && altura<0.9))
    fun semillaPosteriorA2007() = this.anioSemilla > 2007
    open fun esParcelaIdeal(unaParcela: Parcela) = this.horasDeSolQueTolera() == unaParcela.horaDeSolPorDia
}
class Quinoa (altura: Double, anioSemilla: Int, val espacioQueOcupa: Double) : Planta(altura, anioSemilla){

    override fun espacio() = espacioQueOcupa
    override fun horasDeSolQueTolera(): Int {
        if (this.espacio() < 0.3){
            return 10
        }
        return Constantes.UMBRAL_DE_HORAS
    }

    override fun daSemillas() = super.daSemillas() or (this.anioSemilla in 2001..2008)
    fun esParcelaIdeal(unaParcela: Parcela) = unaParcela.plantas.all { it.altura < 1.5 }

}
class SojaTransgenica(altura: Double, anioSemilla: Int): Soja(altura, anioSemilla)
{
    override fun daSemillas() = false
    override fun esParcelaIdeal(unaParcela: Parcela) = unaParcela.cantidadMaximaDePlantas() == 1
}
class Peperina(altura: Double, anioSemilla: Int): Menta(altura, anioSemilla)
{
    override fun espacio() = super.espacio()*2
}

class Parcela(var ancho: Double,var largo: Double,var horaDeSolPorDia: Int , val plantas: MutableList<Planta>) {

    fun superficie() = ancho*largo
    fun cantidadMaximaDePlantas(): Int {
        var cantidadMaxima = this.superficie() / 3 + largo

        if (ancho>largo) {
            cantidadMaxima = this.superficie()/5
        }
        return cantidadMaxima.toInt()
    }
    fun tieneComplicaciones() = plantas.any {  it.horasDeSolQueTolera() < this.horaDeSolPorDia }
    fun plantarUnaPlanta(planta: Planta) {

        if (this.alcanzoCantidadMaxima()) {
            throw Exception("Ya no hay lugar en esta parcela")
        } else {
            plantas.add(planta)
        }
    }
    fun cantidadPlantas() = plantas.size
    fun espacioDisponible() = this.cantidadMaximaDePlantas() - this.cantidadPlantas()
    fun alcanzoCantidadMaxima() = espacioDisponible() == 0
}





