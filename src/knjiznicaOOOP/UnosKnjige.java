package knjiznicaOOOP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class UnosKnjige {

	private JFrame frame;
	private JTextField naslov;
	private JTextField autor;
	private JTextField godIzd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosKnjige window = new UnosKnjige();
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
	public UnosKnjige() {
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
		
		JLabel lblNewLabel = new JLabel("Naslov");
		lblNewLabel.setBounds(29, 27, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(29, 100, 61, 16);
		frame.getContentPane().add(lblAutor);
		
		JLabel lblGodinaIzadnja = new JLabel("Godina izadnja");
		lblGodinaIzadnja.setBounds(29, 176, 147, 16);
		frame.getContentPane().add(lblGodinaIzadnja);
		
		JButton btnNewButton = new JButton("Unesi Knjigu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String naslovs, autors, godIzds;
				naslovs=naslov.getText();
				autors=autor.getText();
				godIzds=godIzd.getText();
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String insert="INSERT INTO knjigaOOOP (naslov,autor,god_izdanja) VALUES (?,?,?)"; 
					PreparedStatement ps=con.prepareStatement(insert);
					ps.setString(1, naslovs);
					ps.setString(2,autors);
					ps.setString(3, godIzds);	
					
					int ubacenoRedaka=ps.executeUpdate();
					
					if(ubacenoRedaka==1) {
						JOptionPane.showMessageDialog(null, "Nova knjga uspijesno unesena");
					}
					else {
						JOptionPane.showMessageDialog(null, "Greska u unosu nove knjige");
					}
				}
					
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(219, 224, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		naslov = new JTextField();
		naslov.setBounds(219, 22, 130, 26);
		frame.getContentPane().add(naslov);
		naslov.setColumns(10);
		
		autor = new JTextField();
		autor.setColumns(10);
		autor.setBounds(219, 95, 130, 26);
		frame.getContentPane().add(autor);
		
		godIzd = new JTextField();
		godIzd.setColumns(10);
		godIzd.setBounds(219, 171, 130, 26);
		frame.getContentPane().add(godIzd);
	}
	public void showWindow(){
		frame.setVisible(true);
	}
}
