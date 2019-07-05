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

public class ChiSquareTest {

	private JFrame frmChiSquareTest;
	private JTextField obdata;
	private JTextField exdata;
	private JTextField observedChi;
	private JTextField sgchi;
	
	ArrayList<Double> odata = new ArrayList<>();
	ArrayList<Double> edata = new ArrayList<>();
	
	Double obchi = 0.0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiSquareTest window = new ChiSquareTest();
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
	public ChiSquareTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChiSquareTest = new JFrame();
		frmChiSquareTest.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\eclipse\\all_code\\test\\src\\ChiSquareTest\\images\\chi_icon.png"));
		frmChiSquareTest.setTitle("Chi Square Test");
		frmChiSquareTest.setBounds(100, 100, 450, 403);
		frmChiSquareTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChiSquareTest.getContentPane().setLayout(null);
		frmChiSquareTest.setResizable(false);             //Hide Resize Button
		
		JLabel lblNewLabel = new JLabel("Chi Square Test");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(132, 11, 168, 46);
		frmChiSquareTest.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Observed Data");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(59, 68, 136, 26);
		frmChiSquareTest.getContentPane().add(lblNewLabel_1);
		
		obdata = new JTextField();
		obdata.setHorizontalAlignment(SwingConstants.CENTER);
		obdata.setFont(new Font("Tahoma", Font.PLAIN, 18));
		obdata.setBounds(223, 68, 152, 26);
		frmChiSquareTest.getContentPane().add(obdata);
		obdata.setColumns(10);
		
		JLabel lblExpectedData = new JLabel("Expected Data");
		lblExpectedData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblExpectedData.setBounds(59, 109, 136, 26);
		frmChiSquareTest.getContentPane().add(lblExpectedData);
		
		exdata = new JTextField();
		exdata.setHorizontalAlignment(SwingConstants.CENTER);
		exdata.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exdata.setColumns(10);
		exdata.setBounds(223, 105, 152, 26);
		frmChiSquareTest.getContentPane().add(exdata);
		
		JButton AddButton = new JButton("Add ");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(obdata.getText().equals("") || exdata.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter Number","Error", JOptionPane.ERROR_MESSAGE);
				}else {
					Double observed = Double.parseDouble(obdata.getText().toString());
					Double expected = Double.parseDouble(exdata.getText().toString());
					
					odata.add(observed);
					edata.add(expected);
					
					obdata.setText("");
					exdata.setText("");
				}
				
			}
		});
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		AddButton.setBounds(157, 152, 136, 26);
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
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCalculate.setBounds(51, 219, 144, 26);
		frmChiSquareTest.getContentPane().add(btnCalculate);
		
		observedChi = new JTextField();
		observedChi.setHorizontalAlignment(SwingConstants.CENTER);
		observedChi.setEditable(false);
		observedChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		observedChi.setColumns(10);
		observedChi.setBounds(223, 219, 152, 26);
		frmChiSquareTest.getContentPane().add(observedChi);
		
		JLabel lblSignificatChiSquare = new JLabel("Significat Chi Square");
		lblSignificatChiSquare.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSignificatChiSquare.setBounds(51, 272, 160, 26);
		frmChiSquareTest.getContentPane().add(lblSignificatChiSquare);
		
		sgchi = new JTextField();
		sgchi.setHorizontalAlignment(SwingConstants.CENTER);
		sgchi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sgchi.setColumns(10);
		sgchi.setBounds(223, 272, 152, 26);
		frmChiSquareTest.getContentPane().add(sgchi);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sgchi.getText().equals("")) {
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
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCheck.setBounds(157, 313, 144, 26);
		frmChiSquareTest.getContentPane().add(btnCheck);
	}
}