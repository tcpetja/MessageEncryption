
package za.ac.tut.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import za.ac.tut.encryption.MessageEncryptor;

public class SecureMessagesFrame extends JFrame{
    
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel buttonPanel = new JPanel(new FlowLayout());
    JPanel messagesPanel = new JPanel(new GridLayout());
    JPanel origTextpanel = new JPanel(new BorderLayout());
    JPanel encryptedTextpanel = new JPanel(new BorderLayout());
    JTextArea origText = new JTextArea();
    JTextArea encryptedText = new JTextArea();
    JButton encryptMesssage = new JButton("Message Encryptor");
    JMenuBar mBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem openFile = new JMenuItem("Open File...");
    JMenuItem encryptFile = new JMenuItem("Encrypt File...");
    JMenuItem saveEncrypFile = new JMenuItem("Save Encrypted File...");
    JMenuItem clear = new JMenuItem("Clear");
    JMenuItem exit = new JMenuItem("Exit");
    public SecureMessagesFrame(){
        setSize(725, 325);
        setTitle("Secure Messages");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel.add(mBar);
        mBar.add(menu);
        menu.add(openFile);
        menu.add(encryptFile);
        menu.add(saveEncrypFile);
        menu.addSeparator();
        menu.add(clear);
        menu.add(exit);
        setJMenuBar(mBar);
        
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        encryptFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptMessage();
            }
        });
        saveEncrypFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEncryptedMessage();
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextAreas();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mainPanel.add(buttonPanel,"North");
        encryptMesssage.setForeground(Color.blue);
        encryptMesssage.setFont(new Font("Times New Roman",Font.ITALIC | Font.BOLD,30) );
        JCheckBox checcbox = new JCheckBox();
        buttonPanel.add(checcbox);
        checcbox.setText("pensioner?");
        buttonPanel.add(encryptMesssage);
        encryptMesssage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptMessage();
            }
        });
        mainPanel.add(messagesPanel,"Center");
        
        messagesPanel.add(origTextpanel);
        messagesPanel.add(encryptedTextpanel);
        
        origTextpanel.add(origText);
        origTextpanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"Plain Message"));
        encryptedTextpanel.add(encryptedText);
        encryptedTextpanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"Encrypted Message"));
        
        JScrollPane scrollPane1 = new JScrollPane(origTextpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JScrollPane scrollPane2 = new JScrollPane(encryptedTextpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        messagesPanel.add(scrollPane1);
        messagesPanel.add(scrollPane2);
        
        add(mainPanel);
    }
    public void encryptMessage(){
        MessageEncryptor messageEncrytor = new MessageEncryptor();
        encryptedText.setText("");
        encryptedText.setText(messageEncrytor.encrypt(origText.getText()));
    }
    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                origText.setText(content);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void saveEncryptedMessage() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(encryptedText.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void clearTextAreas() {
        origText.setText("");
        encryptedText.setText("");
    }
    public static void main(String[] args) {
        SecureMessagesFrame secureMessagesFrame = new SecureMessagesFrame();
         secureMessagesFrame.setVisible(true);
    }
}
