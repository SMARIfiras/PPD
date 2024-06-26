package projet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class ProfileGUI extends JFrame {
    private User user = new User();
    private User user1= new User();
    private JLabel usernameLabel;
    private JLabel nameLabel;
    private JLabel userIdLabel;
    private JLabel coinsLabel;
    private JLabel genderLabel;
    private JButton editUsernameButton;
    private JButton editNameButton;
    private JButton editGenderButton;
    private JButton saveChangesButton;
    private JButton backButton;  
	private JLabel oldPasswordLabel;
	private JLabel newPasswordLabel;
	private JLabel confirmmedPasswordLabel;
	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField confirmedPasswordField;
	private JButton editPasswordButton;

    public ProfileGUI(User user) {
    	this.user = user;
    	copyUser();
        setTitle("Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        
        setLayout(new GridLayout(9, 2));

        usernameLabel = new JLabel("Username: " + user.getUsername());
        nameLabel = new JLabel("Name: " + user.getName());
        userIdLabel = new JLabel("User ID: " + user.getUser_id());
        coinsLabel = new JLabel("Coins: " + user.getCoins());
        genderLabel = new JLabel("Gender: " + (user.getGender() ? "Male" : "Female"));

        backButton = new JButton("Back");
        editUsernameButton = new JButton("Edit Username");
        editNameButton = new JButton("Edit Name");
        editGenderButton = new JButton("Edit Gender");
    	editPasswordButton = new JButton("Edit Password");
        saveChangesButton = new JButton("Save Changes");
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new Main2(user);
            }
        });
        editUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String newUsername = "";
                    JPanel panel = new JPanel(new GridLayout(0,1));
                    JTextField usernameField = new JTextField();
                    JLabel errorLabel = new JLabel("");
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setVisible(true);
                    usernameField.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            errorLabel.setText("");
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                        }
                    });

                    panel.add(new JLabel("Enter new username:"));
                    panel.add(usernameField);
                    panel.add(errorLabel);
                    int result;
                    do {
                        result = JOptionPane.showConfirmDialog(null, panel, "Change UserName", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                            return;
                          }
                    newUsername = usernameField.getText();
                    if(newUsername.equals(user1.getUsername())) {
                        errorLabel.setText("Same username!");
                    }
                    else if (!newUsername.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
                        errorLabel.setText("Invalid username format!");
                    } else if (newUsername.length() < 7) {
                        errorLabel.setText("Username too short!");
                    } else if (!isUsernameAvailable(newUsername)) {
                        user1.setUsername(newUsername);
                        usernameLabel.setText("Username: " + user1.getUsername());                     
                        return;
                    } else {
                        errorLabel.setText("Username already exists!");
                    }
                }while (result == JOptionPane.OK_OPTION);
            }
        });
        editNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String newName = "";
                    JPanel panel = new JPanel(new GridLayout(0,1));
                    JTextField nameField = new JTextField();
                    JLabel errorLabel = new JLabel("");
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setVisible(true);
                    nameField.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            errorLabel.setText("");
                        }
                      
                        @Override
                        public void focusLost(FocusEvent e) {
                        }
                    });

                    panel.add(new JLabel("Enter new Name:"));
                    panel.add(nameField);
                    panel.add(errorLabel);
                    int result;
                    do {
                        result = JOptionPane.showConfirmDialog(null, panel, "Change Name", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                            return;
                          }
                    newName = nameField.getText();
                    if(newName.equals(user1.getName())) {
                        errorLabel.setText("Same Name!");
                    }
                    else if (!newName.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
                        errorLabel.setText("Invalid Nname format!");
                    } else if (newName.length() < 7) {
                        errorLabel.setText("Name too short!");
                    } else { 
                        user1.setName(newName);
                        nameLabel.setText("Name: " + user1.getName());                     
                      return;
                    }
                }while (result == JOptionPane.OK_OPTION);
            }
        });
        editGenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Male", "Female"};
                int choice = JOptionPane.showOptionDialog(null, "Select gender:", "Edit Gender",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (choice != JOptionPane.CLOSED_OPTION) {
                    boolean newGender = (choice == 0);
                    user1.setGender(newGender);
                    genderLabel.setText("Gender: " + (user1.getGender() ? "Male" : "Female"));
                }
            }
        });
        editPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new GridLayout(0, 1));
                JPasswordField oldPasswordField = new JPasswordField();
                JPasswordField newPasswordField = new JPasswordField();
                JPasswordField confirmedPasswordField = new JPasswordField();
                JLabel messageLabel = new JLabel();
                messageLabel.setForeground(Color.RED);
                FocusListener focusListener = new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        messageLabel.setText("");
                    }
                };
                oldPasswordField.addFocusListener(focusListener);
                newPasswordField.addFocusListener(focusListener);
                confirmedPasswordField.addFocusListener(focusListener);

                panel.add(new JLabel("Enter Old Password:"));
                panel.add(oldPasswordField);
                panel.add(new JLabel("Enter New Password:"));
                panel.add(newPasswordField);
                panel.add(new JLabel("Confirm New Password:"));
                panel.add(confirmedPasswordField);
                panel.add(messageLabel);

                int result;
                do {
                    result = JOptionPane.showConfirmDialog(null, panel, "Change Password", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                        return;
                        }

                    String oldPassword = new String(oldPasswordField.getPassword());
                    String newPassword = new String(newPasswordField.getPassword());
                    String confirmedPassword = new String(confirmedPasswordField.getPassword());

                    if (!oldPassword.equals(user1.getPassword())) {
                        messageLabel.setText("Incorrect old password!");
                    } else if (newPassword.length() < 8) {
                        messageLabel.setText("New password must be at least 8 characters long!");
                    } else if (!newPassword.equals(confirmedPassword)) {
                        messageLabel.setText("New passwords do not match!");
                    } else {
                        user1.setPassword(newPassword);
                        //JOptionPane.showMessageDialog(null, "Password changed successfully!");
                        return;
                    }
                } while (result == JOptionPane.OK_OPTION);
            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	updateUserDatabase();
                JOptionPane.showMessageDialog(null, "Changes saved successfully!");   
            }
        });

        add(usernameLabel);
        add(editUsernameButton);
        add(nameLabel);
        add(editNameButton);
        add(userIdLabel);
        add(new JLabel()); 
        add(coinsLabel);
        add(new JLabel()); 
        add(genderLabel);
        add(editGenderButton);
        add(new JLabel()); 
        add(editPasswordButton);     
        add(new JLabel());
        add (new JLabel());
        add(backButton);
        add(saveChangesButton);
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
            return resultSet.next(); 
            } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
//    private boolean isNameAvailable(String Name) {
//        String url = "jdbc:mysql://localhost:3306/game_database";
//        String dbUsername = "root";
//        String dbPassword = "Mortada@2003";
//        String query = "SELECT * FROM game_database.users WHERE name = ?";
//        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, Name);
//            ResultSet resultSet = statement.executeQuery();
//            return resultSet.next(); 
//            } catch (SQLException ex) {
//            ex.printStackTrace();
//            return false;
//        }
//    }
    private void updateUserDatabase() {
        String url = "jdbc:mysql://localhost:3306/game_database";
        String dbUsername = "root";
        String dbPassword = "Mortada@2003";
        String query = "UPDATE game_database.users SET username = ?, password = ?, gender = ?, coins = ?, name = ? WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user1.getUsername());
            statement.setString(2, user1.getPassword());
            statement.setBoolean(3, user1.getGender());
            statement.setInt(4, user1.getCoins());
            statement.setString(5, user1.getName());
            statement.setString(6, user.getUsername());
            int rowsAffected = statement.executeUpdate(); 
            //System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
public void copyUser() {
user1.setCoins(user.getCoins());
user1.setGender(user.getGender());
user1.setName(user.getName());
user1.setUser_id(user.getUser_id());
user1.setPassword(user.getPassword());
user1.setUsername(user.getUsername());
}
}
