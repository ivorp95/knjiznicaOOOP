package hash;
import org.mindrot.jbcrypt.BCrypt;
//ovo je klasa koju morate importati i koja sadrži taj algoritam za hash
//proèitati notepad

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Hash extends JFrame {
    private JTextField imes;
    private JTextField prezimes;
    private JPasswordField lozinkas;
    private JButton registrirajs;

    public Hash() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel imeLabel = new JLabel("Ime:");
        JLabel prezimeLabel = new JLabel("Prezime:");
        JLabel lozinkaLabel = new JLabel("Lozinka:");

        imes = new JTextField();
        prezimes = new JTextField();
        lozinkas = new JPasswordField();

        registrirajs = new JButton("Registriraj");
        registrirajs.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	String ime = imes.getText();
                String prezime = prezimes.getText();
                char[] lozinkaChars = lozinkas.getPassword();
                String lozinka = new String(lozinkaChars);

                // Generirajte sol za bcrypt
                String salt = BCrypt.gensalt();
                // Hashirajte lozinku sa soli
                String hashLozinke = BCrypt.hashpw(lozinka, salt);

                
                
                try {
                    
                	Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/isimac?serverTimezone=UTC", "isimac", "is123");

                   
                    String sql = "INSERT INTO korisnici (ime, prezime, lozinka) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, ime);
                    preparedStatement.setString(2, prezime);
                    preparedStatement.setString(3, hashLozinke); // Spremite hash lozinke umjesto izvorne lozinke

                    
                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Korisnik je uspješno registriran!");
                    
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Registracija nije uspjela!");
                } 
            }
        });

        
    }

    
        
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Hash forma = new Hash();
                forma.setVisible(true);
            }
        });
    }
}
