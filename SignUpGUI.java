package projet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;

public class SignUpGUI extends JFrame {
    private JTextField usernameField;
    private JTextField nameField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton signUpButton;
    private JButton backButton;
    private JLabel errorLabel;
    private JLabel successLabel;
    private User user = new User();
	public SignUpGUI() {
    	setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false); 
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(8, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel genderLabel = new JLabel("Gender:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        errorLabel = new JLabel("");
        successLabel = new JLabel("");

        usernameField = new JTextField();
        nameField = new JTextField();
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        
        signUpButton = new JButton("Next");
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		dispose();
		new MenuGUI();
			}
		});
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String name = nameField.getText();
                String gender = "";
                if (maleRadioButton.isSelected()) {
                    gender = "Male";
                } else if (femaleRadioButton.isSelected()) {
                    gender = "Female";
                }
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                if (username.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gender.isEmpty()) {
                    errorLabel.setText("Please fill in all fields.");
                }
                 else if (!username.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
                    errorLabel.setText("Username can only contain letters and numbers, and must start with a letter.");
                 } else if (!name.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
                    errorLabel.setText("Name can only contain letters and numbers, and must start with a letter.");
                 } else if (name.length() < 7) {
                    errorLabel.setText("Name must be at least 7 characters long.");
        	                   } else if (password.length() < 8) {
                    errorLabel.setText("Password must be at least 8 characters long.");
                } else if (!password.equals(confirmPassword)) {
                	errorLabel.setText("Passwords do not match.");
               
                } else {
                    if (isUsernameAvailable(username)) {
                    	if (signupDataBase(username, name, confirmPassword, gender)) {
                    		  successLabel.setText("Sign up successful!");
                                 try {
              					new Thread().sleep(2000);
              					dispose();
              					new Main2(user);
              				} catch (InterruptedException e1) {
              					e1.printStackTrace();
              				} 	  
                    	}
                    	else {
                            errorLabel.setText("Server Error");
                    	} 
                    	
                    } else {
                   errorLabel.setText("Username already exists. Please choose another one.");
                    }
                    
                }
            }
        });
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                errorLabel.setText("");
                successLabel.setText(""); 
           
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });

        nameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                errorLabel.setText("");
                successLabel.setText(""); 
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                errorLabel.setText("");
                successLabel.setText(""); 
           
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        confirmPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                errorLabel.setText("");
                successLabel.setText(""); 
           
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        add(usernameLabel);
        add(usernameField);
        add(nameLabel);
        add(nameField);
        add(genderLabel);
        add(maleRadioButton);
        add(new JLabel());
        add(femaleRadioButton);
        add(passwordLabel);
        add(passwordField);
        add(confirmPasswordLabel);
        add(confirmPasswordField);
        //add(new JLabel());
        add(backButton);
        add(signUpButton);
        add(errorLabel); 
        add(successLabel); 
        errorLabel.setForeground(Color.RED);
        successLabel.setForeground(Color.GREEN);
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        successLabel.setHorizontalAlignment(JLabel.CENTER);

        setVisible(true);
    }
    private boolean isUsernameAvailable(String username) {
        String url = "jdbc:mysql://localhost:3306/game_database";
        String dbUsername = "root";
        String dbPassword = "Mortada@2003";
        String query = "SELECT * FROM game_database.users WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next(); 
            } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    private boolean signupDataBase(String username, String name, String password, String gender) {
        String url = "jdbc:mysql://localhost:3306/game_database";
        String dbUsername = "root";
        String dbPassword = "Mortada@2003";
        
        if (gender.equals("Male")) {
            user.setGender(true);
         
        } else {
            user.setGender(false);
        }
        
        user.setCoins(2000);
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        
        String query = "INSERT INTO game_database.users (username, password, gender, coins, name ) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.getGender());
            statement.setInt(4, user.getCoins());
            statement.setString(5, user.getName());
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
