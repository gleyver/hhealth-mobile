package br.com.mobilehhealth.app;

public class Exames{

    private String id_resultado_exame;
    private String exame;
    private String funcionario;
    private String especialidade;
    private String unidade;
    private String data;

    public static Exames create (String exame, String data, String id_resultado_exame){

        Exames e = new Exames();

        e.setId_resultado_exame(id_resultado_exame);
        e.setData(data);
        e.setExame(exame);

        return e;
    }

    public String getId_resultado_exame() {
        return id_resultado_exame;
    }

    public void setId_resultado_exame(String id_resultado_exame) {
        this.id_resultado_exame = id_resultado_exame;
    }

    public String getExame() {
        return exame;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
