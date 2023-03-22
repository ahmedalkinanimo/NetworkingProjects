import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IPFrame {
	
	public static boolean classABC(short oq1,short oq2,short oq3, short oq4, byte pfx) {
		if(oq1==10 && (oq2>=0 && oq2<=255) && (oq3>=0 && oq3<=255) && (oq4>=0 && oq4<=255) && (pfx>=8 && pfx <32)) {
			return true;
		}else if(oq1==172 && (oq2>=16 && oq2<=31) && (oq3>=0 && oq3<=255) && (oq4>=0 && oq4<=255) && (pfx>=12 && pfx <32)) {
			return true;
		}else if(oq1==192 && (oq2==168) && (oq3>=0 && oq3<=255) && (oq4>=0 && oq4<=255) && (pfx>=16 && pfx <32)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public IPFrame() {
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		JFrame IPCalFram = new JFrame("IP Calculations");
		IPCalFram.setSize(850,350);
		IPCalFram.setLocationRelativeTo(null);
		IPCalFram.setResizable(false);
		JLabel ipLabel = new JLabel("Ip Address / Prefix: ");
		JLabel preLabel = new JLabel("/");
		JTextField Toq1,Toq2,Toq3,Toq4,Tpfx;
		Toq1=new JTextField("",3);  
		Toq2=new JTextField("",3);  
		Toq3=new JTextField("",3);  
		Toq4=new JTextField("",3);  
		Tpfx=new JTextField("",2);  
		JButton Cal=new JButton("Calculate");
		JButton Reset=new JButton(" Reset");
		JPanel bodyTop = new JPanel();
		
		ipLabel.setFont(font1);
		preLabel.setFont(font1);
		
		Toq1.setFont(font1);
		Toq2.setFont(font1);
		Toq3.setFont(font1);
		Toq4.setFont(font1);
		Tpfx.setFont(font1);
		Cal.setFont(font1);
		Reset.setFont(font1);
		bodyTop.add(ipLabel);
		bodyTop.add(Toq1);
		bodyTop.add(Toq2);
		bodyTop.add(Toq3);
		bodyTop.add(Toq4);
		bodyTop.add(Toq4);
		bodyTop.add(preLabel);
		bodyTop.add(Tpfx);
		bodyTop.add(Cal);
		bodyTop.add(Reset);
		bodyTop.setLayout(new FlowLayout());
		
		JPanel bodyCenter = new JPanel();
		bodyCenter.setBackground(new Color(192,192,192));
		
		IPCalFram.add(bodyTop,BorderLayout.NORTH);
		IPCalFram.add(bodyCenter,BorderLayout.CENTER);
		JLabel enteredIP = new JLabel("      Entered IP Address: ");
		JLabel Net = new JLabel("      Network IP Address: ");
		JLabel BC = new JLabel("      Broad Cast IP Address: ");
		JLabel first = new JLabel("      First IP Address: ");
		JLabel last = new JLabel("      Last IP Address: ");
		JLabel numberHost = new JLabel("      # of Available Hosts: ");
		enteredIP.setFont(font1);
		Net.setFont(font1);
		BC.setFont(font1);
		first.setFont(font1);
		last.setFont(font1);
		numberHost.setFont(font1);
		bodyCenter.setLayout(new GridLayout(6, 1));
		bodyCenter.add(enteredIP);
		bodyCenter.add(Net);
		bodyCenter.add(BC);
		bodyCenter.add(first);
		bodyCenter.add(last);
		bodyCenter.add(numberHost);
		
		
		Cal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				short oq1=0,oq2=0,oq3=0,oq4=0;
				byte pfx=0;
				String Soq1,Soq2,Soq3,Soq4,Spft;
				Soq1=Toq1.getText();
				Soq2=Toq2.getText();
				Soq3=Toq3.getText();
				Soq4=Toq4.getText();
				Spft=Tpfx.getText();
				//--------------------------------------------
				try {
					oq1=Short.parseShort(Soq1);
					oq2=Short.parseShort(Soq2);
					oq3=Short.parseShort(Soq3);
					oq4=Short.parseShort(Soq4);
					pfx=Byte.parseByte(Spft);
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(IPCalFram,e1.getMessage(),"Input Error",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				
				if(!(classABC(oq1,oq2,oq3,oq4,pfx))) {
					JOptionPane.showMessageDialog(IPCalFram,"Private Address Error\n10.0.0.0 — 10.255.255.255\n172.16.0.0 — 172.31.255.255\n192.168.0.0 — 192.168.255.255","Invalid IP Address",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				//--------------------------------------------					
				IPAdress ip1=new IPAdress(oq1,oq2,oq3,oq4,pfx);
				enteredIP.setText("      Entered IP Address: "+ip1);
				Net.setText("      Network IP Address: "+ip1.NetWorkIP());
				BC.setText("      Broad Cast IP Address: "+ip1.BCIP());
				first.setText("      First IP Address: "+ip1.firstIP());
				last.setText("      Last IP Address: "+ip1.lastIP());
				numberHost.setText("      # of Available Hosts: "+ip1.numberOfHosts());	
			}
		});
		
		
		Reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Toq1.setText("");
				Toq2.setText("");
				Toq3.setText("");
				Toq4.setText("");
				Tpfx.setText("");

				enteredIP.setText("      Entered IP Address: ");
				Net.setText("      Network IP Address: ");
				BC.setText("      Broad Cast IP Address: ");
				first.setText("      First IP Address: ");
				last.setText("      Last IP Address: ");
				numberHost.setText("      # of Available Hosts: ");
				
			}
		});
			
		IPCalFram.setVisible(true);
	}
	
}
