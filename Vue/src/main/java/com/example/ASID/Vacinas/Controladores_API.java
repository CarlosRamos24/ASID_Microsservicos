package com.example.ASID.Vacinas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/Vue", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controladores_API {


    @CrossOrigin
    @GetMapping
    @ResponseBody
    public String HistoricoMarcacoes() {/// recebe cod utente e localização para filtrar o centro

         // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3305/Vue";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "SELECT * FROM Marcacao";

        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            // Executando a declaração SQL para inserir os dados
            ResultSet resultado = stmt.executeQuery();
            JSONArray marcacoes = new JSONArray();
           // Verifica se há algum resultado retornado
           if(resultado.next()){
            do {
                JSONObject marcacao = new JSONObject();
                
                // Adicionar os campos e valores ao registro
                marcacao.put("data", resultado.getString("data"));
                marcacao.put("numUtente", resultado.getString("numUtente"));
                marcacao.put("cod_centro", resultado.getString("cod_centro"));


                marcacoes.put(marcacao);
             
             }while (resultado.next());
             return marcacoes.toString();
           }else{
            return "Não existem marcacoes em registo";
           }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
       return "A conexão falhou";
      }
}