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
    //Otras variables
    private var rows=4;
    private var cols=4;

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

    }
}