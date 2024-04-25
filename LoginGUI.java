package projet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;
    private JLabel successLabel;
    private JButton backButton;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private User user;

    public LoginGUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        errorLabel = new JLabel("");
        successLabel = new JLabel("");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuGUI();
            }
        });
        loginButton = new JButton("Next");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    connectToServer(username, password);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(backButton);
        add(loginButton);
        add(errorLabel);
        add(successLabel);
        errorLabel.setForeground(Color.RED);
        successLabel.setForeground(Color.GREEN);
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        successLabel.setHorizontalAlignment(JLabel.CENTER);
        setVisible(true);
    }

    private void connectToServer(String username, String password) throws IOException, ClassNotFoundException {
        socket = new Socket("192.168.43.76", 12345);
        writer = new PrintWriter(socket.getOutputStream(), true);
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        sendAuthenticationRequest(username, password);
        user = (User) objectInputStream.readObject();
        if (user != null) {
            successLabel.setText("Login successful!");
            new Main2(user);
        } else {
            errorLabel.setText("Invalid username or password. Please try again.");
        }
    }

    private void sendAuthenticationRequest(String username, String password) {
        writer.println(username + ":" + password);
    }
}
