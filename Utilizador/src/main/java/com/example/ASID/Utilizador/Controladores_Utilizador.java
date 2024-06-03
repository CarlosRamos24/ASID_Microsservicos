package com.example.ASID.Utilizador;

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
// @CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "/Utilizador", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controladores_Utilizador {

    @CrossOrigin
    @PostMapping("/Gestor")
    @ResponseBody
    public String RegistarGestor(@RequestBody String dados) {
        // Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        // Obter o valor do campo "username"
        String username = jsonObject.getString("username");

        // Obter o valor do campo "password"
        String passwd = jsonObject.getString("password");

        String nome = jsonObject.getString("nome");

        String email = jsonObject.getString("email");

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3308/Utilizador";
        // String url = "jdbc:mysql://192.168.56.101:3308/Utilizador";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "INSERT INTO Gestor (nome, username, password, email) VALUES (?, ?, ?, ?)";

        try (
                // Estabelecendo a conexão com o banco de dados
                Connection conn = DriverManager.getConnection(url, user, password);
                // Preparando a declaração SQL para a inserção
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, nome);
            stmt.setString(2, username);
            stmt.setString(3, passwd);
            stmt.setString(4, email);

            // Executando a declaração SQL para inserir os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("Os dados foram inseridos com sucesso.");
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
    @PostMapping("/Utente")
    @ResponseBody
    public String RegistarUtente(@RequestBody String dados) {
        // Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        String username = jsonObject.getString("username");

        String passwd = jsonObject.getString("password");

        String nome = jsonObject.getString("nome");

        String num_Utente = jsonObject.getString("num_Utente");

        String data_nasc = jsonObject.getString("data_nasc");

        String localidade = jsonObject.getString("localidade");

        String morada = jsonObject.getString("morada");

        String cod_centro = jsonObject.getString("cod_centro");

        String data_adminVac = jsonObject.getString("data_adminVac");

        String cod_vacina = jsonObject.getString("cod_vacina");

        int tomas_Utente = jsonObject.getInt("tomas_Utente");

        String cod_doencas = jsonObject.getString("cod_doencas");

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3308/Utilizador";
        // String url = "jdbc:mysql://192.168.56.101:3308/Utilizador";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "INSERT INTO Utente (username, password, nome, numUtente, data_nasc, localidade, morada, cod_centro, data_adminVac, cod_vacina, tomasUtente, cod_doencas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                // Estabelecendo a conexão com o banco de dados
                Connection conn = DriverManager.getConnection(url, user, password);
                // Preparando a declaração SQL para a inserção
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, username);
            stmt.setString(2, passwd);
            stmt.setString(3, nome);
            stmt.setString(4, num_Utente);
            stmt.setString(5, data_nasc);
            stmt.setString(6, localidade);
            stmt.setString(7, morada);
            stmt.setString(8, cod_centro);
            stmt.setString(9, data_adminVac);
            stmt.setString(10, cod_vacina);
            stmt.setInt(11, tomas_Utente);
            stmt.setString(12, cod_doencas);

            // Executando a declaração SQL para inserir os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("Os dados foram inseridos com sucesso.");
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
    @GetMapping("/Autenticar")
    @ResponseBody
    public String Autenticar(@RequestBody String dados) {
        // Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        // Obter o valor do campo "username"
        String username = jsonObject.getString("username");

        // Obter o valor do campo "password"
        String passwd = jsonObject.getString("password");

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3308/Utilizador";
        // String url = "jdbc:mysql://192.168.56.101:3308/Utilizador";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "SELECT * FROM Utente WHERE username = ?";

        try (
                // Estabelecendo a conexão com o banco de dados
                Connection conn = DriverManager.getConnection(url, user, password);
                // Preparando a declaração SQL para a inserção
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, username);
            // Executando a declaração SQL para inserir os dados
            ResultSet resultado = stmt.executeQuery();

            // Verifica se há algum resultado retornado
            if (resultado.next()) {
                // Extrai as informações do utente do resultado
                String passwdUtente = resultado.getString("password");
                System.out.println(passwdUtente);
                System.out.println(passwd);
                if (passwdUtente.equals(passwd)) {
                    return "Autenticado com sucesso";
                }
            } else {
                System.out.println("Nenhum utente encontrado com o username fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Autenticação falhada";
    }

    @CrossOrigin
    @PostMapping("/Editar")
    @ResponseBody
    public String EditarUtente(@RequestBody String dados) {
        // Converter a string JSON para um objeto JSONObject
        JSONObject jsonObject = new JSONObject(dados);

        String username = jsonObject.getString("username");
        String passwd = jsonObject.getString("password");
        String nome = jsonObject.getString("nome");
        String num_Utente = jsonObject.getString("num_Utente");
        String data_nasc = jsonObject.getString("data_nasc");
        String localidade = jsonObject.getString("localidade");
        String morada = jsonObject.getString("morada");
        String cod_centro = jsonObject.getString("cod_centro");
        String data_adminVac = jsonObject.getString("data_adminVac");
        String cod_vacina = jsonObject.getString("cod_vacina");
        int tomas_Utente = jsonObject.getInt("tomas_Utente");
        String cod_doencas = jsonObject.getString("cod_doencas");

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3308/Utilizador";
        String user = "root";
        String password = "root";

        // Declaração SQL para atualizar os dados do utente
        String sql = "UPDATE Utente SET username=?, password=?, nome=?, numUtente=?, data_nasc=?, localidade=?, morada=?, cod_centro=?, data_adminVac=?, cod_vacina=?, tomasUtente=?, cod_doencas=? WHERE username=?";

        try (
                // Estabelecendo a conexão com o banco de dados
                Connection conn = DriverManager.getConnection(url, user, password);
                // Preparando a declaração SQL para atualização
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            // Preenchendo os parâmetros da declaração preparada com os novos valores dos
            // campos
            stmt.setString(1, username);
            stmt.setString(2, passwd);
            stmt.setString(3, nome);
            stmt.setString(4, num_Utente);
            stmt.setString(5, data_nasc);
            stmt.setString(6, localidade);
            stmt.setString(7, morada);
            stmt.setString(8, cod_centro);
            stmt.setString(9, data_adminVac);
            stmt.setString(10, cod_vacina);
            stmt.setInt(11, tomas_Utente);
            stmt.setString(12, cod_doencas);
            stmt.setString(13, username);

            // Executando a declaração SQL para atualizar os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a atualização foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("Os dados do utente foram atualizados com sucesso.");
                return "Sucesso";
            } else {
                System.out.println("Nenhum dado do utente foi atualizado.");
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
    public String RemoverUtilizador(@RequestBody String username) {

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3308/Utilizador";
        String user = "root";
        String password = "root";

        // Declaração SQL para atualizar os dados do utente
        String sql = "DELETE FROM Utente WHERE username=?";

        try (
                // Estabelecendo a conexão com o banco de dados
                Connection conn = DriverManager.getConnection(url, user, password);
                // Preparando a declaração SQL para atualização
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            // Preenchendo os parâmetros da declaração preparada com os novos valores dos
            // campos
            stmt.setString(1, username);

            // Executando a declaração SQL para atualizar os dados
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a atualização foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("O utente foi eliminado com sucesso!");
                return "Sucesso";
            } else {
                System.out.println("Nenhum dado do utente foi atualizado.");
                return "Erro";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro";
        }
    }

    @CrossOrigin
    @GetMapping("/Gestores")
    @ResponseBody
    public String getGestor(@RequestBody String username) throws Exception {

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3308/Utilizador";
        // String url = "jdbc:mysql://192.168.56.101:3308/Utilizador";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "SELECT * FROM Gestor WHERE username = ?";

        try (
                // Estabelecendo a conexão com o banco de dados
                Connection conn = DriverManager.getConnection(url, user, password);
                // Preparando a declaração SQL para a inserção
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, username);
            // Executando a declaração SQL para inserir os dados
            ResultSet resultado = stmt.executeQuery();

            // Verifica se há algum resultado retornado
            if (resultado.next()) {
                JSONObject gestor = new JSONObject();
                // Supondo que há colunas chamadas "coluna1", "coluna2", etc. Substitua-as pelos
                // nomes reais das colunas no seu banco de dados
                gestor.put("nome", resultado.getString("nome"));
                gestor.put("username", resultado.getString("username"));
                gestor.put("password", resultado.getString("password"));
                gestor.put("email", resultado.getString("email"));
                String gestorString = gestor.toString();
                return gestorString;
            } else {
                System.out.println("Nenhum utente encontrado com o username fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "A conexão falhou";
    }

    @CrossOrigin
    @GetMapping("/Utentes")
    @ResponseBody
    public String getUtente(@RequestParam("username") String username) {
        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3308/Utilizador";
        String user = "root";
        String password = "root";

        // Declaração SQL para a seleção de dados
        String sql = "SELECT * FROM Utente WHERE username = ?";

        try (
                // Estabelecendo a conexão com o banco de dados
                Connection conn = DriverManager.getConnection(url, user, password);
                // Preparando a declaração SQL para a consulta
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            // Preenchendo os parâmetros da declaração preparada com os valores dos campos
            stmt.setString(1, username);
            // Executando a declaração SQL para consultar os dados
            ResultSet resultado = stmt.executeQuery();

            // Verifica se há algum resultado retornado
            if (resultado.next()) {
                JSONObject Utente = new JSONObject();

                // Adicionar os campos e valores ao registro
                Utente.put("username", resultado.getString("username"));
                Utente.put("nome", resultado.getString("nome"));
                Utente.put("num_Utente", resultado.getString("numUtente"));
                Utente.put("data_nasc", resultado.getString("data_nasc"));
                Utente.put("localidade", resultado.getString("localidade"));
                Utente.put("morada", resultado.getString("morada"));
                Utente.put("cod_centro", resultado.getString("cod_centro"));
                Utente.put("data_adminVac", resultado.getString("data_adminVac"));
                Utente.put("cod_vacina", resultado.getString("cod_vacina"));
                Utente.put("tomas_Utente", resultado.getInt("tomasUtente"));
                Utente.put("cod_doencas", resultado.getString("cod_doencas"));

                String jsonString = Utente.toString();

                return jsonString;
            } else {
                return "O utilizador não foi encontrado";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "A conexão falhou";
    }

    @CrossOrigin
    @GetMapping("/Gestor")
    @ResponseBody
    public String ListarGestores() {

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3308/Utilizador";
        // String url = "jdbc:mysql://192.168.56.101:3308/Utilizador";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "SELECT * FROM Gestor";

        try (
                // Estabelecendo a conexão com o banco de dados
                Connection conn = DriverManager.getConnection(url, user, password);
                // Preparando a declaração SQL para a inserção
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            // Cria um array JSON para armazenar os resultados
            JSONArray jsonArray = new JSONArray();
            ResultSet resultado = stmt.executeQuery();

            // Verifica se há algum resultado retornado
            if (resultado.next()) {
                do {
                    JSONObject gestor = new JSONObject();
                    // Supondo que há colunas chamadas "coluna1", "coluna2", etc. Substitua-as pelos
                    // nomes reais das colunas no seu banco de dados
                    gestor.put("nome", resultado.getString("nome"));
                    gestor.put("username", resultado.getString("username"));
                    gestor.put("password", resultado.getString("password"));
                    gestor.put("email", resultado.getString("email"));
                    jsonArray.put(gestor);
                } while (resultado.next());
                String jsonArrayString = jsonArray.toString();
                return jsonArrayString;
            } else {
                System.out.println("Nenhum utente encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "A conexão falhou";

    }

    @CrossOrigin
    @GetMapping("/Utente")
    @ResponseBody
    public String ListarUtentes() {
        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://143.47.60.254:3308/Utilizador";
        // String url = "jdbc:mysql://192.168.56.101:3308/Utilizador";
        String user = "root";
        String password = "root";

        // Declaração SQL para a inserção de dados
        String sql = "SELECT * FROM Utente";

        try (
                // Estabelecendo a conexão com o banco de dados
                Connection conn = DriverManager.getConnection(url, user, password);
                // Preparando a declaração SQL para a inserção
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            // Executando a declaração SQL para inserir os dados
            ResultSet resultado = stmt.executeQuery();
            JSONArray Utentes = new JSONArray();
            // Verifica se há algum resultado retornado
            if (resultado.next()) {
                do {
                    JSONObject Utente = new JSONObject();

                    // Adicionar os campos e valores ao registro
                    Utente.put("username", resultado.getString("username"));
                    Utente.put("nome", resultado.getString("nome"));
                    Utente.put("num_Utente", resultado.getString("numUtente"));
                    Utente.put("data_nasc", resultado.getString("data_nasc"));
                    Utente.put("localidade", resultado.getString("localidade"));
                    Utente.put("morada", resultado.getString("morada"));
                    Utente.put("cod_centro", resultado.getString("cod_centro"));
                    Utente.put("data_adminVac", resultado.getString("data_adminVac"));
                    Utente.put("cod_vacina", resultado.getString("cod_vacina"));
                    Utente.put("tomas_Utente", resultado.getInt("tomasUtente"));
                    Utente.put("cod_doencas", resultado.getString("cod_doencas"));

                    Utentes.put(Utente);

                } while (resultado.next());
                return Utentes.toString();
            } else {
                return "Não existem Utentes Registados";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "A conexão falhou";
    }
}