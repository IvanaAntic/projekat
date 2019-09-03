package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ShowWindow extends JDialog implements WindowListener{
	
	Timer t=null;
	private JPanel panel = new JPanel();
	private Color blinkColor= new Color(255,255,255); //belaboja
	
	
	public ShowWindow(Color activeColorLabel, int parseInt) {
		// TODO Auto-generated constructor stub
		//podesavanje Prozora
		Toolkit kit= Toolkit.getDefaultToolkit();
		Dimension screenSize= kit.getScreenSize();
		
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setSize(screenWidth/2-250,screenHeight/2-250);
		setTitle("Show Window");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		panel.setBackground(activeColorLabel);
		
		
		t= new Timer(parseInt,new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					if(activeColorLabel==panel.getBackground()) {
						panel.setBackground(blinkColor);
					}else {
						panel.setBackground(activeColorLabel);
					}
					repaint();
				}
				 
			 });
	         t.start();
		
		
	     addWindowListener(this);
	
		
		
		add(panel);
		System.out.println(parseInt);
		
		setVisible(true);
	}

	

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
			t.stop();
		}



		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
		
			
		}



		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
}
