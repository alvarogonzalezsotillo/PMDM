package gonzalez.tarea07

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException
import kotlin.random.Random

private val s = "NUMBER_TO_GUESS"

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "TAREA7"
        private const val NUMBER_TO_GUESS = "NUMBER_TO_GUESS"
        private const val SCORE = "SCORE"
    }

    private val random = Random.Default

    private fun button() = findViewById<Button>(R.id.button)
    private fun numberInput() = findViewById<EditText>(R.id.numberInput)
    private fun scoreText() = findViewById<TextView>(R.id.scoreText)
    private fun randomInt() = random.nextInt(1, 20)
    private fun preferences() = getPreferences(Context.MODE_PRIVATE)

    private fun toast(msg: String) : Unit  {
        Log.d(TAG, "Toast:${msg}")
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun getNumberToGuess(): Int {
        if (preferences().contains(NUMBER_TO_GUESS)) {
            return preferences().getInt(NUMBER_TO_GUESS, -1)
        }
        return updateNumberToGuess()
    }

    private fun updateNumberToGuess(): Int {
        val number = randomInt()
        preferences()
            .edit()
            .putInt(NUMBER_TO_GUESS, number)
            .commit()
        Log.d(TAG, "El nuevo número a acertar es:${number}")
        return number;
    }

    private fun getScore() = preferences().getInt(SCORE, 0)
    private fun setScore(value: Int) = preferences()
        .edit()
        .putInt(SCORE, value).commit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)

        scoreText().text = getScore().toString()

        button().setOnClickListener {
            val text = numberInput().text.toString()
            try {
                val toGuess = getNumberToGuess()
                val number = Integer.parseInt(text)
                Log.d(TAG, "number:${number} to guess:${toGuess}")
                if (number == toGuess) {
                    toast("Has acertado el número! Prueba otra vez.")
                    setScore(getScore() + 1)
                    scoreText().text = scoreText().toString()
                    updateNumberToGuess()
                }
            } catch (e: NumberFormatException) {
                Log.d(TAG, "No es un número:${text}", e)
                toast("Introduce un número válido")
            }
        }
    }
}
