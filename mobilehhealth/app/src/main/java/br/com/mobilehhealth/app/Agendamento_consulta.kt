package br.com.mobilehhealth.app

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.content_agendamento_consulta.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

class Agendamento_consulta: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_agendamento_consulta)

        // Fazendo o findbyid dos componentes da view
        val spnEspecialidade: Spinner = findViewById(R.id.spn_agendamento_especialidade)
        val spnUnidade: Spinner = findViewById(R.id.spn_agendamento_unidade)
        val spnMedico: Spinner = findViewById(R.id.spn_agendamento_medico)

        // Criando um array list para cada spinner
        val arrayListEspecialidade = java.util.ArrayList<String>()
        val arrayListUnidade = java.util.ArrayList<String>()
        val arrayListMedico = java.util.ArrayList<String>()

        //Roda por tras da aplicação
        doAsync {
            // Faço a conexão com o banco de dados e guardo atraves de rest com as strings
            var resultadoEspecialidade = HttpConexao.get(getString(R.string.URL_LISTAR_ESPECIALIDADES))
            var resultadoUnidade = HttpConexao.get(getString(R.string.URL_LISTAR_UNIDADES))
            var resultadoMedico = HttpConexao.get(getString(R.string.URL_LISTAR_MEDICOS))

            //Log.d para ver se como está as coisas
            Log.d("API-Especialidade", resultadoEspecialidade)
            Log.d("API-Unidade", resultadoUnidade)
            Log.d("API-Medico", resultadoMedico)

            uiThread {

                // Crio os objetos json dos spiners
                val jsonObjectEspecialidade = JSONObject(resultadoEspecialidade)
                val jsonObjectUnidade = JSONObject(resultadoUnidade)
                val jsonObjectMedico = JSONObject(resultadoMedico)

                // Resgato o array da api
                val itensEspecialidade = jsonObjectEspecialidade.getJSONArray("especialidades")
                val itensUnidade = jsonObjectUnidade.getJSONArray("unidades")
                val itensMedico = jsonObjectMedico.getJSONArray("medicos")

                // Faço  3 for para pegar os dados trazidos de dentro da API
                for (i in 0 until itensEspecialidade.length()) {

                    val item = itensEspecialidade.getJSONObject(i)
                    arrayListEspecialidade.add(item.getString("especialidade"))

                }


                for (i in 0 until itensUnidade.length()) {

                    val item = itensUnidade.getJSONObject(i)
                    arrayListUnidade.add(item.getString("nome_unidade"))

                }


                for (i in 0 until itensMedico.length()) {

                    val item = itensMedico.getJSONObject(i)
                    arrayListMedico.add(item.getString("nome"))

                }

                val adapterEspecialidade = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item,
                        arrayListEspecialidade)
                adapterEspecialidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                spnEspecialidade.adapter = adapterEspecialidade


                val adapterUnidade = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item,
                        arrayListUnidade)
                adapterUnidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                spnUnidade.adapter = adapterUnidade


                val AdapterMedico = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item,
                        arrayListMedico)
                AdapterMedico.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                spnMedico.adapter = AdapterMedico

            }
        }

        btn_agendar_consulta.setOnClickListener {

            val posicaoEspecialidade = spnEspecialidade.getSelectedItemPosition();
            val itemSelecionadoEspecialidade = arrayListEspecialidade.get(posicaoEspecialidade);

            val posicaoUnidade = spnUnidade.getSelectedItemPosition();
            val itemSelecionadoUnidade = arrayListUnidade.get(posicaoUnidade);

            val posicaoMedico = spnMedico.getSelectedItemPosition();
            val itemSelecionadoMedico = arrayListMedico.get(posicaoMedico);

//            val textoEspecialidade = spnEspecialidade.selectedItem
            val dataAg = txt_data_agendamento.text.toString()
            val horaAg = txt_hora_agendamento.text.toString()
//            val textoUnidade = spnUnidade.selectedItem.toString()
//            val textoMedico = spn_agendamento_medico.selectedItem.toString()

            var pref = getSharedPreferences("hhealth", Context.MODE_PRIVATE)
            var id = pref.getInt("id_paciente", 0)

            doAsync {


                // Cria url a qual vai buscar api do login
                val urlAgendamento_consulta = getString(R.string.URL_AGENDAMENTO_CONSULTA)

                //Criando objeto de map
                val map = HashMap<String, String>()

                //Preenchendo objeto map
                map.put("id_paciente", id.toString())
                map.put("id_especialidade", itemSelecionadoEspecialidade)
                map.put("id_funcionario", itemSelecionadoMedico)
                map.put("id_unidade", itemSelecionadoUnidade)
                map.put("data", dataAg)
                map.put("hora", horaAg)

                //Objeto que vai trazer o resultado da URL
//                val resultado = HttpConexao.post(url, map)
                val resultado = HttpConexao.post(urlAgendamento_consulta, map)
                Log.d("RESULTADOOOOOOOOO", resultado)

                uiThread {
                    // Crio um objeto Json
//                    val json = JSONObject(resultado)

                    toast("Inserido com sucesso")
                    startActivity<Escolha_opcao>()
                }
            }
        }
    spnEspecialidade.onItemSelectedListener {  }
    }
}