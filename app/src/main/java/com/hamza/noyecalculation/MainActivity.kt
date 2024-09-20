package com.hamza.noyecalculation

import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fun obtenirMention(moy: Float): String {
            return when {
                moy >= 16 -> "Excellent"
                moy >= 14 -> "Très bien"
                moy >= 12 -> "Bien"
                moy >= 10 -> "Assez bien"
                else -> "Échec"
            }
        }

        val btn : Button = findViewById(R.id.calculateButton)

        btn.setOnClickListener{

            val stg1 = findViewById<EditText>(R.id.noteInput1).text.toString()
            val stg2 = findViewById<EditText>(R.id.noteInput2).text.toString()
            val stg3 = findViewById<EditText>(R.id.noteInput3).text.toString()
            val stg4 = findViewById<EditText>(R.id.noteInput4).text.toString()

            if (stg1.isEmpty() || stg2.isEmpty() || stg3.isEmpty() || stg4.isEmpty()) {
                Toast.makeText(this, "Input is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val note1 = stg1.toFloat()
            val note2 = stg2.toFloat()
            val note3 = stg3.toFloat()
            val note4 = stg4.toFloat()

            if (note1 < 0 || note1 > 20) {
                Toast.makeText(this, "La note 1 doit etre compris 0 et 20", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (note2 < 0 || note2 > 20) {
                Toast.makeText(this, "La note 2 doit etre compris 0 et 20", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (note3 < 0 || note3 > 20) {
                Toast.makeText(this, "La note 3 doit etre compris 0 et 20", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (note4 < 0 || note4 > 20) {
                Toast.makeText(this, "La note 4 doit etre compris 0 et 20", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val moy = (note1 + note2 + note3 + note4) / 4
            val mention = obtenirMention(moy)

            val txt : TextView = findViewById(R.id.moyText)

            txt.text = "$moy\n$mention"
        }
    }
}