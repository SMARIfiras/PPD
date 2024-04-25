package projet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class UserLoginSignupGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField countryField;
    private JButton loginButton;
    private JButton signupButton;

    public UserLoginSignupGUI() {
        setTitle("User Login/Sign-up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(2, 3));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel countryLabel = new JLabel("Country:");
        JLabel genderLabel = new JLabel("Gender:");
        
        //JLabel countryLabel = new JLabel("Country:");
       // JLabel countryLabel = new JLabel("Country:");
        
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        countryField = new JTextField();

        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (UserAuthentication.loginUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // Proceed with the game logic or open the game window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String country = countryField.getText();
                
                if (UserAuthentication.signupUser(username, password, country)) {
                    JOptionPane.showMessageDialog(null, "Sign-up successful!");
                    // Proceed with the game logic or open the game window
                } else {
                    JOptionPane.showMessageDialog(null, "Sign-up failed. Please try again.");
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField); 
        add(countryLabel);
        add(countryField);
        add(loginButton);
        add(signupButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserLoginSignupGUI();
            }
        });
    }
}

class UserAuthentication {
    private static final String URL = "jdbc:mysql://localhost:3306/game_database";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static boolean loginUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if user exists with provided credentials
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
     public static boolean signupUser(String username, String password, String country) {
        String query = "INSERT INTO users (username, password, country) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, country);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Returns true if user was successfully inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
