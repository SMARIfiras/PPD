package projet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main2 extends JFrame {
    private JButton classicGameButton;
    private JButton privateGameButton;
    private JButton championshipButton;
    private JButton classifyItemsButton;
    private JButton profileButton;
    
    private JButton exitButton;
    public Main2(User user) {
        setTitle("Welcome " + user.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));
        
        
        profileButton = new JButton("Profile");
        classicGameButton = new JButton(" Private Game");
        privateGameButton = new JButton("Classic Game");
        championshipButton = new JButton("Championship");
        classifyItemsButton = new JButton("Classify Items");
        exitButton = new JButton("Exit");

        
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           dispose();
           new ProfileGUI(user);
            }
        });
        classicGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           dispose();
           new Main3(user);
            }
        });

        privateGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Work On It");   
                }
        });

        championshipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Work On It");   
                 }
        });

        classifyItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Work On It");   
              }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	dispose();
                new MenuGUI();
            }
        });
        add(profileButton);
        add(classicGameButton);
        add(privateGameButton);
        add(championshipButton);
        add(classifyItemsButton);
        add(exitButton);
        setVisible(true);
    }
}
