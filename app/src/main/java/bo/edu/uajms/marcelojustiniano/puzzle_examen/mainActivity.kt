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
    private lateinit var tablero: Array<Array<String>>
    //Otras variables
    private var rows=4;
    private var cols=4;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymain)


    }
}