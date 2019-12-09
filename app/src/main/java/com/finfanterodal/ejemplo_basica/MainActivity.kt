package com.finfanterodal.ejemplo_basica

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import java.text.SimpleDateFormat
import java.util.*


//CONSTANTE para el intentForResult
const val SUMA_REQUEST = 1

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Intents
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }


        //Botones con diferentes funcionalidades para probar Anko
        botonFecha.setOnClickListener{
            val formato = SimpleDateFormat("HH:mm:ss")
            val fechaAtual = Calendar.getInstance().getTime()
            val s = formato.format(fechaAtual)
            textoFecha.setText(String.format("Botn: %s", s))
            longToast("hola")
            it.snackbar("Pedro Sánchez")

        }



        bRojo.setOnClickListener{
            startActivity(intentFor<Anko_Intent_Prueba>("color" to "rojo"))
        }

        bAmarillo.setOnClickListener{
            startActivity(intentFor<Anko_Intent_Prueba>("color" to "amarillo"))
        }

        bBrowser.setOnClickListener {
            browse("https://www.20minutos.es/")
        }

        //Botón para utilizar el ForResulta y hacer una suma
        bSuma.setOnClickListener {
            var num1 = num1Text.text
            var num2 = num2Text.text

            try {

                var n1 = Integer.parseInt(num1.toString())
                var n2 = Integer.parseInt(num2.toString())

                val intent = Intent(this, Anko_Intent_Prueba::class.java)
                intent.putExtra("num1", n1)
                intent.putExtra("num2", n2)

                startActivityForResult(intent, SUMA_REQUEST)


            } catch (nfe: NumberFormatException) {

            }
        }

        //COMROBACION SI TENEMOS PERMISO DE LLAMADA
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }



    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Método que se llama al cerrar la otra Activity que nos devuelve el valor de la suma, recoge el resultado del ForResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SUMA_REQUEST) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getIntExtra("suma", 0)
                textSuma.text = result.toString()
            }
        }
    }
}




