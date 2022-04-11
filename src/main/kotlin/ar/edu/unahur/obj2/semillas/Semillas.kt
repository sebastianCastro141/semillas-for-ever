package ar.edu.unahur.obj2.semillas

abstract class Planta(var altura: Double, val anioSemilla: Int) {
    open fun horasDeSolQueTolera() = 7
    fun esFuerte() = horasDeSolQueTolera()>9
    open fun daSemillas() = this.esFuerte()
    abstract fun espacio(): Double
}

class Menta(altura: Double,  anioSemilla: Int) : Planta(altura, anioSemilla)
{
    override fun daSemillas() =  super.daSemillas() || altura > 0.4
    override fun espacio() = altura + 1.0
}

class Soja( altura: Double, anioSemilla: Int) : Planta(altura, anioSemilla)
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



