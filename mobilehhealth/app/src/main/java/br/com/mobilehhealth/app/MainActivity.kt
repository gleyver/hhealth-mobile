package br.com.mobilehhealth.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity() {

    // Crio uma variavel que mais tarde vai receber a URL
    var API_URL_LOGIN = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val pref = getSharedPreferences("hhealth", Context.MODE_PRIVATE)
        val nomeLogado = pref.getString("usuario", "")

        // Variavel recebe String
        API_URL_LOGIN = getString(R.string.URL_LOGIN)

        // Se nome não for vazio ja entra no aplicativo
       if (nomeLogado.isNotEmpty()) {
            // Chamo a tela
            var iniciaEscolhaOpcao = Intent(this, Escolha_opcao::class.java)
            startActivity(iniciaEscolhaOpcao)
            finish()
       }

        // No click do botão entrar
        btn_entrar.setOnClickListener {

            // Pego os texto inseridos no EditText
            val usuario = txt_usuario.text.toString()

            val senha = txt_senha.text.toString()

            // Rodo em background do aplicativo
            doAsync {
                // Cria url a qual vai buscar api do login
                val url = API_URL_LOGIN

                //Criando objeto de map
                val map = HashMap<String, String>()

                //Preenchendo objeto map
                map.put("usuario", usuario)
                map.put("senha", senha)
//               map.put("id_paciente", id_paciente)

                //Objeto que vai trazer o resultado da URL
                val resultado = HttpConexao.post(url, map)
                Log.d("API", resultado)

                // Tela do usuário ja
                uiThread {

                    // Crio um objeto Json
                    val json = JSONObject(resultado)
                   // toast(resultado)
                    val login_state = json.getBoolean("login_state")
                    val id_paciente = json.getInt("id_paciente")
                    //Se a variavel status do login for true   ele ja abre a tela
                    if (login_state == true) {

                        // criando objetos json
                        val usuario = json.getJSONObject("usuario")
//                      val id_paciente = resultado.getInt("id_paciente")

//                      val nome = json.getJSONObject("nome")

                        // Toast para avisar que o cara foi logado com sucesso
                        toast("Usuário logado com sucesso")

                        // Variavel edit para armazena
                        val edit = pref.edit()

                        // Preencho edit
                        // edit.putString("nome", nome.toString())
                        edit.putString("usuario", usuario.toString())
                        edit.putInt("id_paciente", id_paciente)
                        //edit.putString("id_paciente", id_paciente.toString())
                        //Aplico
                        edit.apply()

                        //Chamo a tela escolha opção
                        startActivity<Escolha_opcao>()

                        // starto a activity e do um finish na anterior para não ter histórico
                        finish()

                    } else {
                        // Toast incorreto
                        toast("Usuário ou senha incorretos")
                    }

                }
            }
        }
    }
}