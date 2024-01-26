package videoteka_1;
import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class PregledClan {

	private JFrame frame;
	private JTable tablica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledClan window = new PregledClan();
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
	public PregledClan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 851, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 53, 444, 235);
		frame.getContentPane().add(scrollPane);
		
		tablica = new JTable();
		scrollPane.setViewportView(tablica);
		tablica.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id èlan", "Ime", "Prezime", "Godina roðenja", "Spol", "Email"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		
		
		JButton btnNewButton = new JButton("Prika\u017Ei podatke");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/isimac?serverTimezone=UTC", "isimac", "is123");
					String upit="SELECT * FROM clanOOT2";
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(upit);
					
					DefaultTableModel model=(DefaultTableModel)tablica.getModel();
					
					while(rs.next()) {
						int id_clan=rs.getInt(1);
						String ime=rs.getString(2);
						String prezime=rs.getString(3);
						String god_rod=rs.getString(4);
						String spol=rs.getString(5);
						String email=rs.getString(6);
						
						model.addRow(new Object[] {id_clan, ime, prezime, god_rod, spol, email});
						
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
				
			}
		});
		btnNewButton.setBounds(208, 324, 147, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
