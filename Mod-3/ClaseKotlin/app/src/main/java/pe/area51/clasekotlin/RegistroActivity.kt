package pe.area51.clasekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val producto = intent.getParcelableExtra<Producto>("producto")
        if (producto != null) {
            etProducto.setText(producto.producto)
            etCantidad.setText(producto.cantidad.toString())
            etPrecio.setText(producto.precio.toString())

            btnGuardar.text = "Modificar"
        }
    }

    override fun onResume() {
        super.onResume()

        btnGuardar.setOnClickListener {
            val producto = etProducto.text.toString()
            val precio = etPrecio.text.toString()
            val cantidad = etCantidad.text.toString()

            val obj = Producto()
            obj.cantidad = cantidad.toInt()
            obj.precio = precio.toDouble()
            obj.producto = producto

            val posicion = intent.getIntExtra("posicion", -1)
            if (posicion > 0) {
                FormularioActivity.lista[posicion] = obj
                Toast.makeText(this@RegistroActivity, "Se Modifico", Toast.LENGTH_SHORT).show()
            } else {
                FormularioActivity.lista.add(obj)
                Toast.makeText(this@RegistroActivity, "Se Registro", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}
