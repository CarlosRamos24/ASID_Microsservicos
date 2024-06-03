package com.example.ASID.Marcacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.kafka.clients.producer.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "/Marcacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controladores_Marcacao {

    public void updateVue(String dados) {
        String bootstrapServers = "http://kafka:9092";
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("acks", "all"); // Ensure all replicas acknowledge
        properties.put("retries", 3); // Number of retries
        properties.put("linger.ms", 1); // Linger time
        properties.put("client.id", "producer-1");
        properties.put("enable.idempotence", "true");

        Producer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "vue";

        String key =  Integer.toString(1);

        // Criar um registro de produtor
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, dados);

        // Enviar o registro
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    System.out.println("Message sent successfully: Key = " + key + ", Value = " + dados);
                } else {
                    System.err.println("Error sending message: " + exception.getMessage());
                }
            }
        });

        // Close the producer
        producer.close();
    }

    @CrossOrigin
    @GetMapping
    @ResponseBody
    public String GetMarcacoes() {
         // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3307/Marcacoes";
        //String url = "jdbc:mysql://192.168.56.101:3307/Marcacoes";
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
    

    @CrossOrigin
    @PostMapping("/Registar")
    @ResponseBody
    public String RegistarMarcacao(@RequestBody String dados) {
         // Converter a string JSON para um objeto JSONObject
         JSONObject jsonObject = new JSONObject(dados);

        
         String codigo = jsonObject.getString("data");
         
         int numUtente = jsonObject.getInt("numUtente");
 
         String cod_centro = jsonObject.getString("cod_centro");

        jsonObject.put("Tipo", "Add");
 
         // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3307/Marcacoes";
        //String url = "jdbc:mysql://192.168.56.101:3307/Marcacoes";
         String user = "root";
         String password = "root";
 
         // Declaração SQL para a inserção de dados
         String sql = "INSERT INTO Marcacao (data, numUtente, cod_centro) VALUES (?, ?, ?)";
 
         try (
             // Estabelecendo a conexão com o banco de dados
             Connection conn = DriverManager.getConnection(url, user, password);
             // Preparando a declaração SQL para a inserção
             PreparedStatement stmt = conn.prepareStatement(sql);
         ) {
             // Preenchendo os parâmetros da declaração preparada com os valores dos campos
             stmt.setString(1, codigo);
             stmt.setInt(2, numUtente);
             stmt.setString(3, cod_centro);
 
             // Executando a declaração SQL para inserir os dados
             int rowsAffected = stmt.executeUpdate();
 
             // Verificando se a inserção foi bem-sucedida
             if (rowsAffected > 0) {
                 System.out.println("A sua marcação foi efetuada com sucesso.");
                 updateVue(jsonObject.toString());
                 return "Sucesso";
             } else {
                 System.out.println("Nenhum dado foi inserido.");
                 return "Erro";
             }
         } catch (SQLException e) {
             e.printStackTrace();
             return "Erro";
         }
 
    }
    
    @CrossOrigin
    @PostMapping("/Cancelar")
    @ResponseBody
    public String CancelarMarcacao(@RequestBody String dados) {

        JSONObject jsonObject = new JSONObject(dados);

        
        String data = jsonObject.getString("data");
        
        int numUtente = jsonObject.getInt("numUtente");

        String cod_centro = jsonObject.getString("cod_centro");

        jsonObject.put("Tipo", "Del");

           // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3307/Marcacoes";
        //String url = "jdbc:mysql://192.168.56.101:3307/Marcacoes";
    String user = "root";
    String password = "root";

    // Declaração SQL para atualizar os dados do utente
    String sql = "DELETE FROM Marcacao WHERE data=? AND numUtente=? AND cod_centro=?";

    try (
        // Estabelecendo a conexão com o banco de dados
        Connection conn = DriverManager.getConnection(url, user, password);
        // Preparando a declaração SQL para atualização
        PreparedStatement stmt = conn.prepareStatement(sql);
    ) {
        // Preenchendo os parâmetros da declaração preparada com os novos valores dos campos
        stmt.setString(1, data);
        stmt.setInt(2, numUtente);
        stmt.setString(3, cod_centro);

        // Executando a declaração SQL para atualizar os dados
        int rowsAffected = stmt.executeUpdate();

        // Verificando se a atualização foi bem-sucedida
        if (rowsAffected > 0) {
            System.out.println("A sua marcacao foi eliminada com sucesso!");
            updateVue(jsonObject.toString());
            return "Sucesso";
        } else {
            System.out.println("Não existe nenhuma marcação.");
            return "Erro";
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return "Erro";
    }
    }

    @CrossOrigin
    @GetMapping("/Utente")
    @ResponseBody
    public String GetMarcacoes(@RequestParam("NumUtente") String NumUtente) {
        


          // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3307/Marcacoes";
        //  String url = "jdbc:mysql://192.168.56.101:3307/Marcacoes";
          String user = "root";
          String password = "root";
  
          // Declaração SQL para a inserção de dados
          String sql = "SELECT * FROM Marcacao WHERE numUtente=?";
  
          try (
              // Estabelecendo a conexão com o banco de dados
              Connection conn = DriverManager.getConnection(url, user, password);
              // Preparando a declaração SQL para a inserção
              PreparedStatement stmt = conn.prepareStatement(sql);
          ) {

            stmt.setString(1,NumUtente);
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