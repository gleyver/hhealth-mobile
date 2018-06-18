package br.com.mobilehhealth.app;

public class Unidade {

    Integer id;
    String nome_unidade;

    public Unidade(){

    }

    public Unidade(int id, String nome_unidade){
        this.id = id;
        this.nome_unidade = nome_unidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_unidade() {
        return nome_unidade;
    }

    public void setNome_unidade(String nome_unidade) {
        this.nome_unidade = nome_unidade;
    }



}
