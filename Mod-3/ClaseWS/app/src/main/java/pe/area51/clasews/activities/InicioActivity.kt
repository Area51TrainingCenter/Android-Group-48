package pe.area51.clasews.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import pe.area51.clasews.R

class InicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val handler = Handler()
        handler.postDelayed({

            val preferences=
                    getSharedPreferences("clasews",
                            Context.MODE_PRIVATE)

            if(preferences.contains("id")) {
                startActivity(
                        Intent(
                                this@InicioActivity,
                                HomeActivity::class.java
                        )
                )
                finish()
            }else{
                startActivity(
                        Intent(
                                this@InicioActivity,
                                MainActivity::class.java
                        )
                )
                finish()
            }
        }, 2000)
    }
}
