package pe.area51.clasekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        btn_Calcular.setOnClickListener {
            val valor1 = tvValor1.text.toString()
            val valor2 = tvValor2.text.toString()

            /*
            Toast.makeText( this@MainActivity,
                "$valor1 - $valor2",
                Toast.LENGTH_SHORT).show()
            */

            if(valor1.isEmpty()){
                tvValor1.error = "El campo es requerido en el valor 1"
                return@setOnClickListener
            }else{
                tvValor1.error = null
            }

            if(valor2.isEmpty()){
                tvValor2.error = "El campo es requerido en el valor 2"
                return@setOnClickListener
            }else{
                tvValor2.error = null
            }

            try {
                val Valor1Entero = valor1.toInt()
                val Valor2Entero = valor2.toInt()

                val total = Valor1Entero + Valor2Entero

                Toast.makeText( this@MainActivity,
                    "Total = $total",
                    Toast.LENGTH_SHORT).show()

            }catch ( e: Exception){
                Toast.makeText( this@MainActivity,
                    "El texto ingresado no es numero",
                    Toast.LENGTH_SHORT).show()
            }
        }

        btn_Enviar.setOnClickListener {
            val intent = Intent(this@MainActivity,FormularioActivity::class.java)
            val valor1 = tvValor1.text.toString()
            intent.putExtra("valor1", valor1)
            startActivity(intent)
        }
    }
}
