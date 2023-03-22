import java.util.ArrayList;
import java.util.Scanner;

/***
 * 
 * @author aaljanabi
 *
 *	The primary aim of this project is to develop an application that can subnet a 
 *	given IPv4 address using three different subnetting methods. Additionally, the 
 *	application validates the input IPv4 address and computes the corresponding network 
 *	IPv4 address. For each network, the application determines the first, last, and 
 *	broadcast IPv4 addresses and also computes the number of available usable IP 
 *	addresses. The goal of this project is to accomplish these tasks without relying on 
 *	any pre-existing network libraries.
 *	Strategic Pattern Design will be utilized in this project.
 */
public class MainClass {

	public static void main(String[] args) throws invalidIP {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		String readIp;
		
		while(true) {
			System.out.print("Enter the IP address: ");
			readIp=in.next();
			if(IPAddress.validateIp(readIp))
				break;
		}
		networkIPAddress netIP=new networkIPAddress(new IPAddress(readIp));
		System.out.println(netIP);
		
		
		ArrayList<String> subnets=new NetSegmentation(new FixedHostSubnettingStrategy()).applySubnet(netIP);	
		
		in.close();
	}

}
