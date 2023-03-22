import java.util.ArrayList;

public class FixedNetworkSubnettingStrategy implements SubnettingStrategy{
	private int numOfSubNets;
	
	public FixedNetworkSubnettingStrategy(int numOfSubNets) {
		this.setNumOfSubNets(numOfSubNets);
	}
	
	public ArrayList<String> Subnet(networkIPAddress netIp) {
		ArrayList<String> temp=new ArrayList<String>();
		int borrowedBits=(int) Math.ceil(Math.log(numOfSubNets)/Math.log(2));
		if(borrowedBits>=(32-netIp.getPrefix())-1) {
			System.out.println("if "+borrowedBits+" bits are borrowed form the Host Portion ("+(32-netIp.getPrefix())+ " bits). No bits are left for the host portion");
			return temp;
		}
		String netIP;
		int newPrefix=netIp.getPrefix()+borrowedBits;	
		int usableIP=(int)Math.pow(2,32-newPrefix);
		String subnet;
		for(int i=0;i<numOfSubNets;i++) {
			subnet="";
			// we are going to use the number of usable ip addresses in each 
			// subnet mask to create the ip address for the subnets
			int accumulativeNumberOfHosts=i*usableIP;
			subnet=netIp.getNetIpOctet()[0]+"."+netIp.getNetIpOctet()[1]+"."+netIp.getNetIpOctet()[2]+"."+(netIp.getNetIpOctet()[3]+accumulativeNumberOfHosts)+"/"+Integer.toString(newPrefix);
			temp.add(subnet);
		}
		return temp;
		
		/*
		 * Case Studey:
		 * Enter the IP address: 192.168.192.12/20
			How Many sub networks? 8
			the Subnets are:
			192.168.192.0/23
			192.168.192.512/23
			192.168.192.1024/23
			192.168.192.1536/23
			192.168.192.2048/23
			192.168.192.2560/23
			192.168.192.3072/23
			192.168.192.3584/23
		 */
	}

	public int getNumOfSubNets() {
		return numOfSubNets;
	}

	public void setNumOfSubNets(int numOfSubNets) {
		this.numOfSubNets = numOfSubNets;
	}
	
}
