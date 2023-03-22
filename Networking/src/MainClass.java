import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainClass {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Networking");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(400,200);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    
	    Font font1 = new Font("SansSerif", Font.BOLD, 20);
	    
	    frame.setLayout(new BorderLayout());
	    
	    JPanel bar = new JPanel();
	    bar.setLayout(new GridLayout(4, 1));
	    JPanel body = new JPanel();
	    JButton IP=new JButton("IP Calculations");
	    JButton FLSM=new JButton("Fixed Length Subnet Mask");
	    JButton VLSM=new JButton("Variable Length Subnet Mask");
	    JButton about=new JButton("About");
	    IP.setFont(font1);
	    FLSM.setFont(font1);
	    VLSM.setFont(font1);
	    about.setFont(font1);
	    bar.add(IP);
	    bar.add(FLSM);
	    bar.add(VLSM);
	    bar.add(about);
	    frame.add(bar,BorderLayout.NORTH);
	    frame.add(body,BorderLayout.CENTER);
	    frame.setVisible(true);
	    
	    IP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IPFrame IPWindow=new IPFrame();
			}	    	
	    });
	    
	    VLSM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VLSMFrame VLSMWindow=new VLSMFrame();
			}
		});
	    
	    
	    FLSM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FLSMFrame FLSMWindow=new FLSMFrame();
			}
		});
	    
	    about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,"CIST Networking Calculator\nAhmed Alkinani 2021-2022\nwww.kckcc.edu\naaljanabi@kckcc.edu","CIST0117 V1.0",JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

}
