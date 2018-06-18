package br.com.mobilehhealth.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExamesAdapter extends ArrayAdapter<Exame> {
    public ExamesAdapter (Context ctx, ArrayList<Exame> item){
        super(ctx, 0, item);
    }


    // METODO getView PARA CRIAR CADA ELEMENTO DA LIST VIEW
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_list_view_exames, null);
        }

        Exame item = getItem(position);

        TextView txtExame = v.findViewById(R.id.txt_exame);
        TextView txtDataExame = v.findViewById(R.id.txt_data_exame);


        txtExame.setText(item.getExame());
        txtDataExame.setText(item.getData());

        return v;
    }

    public static class ConsultasAdapter extends ArrayAdapter<Consultas> {

        public ConsultasAdapter(Context ctx, ArrayList<Consultas> item){
            super(ctx, 0, item);
        }


        // METODO getView PARA CRIAR CADA ELEMENTO DA LIST VIEW
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                v = LayoutInflater.from(getContext()).inflate(R.layout.item_list_view, null);
            }

            Consultas item = getItem(position);

            TextView txtEspecialidade = v.findViewById(R.id.txt_especialidade_item);
            TextView txtDataAgendada = v.findViewById(R.id.txt_data_agendada_item);


            txtEspecialidade.setText(item.getId_especialidade());
            txtDataAgendada.setText(item.getData());

            return v;
        }
    }
}
