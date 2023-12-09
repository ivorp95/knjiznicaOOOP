package knjiznicaOOOP;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

//import com.mysql.cj.xdevapi.Statement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PregledClana {

	private JFrame frame;
	private JTable tablica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledClana window = new PregledClana();
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
	public PregledClana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 27, 456, 205);
		frame.getContentPane().add(scrollPane);
		
		tablica = new JTable();
		tablica.setBounds(17, 19, 414, 184);
		frame.getContentPane().add(tablica);
		
		tablica = new JTable();
		scrollPane.setViewportView(tablica);
		tablica.setModel(new DefaultTableModel(
			new Object[][] {

			},
			new String[] {
			"Id clana", "Ime", "Prezime", "Broj mobitela"
			}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});


		JButton btnNewButton = new JButton("Popuni podatcima");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String upit="SELECT * FROM clanOOOP";
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(upit);

					DefaultTableModel model = (DefaultTableModel) tablica.getModel();
					model.setRowCount(0);
					
					while (rs.next()){
						int clan_id=rs.getInt(1);
						String ime=rs.getString(2);
						String prezime=rs.getString(3);
						String brojMob=rs.getString(4);
						model.addRow(new Object[] {clan_id, ime, prezime, brojMob});
						}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greška servera!");
					
				}
				
				
			}
		});
		btnNewButton.setBounds(141, 225, 175, 29);
		frame.getContentPane().add(btnNewButton);
	}
}
