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

public class networkRequirementsButton extends JButton implements netButtons{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputField;
	
	public networkRequirementsButton(JTextField inputField) {
		super("By Network requirements");
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
			String userInput = JOptionPane.showInputDialog(null, "How Many Networks do You Need?");
			try {
				int numberofSubNets=Integer.parseInt(userInput);
				ArrayList<String> subnet2=new NetSegmentation(new FixedNetworkSubnettingStrategy(numberofSubNets)).applySubnet(netIP);	
				if(subnet2.size()==0) {
					JOptionPane.showMessageDialog(null,"It's impossible to complete the task with the given information","No Subnets", JOptionPane.ERROR_MESSAGE);
				}
				else{
					PrintWriter writer = new PrintWriter("NetReq.txt", "UTF-8");		          
		            writer.println("The Given NetWork IP address: "+netIP.getNetIp()+"/"+netIP.getPrefix());
		            writer.println("The Given Number of Required subnets: "+numberofSubNets);
		            writer.println("The IP Addresses of The Subnets are: ");
		            for(String sub : subnet2) {
		            	writer.println(sub);
					}
		            writer.close();
		            File file = new File("NetReq.txt");
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
