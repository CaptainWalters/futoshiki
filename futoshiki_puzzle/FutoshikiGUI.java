package futoshiki_puzzle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;

public class FutoshikiGUI extends JFrame {

	private final FutoshikiPuzzle FP;
	private JPanel contentPane, gridPanel, buttonPanel;
	JTextField[][] gridBoxes;
	private JLabel[][] rowBoxes, colBoxes;
	private JButton btnCheck, btnReset, btnSolve;
	private JTextField textField;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		FutoshikiPuzzle FP = new FutoshikiPuzzle(5, false);
		FutoshikiGUI frame = new FutoshikiGUI(FP);
		FP.setGUI(frame);
		//System.out.println(FP.toString());
		//FP.isLegal();
	}
	
	/**
	 * Create the frame.
	 */
	public FutoshikiGUI(FutoshikiPuzzle FP) {
		super("Futoshiki Puzzle");
		int size = FP.getSize();
		
		this.FP = FP;
		createWindow();
		gridBoxes = new JTextField[size][size];
		rowBoxes = new JLabel[size][size-1];
		colBoxes = new JLabel[size-1][size];
		fillBoxes();
		setVisible(true);
	}
	
	public void createWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		gridPanel = new JPanel();
		gridPanel.setBounds(136, 11, 350, 350);
		contentPane.add(gridPanel);
		gridPanel.setLayout(new GridLayout(9, 9, 0, 0));

		buttonPanel = new JPanel();
		buttonPanel.setBounds(10, 11, 115, 350);
		contentPane.add(buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_buttonPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		btnCheck = new JButton("Check");
		GridBagConstraints gbc_btnCheck = new GridBagConstraints();
		gbc_btnCheck.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCheck.anchor = GridBagConstraints.NORTH;
		gbc_btnCheck.insets = new Insets(0, 0, 5, 0);
		gbc_btnCheck.gridx = 0;
		gbc_btnCheck.gridy = 0;
		buttonPanel.add(btnCheck, gbc_btnCheck);
		btnCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setGrid();
				if(FP.isLegal()) {
					textField.setText("LEGAL!");
				}
				else {
					textField.setText("ILLEGAL!");
				}
			}
		});
		
		btnReset = new JButton("Reset");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReset.insets = new Insets(0, 0, 5, 0);
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 1;
		buttonPanel.add(btnReset, gbc_btnReset);
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		btnSolve = new JButton("Solve");
		GridBagConstraints gbc_btnSolve = new GridBagConstraints();
		gbc_btnSolve.insets = new Insets(0, 0, 5, 0);
		gbc_btnSolve.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSolve.gridx = 0;
		gbc_btnSolve.gridy = 2;
		buttonPanel.add(btnSolve, gbc_btnSolve);
		btnSolve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(FP.solve()) {
					textField.setText("Solved!");
				}
				else {
					textField.setText("Not solvable!");
				}
			}
		});
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 3;
		buttonPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setEditable(false);
	}
	
	public void fillBoxes() {
		for(int i=0;i<gridBoxes.length;i++) {
			for(int j=0;j<gridBoxes.length;j++) {
				gridBoxes[i][j] = new JTextField(1);
				gridBoxes[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				if(FP.grid[i][j].getValue() != 0) {
					gridBoxes[i][j].setText(Integer.toString(FP.grid[i][j].getValue()));
					gridBoxes[i][j].setEditable(false);
					gridBoxes[i][j].setBackground(Color.WHITE);
				}
				else {
					gridBoxes[i][j].setText("");
					listen(gridBoxes[i][j]);
				}
			}
		}
		for(int i=0;i<rowBoxes.length;i++) {
			for(int j=0;j<rowBoxes[0].length;j++) {
				rowBoxes[i][j] = new JLabel();
				if (FP.rowConstraints[i][j] != null) {
					rowBoxes[i][j].setText(FP.rowConstraints[i][j].getSymbol());
				}
				rowBoxes[i][j].setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		for(int i=0;i<colBoxes.length;i++) {
			for(int j=0;j<colBoxes[0].length;j++) {
				colBoxes[i][j] = new JLabel();
				if (FP.columnConstraints[i][j] != null) {
					colBoxes[i][j].setText(FP.columnConstraints[i][j].getSymbol());
				}
				colBoxes[i][j].setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		displayBoxes();
	}
	
	public void displayBoxes() {
		for(int i=0;i<FP.getSize();i++) {
			//top row
			for(int j=0;j<FP.getSize();j++) {
				gridPanel.add(gridBoxes[i][j]);
				if(j+1<FP.getSize()) {
					gridPanel.add(rowBoxes[i][j]);
				}
			}
			//next row
			if(i+1<FP.getSize()){
				for(int j=0;j<FP.getSize();j++) {
					gridPanel.add(colBoxes[i][j]);
					if(j+1<FP.getSize()) {
						gridPanel.add(new JLabel());
					}
				}
			}
		}
	}
	
	public void listen(JTextField tF) {
		tF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!((c >= '1' && c <= '5') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || (tF.getText().length() >= 1)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
	}
	
	public void reset() {
		for(int i=0;i<gridBoxes.length;i++) {
			for(int j=0;j<gridBoxes.length;j++) {
				if(gridBoxes[i][j].isEditable()) {
					gridBoxes[i][j].setText("");
				}
			}
		}
	}
	
	public void setGrid() {
		int a;
		for(int i=0;i<gridBoxes.length;i++) {
			for(int j=0;j<gridBoxes.length;j++) {
				a = 0;
				if(gridBoxes[i][j].isEditable()) {
					try {
						a = Integer.parseInt(gridBoxes[i][j].getText());
					}  catch (NumberFormatException e) {
						a = 0;
					}
					FP.grid[i][j].setValue(a);
					if(FP.grid[i][j].getValue() == 0) {
						gridBoxes[i][j].setText("");
					}
					else {
						gridBoxes[i][j].setText(Integer.toString(FP.grid[i][j].getValue()));
					}
				}
			}
		}
	}
	

}
