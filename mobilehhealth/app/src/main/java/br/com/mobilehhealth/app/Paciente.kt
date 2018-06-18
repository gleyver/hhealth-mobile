package br.com.mobilehhealth.app

// Classe de dados
data class Paciente (val id_paciente:Int, val nome:String, val rg:Int, val cpf: Long, val dt_nasc: Int, val endereco: String, val convenio:String,
                     val especialidade:String)