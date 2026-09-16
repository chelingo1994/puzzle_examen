package bo.edu.uajms.marcelojustiniano.puzzle_examen

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class mainActivity : AppCompatActivity() {
    // Controles
    private lateinit var BTNtablero: Array<Button>
    private lateinit var btnRestart: Button
    private lateinit var btnRandom: Button
    private lateinit var btnVerify: Button
    private lateinit var TXVMessage: TextView


    private var rows = 4
    private var cols = 4
    private var posVacia = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymain)

        BTNtablero = arrayOf(
            findViewById(R.id.btn01), findViewById(R.id.btn02), findViewById(R.id.btn03), findViewById(R.id.btn04),
            findViewById(R.id.btn05), findViewById(R.id.btn06), findViewById(R.id.btn07), findViewById(R.id.btn08),
            findViewById(R.id.btn09), findViewById(R.id.btn10), findViewById(R.id.btn11), findViewById(R.id.btn12),
            findViewById(R.id.btn13), findViewById(R.id.btn14), findViewById(R.id.btn15), findViewById(R.id.btn16)
        )

        TXVMessage = findViewById(R.id.txtVMessage)
        btnRestart = findViewById(R.id.btnRestart)
        btnRandom = findViewById(R.id.btnRandomize)
        btnVerify = findViewById(R.id.btnVerify)


        llenarEspiralPlano(BTNtablero)


        for (i in BTNtablero.indices) {
            BTNtablero[i].setOnClickListener {
                moverFicha(i)
            }
        }

        btnRestart.setOnClickListener { llenarEspiralPlano(BTNtablero) }
        btnRandom.setOnClickListener { desordenar() }
        btnVerify.setOnClickListener { verificar() }
    }

    private fun llenarEspiralPlano(tablero: Array<Button>) {
        var valor = 1
        var inicioFila = 0
        var finFila = 3
        var inicioColumna = 0
        var finColumna = 3

        TXVMessage.text = ""

        while (inicioFila <= finFila && inicioColumna <= finColumna) {


            for (col in inicioColumna..finColumna) {
                val indicePlano = (inicioFila * 4) + col
                asignarValorCasilla(tablero[indicePlano], valor++)
            }
            inicioFila++


            for (fil in inicioFila..finFila) {
                val indicePlano = (fil * 4) + finColumna
                asignarValorCasilla(tablero[indicePlano], valor++)
            }
            finColumna--


            if (inicioFila <= finFila) {
                for (col in finColumna downTo inicioColumna) {
                    val indicePlano = (finFila * 4) + col
                    asignarValorCasilla(tablero[indicePlano], valor++)
                }
                finFila--
            }


            if (inicioColumna <= finColumna) {
                for (fil in finFila downTo inicioFila) {
                    val indicePlano = (fil * 4) + inicioColumna
                    asignarValorCasilla(tablero[indicePlano], valor++)
                }
                inicioColumna++
            }
        }
    }


    private fun asignarValorCasilla(boton: Button, valor: Int) {
        if (valor <= 15) {
            boton.text = valor.toString()
        } else {
            boton.text = ""
            // Buscamos cuál es la posición de este botón en el arreglo global para actualizar el hueco
            posVacia = BTNtablero.indexOf(boton)
        }
    }

    private fun fila(pos: Int) = pos / cols
    private fun columna(pos: Int) = pos % cols

    private fun esAdyacente(pos: Int): Boolean {
        val mismaFila = fila(pos) == fila(posVacia)
        val mismaColumna = columna(pos) == columna(posVacia)
        val distancia = Math.abs(pos - posVacia)

        val esVecinoHorizontal = mismaFila && distancia == 1
        val esVecinoVertical = mismaColumna && distancia == cols
        return esVecinoHorizontal || esVecinoVertical
    }

    private fun moverFicha(pos: Int) {
        if (esAdyacente(pos)) {
            BTNtablero[posVacia].text = BTNtablero[pos].text
            BTNtablero[pos].text = ""
            posVacia = pos
        }
    }

    private fun desordenar() {
        repeat(200) {
            val vecinos = obtenerVecinos(posVacia)
            if (vecinos.isNotEmpty()) {
                moverFicha(vecinos.random())
            }
        }
        TXVMessage.text = "Completado"
    }

    private fun obtenerVecinos(pos: Int): List<Int> {
        val lista = mutableListOf<Int>()
        val f = fila(pos)
        val c = columna(pos)

        if (c > 0) lista.add(pos - 1)            // izquierda
        if (c < cols - 1) lista.add(pos + 1)     // derecha
        if (f > 0) lista.add(pos - cols)         // arriba
        if (f < rows - 1) lista.add(pos + cols)  // abajo

        return lista
    }


    private fun verificar() {
        var valorEsperado = 1
        var inicioFila = 0
        var finFila = 3
        var inicioColumna = 0
        var finColumna = 3

        while (inicioFila <= finFila && inicioColumna <= finColumna) {

            for (col in inicioColumna..finColumna) {
                if (!validarCasilla((inicioFila * 4) + col, valorEsperado++)) return
            }
            inicioFila++


            for (fil in inicioFila..finFila) {
                if (!validarCasilla((fil * 4) + finColumna, valorEsperado++)) return
            }
            finColumna--


            if (inicioFila <= finFila) {
                for (col in finColumna downTo inicioColumna) {
                    if (!validarCasilla((finFila * 4) + col, valorEsperado++)) return
                }
                finFila--
            }


            if (inicioColumna <= finColumna) {
                for (fil in finFila downTo inicioFila) {
                    if (!validarCasilla((fil * 4) + inicioColumna, valorEsperado++)) return
                }
                inicioColumna++
            }
        }
        TXVMessage.text = "Juego Ordenado"
    }

    private fun validarCasilla(indicePlano: Int, valorEsperado: Int): Boolean {
        val textoActual = BTNtablero[indicePlano].text.toString()
        if (valorEsperado <= 15) {
            if (textoActual != valorEsperado.toString()) {
                TXVMessage.text = "Juego Desordenado"
                return false
            }
        } else {
            if (textoActual.isNotEmpty()) {
                TXVMessage.text = "Juego Desordenado"
                return false
            }
        }
        return true
    }
}
