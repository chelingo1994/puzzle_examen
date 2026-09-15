package bo.edu.uajms.marcelojustiniano.puzzle_examen
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class mainActivity:AppCompatActivity(){
    //Variables
    //Controles
    private lateinit var BTNtablero: Array<Button>
    private lateinit var btnRestart: Button
    private lateinit var btnRandom: Button
    private lateinit var btnVerify: Button
    private lateinit var tablero: Array<Array<String>>
    private lateinit var TXVMessage: TextView
    //Otras variables
    private var rows=4;
    private var cols=4;
    private var posVacia = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymain)
        BTNtablero=arrayOf(
            findViewById(R.id.btn01),
            findViewById(R.id.btn02),
            findViewById(R.id.btn03),
            findViewById(R.id.btn04),
            findViewById(R.id.btn05),
            findViewById(R.id.btn06),
            findViewById(R.id.btn07),
            findViewById(R.id.btn08),
            findViewById(R.id.btn09),
            findViewById(R.id.btn10),
            findViewById(R.id.btn11),
            findViewById(R.id.btn12),
            findViewById(R.id.btn13),
            findViewById(R.id.btn14),
            findViewById(R.id.btn15),
            findViewById(R.id.btn16)

        )
        TXVMessage=findViewById(R.id.txtVMessage)
        btnRestart=findViewById(R.id.btnRestart)
        btnRandom=findViewById(R.id.btnRandomize)
        btnVerify=findViewById(R.id.btnVerify)
        tablero= Array(rows){ Array(cols){""} }
        var numero = 1

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (numero <= 15) {
                    tablero[i][j] = numero.toString()
                    numero++
                } else {
                    tablero[i][j] = ""
                }
            }
        }
        var posicion = 0

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                BTNtablero[posicion].text = tablero[i][j]
                posicion++
            }
        }


        // Un mismo listener sirve para las 16 fichas: cada una "sabe" su posición (i)
        for (i in BTNtablero.indices) {
            BTNtablero[i].setOnClickListener {
                moverFicha(i)
            }
        }


    }



    // El tablero es un array de 16 casillas (0..15), pero se ve como una grilla 4x4.
    // Estas dos funciones traducen una posición del array a fila/columna.
    private fun fila(pos: Int) = pos / cols
    private fun columna(pos: Int) = pos % cols

    // ¿La casilla "pos" está pegada al hueco vacío? (arriba, abajo, izquierda o derecha)
    private fun esAdyacente(pos: Int): Boolean {
        val mismaFila = fila(pos) == fila(posVacia)
        val mismaColumna = columna(pos) == columna(posVacia)
        val distancia = Math.abs(pos - posVacia)

        val esVecinoHorizontal = mismaFila && distancia == 1      // izquierda/derecha
        val esVecinoVertical = mismaColumna && distancia == cols  // arriba/abajo (salta 4 en 4)
        return esVecinoHorizontal || esVecinoVertical
    }

    // Se ejecuta al tocar la ficha en "pos"
    private fun moverFicha(pos: Int) {
        if (esAdyacente(pos)) {
            // El número de la ficha tocada "salta" al hueco, y la ficha tocada queda vacía
            BTNtablero[posVacia].text = BTNtablero[pos].text
            BTNtablero[pos].text = ""
            posVacia = pos
        }
    }


}