import java.util.ArrayList;

public class FLSMNetwork {
	private IPAdress netAddress;
	private ArrayList<IPAdress> networkIPs;
	private byte newpfx;
	private short numberNetworks;
	private short neededNetWorks;
	//---------------------------------------------

	public FLSMNetwork(short b1,short b2,short b3,short b4,byte pf,short n) {
		netAddress=new IPAdress(b1,b2,b3,b4,pf);
		neededNetWorks=n;
		networkIPs=new ArrayList<IPAdress>();
		subNetting();
	}
	
	public IPAdress getNetAddress() {
		return netAddress;
	}

	public void setNetAddress(IPAdress netAddress) {
		this.netAddress = netAddress;
	}

	public ArrayList<IPAdress> getNetworkIPs() {
		return networkIPs;
	}

	public void setNetworkIPs(ArrayList<IPAdress> networkIPs) {
		this.networkIPs = networkIPs;
	}

	public short getNeededNetWorks() {
		return neededNetWorks;
	}

	public void setNeededNetWorks(short netWorks) {
		neededNetWorks = netWorks;
	}
	//----------------------------------------------------------------------
	public void subNetting() {
		byte bw=1;
		while(Math.pow(2,bw)<neededNetWorks) {
			bw++;
		}
		newpfx=(byte)(netAddress.getPrefix()+bw);
		numberNetworks=(short) Math.pow(2,bw);
		int num;
		if(netAddress.getPrefix()>=24) {
			for(int i=0;i<numberNetworks;i++) {
				IPAdress temp=new IPAdress(netAddress.getByte1(),netAddress.getByte2(),netAddress.getByte3(),netAddress.getByte4(),newpfx);
				num=i;
				num=(num<<(32-newpfx));
				temp.setByte4((short)(num+netAddress.getByte4()));
				networkIPs.add(temp);
			}
		}else if(netAddress.getPrefix()>=16 && (newpfx<=24)) {
			for(int i=0;i<numberNetworks;i++) {
				IPAdress temp=new IPAdress(netAddress.getByte1(),netAddress.getByte2(),netAddress.getByte3(),netAddress.getByte4(),newpfx);
				num=i;
				num=(num<<(24-newpfx));
				temp.setByte3((short)(num+netAddress.getByte3()));
				networkIPs.add(temp);
			}	
		}else if(netAddress.getPrefix()>=8 && (newpfx<=16)) {
			for(int i=0;i<numberNetworks;i++) {
				IPAdress temp=new IPAdress(netAddress.getByte1(),netAddress.getByte2(),netAddress.getByte3(),netAddress.getByte4(),newpfx);
				num=i;
				num=(num<<(16-newpfx));
				temp.setByte2((short)(num+netAddress.getByte2()));
				networkIPs.add(temp);
			}	
		}else if(netAddress.getPrefix()>=16 && (newpfx>24)) {
			for(int i=0;i<numberNetworks;i++) {
				IPAdress temp=new IPAdress(netAddress.getByte1(),netAddress.getByte2(),netAddress.getByte3(),netAddress.getByte4(),newpfx);
				num=i;				
				num=(num<<(32-newpfx));
				// byte4 and byte3
				int left=num&255;
				int right=(num&65280)>>8;
				temp.setByte4((short)(left+netAddress.getByte4()));
				temp.setByte3((short)(right+netAddress.getByte3()));
				networkIPs.add(temp);
			}
		}else if(netAddress.getPrefix()>=8 && (newpfx>16) && (newpfx<=24)) {
			for(int i=0;i<numberNetworks;i++) {
				IPAdress temp=new IPAdress(netAddress.getByte1(),netAddress.getByte2(),netAddress.getByte3(),netAddress.getByte4(),newpfx);
				num=i;
				num=(num<<(24-newpfx));
				int left=num&255;
				int right=(num&65280)>>8;
				temp.setByte3((short)(left+netAddress.getByte3()));
				temp.setByte2((short)(right+netAddress.getByte2()));
				networkIPs.add(temp);
			}
		}else if(netAddress.getPrefix()>=8 && (newpfx>24)) {
			for(int i=0;i<numberNetworks;i++) {
				IPAdress temp=new IPAdress(netAddress.getByte1(),netAddress.getByte2(),netAddress.getByte3(),netAddress.getByte4(),newpfx);
				num=i;
				num=(num<<(32-newpfx));
				int left=num&255;
				int mid=(num&65280)>>8;
				int right=(num&16711680)>>16;
				temp.setByte4((short)(left+netAddress.getByte4()));
				temp.setByte3((short)(mid+netAddress.getByte3()));
				temp.setByte2((short)(right+netAddress.getByte2()));
				networkIPs.add(temp);
			}
		}else {
			// Do Nothing
		}		
	}
}





