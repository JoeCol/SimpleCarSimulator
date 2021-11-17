package visual_car_sim;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;

import core_car_sim.AbstractCell;
import core_car_sim.LoadWorld;
import core_car_sim.WorldSim;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class CarSimGUI {

	private JFrame frame;
	private JFileChooser loadWorldDialog = new JFileChooser();
	private WorldSim simworld;
	private JPanel pnlWorld = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarSimGUI window = new CarSimGUI();
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
	public CarSimGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 645, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Load Simulation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (loadWorldDialog.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
					{
						BufferedReader br = new BufferedReader(new FileReader(loadWorldDialog.getSelectedFile()));
						simworld = LoadWorld.loadWorldFromFile(br, null);
						generateGUIWorld();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Run Simulation");
		panel.add(btnNewButton_1);
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("until finished");
		rdbtnNewRadioButton.setSelected(true);
		panel.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("set number");
		panel.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_1);
		
		JSpinner spinner = new JSpinner();
		panel.add(spinner);
	
		frame.getContentPane().add(pnlWorld, BorderLayout.CENTER);
		pnlWorld.setLayout(new GridLayout(3, 3, 0, 0));
	}
	
	private void generateGUIWorld()
	{
		pnlWorld.setLayout(new GridLayout(simworld.getWidth(), simworld.getHeight(), 0, 0));
		for (int x = 0; x < simworld.getWidth(); x++)
		{
			for (int y = 0; y < simworld.getHeight(); y++)
			{
				AbstractCell c = simworld.getCell(x, y);
				JPanel guiCell = new JPanel();
				guiCell.add(new JLabel("Cell Position(" + x + "," + y + ")"));
				switch (c.getCellType())
				{
				case ct_blank:
					guiCell.setBackground(Color.GREEN);
					break;
				case ct_information:
					guiCell.setBackground(Color.BLUE);
					break;
				case ct_road:
					guiCell.setBackground(Color.GRAY);
					break;
				default:
					break;
				}
				pnlWorld.add(guiCell);
			}
		}
	}

}
