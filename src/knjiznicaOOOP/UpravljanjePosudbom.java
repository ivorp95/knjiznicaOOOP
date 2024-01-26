package knjiznica;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UpravljanjePosudbom {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpravljanjePosudbom window = new UpravljanjePosudbom();
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
	public UpravljanjePosudbom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frame = new JFrame();
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(28, 63, 108, 22);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(203, 63, 108, 22);
		frame.getContentPane().add(comboBox_1);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {	
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://student.veleri.hr/bkondic?serverTimezone=UTC", "bkondic", "11");
					
					String pregledClanova = "SELECT clan_id FROM clanOOOP";
					String pregledKnjiga = "SELECT knjiga_id FROM knjigaOOOP";
					
					Statement stmtClan = con.createStatement();
					ResultSet rsClan = stmtClan.executeQuery(pregledClanova);
					
					Statement stmtKnjiga = con.createStatement();
					ResultSet rsKnjiga = stmtKnjiga.executeQuery(pregledKnjiga);
					
					while(rsClan.next()) {
						
						String ime = rsClan.getString("clan_id");
						comboBox.addItem(ime);
												
					}
					
					while(rsKnjiga.next()) {
						
						String knjigaId = rsKnjiga.getString("knjiga_id");
						comboBox_1.addItem(knjigaId);
						
					}															
				}catch(Exception e1) {
					System.out.println(e1);
				}	

			}
		});
		frame.setBounds(100, 100, 396, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ČLANOVI");
		lblNewLabel.setBounds(60, 41, 76, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("KNJIGE");
		lblNewLabel_1.setBounds(241, 41, 88, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DATUM POSUDBE");
		lblNewLabel_2.setBounds(47, 125, 124, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("DATUM POVRATA");
		lblNewLabel_2_1.setBounds(221, 125, 124, 14);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JDateChooser datumPosudbe = new JDateChooser();
		datumPosudbe.setDateFormatString("dd-MM-yyyy");
		datumPosudbe.setBounds(28, 150, 108, 20);
		frame.getContentPane().add(datumPosudbe);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(203, 150, 108, 20);
		frame.getContentPane().add(dateChooser_1);
		
		JButton btnNewButton = new JButton("UNOS POSUDBE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				String comboClana, comboKnjige;
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://student.veleri.hr/bkondic?serverTimezone=UTC", "bkondic", "11");
					
					java.util.Date odaberenDatumPosudbe = datumPosudbe.getDate();
					java.util.Date zapisanDatumPosudbe = new java.sql.Date(odaberenDatumPosudbe.getTime());
																						
					String unosPosudbe = "INSERT INTO posudbaOOOP(clan_id, knjiga_id, datum_posudbe) VALUES (?,?,?)";
					String pregledPosudbe = "SELECT * FROM posudbaOOOP WHERE clan_id=? AND knjiga_id=? AND datum_vracanja IS NULL";
					PreparedStatement psUnosPosudbe = con.prepareStatement(unosPosudbe);
					PreparedStatement psPregledPosudbe = con.prepareStatement(pregledPosudbe);
									
					comboClana = (String) comboBox.getSelectedItem();
					comboKnjige = (String) comboBox_1.getSelectedItem();
					psPregledPosudbe.setInt(1, Integer.parseInt(comboClana));
					psPregledPosudbe.setInt(2, Integer.parseInt(comboKnjige));
					
					
					ResultSet rsPregleda = psPregledPosudbe.executeQuery();
					
					if(rsPregleda.next()) {
						JOptionPane.showMessageDialog(null, "Posudba se ne može izvršiti jer knjiga nije vraćena.");
					}else {
						
						psUnosPosudbe.setInt(1, Integer.parseInt(comboClana));										
						psUnosPosudbe.setInt(2, Integer.parseInt(comboKnjige));
						
						psUnosPosudbe.setDate(3, (java.sql.Date) zapisanDatumPosudbe);
						
						int ubacenoRedaka = psUnosPosudbe.executeUpdate();
						
						if(ubacenoRedaka == 1) {
							JOptionPane.showMessageDialog(null, "Unesena posudba.");
						}
						
					}
					
					
					
				}catch(Exception e1) {
					System.out.println(e1);
				}			
			}
		});
		btnNewButton.setBounds(103, 204, 143, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("UNOS POVRATA");
		btnNewButton_1.setBounds(103, 264, 143, 23);
		frame.getContentPane().add(btnNewButton_1);
		
						
	}			
	public void showWindow() {
		frame.setVisible(true);
	}
}
