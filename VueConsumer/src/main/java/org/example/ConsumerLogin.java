package org.example;

import ch.qos.logback.classic.Level;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.Backend.Utilizador;
import org.example.Frontend.EscolherRotas;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.sql.*;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ConsumerLogin {
    public static void main(String[] args) {

        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "meu-grupo");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.WARN);

        // Criação do consumidor
        try (Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(consumerProperties)) {

            String topicName = "topicoDadosLogin";
            kafkaConsumer.subscribe(Collections.singletonList(topicName));


            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    String pedido = record.value();
                    JSONObject pagamento = new JSONObject(pedido);


                    //String url = "jdbc:mysql://192.168.56.10:3306/TubMobile";
                    String url = "jdbc:mysql://192.168.217.132:3306/TubMobile";
                    String usuario = "user";
                    String senha = "pass";

                    // Dados para inserção
                    String nome = pagamento.getString("nome");
                    String pass = pagamento.getString("password");

                    try {
                        // Carregar o driver JDBC
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Estabelecer a conexão com o banco de dados
                        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
                            // Consulta SQL para verificar o usuário com o nome de usuário e senha fornecidos
                            String sql = "SELECT * FROM Utilizadores WHERE username = ? AND password = ?";

                            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                                // Atribuir valores aos parâmetros da consulta
                                statement.setString(1, String.valueOf(nome));
                                statement.setString(2, String.valueOf(pass));

                                // Executar a consulta
                                try (ResultSet resultSet = statement.executeQuery()) {
                                    if (resultSet.next()) {
                                        ProducerResultadoLogin prl = new ProducerResultadoLogin();
                                        prl.producerResultadoLogin("1");
                                    } else {
                                        // Nenhum usuário correspondente encontrado
                                        System.out.println("Usuário não encontrado ou senha incorreta.");
                                    }
                                }
                            }
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            System.err.println("Erro no consumidor: " + e.getMessage());
        }
    }
}

