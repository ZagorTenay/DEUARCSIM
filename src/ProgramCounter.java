
public class ProgramCounter extends Register {
	

	
	public ProgramCounter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void incrementPC(){
		
		int temp;
		temp = Integer.parseInt(data,2) + 1;
		data = convertToBinary(temp);
		
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
	public String convertToBinary(int value) {

		String binary = "";
		

		for (int j = 0; j < 5; j++) {

			if (value == 1)
				binary = "1" + binary;
			else if (value == 0)
				binary = "0" + binary;
			else if (value % 2 == 1)
				binary = "1" + binary;
			else if (value % 2 == 0)
				binary = "0" + binary;

			value = value / 2;
		}

		return binary;

	}

	
}
