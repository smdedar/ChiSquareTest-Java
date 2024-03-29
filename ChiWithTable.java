package ChiSquareTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ChiWithTable {

	private JFrame frmChiSquareTest;
	private JTextField obdata;
	private JTextField exdata;
	private JTextField observedChi;
	private JTextField sgchi;
	
	ArrayList<Double> odata = new ArrayList<>();  			//Define Arraylist For Observed Data
	ArrayList<Double> edata = new ArrayList<>();			//Define Arraylist For Expected Data

	Double obchi = 0.0;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiWithTable window = new ChiWithTable();
					window.frmChiSquareTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChiWithTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChiSquareTest = new JFrame();
		frmChiSquareTest.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\image.png"));
		frmChiSquareTest.setTitle("Chi Square Test");
		frmChiSquareTest.setBounds(100, 100, 450, 439);
		frmChiSquareTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChiSquareTest.getContentPane().setLayout(null);
		frmChiSquareTest.setResizable(false);             //Hide Resize Button
		
		JLabel lblNewLabel = new JLabel("Chi Square Test");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(132, 11, 168, 46);
		frmChiSquareTest.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Observed Data");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(59, 68, 136, 26);
		frmChiSquareTest.getContentPane().add(lblNewLabel_1);
		
		obdata = new JTextField();
		obdata.setHorizontalAlignment(SwingConstants.CENTER);
		obdata.setFont(new Font("Tahoma", Font.PLAIN, 16));
		obdata.setBounds(223, 68, 152, 26);
		frmChiSquareTest.getContentPane().add(obdata);
		obdata.setColumns(10);
		
		JLabel lblExpectedData = new JLabel("Expected Data");
		lblExpectedData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblExpectedData.setBounds(59, 109, 136, 26);
		frmChiSquareTest.getContentPane().add(lblExpectedData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 178, 323, 108);
		frmChiSquareTest.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Observed Data", "Expected Data"
			}
		));
		scrollPane.setViewportView(table);
		
		exdata = new JTextField();
		exdata.setHorizontalAlignment(SwingConstants.CENTER);
		exdata.setFont(new Font("Tahoma", Font.PLAIN, 16));
		exdata.setColumns(10);
		exdata.setBounds(223, 105, 152, 26);
		frmChiSquareTest.getContentPane().add(exdata);
		
		JButton AddButton = new JButton("Add ");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(obdata.getText().equals("") || exdata.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter Number","Error", JOptionPane.ERROR_MESSAGE);
				}else {
					Double observed = Double.parseDouble(obdata.getText().toString()); 		//Get Data From Observed Textfield 
					Double expected = Double.parseDouble(exdata.getText().toString());		//Get Data From Expected Textfield 
					
					odata.add(observed);							//Push Data to Observed Arraylist
					edata.add(expected);							//Push Data to Expected Arraylist
					
					obdata.setText("");								//Clear Observed Textfield
					exdata.setText("");								//Clear Expected Textfield
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[] {observed,expected});
				}
				
			}
		});
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		AddButton.setBounds(157, 142, 136, 26);
		frmChiSquareTest.getContentPane().add(AddButton);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double[] ob = new double[odata.size()];
				double[] ex = new double[edata.size()];
				double[] tp = new double[odata.size()];
				
				for (int i = 0; i < odata.size(); i++) {
					ob[i] = odata.get(i);
					ex[i] = edata.get(i);
				}
				
				for (int i = 0; i < odata.size(); i++) {
					tp[i] = ob[i] - ex[i];
					tp[i] = tp[i] * tp[i];
					tp[i] = tp[i] / ex[i];
					
					obchi = obchi + tp[i];
				}
				
				String obchis= String.valueOf(obchi);  
				
				observedChi.setText(obchis.substring(0, 12));
				
				AddButton.setEnabled(false);
				btnCalculate.setEnabled(false);
			}
		});
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCalculate.setBounds(59, 297, 144, 26);
		frmChiSquareTest.getContentPane().add(btnCalculate);
		
		observedChi = new JTextField();
		observedChi.setHorizontalAlignment(SwingConstants.CENTER);
		observedChi.setEditable(false);
		observedChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		observedChi.setColumns(10);
		observedChi.setBounds(223, 297, 152, 26);
		frmChiSquareTest.getContentPane().add(observedChi);
		
		JLabel lblSignificatChiSquare = new JLabel("Significat Chi Square");
		lblSignificatChiSquare.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSignificatChiSquare.setBounds(59, 334, 160, 26);
		frmChiSquareTest.getContentPane().add(lblSignificatChiSquare);
		
		sgchi = new JTextField();
		sgchi.setHorizontalAlignment(SwingConstants.CENTER);
		sgchi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sgchi.setColumns(10);
		sgchi.setBounds(223, 334, 152, 26);
		frmChiSquareTest.getContentPane().add(sgchi);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sgchi.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Number","Error", JOptionPane.ERROR_MESSAGE);
				}else {
					Double schi = Double.parseDouble(sgchi.getText().toString());
					
					if(obchi < schi){
						JOptionPane.showMessageDialog(null, "Accepted", "", JOptionPane.PLAIN_MESSAGE);
					}
			 
					else {
						JOptionPane.showMessageDialog(null, "Rejected", "", JOptionPane.PLAIN_MESSAGE);
					}
				}
				
			}
		});
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCheck.setBounds(157, 370, 144, 26);
		frmChiSquareTest.getContentPane().add(btnCheck);
	}
}