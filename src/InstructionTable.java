
public class InstructionTable {
	private String[] table;
	private Simulator simulation;
	
	private final String[] opcodes = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
			"1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
	private static int line; // hangi satýrdan devam edeceðini tutar
	
	public InstructionTable(Simulator simulation){
		this.simulation=simulation;
		line = 0;
		table = new String[32];
	}
	public InstructionTable(){

	}
	
	public void addNewInstruction(String[] instruction){
		
		String binaryInstruction = "";
		boolean IFlag = false;
		int instructionGroupNum = 0; // 1 = Aritmetic and Logic Op., 2 = Data Transfer, 3 = Program Control
		
		if(instruction[0].equalsIgnoreCase("ADD") || instruction[0].equalsIgnoreCase("AND")){
					
			instructionGroupNum = 1;
			binaryInstruction += "0";
			
			if(instruction[0].equalsIgnoreCase("ADD"))
				binaryInstruction += opcodes[0];
			else if(instruction[0].equalsIgnoreCase("AND"))
				binaryInstruction += opcodes[5];
			
			if(convertR(instruction[1], instructionGroupNum) != null){				
				binaryInstruction += convertR(instruction[1], instructionGroupNum);				
			}
			
			if(convertR(instruction[2], instructionGroupNum) != null){				
				binaryInstruction += convertR(instruction[2], instructionGroupNum);				
			}
			
			if(convertR(instruction[3], instructionGroupNum) != null){				
				binaryInstruction += convertR(instruction[3], instructionGroupNum);					
			}		
			
		}
		else if(instruction[0].equalsIgnoreCase("INC") 
				|| instruction[0].equalsIgnoreCase("DBL")
				|| instruction[0].equalsIgnoreCase("DBT")
				|| instruction[0].equalsIgnoreCase("NOT")){	
			
			instructionGroupNum = 1;
			
			binaryInstruction += "0";
			
			if(instruction[0].equalsIgnoreCase("INC"))
				binaryInstruction += opcodes[1];
			else if(instruction[0].equalsIgnoreCase("DBL"))
				binaryInstruction += opcodes[2];
			else if(instruction[0].equalsIgnoreCase("DBT"))
				binaryInstruction += opcodes[3];
			else if(instruction[0].equalsIgnoreCase("NOT"))
				binaryInstruction += opcodes[4];
			
			if(convertR(instruction[1], instructionGroupNum) != null){				
					binaryInstruction += convertR(instruction[1], instructionGroupNum);				
			}
			
			if(convertR(instruction[1], instructionGroupNum) != null){				
				binaryInstruction += convertR(instruction[1], instructionGroupNum);				
			}	
			
			binaryInstruction += "00";
			
		}
		else if(instruction[0].equalsIgnoreCase("LD")
				|| instruction[0].equalsIgnoreCase("ST")
				|| instruction[0].equalsIgnoreCase("TSF")){
			
			instructionGroupNum = 2;
			
			if(instruction[0].equalsIgnoreCase("LD"))
				binaryInstruction += opcodes[6];
			else if(instruction[0].equalsIgnoreCase("ST"))
				binaryInstruction += opcodes[7];
			else if(instruction[0].equalsIgnoreCase("TSF"))
				binaryInstruction += opcodes[8];
			
			
			if(convertR(instruction[1], instructionGroupNum) != null){				
					binaryInstruction += convertR(instruction[1], instructionGroupNum);				
			}
			
			if(convertR(instruction[2], instructionGroupNum) != null){				
				binaryInstruction += convertR(instruction[2], instructionGroupNum);
				
				if((instruction[2].substring(0, 1).equalsIgnoreCase("#")))
					IFlag = true;				
			}			
			
			if(IFlag == false)
				binaryInstruction = "0" + binaryInstruction;
			else
				binaryInstruction = "1" + binaryInstruction;
			
		}
		else if(instruction[0].equalsIgnoreCase("CAL")){
			
			instructionGroupNum = 3;
			binaryInstruction += "0";

			binaryInstruction += opcodes[10];
				
			binaryInstruction += "0";
			if(isNumeric(instruction[1]))
				binaryInstruction += convertToBin(Integer.parseInt(instruction[1]), 5);	
			else
				binaryInstruction += convertToBin(Integer.parseInt(simulation.findLabelAddress(instruction[1])), 5);
			
		}
		else if(instruction[0].equalsIgnoreCase("RET")){
			instructionGroupNum = 3;
			binaryInstruction += "0";
			binaryInstruction += opcodes[11];
			binaryInstruction += "000000";
		}
		else if(instruction[0].equalsIgnoreCase("JMP")){
			
			boolean qflag = false;
			
			binaryInstruction += opcodes[12];
			binaryInstruction += "0";
			binaryInstruction += convertToBin(Integer.parseInt(instruction[1]), 5);
			
			if(instruction.length > 2)
				if(instruction[2].equalsIgnoreCase("V"))
					qflag = true;
			
			if(qflag == true)
				binaryInstruction = "1" + binaryInstruction;
			else
				binaryInstruction = "0" + binaryInstruction;
			
		}
		else if(instruction[0].equalsIgnoreCase("JMR")){
			binaryInstruction += "0";
			binaryInstruction += opcodes[13];
			binaryInstruction += "00";
			
			if(instruction[1].substring(0, 1).equals("+")){
				binaryInstruction += convertToBin(Integer.parseInt(instruction[1].substring(1)), 4);
			}
			else if(instruction[1].substring(0, 1).equals("-")){
				binaryInstruction += "1";
				int kalan = 8 - Integer.parseInt(instruction[1].substring(1));
				binaryInstruction += convertToBin(kalan, 3);
			}
			
		}
		else if(instruction[0].equalsIgnoreCase("PSH")){
			binaryInstruction = "0111000";
			binaryInstruction += convertToBin(Integer.parseInt(instruction[1]), 4);
		}
		else if(instruction[0].equalsIgnoreCase("POP")){
			binaryInstruction = "0111100";
			binaryInstruction += convertToBin(Integer.parseInt(instruction[1]), 4);
		}
		else if(instruction[0].equalsIgnoreCase("HLT")){
			binaryInstruction = "01000000000";
		}
		
		table[line] = binaryInstruction;		
		line++;
	}
	
	public String convertR(String R, int groupNum){
		
		if(R.substring(0, 1).equalsIgnoreCase("R")){
			return convertToBin(Integer.parseInt(R.substring(1, 2)), 2);
		}
		else if(R.substring(0, 1).equalsIgnoreCase("#") && groupNum == 2){
			System.out.println(" test : "+R.substring(1,2));
			return convertToBin(Integer.parseInt(R.substring(1, 2)), 4);
		}
		else if(R.substring(0, 1).equalsIgnoreCase("@") && groupNum == 2){
			String addres = simulation.findLabelAddress(R.substring(1));
			return convertToBin(Integer.parseInt(addres), 4);
		}
		else if(R.equalsIgnoreCase("INPR")){
			return "1100";
		}
		else if(R.equalsIgnoreCase("OUTR")){
			return "11";
		}
		else
			return null;
	}
	
	public String convertToBin(int number, int numOfBit) {

		String binary = "";
		int value = number;

		for (int j = 0; j < numOfBit; j++) {

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

	public boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	
	public String[] getTable() {
		return table;
	}	
	
	public void prt(){
		System.out.println(table[line - 1]);
	}

}
