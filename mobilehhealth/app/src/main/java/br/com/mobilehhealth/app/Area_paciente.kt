package br.com.mobilehhealth.app

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.content_area_paciente.*
import kotlinx.android.synthetic.main.content_escolha_opcao.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class Area_paciente: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_area_paciente)


        var pref = getSharedPreferences("hhealth", Context.MODE_PRIVATE)
        var id = pref.getInt("id_paciente", 0)

        val url_visualizar_paciente = getString(R.string.url_visualizar_paciente)+id
        Log.d("URL da api",url_visualizar_paciente)

            doAsync {
                //Criando objeto de map
                val map = HashMap<String, String>()

                //Preenchendo objeto map
                map.put("id_paciente", id.toString())

                //Objeto que vai trazer o resultado da URL
                val resultado = HttpConexao.get(url_visualizar_paciente)
                Log.d("API", resultado)
                // Crio um objeto Json


                uiThread {
                    val json = JSONObject(resultado)

                    val cpf =  json.getInt("cpf")
                    val nome = json.getString("nome")
                    val sobrenome = json.getString("sobrenome")
//                    val rg = json.getString("rg")
                    val endereco = json.getInt("id_endereco")
//                    val convenio = json.getInt("id_convenio")

                    txt_cpf_area_paciente.setText(""+cpf)
                    txt_nome_area_paciente.setText(""+nome+" "+sobrenome)
                    txt_dtnasc_area_paciente.setText("16/07/2000")
                    txt_endereco_area_paciente.setText(""+endereco)
  //                  txt_convenio_area_paciente.setText(""+convenio)

                }
            }


    }
}