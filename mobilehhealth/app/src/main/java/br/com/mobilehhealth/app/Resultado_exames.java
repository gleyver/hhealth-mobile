package br.com.mobilehhealth.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;




public class Resultado_exames extends AppCompatActivity    {
    ListView listView;
    ExamesAdapter adapter;
    Context context;
    SharedPreferences pref;
    Integer id_paciente;
    String id_resultado_exame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_resultado_exames);

        context = this;


        pref = getSharedPreferences("hhealth", Context.MODE_PRIVATE);
        id_paciente = pref.getInt("id_paciente", 0);

        // CRICAO DO ADAPTER
        listView = (ListView) findViewById(R.id.lst_resultado_exames);
        adapter = new ExamesAdapter(context, new ArrayList<Exame>());

        // FAZER A CONEXAO COM A API

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                String retornoJson = HttpConexao.get(getString(R.string.URL_RESULTADO_EXAMES)+id_paciente);
                Log.d("TAG", retornoJson);

                try{
                    JSONArray jsonArray = new JSONArray(retornoJson);

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject item = jsonArray.getJSONObject(i);

                        adapter.add(Exame.create(

                                item.getString("nome"),
                                item.getString("data"),
                                item.getString("id_resultado_exame")
                        ));

                    }
                }catch(Exception e){
                    Log.e("ERROR", e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


                listView.setAdapter(adapter);

//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(Visualizar_consultas.this, Visualizar_consultas_marcadas.class);
//                        startActivity(intent);
//                    }
//                });

            }
        }.execute();






    }
}
