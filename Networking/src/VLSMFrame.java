import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VLSMFrame {
	
	
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
	
	public static boolean checkNum(byte pfx, short num, boolean network) { 
		if(network) {
			if(num>(int)(Math.pow(2,(32-pfx-2))))
				return false;
			else
				return true;
		}else {
			if(num>(int)(Math.pow(2,(pfx+1))-2))
				return false;
			else
				return true;
		}
	}
	
	public VLSMFrame() {
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		JFrame NetworkVLSM = new JFrame("Variable Length Subnet Mast");
		NetworkVLSM.setSize(700,500);
		NetworkVLSM.setLocationRelativeTo(null);
		NetworkVLSM.setResizable(false);
		JLabel ipLabel = new JLabel("Ip Address / Prefix: ");
		JLabel preLabel = new JLabel("/");
		JLabel netLabel = new JLabel("#networks");
		JTextField Toq1,Toq2,Toq3,Toq4,Tpfx, numN;
		Toq1=new JTextField("",3);  
		Toq2=new JTextField("",3);  
		Toq3=new JTextField("",3);  
		Toq4=new JTextField("",3);  
		Tpfx=new JTextField("",2);
		numN=new JTextField("",2);

		//JButton Cal=new JButton("Calculate");
		JButton Reset=new JButton("Reset");
		JButton Next=new JButton("Next");
		
		JPanel bodylevel1 = new JPanel();
		JPanel bodylevel2 = new JPanel();
		
		bodylevel1.setBackground(new Color(119,136,153));
		bodylevel2.setBackground(new Color(119,136,153));
		
		
		ipLabel.setFont(font1);
		preLabel.setFont(font1);
		
		Toq1.setFont(font1);
		Toq2.setFont(font1);
		Toq3.setFont(font1);
		Toq4.setFont(font1);
		Tpfx.setFont(font1);
		netLabel.setFont(font1);
		numN.setFont(font1);
		
		//Cal.setFont(font1);
		Reset.setFont(font1);
		Next.setFont(font1);
		
		bodylevel1.add(ipLabel);
		bodylevel1.add(Toq1);
		bodylevel1.add(Toq2);
		bodylevel1.add(Toq3);
		bodylevel1.add(Toq4);
		bodylevel1.add(Toq4);
		bodylevel1.add(preLabel);
		bodylevel1.add(Tpfx);
		
		bodylevel2.add(netLabel);
		bodylevel2.add(numN);
		bodylevel2.add(Next);
		bodylevel2.add(Reset);
		
		bodylevel1.setLayout(new FlowLayout());
		bodylevel2.setLayout(new FlowLayout());
		
		
		JPanel bodylevel3 = new JPanel();				
		bodylevel3.setBackground(new Color(169,169,169));
		
		
		JPanel bodylevel4 = new JPanel(new FlowLayout());
		bodylevel4.setBackground(new Color(220,220,220));
		
		
		NetworkVLSM.add(bodylevel1,BorderLayout.NORTH);
		NetworkVLSM.add(bodylevel2,BorderLayout.SOUTH);
		NetworkVLSM.add(bodylevel3,BorderLayout.WEST);
		NetworkVLSM.add(bodylevel4,BorderLayout.CENTER); 
		
		
		/*
		Cal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i=1;i<=20;i++) {
					printL[i-1].setText("  Networks "+((i<=9)?"0"+i:i)+" : ");
				} 
				
				short oq1=0,oq2=0,oq3=0,oq4=0,num=0;
				byte pfx=0;
				String Soq1,Soq2,Soq3,Soq4,Spft, SnumNH;
				Soq1=Toq1.getText();
				Soq2=Toq2.getText();
				Soq3=Toq3.getText();
				Soq4=Toq4.getText();
				Spft=Tpfx.getText();
				SnumNH=numNH.getText();
				if(!(jRadioButton1.isSelected()) && !(jRadioButton2.isSelected())) {
					JOptionPane.showMessageDialog(NetworkVLSM,"Select # NetWorks or # Hosts","unselected option",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				
				//--------------------------------------------
				try {
					oq1=Short.parseShort(Soq1);
					oq2=Short.parseShort(Soq2);
					oq3=Short.parseShort(Soq3);
					oq4=Short.parseShort(Soq4);
					pfx=Byte.parseByte(Spft);
					num=Short.parseShort(SnumNH);
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(NetworkVLSM,e1.getMessage(),"Input Error",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(!(classABC(oq1,oq2,oq3,oq4,pfx))) {
					JOptionPane.showMessageDialog(NetworkVLSM,"Private Address Error\n10.0.0.0 — 10.255.255.255\n172.16.0.0 — 172.31.255.255\n192.168.0.0 — 192.168.255.255","Invalid IP Address",JOptionPane.ERROR_MESSAGE);
					return ;
				}
	
				IPAdress ip=new IPAdress(oq1,oq2,oq3,oq4,pfx);
				String temp=ip.NetWorkIP()+"/"+pfx;
				if(!(ip.toString().equals(temp))) {
					JOptionPane.showMessageDialog(NetworkVLSM,"This is Not a Network IP Address","unacceptable Network IP Address",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				
				
				if(!(checkNum(pfx,num, jRadioButton1.isSelected()))) {
					JOptionPane.showMessageDialog(NetworkVLSM,"# of Networks or Hosts Not Acceptable","# of Networks or Hosts Error",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				//--------------------------------------------
				ArrayList<IPAdress> networkIPs=new ArrayList<IPAdress>();
				if (jRadioButton1.isSelected()) { 
					FLSMNetwork net=new FLSMNetwork(oq1,oq2,oq3,oq4,pfx,num);
					networkIPs=net.getNetworkIPs();	
                }		  
                if (jRadioButton2.isSelected()) {
                	FLSMHosts net=new FLSMHosts(oq1,oq2,oq3,oq4,pfx,num);
                	networkIPs=net.getNetworkIPs();	
                }
                //------------------------------------------
                
                String str="";
                int size=networkIPs.size();
                try {
            	    FileWriter fw = new FileWriter("outData.txt", false);
            	    PrintWriter pw = new PrintWriter(fw, false);
            	    pw.flush();
            	    pw.close();
            	    fw.close();
					PrintWriter newOutput=new PrintWriter(new FileOutputStream(new File("outData.txt"),true));
					for(int x=1;x<=size;x++) {
                		str=((x<=9)?"0"+x:x)+": "+networkIPs.get(x-1).toString();	
                		newOutput.println(str);
                		System.out.println(str);
                	}
					newOutput.close();
            	} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
                
            	if(size<=20){
	                int i=1;
	                for(;i<=networkIPs.size();i++) {
	                	str=networkIPs.get(i-1).toString();
	                	printL[i-1].setText("  Networks "+((i<=9)?"0"+i:i)+" : "+str);
	                }
	                for(;i<=20;i++) {
	                	printL[i-1].setText("");
	                }
	                JOptionPane.showMessageDialog(NetworkVLSM,"Network list is stored in a text File\noutData.txt","Networks",JOptionPane.INFORMATION_MESSAGE);
                }else {
                	int i=1;
                	for(;i<=20;i++) {
	                	str=networkIPs.get(i-1).toString();
	                	printL[i-1].setText("  Networks "+((i<=9)?"0"+i:i)+" : "+str);
	                }
                	JOptionPane.showMessageDialog(NetworkVLSM,"No space enough to print all the networks\nNetwork list is stored in a text File\noutData.txt","Networks",JOptionPane.INFORMATION_MESSAGE);
                }
                
                //------------------------------------------
			}
		});
		*/
		Reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Toq1.setText("");
				Toq2.setText("");
				Toq3.setText("");
				Toq4.setText("");
				Tpfx.setText("");
				numN.setText("");
				
				//for(int i=1;i<=20;i++) {
				//	printL[i-1].setText("");
                //}
			}
		});
		
		NetworkVLSM.setVisible(true);
	}
}
