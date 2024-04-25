package projet;

import java.io.*;
import java.net.*;
import java.sql.*;

public class MultiUserServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private ObjectOutputStream objectOutputStream;
        private ObjectInputStream objectInputStream;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                String message;
                String username = "-";
                String password = "-";
                if ((message = objectInputStream.readLine()) != null) {
                    // Authentication logic
                    String[] parts = message.split(":");
                    if (parts.length == 2) {
                        username = parts[0];
                        password = parts[1];
                    }
                }
                User user = fetchDataFromDatabase(username, password);
                objectOutputStream.writeObject(user);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private User fetchDataFromDatabase(String username, String password) {
            String url = "jdbc:mysql://localhost:3306/game_database";
            String dbUsername = "root";
            String dbPassword = "Mortada@2003";
            String query = "SELECT * FROM game_database.users WHERE username = ? AND password = ?";
            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    User user = new User();
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setName(resultSet.getString("name"));
                    user.setUser_id(resultSet.getInt("user_id"));
                    user.setCoins(resultSet.getInt("coins"));
                    user.setGender(resultSet.getInt("gender") == 1);
                    return user;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
}
