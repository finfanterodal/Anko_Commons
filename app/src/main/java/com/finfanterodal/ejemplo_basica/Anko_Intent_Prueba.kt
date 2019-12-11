package com.finfanterodal.ejemplo_basica

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_anko__intent__prueba.*


class Anko_Intent_Prueba : AppCompatActivity() {

    //Inicialización de variables
    var num1 = 0
    var num2 = 0
    var suma = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anko__intent__prueba)

        //Recogemos un valor del MainActivity y coloreamos un textview cambiando el texto según el dato recibido.
        var value = intent.getStringExtra("color")
        when (value) {
            "amarillo" -> {
                textResultado.setBackgroundResource(R.color.colorTextView2)
                textResultado.setText(value)
            }
            "rojo" -> {
                textResultado.setBackgroundResource(R.color.colorTextView)
                textResultado.setText(value)
            }
            else -> println("Number too high")
        }

        //Recogida de datos del MainActivity y posterior cálculo
        num1 = intent.getIntExtra("num1", 0)
        num2 = intent.getIntExtra("num2", 0)
        suma = num1 + num2
        textResultado.text = suma.toString()

        //Botón que cierra este Activity y devuelve el valor de la suma al MainActivitY
        bCerrar.setOnClickListener {
            val data = Intent()
            data.putExtra("suma", suma)
            setResult(Activity.RESULT_OK, data) //devolvemos valor
            finish()
        }
    }
}
