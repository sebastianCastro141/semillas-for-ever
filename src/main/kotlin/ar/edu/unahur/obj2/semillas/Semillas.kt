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

}
class SojaTransgenica(altura: Double, anioSemilla: Int): Soja(altura, anioSemilla)
{
    override fun daSemillas() = false
}
class Peperina(altura: Double, anioSemilla: Int): Menta(altura, anioSemilla)
{
    override fun espacio() = super.espacio()*2
}




