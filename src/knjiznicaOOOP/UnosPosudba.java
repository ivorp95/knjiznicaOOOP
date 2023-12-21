package knjiznicaOOOP;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UnosPosudba {

	private JFrame frame;
	private JTextField DP;
	private JTextField DV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosPosudba window = new UnosPosudba();
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
	public UnosPosudba() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboClan = new JComboBox();
		comboClan.setBounds(26, 37, 129, 27);
		frame.getContentPane().add(comboClan);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
			String upit="SELECT prezime FROM clanOOOP";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(upit);
			
			while (rs.next()) {
				String podatak=rs.getString(1);
				comboClan.addItem(podatak);
			}
			
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, "Greška servera!");
		};
		
		JComboBox comboKnjiga = new JComboBox();
		comboKnjiga.setBounds(26, 130, 129, 27);
		frame.getContentPane().add(comboKnjiga);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
			String upit="SELECT naslov FROM knjigaOOOP";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(upit);
			
			while (rs.next()) {
				String podatak=rs.getString(1);
				comboKnjiga.addItem(podatak);
			}
			
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, e1);
		};
		
		
		JButton btnNewButton = new JButton("Posudba");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String odabraniClan=(String)comboClan.getSelectedItem();
					String odabranaKnjiga=(String)comboKnjiga.getSelectedItem();
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String upitClan="SELECT clan_id FROM clanOOOP WHERE prezime='"+odabraniClan+"'";
					String upitKnjiga="SELECT knjiga_id FROM knjigaOOOP WHERE naslov='"+odabranaKnjiga+"'";
					
					Statement stmtClan=con.createStatement();
					Statement stmtKnjiga=con.createStatement();
					
					ResultSet rsClan=stmtClan.executeQuery(upitClan);
					ResultSet rsKnjiga=stmtKnjiga.executeQuery(upitKnjiga);
					
					//treba nam dva broja jer posudba ima dva vanjska kljuca id knjige i id clana
					
					int idClan=0, idKnjiga=0
							;
					if(rsClan.next()) {
						idClan=rsClan.getInt(1);
					}
					if(rsKnjiga.next()) {
						idKnjiga=rsKnjiga.getInt(1);
					}
					
					String upit="INSERT INTO posudbaOOOP(clan_id,knjiga_id) VALUES (?,?)";
					PreparedStatement ps=con.prepareStatement(upit);
					ps.setInt(1, idClan);
					ps.setInt(2, idKnjiga);
					
					int ubacenoRedaka=ps.executeUpdate();
					
					if(ubacenoRedaka==1) {
						JOptionPane.showMessageDialog(null, "Uspjesno posudena knjiga");
					}
					else {
						JOptionPane.showMessageDialog(null, "Posudba neuspjesna");
					}
					
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnNewButton.setBounds(166, 219, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		DP = new JTextField();
		DP.setBounds(264, 36, 130, 26);
		frame.getContentPane().add(DP);
		DP.setColumns(10);
		
		DV = new JTextField();
		DV.setColumns(10);
		DV.setBounds(264, 129, 130, 26);
		frame.getContentPane().add(DV);
	}
}
