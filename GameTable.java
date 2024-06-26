package projet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class GameTable extends JFrame {
    User User = new User();
    JMenuBar exitBar = new JMenuBar();
    private JButton exitButton = new JButton("Exit");
    JPanel card1Panel = new JPanel();
    JPanel card2Panel = new JPanel();
    JPanel tablePanel = new JPanel();
    JPanel MiddlePanel = new JPanel();
    JLabel user1LabelName = new JLabel();
    JLabel user2LabelName = new JLabel("User2");
    JLabel MiddleLabel1 = new JLabel();
    JLabel MiddleLabel2 = new JLabel();
    JLabel MiddleLabel3 = new JLabel();
    JLabel MiddleLabel4 = new JLabel();
    JLabel MiddleLabel5 = new JLabel();
    JLabel MiddleLabel6 = new JLabel();
    JLabel MiddleLabel7 = new JLabel();
    JLabel MiddleLabel8 = new JLabel();
    JLabel GreatLabel1 = new JLabel();
    JLabel GreatLabel2 = new JLabel();
    int User1score = 0;
    int User2score = 0;
    final int NUM_ROUNDS = 3;

    JLabel user1LabelScore = new JLabel();
    JLabel user2LabelScore = new JLabel();
    JLabel MiddleLabelCard = new JLabel();
    private JButton user1Button1 = new JButton();
    private JButton user1Button2 = new JButton();
    private JButton user1Button3 = new JButton();
    private JButton user1Button4 = new JButton();
    private JButton user1Button5 = new JButton();
    private JButton user1Button6 = new JButton();
    private JButton user1Button7 = new JButton();
    private JButton user1Button8 = new JButton();
    private JButton user2Button1 = new JButton();
    private JButton user2Button2 = new JButton();
    private JButton user2Button3 = new JButton();
    private JButton user2Button4 = new JButton();
    private JButton user2Button5 = new JButton();
    private JButton user2Button6 = new JButton();
    private JButton user2Button7 = new JButton();
    private JButton user2Button8 = new JButton();
    Table table = new Table();
    GameCards Cards = new GameCards();
    Card User1Card = new Card();
    Card User2Card = new Card();
    Card MiddleCard = new Card();

    public GameTable(User User) {
        this.User = User;
        user1LabelName = new JLabel(User.getName());
        User1Card = table.ChooseNewCard(Cards.cards);
        User2Card = table.ChooseNewCard(Cards.cards);
        MiddleCard = table.ChooseNewCard(Cards.cards);
        User.setCoins(User.getCoins() - 500);
        setTitle("Classic Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        card1Panel.setLayout(new GridLayout(1, 11));
        card2Panel.setLayout(new GridLayout(1, 11));
        tablePanel.setLayout(new GridLayout(5, 1));
        String objectButton;
        exitBar.add(exitButton);
        card1Panel.add(user1LabelName);
        String Score1 = String.valueOf(User1score);
        user1LabelScore.setText(Score1);
        card1Panel.add(user1LabelScore);
        card1Panel.add(new JLabel());
        card1Panel.add(user1Button1);
        card1Panel.add(user1Button2);
        card1Panel.add(user1Button3);
        card1Panel.add(user1Button4);
        card1Panel.add(user1Button5);
        card1Panel.add(user1Button6);
        card1Panel.add(user1Button7);
        card1Panel.add(user1Button8);
        tablePanel.add(card1Panel);

        tablePanel.add(GreatLabel1);

        MiddlePanel.setLayout(new GridLayout(1, 11));

        MiddlePanel.add(new JPanel());
        MiddlePanel.add(new JPanel());
        MiddlePanel.add(new JPanel());

        MiddlePanel.add(MiddleLabel1);
        MiddlePanel.add(MiddleLabel2);
        MiddlePanel.add(MiddleLabel3);
        MiddlePanel.add(MiddleLabel4);
        MiddlePanel.add(MiddleLabel5);
        MiddlePanel.add(MiddleLabel6);
        MiddlePanel.add(MiddleLabel7);
        MiddlePanel.add(MiddleLabel8);

        tablePanel.add(MiddlePanel);

        tablePanel.add(GreatLabel2);

        card2Panel.add(user2LabelName);
        String Score2 = String.valueOf(User2score);
        user2LabelScore.setText(Score2);
        card2Panel.add(user2LabelScore);
        card2Panel.add(new JLabel());
        card2Panel.add(user2Button1);
        card2Panel.add(user2Button2);
        card2Panel.add(user2Button3);
        card2Panel.add(user2Button4);
        card2Panel.add(user2Button5);
        card2Panel.add(user2Button6);
        card2Panel.add(user2Button7);
        card2Panel.add(user2Button8);
        tablePanel.add(card2Panel);

        add(exitBar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        user1Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check1(1, user1Button1.getText());
            }
        });

        user1Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check1(1, user1Button2.getText());
            }
        });

        user1Button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check1(1, user1Button3.getText());
            }
        });

        user1Button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check1(1, user1Button4.getText());
            }
        });

        user1Button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check1(1, user1Button5.getText());
            }
        });

        user1Button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check1(1, user1Button6.getText());
            }
        });

        user1Button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check1(1, user1Button7.getText());
            }
        });

        user1Button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check1(1, user1Button8.getText());
            }
        });

        user2Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check2(2, user2Button1.getText());
            }
        });

        user2Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check2(2, user2Button2.getText());
            }
        });

        user2Button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check2(2, user2Button3.getText());
            }
        });

        user2Button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check2(2, user2Button4.getText());
            }
        });

        user2Button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check2(2, user2Button5.getText());
            }
        });

        user2Button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check2(2, user2Button6.getText());
            }
        });

        user2Button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check2(2, user2Button7.getText());
            }
        });

        user2Button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check2(2, user2Button8.getText());
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Main2(User);
            }
        });

        update();
        setVisible(true);
    }

    private void check1(int userNumber, String ObjectString) {
        int ObjectInt = Integer.parseInt(ObjectString);
        if (User1Card.Objects.contains(ObjectInt)) {
            int j = 0;
            int i = 0;
            while (i < MiddleCard.NumberofObjects) {
                if (MiddleCard.Objects.get(i) == ObjectInt) {
                    if (User1score <= NUM_ROUNDS - 1) {
                        User1score++;
                        great(1, true);
                        MiddleCard = User1Card;
                        User1Card = table.ChooseNewCard(Cards.cards);
                        update();
                        i = 8;
                        j = 1;
                    } else {
                        String message = User.getName() + " :  wins!     : 1000 Coins";
                        String[] options = {"Replay", "Exit"};
                        User.setCoins(User.getCoins() + 1000);
                        updateUserDatabaseCoins();
                        int choice = JOptionPane.showOptionDialog(this, message, "Congratulations!",
                                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
                                options[0]);

                        if (choice == JOptionPane.YES_OPTION) {
                            dispose();
                            new Main3(User);
                        } else if (choice == JOptionPane.NO_OPTION) {
                            dispose();
                            new Main2(User);
                        }
                        i = 8;
                        j = 1;
                    }
                }
                i++;
            }
            if (j == 0) {
                User1score--;
                great(1, false);
                update();
            }
        }
    }

    private void check2(int userNumber, String ObjectString) {
        int ObjectInt = Integer.parseInt(ObjectString);
        if (User2Card.Objects.contains(ObjectInt)) {
            int j = 0;
            int i = 0;
            while (i < MiddleCard.NumberofObjects) {
                if (MiddleCard.Objects.get(i) == ObjectInt) {
                    if (User2score <= NUM_ROUNDS - 1) {
                        User2score++;
                        MiddleCard = User2Card;
                        User2Card = table.ChooseNewCard(Cards.cards);
                        update();
                        i = 8;
                        j = 1;
                        great(2, true);
                    } else {

                        String message = "User 2 :  wins!";
                        String[] options = {"Replay", "Exit"};
                        updateUserDatabaseCoins();
                        int choice = JOptionPane.showOptionDialog(this, message, "Congratulations!",
                                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
                                options[0]);
                        if (choice == JOptionPane.YES_OPTION) {
                            dispose();
                            new Main3(User);
                        } else if (choice == JOptionPane.NO_OPTION) {
                            dispose();
                            new Main2(User);
                        }
                        MiddleCard = User2Card;
                        User2Card = table.ChooseNewCard(Cards.cards);
                        update();
                        i = 8;
                        j = 1;
                    }
                }
                i++;

            }
            if (j == 0) {
                User2score--;
                great(2, false);
                update();
            }

        }
    }

    private void great(int userNumber, boolean found) {
        JLabel greatLabel;
        if (userNumber == 1) {
            greatLabel = GreatLabel1;
        } else {
            greatLabel = GreatLabel2;
        }
        if (found) {
        	 greatLabel.setForeground(Color.GREEN);
             greatLabel.setText("              Great ");
        } else {
        	greatLabel.setForeground(Color.RED);
            greatLabel.setText("              Couldn't Find The Object.");
        }
        Timer timer = new Timer(3000, e -> greatLabel.setText(""));
        timer.setRepeats(false);
        timer.start();
    }

    private void update() {
        String objectButton;
        objectButton = String.valueOf(MiddleCard.Objects.get(0));
        MiddleLabel1.setText(objectButton);
        objectButton = String.valueOf(MiddleCard.Objects.get(1));
        MiddleLabel2.setText(objectButton);
        objectButton = String.valueOf(MiddleCard.Objects.get(2));
        MiddleLabel3.setText(objectButton);
        objectButton = String.valueOf(MiddleCard.Objects.get(3));
        MiddleLabel4.setText(objectButton);
        objectButton = String.valueOf(MiddleCard.Objects.get(4));
        MiddleLabel5.setText(objectButton);
        objectButton = String.valueOf(MiddleCard.Objects.get(5));
        MiddleLabel6.setText(objectButton);
        objectButton = String.valueOf(MiddleCard.Objects.get(6));
        MiddleLabel7.setText(objectButton);
        objectButton = String.valueOf(MiddleCard.Objects.get(7));
        MiddleLabel8.setText(objectButton);
        objectButton = String.valueOf(User1Card.Objects.get(0));
        user1Button1.setText(objectButton);
        objectButton = String.valueOf(User1Card.Objects.get(1));
        user1Button2.setText(objectButton);
        objectButton = String.valueOf(User1Card.Objects.get(2));
        user1Button3.setText(objectButton);
        objectButton = String.valueOf(User1Card.Objects.get(3));
        user1Button4.setText(objectButton);
        objectButton = String.valueOf(User1Card.Objects.get(4));
        user1Button5.setText(objectButton);
        objectButton = String.valueOf(User1Card.Objects.get(5));
        user1Button6.setText(objectButton);
        objectButton = String.valueOf(User1Card.Objects.get(6));
        user1Button7.setText(objectButton);
        objectButton = String.valueOf(User1Card.Objects.get(7));
        user1Button8.setText(objectButton);
        objectButton = String.valueOf(User2Card.Objects.get(0));
        user2Button1.setText(objectButton);
        objectButton = String.valueOf(User2Card.Objects.get(1));
        user2Button2.setText(objectButton);
        objectButton = String.valueOf(User2Card.Objects.get(2));
        user2Button3.setText(objectButton);
        objectButton = String.valueOf(User2Card.Objects.get(3));
        user2Button4.setText(objectButton);
        objectButton = String.valueOf(User2Card.Objects.get(4));
        user2Button5.setText(objectButton);
        objectButton = String.valueOf(User2Card.Objects.get(5));
        user2Button6.setText(objectButton);
        objectButton = String.valueOf(User2Card.Objects.get(6));
        user2Button7.setText(objectButton);
        objectButton = String.valueOf(User2Card.Objects.get(7));
        user2Button8.setText(objectButton);
        objectButton = String.valueOf(User1score);
        user1LabelScore.setText(objectButton);
        objectButton = String.valueOf(User2score);
        user2LabelScore.setText(objectButton);

    }

    private void updateUserDatabaseCoins() {
        String url = "jdbc:mysql://localhost:3306/game_database";
        String dbUsername = "root";
        String dbPassword = "Mortada@2003";
        String query = "UPDATE game_database.users SET coins = ? WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, User.getCoins());
            statement.setString(2, User.getUsername());
            int rowsAffected = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
