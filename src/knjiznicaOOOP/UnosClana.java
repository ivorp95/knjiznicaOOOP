package knjiznicaOOOP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class UnosClana {

	private JFrame frame;
	private JTextField ime;
	private JTextField prezime;
	private JTextField brojMob;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosClana window = new UnosClana();
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
	public UnosClana() {
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
		lblNewLabel.setBounds(30, 30, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(30, 100, 61, 16);
		frame.getContentPane().add(lblPrezime);
		
		JLabel lblBrojMobitela = new JLabel("Broj Mobitela");
		lblBrojMobitela.setBounds(30, 174, 117, 16);
		frame.getContentPane().add(lblBrojMobitela);
		
		ime = new JTextField();
		ime.setBounds(208, 25, 130, 26);
		frame.getContentPane().add(ime);
		ime.setColumns(10);
		
		prezime = new JTextField();
		prezime.setColumns(10);
		prezime.setBounds(208, 95, 130, 26);
		frame.getContentPane().add(prezime);
		
		brojMob = new JTextField();
		brojMob.setColumns(10);
		brojMob.setBounds(208, 169, 130, 26);
		frame.getContentPane().add(brojMob);
		
		JButton btnNewButton = new JButton("Unesi Clana");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String imes, prezimes, brojMobs;
				imes = ime.getText();
				prezimes = prezime.getText();
				brojMobs = brojMob.getText();
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String insert="INSERT INTO clanOOOP (ime,prezime,broj_mob) VALUES (?,?,?)"; 
					PreparedStatement ps=con.prepareStatement(insert);
					ps.setString(1, imes);
					ps.setString(2,prezimes);
					ps.setString(3, brojMobs);	
					
					int ubacenoRedaka=ps.executeUpdate();
					
					if(ubacenoRedaka==1) {
						JOptionPane.showMessageDialog(null, "Novi clan uspijesno kreiran");
					}
					else {
						JOptionPane.showMessageDialog(null, "Greska u unosu novog clana");
					}
				}
					
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(208, 221, 117, 29);
		frame.getContentPane().add(btnNewButton);

	}
	
	public void showWindow(){			//na kraju klase nova metoda showwindow koju pozivamo u glavnomizborniku ili gjde je potrebno
		frame.setVisible(true);
	}
}
