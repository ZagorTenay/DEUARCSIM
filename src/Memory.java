
public class Memory {

	String word[];
	int wordNumber=0;
	int memoryIndex=0;
	int indexTemp=0;
	boolean readFlag;
	boolean writeFlag;
	

	public Memory() {
		super();

	}
	
	
	/* 
	 *	Reads data at the index. If read enable is false, doesn't return 
	 * 	empty space.
	 * 
	 * 	memoryIndex = holds number memory slot that will be read.
	 * 
	 * */
	public String readAdress(int address){
		if(readFlag==false)
			System.out.println("read enable is false");
		else{
			
			String temp=getWord(address);
			if(temp!=null){
				
				return temp;
			}
			
		}
		return "";
	}
	
	public String readAdress2(){
		if(readFlag==false)
			System.out.println("read enable is false");
		else{
			
			String temp=getWord(memoryIndex);
			if(temp!=null){
				memoryIndex++;
				return temp;
			}
			
		}
		return "";
	}
	
	
	
	public void setAddress(int address){
		
		//memoryIndex=Integer.parseInt(address)-1;
		memoryIndex=address;
	}
	
	public String getIndex(){
		return Integer.toString(memoryIndex);
	}
	
	
	/*
	 *	Writes data to memory
	 * 
	 *	data = data coming from process
	 *	index = address number
	 * 
	 */
	public void writeAdress(int address, String data){

		if(address!=-1){
			//memoryIndex=binaryToIntConvertor(address);
			memoryIndex=address-1;
		}
		if(writeFlag==false)
			System.out.println("write enable is false");
		else{
			setWord(data);
			wordNumber++;
		}

	}

	
	/* 
	 * 	Converts binary address to decimal 
	 * 	
	 * 	length = length of adress
	 * 	result = decimal result
	 * 	temp = for calculating 2's power.
	 * 
	 */
	public int binaryToIntConvertor(String binary){

		int length=binary.length();
		int result=0;
		int temp=1;
		
		while(length!=0){
			if((binary.substring(length-1)).equals("1")){
				for (int i = 0; i < length-1; i++) {
					temp=temp*2;

				}
				result+=temp;
				temp=1;
			}
			length--;
		}

		return result;

	}

	public String getWord(int addressIndex) {
		return word[addressIndex];
	}
	public void setWord(String data) {
		memoryIndex++;
		word[memoryIndex] = data;
	}
	public int getWordNumber() {
		return wordNumber;
	}
	public void setWordNumber(int wordNumber) {
		this.wordNumber = wordNumber;
	}






}
