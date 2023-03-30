import java.util.ArrayList;
import java.util.Arrays;

public class VLSMSubnettingStrategy implements SubnettingStrategy {
	private int[] numOhHostsPerSubNet;
	
	public VLSMSubnettingStrategy(int[] numOhHostsPerSubNet) {
		Arrays.sort(numOhHostsPerSubNet);
        for (int i = 0; i < numOhHostsPerSubNet.length / 2; i++) {
            int temp = numOhHostsPerSubNet[i];
            numOhHostsPerSubNet[i] = numOhHostsPerSubNet[numOhHostsPerSubNet.length - 1 - i];
            numOhHostsPerSubNet[numOhHostsPerSubNet.length - 1 - i] = temp;
        }
		this.setNumOhHostsPerSubNet(numOhHostsPerSubNet);
	}
	
	  public ArrayList<String> Subnet(networkIPAddress netIp) {
		  ArrayList<String> subnets=new ArrayList<String>();
		  int hostInd=0;	  
		  int hostPortion=32-netIp.getPrefix();
		  int subnetHosts = numOhHostsPerSubNet[hostInd];	
		  int keptBits=(int) Math.ceil(Math.log(subnetHosts+2)/Math.log(2));	  		   
		  if(keptBits>=hostPortion) {
				//System.out.println("not enough addresses for subnet with " + subnetHosts + " hosts");
		  }else {
			  subnets=Subnet(hostInd,subnets, netIp); 
		  }
		  return subnets;
	  }
	  
	  public ArrayList<String> Subnet(int hostInd,ArrayList<String> subnets,networkIPAddress netIp) {
		  if (hostInd == numOhHostsPerSubNet.length) {
	          // Base case: All hosts have been allocated to subnets
	          return subnets;
	      }
		  int hostPortion=32-netIp.getPrefix();
		  int subnetHosts = numOhHostsPerSubNet[hostInd];
		  int keptBits=(int) Math.ceil(Math.log(subnetHosts+2)/Math.log(2));
		  
		  int borrowedBits=hostPortion-keptBits;
		  int newPrefix=netIp.getPrefix()+borrowedBits;	
		  int usableIP=(int)Math.pow(2,32-newPrefix);
		  String subnet="";
		  subnet=(netIp.getNetIpOctet()[0])
					+"."+(netIp.getNetIpOctet()[1])
					+"."+(netIp.getNetIpOctet()[2])
					+"."+(netIp.getNetIpOctet()[3])
					+"/"+Integer.toString(newPrefix);
		  subnets.add(subnet);
		  hostInd++;
		  int[] maskMod=new int[4];
		  for(int j=0;j<4;j++) {
			  maskMod[j]=(usableIP>>j*8) & 0xFF;	
		  }
		  for(int j=0;j<4;j++) {
			  netIp.setNetIpOctet(netIp.getNetIpOctet()[j]+maskMod[3-j],j);
			  if(netIp.getNetIpOctet()[j]>=255)
				  return subnets;
		  }
		  
		  return Subnet(hostInd,subnets,netIp); 
	  }
	
	public int[] getNumOhHostsPerSubNet() {
		return numOhHostsPerSubNet;
	}

	public void setNumOhHostsPerSubNet(int[] numOhHostsPerSubNet) {
		this.numOhHostsPerSubNet = numOhHostsPerSubNet;
	}
	
}
