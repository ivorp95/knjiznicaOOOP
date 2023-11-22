package knjiznicaOOOP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class Registracija {

	private JFrame frame;
	private JTextField ime;
	private JTextField prezime;
	private JTextField brojMob;
	private JPasswordField lozinka;
	private JPasswordField ponLozinka;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registracija window = new Registracija();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registracija() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ime");
		lblNewLabel.setBounds(54, 21, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(54, 54, 61, 16);
		frame.getContentPane().add(lblPrezime);
		
		JLabel lblBrojMobitela = new JLabel("Broj Mobitela");
		lblBrojMobitela.setBounds(54, 92, 104, 16);
		frame.getContentPane().add(lblBrojMobitela);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(54, 130, 61, 16);
		frame.getContentPane().add(lblLozinka);
		
		JLabel lblPonovljenaLozinka = new JLabel("Ponovljena Lozinka");
		lblPonovljenaLozinka.setBounds(54, 168, 150, 16);
		frame.getContentPane().add(lblPonovljenaLozinka);
		
		ime = new JTextField();
		ime.setBounds(222, 16, 130, 26);
		frame.getContentPane().add(ime);
		ime.setColumns(10);
		
		prezime = new JTextField();
		prezime.setBounds(222, 49, 130, 26);
		prezime.setColumns(10);
		frame.getContentPane().add(prezime);
		
		brojMob = new JTextField();
		brojMob.setBounds(222, 87, 130, 26);
		brojMob.setColumns(10);
		frame.getContentPane().add(brojMob);
		
		lozinka = new JPasswordField();
		lozinka.setBounds(222, 125, 130, 26);
		frame.getContentPane().add(lozinka);
		
		ponLozinka = new JPasswordField();
		ponLozinka.setBounds(222, 158, 130, 26);
		frame.getContentPane().add(ponLozinka);
		
		JButton btnNewButton = new JButton("Registriraj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String imes, prezimes, brojMobs, lozinkas,  ponLozinkas;
				
				imes=ime.getText();
				prezimes=prezime.getText();
				brojMobs=brojMob.getText();
				
				lozinkas=new String (lozinka.getPassword());
				ponLozinkas=new String(ponLozinka.getPassword());				// bcrypt -- algoritam za hashiranje pasworda
				
				if(lozinkas.equals(ponLozinkas)) {					//provjera ako su lozinke iste sa metodom String.eqals
					
					
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
						String upit="SELECT * FROM registracija WHERE brojMob=?";
						//String upit="INSERT INTO ooop_simac(ime,prezime,brojMob,lozinka) VALUES (?,?,?,?)"; 
						PreparedStatement ps=con.prepareStatement(upit);
						ps.setString(1, brojMobs);							//provjera ako u bazi ima vec taj broj mobitela
						ResultSet rs=ps.executeQuery();
						
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Registracija nije moguca, korisnik sa tim brojem mobitela vec postoji");
						}
						else {
						String insert="INSERT INTO registracija (ime,prezime,brojMob,lozinka) VALUES (?,?,?,?)";  //provjera ako u bazi ima vec taj broj mobitela
						PreparedStatement psInsert=con.prepareStatement(insert);
						psInsert.setString(1, imes);
						psInsert.setString(2, prezimes);
						psInsert.setString(3, brojMobs);
						psInsert.setString(4, lozinkas);
						
						
						int ubacenoRedaka=psInsert.executeUpdate();
							if(ubacenoRedaka==1) {
								JOptionPane.showMessageDialog(null, "Registracija uspjesna");
							}
							else {
								JOptionPane.showMessageDialog(null, "Registracija neuspjesna");
							}
						}
						
						
						
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Doslo je do greske u povezivanju sa bazom bloku");
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Lozinke nisu jednake");
				}
				
				
			}
		});
		btnNewButton.setBounds(221, 208, 117, 29);
		frame.getContentPane().add(btnNewButton);
	}
	
	public void showWindow(){			//metoda showWindow() definicija nove metode za otvaranje novog prozora -----definicija metoda ide na pocetak ili kraj 
		frame.setVisible(true);
	}
}
