package br.com.mobilehhealth.app;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexao {

    public static String postDados(String urlUsuario, String parametrosUsuarios){
        // Crio as variaveis
        URL url;
        HttpURLConnection connection = null;

        try {
            // faço um objeto url, que paga a url que o usuario passar
            url = new URL(urlUsuario);

            // Abro a conexão com o banco
            connection = (HttpURLConnection) url.openConnection();

            // Digo qual método sera usudo
            connection.setRequestMethod("POST");

            // Digo as propriedades
            connection.setRequestProperty("Content-Type", "'form' => 'application/x-www-form-urlencoded'");
            connection.setRequestProperty("Content-Lenght", "" + Integer.toString(Integer.parseInt(parametrosUsuarios)).getBytes().length);
            connection.setRequestProperty("Content-Language", "pt-BR");

            // Não armazenar cache do banco
            connection.setUseCaches(false);

            // Abilito entrada e saída de informções
            connection.setDoInput(true);
            connection.setDoOutput(true);


           /* DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(parametrosUsuarios);
            dataOutputStream.flush();
            dataOutputStream.close();*/

            // Faz leitura de dados de entrada e saida e feço a solicitação
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(parametrosUsuarios);
            outputStreamWriter.flush();

            // Obtive as informações passadas pela conexão
            InputStream inputStream = connection.getInputStream();

            // Armazenos essas inforrmações em UTF - 8
            BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String linha;

            // Faço um string buufer
            StringBuffer resposta = new StringBuffer();

            // Monto ela
            while ((linha = bufferedReader.readLine()) != null){
                // O append junta uma string ao final da outra
                resposta.append(linha);
                resposta.append("\r");
            }

            // Fecho ela e retorno uma resposta
            bufferedReader.close();

            // E retorno a conexão a nula
            return resposta.toString();

        }catch (Exception erro){
            // Retorna nula caso encontre algum erro
            return  null;

        }finally {
            // Se coneção for diferente de nula ele disconecta
            if (connection != null){
                connection.disconnect();
            }
        }

    }
}
