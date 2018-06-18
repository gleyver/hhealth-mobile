package br.com.mobilehhealth.app;

import java.sql.Date;

public class Consultas {

    private String id_especialidade;
    private String data;
    private String id_agendamento_consulta;
    private Double hora;
    private Integer id_unidade;
    private Integer id_medico;

    public static Consultas create (String id_especialidade, String data, String id_agendamento_consulta){

        Consultas c = new Consultas();

        c.setId_agendamento_consulta(id_agendamento_consulta);
        c.setData(data);
        c.setId_especialidade(id_especialidade);

        return c;
    }

    public String getId_especialidade() {
        return id_especialidade;
    }

    public void setId_especialidade(String id_especialidade) {
        this.id_especialidade = id_especialidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId_agendamento_consulta() {
        return id_agendamento_consulta;
    }

    public void setId_agendamento_consulta(String id_agendamento_consulta) {
        this.id_agendamento_consulta = id_agendamento_consulta;
    }

    public Double getHora() {
        return hora;
    }

    public void setHora(Double hora) {
        this.hora = hora;
    }

    public Integer getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(Integer id_unidade) {
        this.id_unidade = id_unidade;
    }

    public Integer getId_medico() {
        return id_medico;
    }

    public void setId_medico(Integer id_medico) {
        this.id_medico = id_medico;
    }

}
