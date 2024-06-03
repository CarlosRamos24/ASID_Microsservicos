package com.example.ASID.CentroDeVacinacao;


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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "/CentroDeVacinacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controladores_Centro {

    @CrossOrigin
    @PostMapping("/Registar")
    @ResponseBody
    public String RegistarCentro(@RequestBody String dados) {
        // Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        
        String codigo = jsonObject.getString("codigo");
        
        int numPostos = jsonObject.getInt("numPostos");

        String morada = jsonObject.getString("morada");

        String localidade = jsonObject.getString("localidade");

        int numMaxVacDia = jsonObject.getInt("numMaxVacDia");

        String cod_gestor = jsonObject.getString("cod_gestor");

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3306/CentroVacinacao";
        //String url = "jdbc:mysql://192.168.56.101:3306/CentroVacinacao";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "INSERT INTO CentroVacinacao (codigo, numPostos, morada, localidade, numMaxVacDia, cod_gestor) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, codigo);
            stmt.setInt(2, numPostos);
            stmt.setString(3, morada);
            stmt.setString(4, localidade);
            stmt.setInt(5, numMaxVacDia);
            stmt.setString(6, cod_gestor);

            // Executando a declaração SQL para inserir os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("Os dados do Centro de Vacinação foram inseridos com sucesso.");
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
    @PostMapping("/Remover")
    @ResponseBody
    public String RemoverCentro(@RequestBody String codigo) {
        // Configurações de conexão com o banco de dados
    String url = "jdbc:mysql://143.47.60.254:3306/CentroVacinacao";
    //String url = "jdbc:mysql://192.168.56.101:3306/CentroVacinacao";
    String user = "root";
    String password = "root";

    // Declaração SQL para atualizar os dados do utente
    String sql = "DELETE FROM CentroVacinacao WHERE codigo=?";

    try (
        // Estabelecendo a conexão com o banco de dados
        Connection conn = DriverManager.getConnection(url, user, password);
        // Preparando a declaração SQL para atualização
        PreparedStatement stmt = conn.prepareStatement(sql);
    ) {
        // Preenchendo os parâmetros da declaração preparada com os novos valores dos campos
        stmt.setString(1, codigo);
     

        // Executando a declaração SQL para atualizar os dados
        int rowsAffected = stmt.executeUpdate();

        // Verificando se a atualização foi bem-sucedida
        if (rowsAffected > 0) {
            System.out.println("O Centro foi eliminado com sucesso!");
            return "Sucesso";
        } else {
            System.out.println("Nenhum Centro foi Encontrado.");
            return "Erro";
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return "Erro";
    }
    }

    @CrossOrigin
    @PostMapping("/Editar")
    @ResponseBody
    public String EditarCentro(@RequestBody String dados) {
        // Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        
        String codigo = jsonObject.getString("codigo");
        
        int numPostos = jsonObject.getInt("numPostos");

        String morada = jsonObject.getString("morada");

        String localidade = jsonObject.getString("localidade");

        int numMaxVacDia = jsonObject.getInt("numMaxVacDia");

        String cod_gestor = jsonObject.getString("cod_gestor");

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3306/CentroVacinacao";
        //String url = "jdbc:mysql://192.168.56.101:3306/CentroVacinacao";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "Update CentroVacinacao SET codigo=?, numPostos=?, morada=?, localidade=?, numMaxVacDia=?, cod_gestor=? WHERE codigo=?";
        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, codigo);
            stmt.setInt(2, numPostos);
            stmt.setString(3, morada);
            stmt.setString(4, localidade);
            stmt.setInt(5, numMaxVacDia);
            stmt.setString(6, cod_gestor);
            stmt.setString(7, codigo);

            // Executando a declaração SQL para inserir os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("Os dados do Centro de Vacinação foram Atualizados com sucesso.");
                return "Sucesso";
            } else {
                System.out.println("Nenhum Centro foi encontrado.");
                return "Erro";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro";
        }

    }

    @CrossOrigin
    @PostMapping("/Alocar")
    @ResponseBody
    public String AlocarGestor(@RequestBody String dados) {
        JSONObject jsonObject = new JSONObject(dados);

        
        String codigo = jsonObject.getString("codigo");
        
        String cod_gestor = jsonObject.getString("cod_gestor");



        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3306/CentroVacinacao";
        //String url = "jdbc:mysql://192.168.56.101:3306/CentroVacinacao";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "Update CentroVacinacao SET cod_gestor=? WHERE codigo=?";
        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, cod_gestor);
            stmt.setString(2, codigo);

            // Executando a declaração SQL para inserir os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("Atribuição de novo gestor para o Centro de Vacinação realizado com sucesso.");
                return "Sucesso";
            } else {
                System.out.println("Nenhum Centro foi encontrado.");
                return "Erro";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro";
        }

    }
    
    @CrossOrigin
    @GetMapping
    @ResponseBody
    public String ListarCentros() {
        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3306/CentroVacinacao";
        //String url = "jdbc:mysql://192.168.56.101:3306/CentroVacinacao";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "SELECT * FROM CentroVacinacao";

        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            // Executando a declaração SQL para inserir os dados
            ResultSet resultado = stmt.executeQuery();
            JSONArray Centros = new JSONArray();
           // Verifica se há algum resultado retornado
           if(resultado.next()){
            do {
                JSONObject Centro = new JSONObject();
                
                // Adicionar os campos e valores ao registro
                Centro.put("codigo", resultado.getString("codigo"));
                Centro.put("numPostos", resultado.getString("numPostos"));
                Centro.put("localidade", resultado.getString("localidade"));
                Centro.put("morada", resultado.getString("morada"));
                Centro.put("numMaxVacDia", resultado.getString("numMaxVacDia"));
                Centro.put("cod_gestor", resultado.getString("cod_gestor"));

                Centros.put(Centro);
             
             }while (resultado.next());
             return Centros.toString();
           }else{
            return "Não existem Centros Registados";
           }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
       return "A conexão falhou";
      }
    
    @CrossOrigin
    @GetMapping("/Centro/local")
    @ResponseBody
    public String GetCentro(@RequestParam("localizacao") String localizacao) {
         // Configurações de conexão com o banco de dados
          String url = "jdbc:mysql://143.47.60.254:3306/CentroVacinacao";
          //String url = "jdbc:mysql://192.168.56.101:3306/CentroVacinacao";
          String user = "root";
          String password = "root";
  
          // Declaração SQL para a inserção de dados
          String sql = "SELECT * FROM CentroVacinacao WHERE localidade = ?";
  
          try (
              // Estabelecendo a conexão com o banco de dados
              Connection conn = DriverManager.getConnection(url, user, password);
              // Preparando a declaração SQL para a inserção
              PreparedStatement stmt = conn.prepareStatement(sql);
          ) {
              // Preenchendo os parâmetros da declaração preparada com os valores dos campos
              stmt.setString(1, localizacao);
              // Executando a declaração SQL para inserir os dados
              ResultSet resultado = stmt.executeQuery();
              JSONArray Centros = new JSONArray();
             // Verifica se há algum resultado retornado
             if(resultado.next()){
                do {
                    JSONObject Centro = new JSONObject();
                    
                    // Adicionar os campos e valores ao registro
                    Centro.put("codigo", resultado.getString("codigo"));
                    Centro.put("numPostos", resultado.getString("numPostos"));
                    Centro.put("localidade", resultado.getString("localidade"));
                    Centro.put("morada", resultado.getString("morada"));
                    Centro.put("numMaxVacDia", resultado.getString("numMaxVacDia"));
                    Centro.put("cod_gestor", resultado.getString("cod_gestor"));
    
                    Centros.put(Centro);
                 
                 }while (resultado.next());
                 String jsonString = Centros.toString();
             
                return jsonString;
               }else{
                return "Não existem Centros Registados";
               }
                
                
          } catch (SQLException e) {
              e.printStackTrace();
          }
          
         return "A conexão falhou";
    }

    @CrossOrigin
    @GetMapping("/Centro")
    @ResponseBody
    public String GetCentroLocal(@RequestBody String cod_centro) {
         // Configurações de conexão com o banco de dados
          String url = "jdbc:mysql://143.47.60.254:3306/CentroVacinacao";
          //String url = "jdbc:mysql://192.168.56.101:3306/CentroVacinacao";
          String user = "root";
          String password = "root";
  
          // Declaração SQL para a inserção de dados
          String sql = "SELECT * FROM CentroVacinacao WHERE codigo = ?";
  
          try (
              // Estabelecendo a conexão com o banco de dados
              Connection conn = DriverManager.getConnection(url, user, password);
              // Preparando a declaração SQL para a inserção
              PreparedStatement stmt = conn.prepareStatement(sql);
          ) {
              // Preenchendo os parâmetros da declaração preparada com os valores dos campos
              stmt.setString(1, cod_centro);
              // Executando a declaração SQL para inserir os dados
              ResultSet resultado = stmt.executeQuery();
             
             // Verifica se há algum resultado retornado
             if (resultado.next()) {
                JSONObject Centro = new JSONObject();
                
                // Adicionar os campos e valores ao registro
                Centro.put("codigo", resultado.getString("codigo"));
                Centro.put("numPostos", resultado.getString("numPostos"));
                Centro.put("localidade", resultado.getString("localidade"));
                Centro.put("morada", resultado.getString("morada"));
                Centro.put("numMaxVacDia", resultado.getString("numMaxVacDia"));
                Centro.put("cod_gestor", resultado.getString("cod_gestor"));


                
                String jsonString = Centro.toString();
             
                return jsonString;
             } else {
                return "O Centro não foi encontrado";
             }
          } catch (SQLException e) {
              e.printStackTrace();
          }
          
         return "A conexão falhou";
    }

   


}