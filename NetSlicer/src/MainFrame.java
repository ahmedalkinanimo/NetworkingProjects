import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.*;

public class MainFrame extends JFrame{
	 private JLabel inputLabel;
	 private JLabel IpFormat;
	 private JLabel subnet;
	 private JLabel message;
	 private JTextField inputField;
	 private netButtons subnetCal, numOfSubnets, VLSM, hostsPSub;
	 
	    public MainFrame() {
	        super("NetSlicer");
	        
	        Font font = new Font("Arial", Font.BOLD, 18);
	        
	        // Set up the layout
	        setLayout(new BorderLayout());

	        // Create the input field and label
	        inputLabel = new JLabel(" Enter IP Address:");
	        inputLabel.setFont(font);
	        inputField = new JTextField(20);
	        inputField.setFont(font);
	        IpFormat = new JLabel(" Example: 192.168.100.117/24");
	        IpFormat.setFont(font);
	        subnet = new JLabel("Subnet");
	        subnet.setFont(font);
	        subnet.setHorizontalAlignment(SwingConstants.CENTER);
	        
	        // Create the buttons
	        
	        subnetCal = new calculatorButton(inputField);
	        numOfSubnets = new networkRequirementsButton(inputField);
	        hostsPSub=new hostsPSubButton(inputField);
	        VLSM = new VLSMButton(inputField);
	        
	        // Add the components to the frame
	        JPanel inputPanel = new JPanel();
	        inputPanel.setLayout(new GridLayout(4, 1));
	        inputPanel.add(inputLabel);
	        inputPanel.add(inputField);
	        inputPanel.add(IpFormat);
	        inputPanel.add(subnet);
	        add(inputPanel, BorderLayout.NORTH);

	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new GridLayout(2, 2));
	        buttonPanel.add((Component) subnetCal);
	        buttonPanel.add((Component) numOfSubnets);
	        buttonPanel.add((Component) hostsPSub);
	        buttonPanel.add((Component) VLSM);
	        add(buttonPanel, BorderLayout.CENTER);

	        JPanel messagePanel = new JPanel();
	        message= new JLabel("Copyright © 2023 Ahmed Alkinani - aaljanabi@kckcc.edu");
	        Font font1 = new Font("Time New Roman", Font.ITALIC, 15);
	        message.setFont(font1);
	        messagePanel.add(message);
	        add(messagePanel,BorderLayout.SOUTH);
	        
	        // Set up the window
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(600, 250);
	        setVisible(true);
	        setLocationRelativeTo(null);
	        setResizable(false);
	    }
}
