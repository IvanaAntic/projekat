package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import javax.swing.*;



public class Window extends JFrame  {
	
	private JPanel panel = new JPanel();
	private ShowWindow w;
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private String speed[]= { "1000","2000","3000","4000","5000"}; 
 	//GUI components
	private JRadioButton  ontime=new JRadioButton("Ontime");
	private JRadioButton  cdown=new JRadioButton("Countdown");
	private JFormattedTextField fieldTime = new JFormattedTextField("00:00:00");
	private JTextField fieldCount=new JTextField("0");
	private JButton btnColor = new JButton("Choose color");
	private JLabel colorLabel=new JLabel("No color selected");
	private JLabel speedLabel=new JLabel("speed (milisecond)");
	private JList<String>  listLabel=new JList<>(speed);
	private JButton start=new JButton("Start");
	private JButton cancel=new JButton("Cancel");
 	
	private Color activeColorLabel;
	private Color blinkColor= new Color(255,255,255); //belaboja
	
	private GridBagConstraints gbc = new GridBagConstraints();
	private ButtonGroup bG = new ButtonGroup();
	
	private Date d;
	private Timer t=null;
	
	public Window()   {
		super();
		
		start.setEnabled(true);
		cancel.setEnabled(false);
		listLabel.setSelectedIndex(1);
		setTitle("First Window");
		setSize(1500,1500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        colorLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        fieldTime.setToolTipText("HH:mm:ss");
        
        //podesavanje Prozora
      	Toolkit kit= Toolkit.getDefaultToolkit();
      	Dimension screenSize= kit.getScreenSize();
      	int screenHeight = screenSize.height;
      	int screenWidth = screenSize.width;
      	setSize(screenWidth/2,screenHeight/2+100);
      	setLocationRelativeTo(null);
    	
      	panel.setLayout(new GridBagLayout());
      	SettingLayout();
      	
    	add(panel);
    	
    	btnColor.addActionListener(new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//boja slova koje je odabrana
				 activeColorLabel = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
				 colorLabel.setText("Selected Color");
				 colorLabel.setFont(new Font("Serif", Font.PLAIN, 24));
				 btnColor.setSelected(true);
				 colorLabel.setForeground(activeColorLabel);
				 Timer t= new Timer(500,new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						changeColor();
					}
					 
				 });
		         t.start();
		        
			}
			
    	});
    	
    	
    	fieldCount.addKeyListener(new KeyAdapter() {
    		public void keyTyped(KeyEvent e) {
    			char c=e.getKeyChar();
    		     if (!((c >= '0') && (c <= '9') ||
    		             (c == KeyEvent.VK_BACK_SPACE) ||
    		             (c == KeyEvent.VK_DELETE))) {
    		            e.consume();
    		          }
    		}
    		
    	});
    
    	fieldTime.addKeyListener(new KeyAdapter() {
    	
    		public void keyTyped(KeyEvent e) {
    			char c=e.getKeyChar();
    		     if (!((c >= '0') && (c <= '9') || 
    		             (c == KeyEvent.VK_BACK_SPACE) || (c == ':')  ||
    		             (c == KeyEvent.VK_DELETE)) ) {
    		            e.consume();
    		          }
    		}
    		
    	});
    	fieldTime.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent arg0) {
				// TODO Auto-generated method stub
	
				try {
					
					d = dateFormat.parse(fieldTime.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
		                    "Format HH:mm:ss", "Neispravan format",
		                    JOptionPane.ERROR_MESSAGE);
				}
				  String f2 =dateFormat.format(d.getTime());
				  System.out.println("ovde datum"+f2);
				  //on transformise sekunde u minute ako stavimo vise od 59 pa da odma updejtujemo i na frontu
				  fieldTime.setText(f2);
				  if(f2.matches("^([0-1]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$")) {
					  return true;
				  }else {
				  JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
				  return false;
				  }
			}
    		
    	});
    	
    
    	start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
		
			if(!btnColor.isSelected()) {
				JOptionPane.showMessageDialog(null,
	                    "Error: Please Choose color", "Error Message",
	                    JOptionPane.ERROR_MESSAGE);
				
			}else {
				System.out.println("Proveri sve ovo"+ btnColor.isSelected() );
				 try {
					 d = dateFormat.parse(fieldTime.getText());
				} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			
		
				//sve neaktivne sem tastera stop
				start.setEnabled(false);
				cancel.setEnabled(true);
				btnColor.setEnabled(false);
				listLabel.setEnabled(false);
				fieldCount.setEditable(false);
				fieldTime.setEditable(false);
				
				//u zavisnosti od toga sta je selektovano od radio button otvaraj novi dijalog
				if(ontime.isSelected()==true) {
					fieldCount.setText("");
					
					    
					//na zadato vreme formata HH:mm:ss
					t= new Timer(10,new ActionListener() {
			
						
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
					
							 Calendar now = Calendar.getInstance();
					         System.out.println(dateFormat.format(now.getTime()));
					         System.out.println(dateFormat.format(d.getTime()));
					         
					         String f1 = dateFormat.format(now.getTime());
					         String f2 =dateFormat.format(d.getTime());
					       
					       //poredim dva stringa, datum koji je uneo i datum trenutni u istom su formatu
							if(f1.equals(f2)) {
								//zaustavi tajmer da se ne ponavlja vise kad je otvoren novi prozor 
								t.stop();
								w= new ShowWindow(activeColorLabel,Integer.parseInt(listLabel.getSelectedValue()));
							}
						}
						
					});
					//pokreni tajmer
					t.start();
				}else {
					//on Count is selected
					fieldTime.setText("");
					
					int count = Integer.parseInt(fieldCount.getText());
					t= new Timer(1000,new ActionListener() {
						 	int cd=count;
							@Override
							public void actionPerformed(ActionEvent arg0) {
								
								if(cd>0) {
									cd--;
								}else {
							
									t.stop();
					
									w= new ShowWindow(activeColorLabel,Integer.parseInt(listLabel.getSelectedValue()));
								}
							}
							 
						 });
				         t.start();
				        
				}
			
				
			}
			}
    	});
    	
    	cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnColor.setSelected(false);
				fieldTime.setText("00:00:00");
				fieldCount.setText("0");
				if(t.isRunning()) { // u slucaju da odustane od otvaranja novog prozora
					//zaustavi timer i oslobodi dugmad
					t.stop();
					start.setEnabled(true);
					
					cancel.setEnabled(false);
					btnColor.setEnabled(true);
					listLabel.setEnabled(true);
					fieldCount.setEditable(true);
					fieldTime.setEditable(true);
				}else {
					w.dispose();
					start.setEnabled(true);
					
					cancel.setEnabled(false);
					btnColor.setEnabled(true);
					listLabel.setEnabled(true);
					fieldCount.setEditable(true);
					fieldTime.setEditable(true);
				}
				
			}
    		
    		
    	});
    	
	}
	
	
 
	public void changeColor() {
		if(activeColorLabel==colorLabel.getForeground()) {
			colorLabel.setForeground(blinkColor);
		}else {
			colorLabel.setForeground(activeColorLabel);
		}
		repaint();
	}
	public void SettingLayout() {
		
	 	//radio button group
      	bG.add(ontime);
      	bG.add(cdown);
      	ontime.setSelected(true);
      	gbc.insets= new Insets(20,20,20,20);
      	//gbc.weightx = 1.0;
      	gbc.weighty = 1.0;
    
     	//add to panel
      	gbc.gridx=0;
      	gbc.gridy=0;
      	//gbc.fill= GridBagConstraints.WEST;
      	gbc.anchor = GridBagConstraints.WEST;
     	panel.add(ontime,gbc);
     	
     	gbc.gridx=1;
      	gbc.gridy=0;
      	//rastezemo horizontalno
      	gbc.fill= GridBagConstraints.HORIZONTAL;
    	panel.add(fieldTime,gbc);
    	
    	gbc.gridx=0;
      	gbc.gridy=1;
      	gbc.fill= GridBagConstraints.NONE;
    	panel.add(cdown,gbc);
    	
    	gbc.gridx=1;
      	gbc.gridy=1;
    	gbc.fill= GridBagConstraints.HORIZONTAL;
    	panel.add(fieldCount,gbc);

    	gbc.gridx=0;
      	gbc.gridy=2;
      	gbc.fill= GridBagConstraints.WEST;
    	panel.add(btnColor,gbc);
    	
    	gbc.gridx=1;
      	gbc.gridy=2;
    	panel.add(colorLabel,gbc);
    	
    	gbc.gridx=0;
      	gbc.gridy=3;
    
    	panel.add(speedLabel,gbc);
    	
    	gbc.gridx=1;
      	gbc.gridy=3;
    	panel.add(listLabel,gbc);
    	
    	
    	gbc.gridx=0;
      	gbc.gridy=4;
    	panel.add(start,gbc);
    
    	gbc.gridx=1;
      	gbc.gridy=4;
    	panel.add(cancel,gbc);
		
	}
	
	
	
}
