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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "/Vacinas", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controladores_Vacinas {

    @CrossOrigin
    @PostMapping
    @ResponseBody
    public String RegistarVacina(@RequestBody String dados) {
// Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        String codigo = jsonObject.getString("codigo");
        String nome = jsonObject.getString("nome");
        String fabricante = jsonObject.getString("fabricante");
        String doseIndividual = jsonObject.getString("doseIndividual");
        String tomas = jsonObject.getString("tomas");
        String idadeMinima = jsonObject.getString("idadeMinima");
        String idadeMaxima = jsonObject.getString("idadeMaxima");
        String listaEfeitos = jsonObject.getString("listaEfeitos");
        String doencasRestritas = jsonObject.getString("doencasRestritas");
        String cod_centro = jsonObject.getString("cod_centro");
        String efeitosSecundarios = jsonObject.getString("efeitosSecundarios");
        String stock = jsonObject.getString("stock");
        


        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3309/Vacinas";
        //String url = "jdbc:mysql://192.168.56.101:3309/Vacinas";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "INSERT INTO Vacina (codigo, nome, fabricante, doseIndividual, tomas, idadeMinima, idadeMaxima, listaEfeitos, doencasRestritas, cod_centro, efeitosSecundarios, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, codigo);
            stmt.setString(2, nome);
            stmt.setString(3, fabricante);
            stmt.setString(4, doseIndividual);
            stmt.setString(5, tomas);
            stmt.setString(6, idadeMinima);
            stmt.setString(7, idadeMaxima);
            stmt.setString(8, listaEfeitos);
            stmt.setString(9, doencasRestritas);
            stmt.setString(10, cod_centro);
            stmt.setString(11, efeitosSecundarios);
            stmt.setString(12, stock);

            // Executando a declaração SQL para inserir os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("A Vacina foi inserida com sucesso.");
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
    public String RemoverVacina(@RequestBody String dados) {
          // Configurações de conexão com o banco de dados
    String url = "jdbc:mysql://143.47.60.254:3309/Vacinas";
    //String url = "jdbc:mysql://192.168.56.101:3309/Vacinas";
    String user = "root";
    String password = "root";

    JSONObject jsonObject = new JSONObject(dados);

    String codigo = jsonObject.getString("codigo");
    String cod_centro = jsonObject.getString("cod_centro");

    // Declaração SQL para atualizar os dados do utente
    String sql = "DELETE FROM Vacina WHERE codigo=? AND cod_centro=?";

    try (
        // Estabelecendo a conexão com o banco de dados
        Connection conn = DriverManager.getConnection(url, user, password);
        // Preparando a declaração SQL para atualização
        PreparedStatement stmt = conn.prepareStatement(sql);
    ) {
        // Preenchendo os parâmetros da declaração preparada com os novos valores dos campos
        stmt.setString(1, codigo);
        stmt.setString(2, cod_centro);

        // Executando a declaração SQL para atualizar os dados
        int rowsAffected = stmt.executeUpdate();

        // Verificando se a atualização foi bem-sucedida
        if (rowsAffected > 0) {
            System.out.println("O vacina foi eliminada com sucesso!");
            return "Sucesso";
        } else {
            System.out.println("Esse tipo de vacina não foi encontrado.");
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
    public String EditarVacina(@RequestBody String dados) {
        // Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        
        String codigo = jsonObject.getString("codigo");
        String nome = jsonObject.getString("nome");
        String fabricante = jsonObject.getString("fabricante");
        String doseIndividual = jsonObject.getString("doseIndividual");
        String tomas = jsonObject.getString("tomas");
        String idadeMinima = jsonObject.getString("idadeMinima");
        String idadeMaxima = jsonObject.getString("idadeMaxima");
        String listaEfeitos = jsonObject.getString("listaEfeitos");
        String doencasRestritas = jsonObject.getString("doencasRestritas");
        String cod_centro = jsonObject.getString("cod_centro");
        String efeitosSecundarios = jsonObject.getString("efeitosSecundarios");

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3309/Vacinas";
        //String url = "jdbc:mysql://192.168.56.101:3309/Vacinas";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "Update Vacina SET  nome=?, fabricante=?, doseIndividual=?, tomas=?, idadeMinima=?, idadeMaxima=?, listaEfeitos=?, doencasRestritas=?, efeitosSecundarios=? WHERE codigo=? AND cod_centro=?";
        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, nome);
            stmt.setString(2, fabricante);
            stmt.setString(3, doseIndividual);
            stmt.setString(4, tomas);
            stmt.setString(5, idadeMinima);
            stmt.setString(6, idadeMaxima);
            stmt.setString(7, listaEfeitos);
            stmt.setString(8, doencasRestritas);
            stmt.setString(9, efeitosSecundarios);
            stmt.setString(10, codigo);
            stmt.setString(11, cod_centro);
            // Executando a declaração SQL para inserir os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("Os dados da vacina foram Atualizados com sucesso.");
                return "Sucesso";
            } else {
                System.out.println("Nenhuma vacina foi encontrada.");
                return "Erro";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro";
        }

    }

    @CrossOrigin
    @PostMapping("/Atualizar")
    @ResponseBody
    public String AtualizarStock(@RequestBody String dados) {
        // Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        String codigo = jsonObject.getString("codigo");
        String cod_centro = jsonObject.getString("cod_centro");
        String stock = jsonObject.getString("stock");

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3309/Vacinas";
        //String url = "jdbc:mysql://192.168.56.101:3309/Vacinas";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "UPDATE Vacina SET stock=? WHERE codigo=? AND cod_centro=?";

        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, stock);
            stmt.setString(2, codigo);
            stmt.setString(3, cod_centro);


            // Executando a declaração SQL para inserir os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("O stock foi atualizado com sucesso.");
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
    @PostMapping("/Efeitos")
    @ResponseBody
    public String RegistarEfeitoSecundario(@RequestBody String dados) {
        // Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        String codigo = jsonObject.getString("codigo");
        String cod_centro = jsonObject.getString("cod_centro");
        String efeitosSecundarios = jsonObject.getString("efeitosSecundarios");

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3309/Vacinas";
        //String url = "jdbc:mysql://192.168.56.101:3309/Vacinas";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "UPDATE Vacina SET efeitosSecundarios=? WHERE codigo=? AND cod_centro=?";

        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, efeitosSecundarios);
            stmt.setString(2, codigo);
            stmt.setString(3, cod_centro);


            // Executando a declaração SQL para inserir os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("O efeito foi registado com sucesso.");
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
    @GetMapping("/Efeitos")
    @ResponseBody
    public String ObterEfeitosSecundarios(@RequestBody String dados) {
        // Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        String codigo = jsonObject.getString("codigo");
        String cod_centro = jsonObject.getString("cod_centro");

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3309/Vacinas";
        //String url = "jdbc:mysql://192.168.56.101:3309/Vacinas";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "SELECT efeitosSecundarios FROM Vacina WHERE codigo=? AND cod_centro=?";

        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, codigo);
            stmt.setString(2, cod_centro);


            // Executando a declaração SQL para inserir os dados
            ResultSet resultado = stmt.executeQuery();

            // Verificando se a inserção foi bem-sucedida
            if (resultado.next()) {

                
                
                String efeitos = resultado.getString("efeitosSecundarios");

             
                return efeitos;
             } else {
                return "O utilizador não foi encontrado";
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @CrossOrigin
    @GetMapping("/stock")
    @ResponseBody
    public String ObterStock(@RequestBody String cod_centro) {
        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3309/Vacinas";
        //String url = "jdbc:mysql://192.168.56.101:3309/Vacinas";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "SELECT * FROM Vacina WHERE cod_centro=?";

        try (
            // Estabelecendo a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);
            // Preparando a declaração SQL para a inserção
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, cod_centro);
            // Executando a declaração SQL para inserir os dados
            ResultSet resultado = stmt.executeQuery();
            JSONArray Vacinas = new JSONArray();
           // Verifica se há algum resultado retornado
           if(resultado.next()){
            do {
                JSONObject Vacina = new JSONObject();

                // Adicionar os campos e valores ao registro
                Vacina.put("codigo", resultado.getString("codigo"));
                Vacina.put("nome", resultado.getString("nome"));
                Vacina.put("fabricante", resultado.getString("fabricante"));
                Vacina.put("tomas", resultado.getString("tomas"));
                Vacina.put("stock", resultado.getString("stock"));
  
                Vacinas.put(Vacina);
             
             }while (resultado.next());
             return Vacinas.toString();
           }else{
            return "Não existem Utentes Registados";
           }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
       return "A conexão falhou";
      }

}