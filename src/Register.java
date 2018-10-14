


public class Register {
	
	String data;
	boolean load=true;
	boolean clear;
	boolean clock;
	public Register() {
		
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		if(load == true){
			this.data = data;
			load = true;}
	}
	
	public void setLoad(boolean load) {
		this.load = load;
	}
	
	public void setClear(boolean clear) {
		if(clear == true)
			data = "";
	}
	public boolean isClock() {
		return clock;
	}
	public void setClock(boolean clock) {
		this.clock = clock;
	}
	
	
	
	
	
	
	
	
	
}
