package mainPackage;

import javax.swing.*;
import java.awt.*;

/**
 * The windowed interface of the program.
 * Warning /!\ Resizable=false !
 * Created by Alexandre on 17/03/2016.
 */
public class MainInterface {


    public MainInterface() {
        Dimension dim = new Dimension(1000, 800);

        JFrame f = new JFrame();
        f.setTitle("Chat stéganographique");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(dim);

        JPanel p = new JPanel();
        p.setLayout(null);

        JLabel infoBannier = new JLabel();
        infoBannier.setBounds(400, 727, 800, 30);
        infoBannier.setForeground(Color.RED);
        infoBannier.setFont(new Font("", Font.PLAIN, 12));
        p.add(infoBannier);

        infoBannier.setText("Test error");

        JTextArea tchat = new JTextArea();
        tchat.setBounds(5, 2, 600, 600);
        tchat.setBackground(Color.WHITE);
        tchat.setFocusable(false);
        tchat.setForeground(Color.BLACK);
        tchat.setLineWrap(true);
        tchat.setFont(new Font("", Font.BOLD, 20));
        p.add(tchat);

        addText(tchat, "User1 : Bonjour petit enfant");

        JTextArea connectedUsers = new JTextArea("Connected Users :");
        connectedUsers.setBounds(650, 2, 327, 600);
        connectedUsers.setBackground(Color.WHITE);
        connectedUsers.setFocusable(false);
        connectedUsers.setForeground(Color.BLACK);
        connectedUsers.setLineWrap(true);
        connectedUsers.setFont(new Font("", Font.BOLD, 15));
        p.add(connectedUsers);

        addText(connectedUsers, "User1");
        addText(connectedUsers, "User2");

        JLabel coverIndicator = new JLabel("The visible message");
        coverIndicator.setBounds(50, 590, 300, 45);
        coverIndicator.setForeground(Color.LIGHT_GRAY);
        coverIndicator.setFont(new Font("", Font.BOLD, 10));
        p.add(coverIndicator);

        JTextArea coverMessage = new JTextArea();
        coverMessage.setBounds(25, 625, 350, 100);
        coverMessage.setBackground(Color.white);
        coverMessage.setForeground(Color.BLACK);
        coverMessage.setFont(new Font("", Font.BOLD, 15));
        coverMessage.setLineWrap(true);
        p.add(coverMessage);

        JLabel secretIndicator = new JLabel("The secret message (Optional)");
        secretIndicator.setBounds(425, 590, 300, 45);
        secretIndicator.setForeground(Color.LIGHT_GRAY);
        secretIndicator.setFont(new Font("", Font.BOLD, 10));
        p.add(secretIndicator);

        JTextArea secretMessage = new JTextArea();
        secretMessage.setBounds(400, 625, 350, 100);
        secretMessage.setBackground(Color.WHITE);
        secretMessage.setForeground(Color.GRAY);
        secretMessage.setFont(new Font("", Font.BOLD, 15));
        secretMessage.setLineWrap(true);
        p.add(secretMessage);

        JTextField userDest = new JTextField();
        userDest.setBounds(800, 640, 150, 20);
        userDest.setBackground(Color.WHITE);
        userDest.setForeground(Color.BLACK);
        userDest.setFont(new Font("", Font.BOLD, 12));
        p.add(userDest);


        f.setContentPane(p);
        f.setVisible(true);
    }

    /**
     * Add a String to the TextArea automatically
     * @param txtArea
     * @param add
     */
    private void addText(JTextArea txtArea, String add) {
        String tmp = txtArea.getText();
        txtArea.setText(tmp+"\r\n"+add);
    }

    /**
     * Check if the user is allowed to send his message
     * @param coverMsg
     * @param secretMsg
     * @param pseudo
     * @return
     */
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
