package pe.area51.clasekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_formulario.*
import java.util.ArrayList

class FormularioActivity : AppCompatActivity() {

    companion object {
        val lista = ArrayList<Producto>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
    }

    override fun onStart() {
        super.onStart()
        tvValor.text = intent.getStringExtra("valor1")
        val adapter = ProductoAdapter(this@FormularioActivity, lista)
        lvlista.adapter =adapter
    }

    override fun onResume() {
        super.onResume()
        btnAgregar.setOnClickListener {
            val intent = Intent (this@FormularioActivity,RegistroActivity::class.java)
            startActivity(intent);
        }
    }
}
