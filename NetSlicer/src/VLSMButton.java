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
import java.util.Arrays;
import java.util.Comparator;

public class VLSMButton extends JButton implements netButtons{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputField;
	
	public VLSMButton(JTextField inputField) {
		super("Using VLSM");
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
			String userInput = JOptionPane.showInputDialog(null, "Enter the # of hosts of each subnet, use space between numbers");
			try {
				String[] strArray = userInput.split(" ");
				Integer[] intArray = new Integer[strArray.length];

				for (int i = 0; i < strArray.length; i++) {
				    intArray[i] = Integer.parseInt(strArray[i]);
				}
				Arrays.sort(intArray, Comparator.reverseOrder());
				int[] sortedArr = Arrays.stream(intArray).mapToInt(Integer::intValue).toArray();
				
				ArrayList<String> subnet2=new NetSegmentation(new VLSMSubnettingStrategy(sortedArr)).applySubnet(netIP);	
				if(subnet2.size()==0) {
					JOptionPane.showMessageDialog(null,"It's impossible to complete the task with the given information","No Subnets", JOptionPane.ERROR_MESSAGE);
				}
				else{
					PrintWriter writer = new PrintWriter("HostReq.txt", "UTF-8");		          
		            writer.println("The Given NetWork IP address: "+netIP.getNetIp()+"/"+netIP.getPrefix());
		            writer.println("The Given Number of Hosts for each subnet: "+Arrays.toString(sortedArr));
		            if(subnet2.size()<sortedArr.length)
		            	writer.println("By using the given information, It's impossible to create "+sortedArr.length+" subnets");
		            writer.println("Subnetting the Given IP address will result in the creation of "+subnet2.size()+" different subnets.");
		            writer.println("The IP Addresses of The Subnets are: ");
		            int counter=0;
		            for(String sub : subnet2) {
		            	writer.println("For "+sortedArr[counter]+" hosts, IP address is "+sub);
		            	counter++;
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
