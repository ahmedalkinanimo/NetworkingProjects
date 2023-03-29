import java.util.ArrayList;
import java.util.Arrays;
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
		MainFrame window=new MainFrame();
		/*
		Scanner in=new Scanner(System.in);
		String readIp;
		
		while(true) {
			System.out.print("Enter the IP address: ");
			readIp=in.next();
			if(IPAddress.validateIp(readIp)) {
				break;
			}
		}
		networkIPAddress netIP=new networkIPAddress(new IPAddress(readIp));
		// System.out.println(netIP);
		
		int numberofHosts=40;
		ArrayList<String> subnet1=new NetSegmentation(new FixedHostSubnettingStrategy(numberofHosts)).applySubnet(netIP);	
		System.out.println(numberofHosts+" hosts for each netowrks");
		for(String sub : subnet1) {
			System.out.println(sub);
		}
		System.out.println("---------------------------------------------");
		
		int numberofSubNets=10;
		ArrayList<String> subnet2=new NetSegmentation(new FixedNetworkSubnettingStrategy(numberofSubNets)).applySubnet(netIP);	
		System.out.println(numberofSubNets+" subnets");
		if(subnet2.size()==0) {
			System.out.println("There are no subnets");
		}
		else{
			System.out.println("the Subnets are:");
			for(String sub : subnet2) {
				System.out.println(sub);
			}
		}
		System.out.println("---------------------------------------------");
		
		int[] HostsPerSubNet= {55,29,12,6,5};
		ArrayList<String> subnet3=new NetSegmentation(new VLSMSubnettingStrategy(HostsPerSubNet)).applySubnet(netIP);	
		System.out.println(Arrays.toString(HostsPerSubNet)+" hosts for each netowrks");
		for(String temp : subnet3) {
			System.out.println(temp);
		}
		
		in.close();
		*/
	}

}
