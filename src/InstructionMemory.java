
public class InstructionMemory extends Memory{
	private String[] instructions;

	
	public InstructionMemory() {
		
		instructions = new String[32];
	}
	


	public void setInstructions(String[] instructions, int startAdress){
		
		memoryIndex = startAdress-1;

		
		for (int i = 0; i < instructions.length; i++) {
			
			if(instructions[i]==null) break;
			this.instructions[memoryIndex + i] = instructions[i];
		}
		
		
	}
	
	public String getInstruction(ProgramCounter pc){
		
		int a = Integer.parseInt(pc.data,2);
		
		System.out.println(" program counter : " + a);
		return instructions[a];//?
	}
	public String[][] getInsructionArray(){
		String[][] temp = new String[instructions.length][2];
		
		for (int i = 0; i < instructions.length; i++) {
			temp[i][0] = Integer.toString(i);
			temp[i][1] = instructions[i];
		}
		return temp;
	}

	
}
