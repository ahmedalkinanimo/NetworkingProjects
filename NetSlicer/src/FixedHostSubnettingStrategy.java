import java.util.ArrayList;

public class FixedHostSubnettingStrategy implements SubnettingStrategy{
	private int numOfHosts;
	
	public FixedHostSubnettingStrategy(int numOfHosts) {
		this.setNumOfHosts(numOfHosts);
	}
	
	public ArrayList<String> Subnet(networkIPAddress netIp) {
		ArrayList<String> temp=new ArrayList<String>();
		
		return temp;
	}

	public int getNumOfHosts() {
		return numOfHosts;
	}

	public void setNumOfHosts(int numOfHosts) {
		this.numOfHosts = numOfHosts;
	}
	
	
}

