
public class DataMemory extends Memory{

	public DataMemory() {
		super();
		word = new String[16];
	}
	
	

	public void setEnable(){
		
		readFlag=true;
		writeFlag=true;
	}
	
	
	
}
