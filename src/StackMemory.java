
public class StackMemory extends Memory {

	public StackMemory() {
		super();
		word=new String[16];
		// TODO Auto-generated constructor stub
	}
		
	public void push(String adr){
		
		if(memoryIndex <= 1000){
			word[memoryIndex] = adr;
			memoryIndex++;
		}
		
	}
	
	public String pop(){
		
		if(memoryIndex > 0){
			memoryIndex--;
			String temp = word[memoryIndex];
			word[memoryIndex] = null;
			return temp;
		}
		else
			return null;
		
	}
	
}
