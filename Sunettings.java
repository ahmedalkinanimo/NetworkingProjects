import java.util.ArrayList;

public class MainClass {
	
  public static void main(String[] args) {
    // TODO Auto-generated method stub
	 	  
	  int numOfSubNets=8;int prefix=22; int[] NetIpOctet= {192,168,224,0};
	  int numOfHostsPerSubNet=50;
	  ArrayList<Integer> hosts=new ArrayList<Integer>();
	  hosts.add(120);
	  hosts.add(60);
	  hosts.add(30);
	  hosts.add(12);
	  hosts.add(2);
	  /*
	  ArrayList<String> subnet1s=Subnet1(numOfSubNets,prefix,NetIpOctet);
	  for(String sub : subnet1s) {
		  System.out.println(sub);
	  }
	  System.out.println("--------------------------------------");
	  
	  ArrayList<String> subnet2s=Subnet2(numOfHostsPerSubNet,prefix,NetIpOctet);
	  for(String sub : subnet2s) {
		  System.out.println(sub);
	  }
	  System.out.println("--------------------------------------");
	  */
	  ArrayList<String> subnet3s=Subnet3(hosts,prefix,NetIpOctet);
	  for(String sub : subnet3s) {
		  System.out.println(sub);
	  }
	  
  }
  
  public static ArrayList<String> Subnet1(int numOfSubNets, int prefix, int[] NetIpOctet) {
		ArrayList<String> subnets=new ArrayList<String>();
		int borrowedBits=(int) Math.ceil(Math.log(numOfSubNets)/Math.log(2));
		if(borrowedBits>=(32-prefix)-1) {
			System.out.println("if "+borrowedBits+" bits are borrowed form the Host Portion ("+(32-prefix)+ " bits). No bits are left for the host portion");
			return subnets;
		}		
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
			subnets.add(subnet);
		}
		return subnets;
	}	
  
  public static ArrayList<String> Subnet2(int numOfHostsPerSubNet, int prefix, int[] NetIpOctet) {
		ArrayList<String> subnets=new ArrayList<String>();
		int hostPortion=32-prefix;
		int keptBits=(int) Math.ceil(Math.log(numOfHostsPerSubNet+2)/Math.log(2));
				
		if(keptBits>=hostPortion) {
			System.out.println("Error: not enough addresses for subnet with " + numOfHostsPerSubNet + " hosts");
			return subnets;
		}
		int borrowedBits=hostPortion-keptBits;
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
			subnets.add(subnet);
		}
		return subnets;
	}
  
  public static ArrayList<String> Subnet3(ArrayList<Integer> hosts, int prefix, int[] NetIpOctet) {
	  ArrayList<String> subnets=new ArrayList<String>();
	  int hostInd=0;	  
	  int hostPortion=32-prefix;
	  int subnetHosts = hosts.get(hostInd);	
	  int keptBits=(int) Math.ceil(Math.log(subnetHosts+2)/Math.log(2));	  		   
	  if(keptBits>=hostPortion) {
			System.out.println("not enough addresses for subnet with " + subnetHosts + " hosts");
	  }else {
		  subnets=Subnet3(hostInd,subnets, hosts, prefix, NetIpOctet); 
	  }
	  return subnets;
  }
  
  public static ArrayList<String> Subnet3(int hostInd,ArrayList<String> subnets, ArrayList<Integer> hosts, int prefix, int[] NetIpOctet) {
	  if (hostInd == hosts.size()) {
          // Base case: All hosts have been allocated to subnets
          return subnets;
      }
	  int hostPortion=32-prefix;
	  int subnetHosts = hosts.get(hostInd);	
	  int keptBits=(int) Math.ceil(Math.log(subnetHosts+2)/Math.log(2));
	  
	  int borrowedBits=hostPortion-keptBits;
	  int newPrefix=prefix+borrowedBits;	
	  int usableIP=(int)Math.pow(2,32-newPrefix);
	  String subnet="";
	  subnet=(NetIpOctet[0])
				+"."+(NetIpOctet[1])
				+"."+(NetIpOctet[2])
				+"."+(NetIpOctet[3])
				+"/"+Integer.toString(newPrefix);
	  subnets.add(subnet);
	  hostInd++;
	  int[] maskMod=new int[4];
	  for(int j=0;j<4;j++) {
		  maskMod[j]=(usableIP>>j*8) & 0xFF;	
	  }
	  for(int j=0;j<4;j++) {
		  NetIpOctet[j]=NetIpOctet[j]+maskMod[3-j];	
	  }
	  return Subnet3(hostInd,subnets,hosts,newPrefix, NetIpOctet); 
  }
  
}

