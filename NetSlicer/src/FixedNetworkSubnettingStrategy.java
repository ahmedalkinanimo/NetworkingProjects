import java.util.ArrayList;

public class FixedNetworkSubnettingStrategy implements SubnettingStrategy{
	private int numOfSubNets;
	
	public FixedNetworkSubnettingStrategy(int numOfSubNets) {
		this.setNumOfSubNets(numOfSubNets);
	}
	
	public ArrayList<String> Subnet(networkIPAddress netIp) {
		ArrayList<String> temp=new ArrayList<String>();
		
		return temp;
	}

	public int getNumOfSubNets() {
		return numOfSubNets;
	}

	public void setNumOfSubNets(int numOfSubNets) {
		this.numOfSubNets = numOfSubNets;
	}
	
}
