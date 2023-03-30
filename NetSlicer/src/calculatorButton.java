import java.awt.Font;
import java.awt.Desktop;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class calculatorButton extends JButton implements netButtons{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputField;
	
	public calculatorButton(JTextField inputField) {
		super("Calculator");
		Font font = new Font("Arial", Font.BOLD, 16);
		super.setFont(font);
		addActionListener(this);
		this.inputField=inputField;
	}
		
	@Override
	  public void actionPerformed(ActionEvent e){
			String IP=inputField.getText();
			if(!(IPAddress.validateIp(IP))) {
				JOptionPane.showMessageDialog(null,"Invalid IP address","Error", JOptionPane.ERROR_MESSAGE);
			}else {
				networkIPAddress netIP=new networkIPAddress(new IPAddress(IP));
				//JOptionPane.showMessageDialog(null,netIP);
				//System.out.println(netIP);
				try {
		            PrintWriter writer = new PrintWriter("Calculator.txt", "UTF-8");		          
		            writer.println(netIP.toString());
		            writer.close();
		            File file = new File("Calculator.txt");
		            Desktop.getDesktop().open(file);
		        } catch (IOException e1) {
		        	JOptionPane.showMessageDialog(null,"Error writing to file: " + e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
	  }

		
}
