package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class InitWindow  extends JFrame{


   
    private JPanel mainPanel = new JPanel();
    private JLabel label1 = new JLabel("Vertical Buttons");
    private JButton settings = new JButton("Settings");
    private JButton exit= new JButton("Exit");

	public InitWindow() {
		//podesavanje Prozora
		Toolkit kit= Toolkit.getDefaultToolkit();
		Dimension screenSize= kit.getScreenSize();
		
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setSize(screenWidth/2,screenHeight/2);
		setTitle("Main Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	
		//button
		settings.setPreferredSize(new Dimension(100,30));
		exit.setPreferredSize(new Dimension(100,30));
		
		mainPanel.add(settings);
		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPanel.add(exit);
		
		
		add(mainPanel);
		validate();
		
		
		settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//dispose clean threads
				//to destroy current window
				dispose();
			
				new Window();
				
			}});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//proces koji se nece nastaviti tj. gasenje aplikacija
				System.exit(0);
			}
			
		});
	}
	
	
	
}
