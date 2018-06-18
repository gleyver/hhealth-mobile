package br.com.mobilehhealth.app;

import java.util.ArrayList;

public class Especialidade {

    private String especialidade;

    public Especialidade(Agendamento_consulta agendamento, ArrayList<Especialidade> especialidades) {
    }

    public Especialidade() {

    }

    public static Especialidade create ( String especialidade){

        Especialidade e = new Especialidade();

        e.setEspecialidade(especialidade);

        return e;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
