import java.util.ArrayList;

public class VLSMSubnettingStrategy implements SubnettingStrategy {
	private int[] numOhHostsPerSubNet;
	
	public VLSMSubnettingStrategy(int[] numOhHostsPerSubNet) {
		this.setNumOhHostsPerSubNet(numOhHostsPerSubNet);
	}
	
	public ArrayList<String> Subnet(networkIPAddress netIp) {
		ArrayList<String> temp=new ArrayList<String>();
		
		return temp;
	}

	public int[] getNumOhHostsPerSubNet() {
		return numOhHostsPerSubNet;
	}

	public void setNumOhHostsPerSubNet(int[] numOhHostsPerSubNet) {
		this.numOhHostsPerSubNet = numOhHostsPerSubNet;
	}
	
}
