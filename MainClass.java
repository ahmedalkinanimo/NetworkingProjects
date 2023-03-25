import java.util.ArrayList;

public class MainClass {
	
  public static void main(String[] args) {
    // TODO Auto-generated method stub
	  int numOfSubNets=8;int prefix=22; int[] NetIpOctet= {192,168,224,0};
	  //ArrayList<String> subnet1s=Subnet1(numOfSubNets,prefix,NetIpOctet);
	  //for(String sub : subnet1s) {
	//	  System.out.println(sub);
	//  }
	  int numOfHostsPerSubNet=50;
	  ArrayList<String> subnet2s=Subnet2(numOfHostsPerSubNet,prefix,NetIpOctet);
	  for(String sub : subnet2s) {
		  System.out.println(sub);
	  }
  }
  
  public static ArrayList<String> Subnet1(int numOfSubNets, int prefix, int[] NetIpOctet) {
		ArrayList<String> temp=new ArrayList<String>();
		int borrowedBits=(int) Math.ceil(Math.log(numOfSubNets)/Math.log(2));
		if(borrowedBits>=(32-prefix)-1) {
			System.out.println("if "+borrowedBits+" bits are borrowed form the Host Portion ("+(32-prefix)+ " bits). No bits are left for the host portion");
			return temp;
		}
		String netIP;
		int newPrefix=prefix+borrowedBits;	
		int usableIP=(int)Math.pow(2,32-newPrefix);
		
		String subnet;
		for(int i=0;i<numOfSubNets;i++) {
			subnet="";
			int accumulativeNumberOfHosts=i*usableIP;
			int[] maskMod=new int[4];
			for(int j=0;j<4;j++) {
				maskMod[j]=(accumulativeNumberOfHosts>>j*8) & 0xFF;	
			}
			subnet=(NetIpOctet[0]+maskMod[3])
					+"."+(NetIpOctet[1]+maskMod[2])
					+"."+(NetIpOctet[2]+maskMod[1])
					+"."+(NetIpOctet[3]+maskMod[0])
					+"/"+Integer.toString(newPrefix);
			temp.add(subnet);
		}
		return temp;
	}	
  
  
  
  public static ArrayList<String> Subnet2(int numOfHostsPerSubNet, int prefix, int[] NetIpOctet) {
		ArrayList<String> temp=new ArrayList<String>();
		int hostPortion=32-prefix;
		int keptBits=(int) Math.ceil(Math.log(numOfHostsPerSubNet+2)/Math.log(2));
				
		if(keptBits>=hostPortion) {
			System.out.println("if "+keptBits+" bits are kept for the Host Portion ("+hostPortion+ " bits). No bits are left to be used for subnetting");
			return temp;
		}
		int borrowedBits=hostPortion-keptBits;
		String netIP;
		int newPrefix=prefix+borrowedBits;	
		int usableIP=(int)Math.pow(2,32-newPrefix);
		int numOfSubNets=(int)Math.pow(2, borrowedBits);
		String subnet;
		for(int i=0;i<numOfSubNets;i++) {
			subnet="";
			int accumulativeNumberOfHosts=i*usableIP;
			int[] maskMod=new int[4];
			for(int j=0;j<4;j++) {
				maskMod[j]=(accumulativeNumberOfHosts>>j*8) & 0xFF;	
			}
			subnet=(NetIpOctet[0]+maskMod[3])
					+"."+(NetIpOctet[1]+maskMod[2])
					+"."+(NetIpOctet[2]+maskMod[1])
					+"."+(NetIpOctet[3]+maskMod[0])
					+"/"+Integer.toString(newPrefix);
			temp.add(subnet);
		}
		return temp;
	}
}

