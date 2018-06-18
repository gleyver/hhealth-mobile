package br.com.mobilehhealth.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import br.com.mobilehhealth.app.R.styleable.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_escolha_opcao.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class Escolha_opcao : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_escolha_opcao)
//        setSupportActionBar(toolbar)


        fun onBackPressed() {
            super.onBackPressed()
//        val    logout = .text.toString().toLong()
        }

        fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
            R.id.logout -> {
                onBackPressed()
                startActivity<MainActivity>()
                true
            }
            else -> false
        }


        btn_agendar_consulta_escolha_opcao.setOnClickListener {
            var intent = Intent(this, Agendamento_consulta::class.java)
            startActivity(intent)


        }
//
        btn_resultado_de_exames_escolha_opcao.setOnClickListener {
            var intent = Intent(this, Resultado_exames::class.java)
            startActivity(intent)


        }

        btn_ver_consultas_marcadas_escolha_opcao.setOnClickListener {

            startActivity<Visualizar_consultas>()

        }


        btn_area_paciente.setOnClickListener {

            startActivity<Area_paciente>()
        }
    }
}

