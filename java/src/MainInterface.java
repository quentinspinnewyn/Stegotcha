package mainPackage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Alexandre on 17/03/2016.
 */
public class MainInterface {

    public MainInterface() {
        Dimension dim = new Dimension(1200, 1000);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(true);
        f.setSize(dim);

        JPanel p = new JPanel();
        p.setLayout(null);

        JLabel infoBannier = new JLabel();
        infoBannier.setBounds(500, 927, 1000, 30);
        infoBannier.setForeground(Color.RED);
        infoBannier.setFont(new Font("", Font.PLAIN, 12));
        p.add(infoBannier);

        infoBannier.setText("Test error");

        JTextArea tchat = new JTextArea();
        tchat.setBounds(0, 0, 800, 800);
        tchat.setBackground(Color.WHITE);
        tchat.setFocusable(false);
        tchat.setForeground(Color.BLACK);
        tchat.setLineWrap(true);
        tchat.setFont(new Font("", Font.BOLD, 22));
        p.add(tchat);

        tchat.setText("Test TEXTAREA");

        JLabel coverIndicator = new JLabel("The visible message");
        coverIndicator.setBounds(50, 790, 300, 45);
        coverIndicator.setForeground(Color.LIGHT_GRAY);
        coverIndicator.setFont(new Font("", Font.BOLD, 10));
        p.add(coverIndicator);

        JTextArea coverMessage = new JTextArea();
        coverMessage.setBounds(25, 825, 350, 100);
        coverMessage.setBackground(Color.white);
        coverMessage.setForeground(Color.BLACK);
        coverMessage.setFont(new Font("", Font.BOLD, 15));
        coverMessage.setLineWrap(true);
        p.add(coverMessage);

        JLabel secretIndicator = new JLabel("The secret message (Optional)");
        secretIndicator.setBounds(425, 790, 300, 45);
        secretIndicator.setForeground(Color.LIGHT_GRAY);
        secretIndicator.setFont(new Font("", Font.BOLD, 10));
        p.add(secretIndicator);

        JTextArea secretMessage = new JTextArea();
        secretMessage.setBounds(400, 825, 350, 100);
        secretMessage.setBackground(Color.WHITE);
        secretMessage.setForeground(Color.GRAY);
        secretMessage.setFont(new Font("", Font.BOLD, 15));
        secretMessage.setLineWrap(true);
        p.add(secretMessage);

        JTextField userDest = new JTextField();
        userDest.setBounds(800, 840, 150, 20);
        userDest.setBackground(Color.WHITE);
        userDest.setForeground(Color.BLACK);
        userDest.setFont(new Font("", Font.BOLD, 12));
        p.add(userDest);


        f.setContentPane(p);
        f.setVisible(true);
    }

    private boolean sendable(JTextArea coverMsg, JTextArea secretMsg, JTextField pseudo) {
        if (coverMsg.getText().isEmpty()) {
            return  false;
        }
        if (!secretMsg.getText().isEmpty()) {
            if (pseudo.getText().isEmpty() || coverMsg.getText().isEmpty() || coverMsg.getText().length()<=secretMsg.getText().length()+50) {
                return false;
            }
        }
        if (!pseudo.getText().isEmpty()) {
            if (coverMsg.getText().isEmpty() || coverMsg.getText().isEmpty() || coverMsg.getText().length()<=secretMsg.getText().length()+50) {
                return  false;
            }
        }
        return  true;
    }

}
