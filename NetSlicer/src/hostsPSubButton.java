import java.awt.Desktop;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class hostsPSubButton extends JButton implements netButtons{

	private JTextField inputField;
	
	public hostsPSubButton(JTextField inputField) {
		super("By Hosts Requirments");
		Font font = new Font("Arial", Font.BOLD, 16);
		super.setFont(font);
		addActionListener(this);
		this.inputField=inputField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String IP=inputField.getText();
		if(!(IPAddress.validateIp(IP))) {
			JOptionPane.showMessageDialog(null,"Invalid IP address","Error", JOptionPane.ERROR_MESSAGE);
		}else {
			networkIPAddress netIP=new networkIPAddress(new IPAddress(IP));
			String userInput = JOptionPane.showInputDialog(null, "How Many Hosts Per Network?");
			try {
				int numberofSubNets=Integer.parseInt(userInput);
				ArrayList<String> subnet2=new NetSegmentation(new FixedHostSubnettingStrategy(numberofSubNets)).applySubnet(netIP);	
				if(subnet2.size()==0) {
					JOptionPane.showMessageDialog(null,"There are NO subnets","No Subnets", JOptionPane.ERROR_MESSAGE);
				}
				else{
					PrintWriter writer = new PrintWriter("HostReq.txt", "UTF-8");		          
		            writer.println("The Given NetWork IP address: "+netIP.getNetIp()+"/"+netIP.getPrefix());
		            writer.println("The Given Number of Required Hosts Per subnet: "+numberofSubNets);
		            writer.println("Subnetting the Given IP address will result in the creation of "+subnet2.size()+" different subnets.");
		            writer.println("The IP Addresses of The Subnets are: ");
		            for(String sub : subnet2) {
		            	writer.println(sub);
					}
		            writer.close();
		            File file = new File("HostReq.txt");
		            Desktop.getDesktop().open(file);
				}
				
	        } catch (IOException e1) {
	        	JOptionPane.showMessageDialog(null,"Error writing to file: " + e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
	        }catch(NumberFormatException e2) {
	        	JOptionPane.showMessageDialog(null,"Invalid Entry","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
