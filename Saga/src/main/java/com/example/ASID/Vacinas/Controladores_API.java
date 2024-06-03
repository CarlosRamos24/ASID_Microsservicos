package com.example.ASID.Vacinas;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@CrossOrigin
@RequestMapping(value = "/Saga", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controladores_API {

    public void RemoverCentro(String id) {
        try {
            // URL e corpo da solicitação
            String url = "http://centro:8090/CentroDeVacinacao/Remover";

            // Criação da conexão
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configuração da requisição
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // Envia o corpo da solicitação
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(id);
                wr.flush();
            }

            // Leitura da resposta
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Exibe a resposta
                System.out.println("Response: " + response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            RemoverCentro(id);
        }
    }

    @CrossOrigin
    @PostMapping
    @ResponseBody
    public String RegistarCentroComStock(@RequestBody String dados) {

        JSONObject jsonObject = new JSONObject(dados);
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        // Adicionar campos ao objeto JSON
        String codigo_centro = jsonObject.getString("codigo");
        int numPostos = jsonObject.getInt("numPostos");
        String morada = jsonObject.getString("morada");
        String localidade = jsonObject.getString("localidade");
        int numMaxVacDia = jsonObject.getInt("numMaxVacDia");
        String codVacina = jsonObject.getString("codVacina");
        String nome = jsonObject.getString("nome");
        String stock = jsonObject.getString("stock");

        // Adicionar campos ao objeto JSON
        jsonObject2.put("codigo", codigo_centro);
        jsonObject2.put("numPostos", numPostos);
        jsonObject2.put("morada", morada);
        jsonObject2.put("localidade", localidade);
        jsonObject2.put("numMaxVacDia", numMaxVacDia);
        jsonObject2.put("cod_gestor", "");

        jsonObject3.put("codigo", codVacina);
        jsonObject3.put("nome", nome);
        jsonObject3.put("fabricante", "");
        jsonObject3.put("doseIndividual", "0.3");
        jsonObject3.put("tomas", "1");
        jsonObject3.put("idadeMinima", "0");
        jsonObject3.put("idadeMaxima", "70");
        jsonObject3.put("listaEfeitos", " ");
        jsonObject3.put("doencasRestritas", " ");
        jsonObject3.put("cod_centro", codigo_centro);
        jsonObject3.put("efeitosSecundarios", "Nenhum");
        jsonObject3.put("stock", stock);

        /////// Registar Centro
        try {
            // URL e corpo da solicitação
            String url = "http://centro:8090/CentroDeVacinacao/Registar";
            String jsonInputString = jsonObject2.toString();

            // Criação da conexão
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configuração da requisição
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // Envia o corpo da solicitação
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(jsonInputString);
                wr.flush();
            }

            // Leitura da resposta
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Exibe a resposta
                System.out.println("Response: " + response.toString());
                if (response.toString() == "Erro") {
                    return "Erro";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro";
        }

        ///////// Registar stock
        try {
            // URL e corpo da solicitação
            String url = "http://vacina:8093/Vacinas";
            String jsonInputString2 = jsonObject3.toString();

            // Criação da conexão
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configuração da requisição
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // Envia o corpo da solicitação
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(jsonInputString2);
                wr.flush();
            }

            // Leitura da resposta
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Exibe a resposta
                System.out.println("Response: " + response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            RemoverCentro(codigo_centro);
            return "Erro";
        }
        return "Sucesso";

    }

}