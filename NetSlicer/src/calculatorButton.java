import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;

public class calculatorButton extends JButton implements netButtons{
		
	private JTextField inputField;
	
	public calculatorButton(JTextField inputField) {
		super("Calculator");
		Font font = new Font("Arial", Font.BOLD, 18);
		super.setFont(font);
		addActionListener(this);
		this.inputField=inputField;
	}
		
	@Override
	  public void actionPerformed(ActionEvent e) {
			String IP=inputField.getText();
	
	  }

		
}
