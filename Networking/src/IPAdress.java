
public class IPAdress {
	private short byte1;
	private short byte2;
	private short byte3;
	private short byte4;
	private byte prefix;
	private short subM1;
	private short subM2;
	private short subM3;
	private short subM4;
	private String subnetMask;
	//-------------------------------
	public short getByte1() {
		return byte1;
	}
	public void setByte1(short Byte1) {
		if(Byte1<=255 && Byte1>=0)
			this.byte1 = Byte1;
		else
			this.byte1=192;
	}
	public short getByte2() {
		return byte2;
	}
	public void setByte2(short Byte2) {
		if(Byte2<=255 && Byte2>=0)
			this.byte2 = Byte2;
		else
			this.byte2=168;
	}
	public short getByte3() {
		return byte3;
	}
	public void setByte3(short Byte3) {
		if(Byte3<=255 && Byte3>=0)
			this.byte3 = Byte3;
		else
			this.byte3=0;
	}
	public short getByte4() {
		return byte4;
	}
	public void setByte4(short Byte4) {
		if(Byte4<=255 && Byte4>=0)
			this.byte4 = Byte4;
		else
			this.byte4=0;
	}
	public byte getPrefix() {
		return prefix;
	}
	public void setPrefix(byte Prefix) {
		if(Prefix>0 && Prefix<32)
			this.prefix = Prefix;
		else
			this.prefix = 16;
	}
	//-------------------------------
	public IPAdress(short b1,short b2,short b3,short b4,byte pf) {
		// default IP address 192.168.0.0/64
		this.setByte1(b1);
		this.setByte2(b2);
		this.setByte3(b3);
		this.setByte4(b4);
		this.setPrefix(pf);
		prefix2SubnetMask();
	}

	public IPAdress() {
		// default IP address 192.168.0.0/64
		byte1=192;
		byte2=168;
		byte3=0;
		byte4=0;
		prefix=16;
		prefix2SubnetMask();
	}

	public String toString() {
		return byte1+"."+byte2+"."+byte3+"."+byte4+"/"+prefix;
	}
	//------------------------------------------------------------
	public void prefix2SubnetMask() {
		if(this.prefix>=24) {
			byte temp=(byte)(8-(32-prefix));
			subM1=255;
			subM2=255;
			subM3=255;
			subM4=p2SM(temp);
		}else if(this.prefix>=16) {
			byte temp=(byte)(16-(32-prefix));
			subM1=255;
			subM2=255;
			subM3=p2SM(temp);
			subM4=0;
		}else if(this.prefix>=8) {
			byte temp=(byte)(24-(32-prefix));
			subM1=255;
			subM2=p2SM(temp);
			subM3=0;
			subM4=0;
		}else {
			byte temp=prefix;
			subM1=p2SM(temp);
			subM2=0;
			subM3=0;
			subM4=0;
		}	
		subnetMask=subM1+"."+subM2+"."+subM3+"."+subM4;
	}
	static public short p2SM(byte r) {
		switch(r) {
		case 0:
			return 0;
		case 1:
			return 128;
		case 2:
			return 192;
		case 3:
			return 224;
		case 4:
			return 240;
		case 5:
			return 248;
		case 6:
			return 252;
		default:
			return 254;
		}
	}
	//------------------------------------------------------------
	public String NetWorkIP() {
		String str=String.valueOf(byte1&subM1);
		str+="."+String.valueOf(byte2&subM2);
		str+="."+String.valueOf(byte3&subM3);
		str+="."+String.valueOf(byte4&subM4);
		return str;
	}
	public int numberOfHosts() {
		return (int)(Math.pow(2, 32-prefix))-2;
	}
	
	public String BCIP() {
		String str=String.valueOf(byte1|(255-subM1));
		str+="."+String.valueOf(byte2|(255-subM2));
		str+="."+String.valueOf(byte3|(255-subM3));
		str+="."+String.valueOf(byte4|(255-subM4));
		return str;
	}
	public String firstIP() {
		String str=String.valueOf(byte1&subM1);
		str+="."+String.valueOf(byte2&subM2);
		str+="."+String.valueOf(byte3&subM3);
		str+="."+String.valueOf((byte4&subM4)+1);
		return str;
	}
	public String lastIP() {
		String str=String.valueOf(byte1|(255-subM1));
		str+="."+String.valueOf(byte2|(255-subM2));
		str+="."+String.valueOf(byte3|(255-subM3));
		str+="."+String.valueOf((byte4|(255-subM4))-1);
		return str;
	}
	public String getSubnetMask() {
		return subnetMask;
	}
}



