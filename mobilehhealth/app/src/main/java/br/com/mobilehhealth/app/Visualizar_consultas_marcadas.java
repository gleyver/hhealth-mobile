package br.com.mobilehhealth.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Visualizar_consultas_marcadas extends AppCompatActivity {

    Button btn_voltar;
    Button btn_ler_qrcode;

    ListView listView;
    ExamesAdapter.ConsultasAdapter adapter;
    final Activity CONTEXT = this;
    SharedPreferences pref;
    Integer id_paciente;
    Integer id_agendamento;
    SharedPreferences.Editor edit;

    TextView data;
    TextView hora;
    TextView especialidade;
    TextView medico;
    TextView unidade;
    String dataPegada;
    String horaPegada;
    String especialidadePegada;
    String funcionarioPegado;
    String unidadePegado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_visualizar_consultas_marcadas);

        btn_voltar = (Button) findViewById(R.id.btn_voltar);
        btn_ler_qrcode = (Button) findViewById(R.id.btn_ler_qrcode);
        final Activity activity = this;


        data = (TextView) findViewById(R.id.txt_dia_visualizar_consultas_marcadas);
        medico = (TextView) findViewById(R.id.txt_medico_visualizar_consultas_marcadas);
        especialidade = (TextView) findViewById(R.id.txt_especialidade_visualizar_consultas_marcadas);
        hora = (TextView)  findViewById(R.id.txt_hora_visualizar_consultas_marcadas);
        unidade = (TextView) findViewById(R.id.txt_unidade_visualizar_consultas_marcadas);

        Bundle bundle = getIntent().getExtras();
//        String vai = bundle.getString("id_agendamento_consulta");
//        System.out.print(vai);
//        data.setText(vai);

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Visualizar_consultas_marcadas.this, Visualizar_consultas.class);
                startActivity(intent);
                finish();
            }
        });

        btn_ler_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setPrompt("Camera Scan");
                intentIntegrator.setCameraId(0);
                intentIntegrator.initiateScan();
            }
        });



        final String url = getString(R.string.URL_VISUALIZAR_CONSULTA_ESPECIFICA);
//        new AsyncTask<Void,Void,Void>(){
//            @Override
//            protected Void doInBackground(Void... voids) {
//
//
//                String retornoJson = HttpConexao.get(url);
////                Log.d("TAG", retornoJson);
//
//                try{
//                    JSONObject json = new JSONObject(retornoJson);
//
//                    dataPegada = json.getString("data");
//
//                    especialidadePegada = json.getString("id_especialidade");
//                    unidadePegado = json.getString("id_unidade");
//                    funcionarioPegado =json.getString("id_funcionario");
//                    horaPegada = json.getString("hora");
//
//
//
//                }catch(Exception e){
//                    Log.e("ERROR", e.getMessage());
//                }
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//
//                data.setText(dataPegada);
//                especialidade.setText(especialidadePegada);
//                hora.setText(horaPegada);
//                medico.setText(funcionarioPegado);
//                unidade.setText(unidadePegado);
//
//            }
//        }.execute();
//

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() != null){
                alert(result.getContents());
            }else {
                alert("cancelado");
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    private void alert(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
