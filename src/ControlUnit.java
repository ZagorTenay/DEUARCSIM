
public class ControlUnit {

	ProgramCounter PC;
	InstructionMemory IM;
	DataMemory DM;
	StackMemory SM; 
	Register instructionReg, r0, r1, r2, r3, inpR,outR;
	ALU ALU;
	static String Q;
	static String S1,S2;
	static String Destination;
	static String OPCode, address;
	static int D;

	
	
	public ControlUnit(ProgramCounter pc, InstructionMemory IM , Register instructionReg,
			DataMemory DM, StackMemory SM) {
		super();
		PC = pc;
		this.DM = DM;
		this.SM = SM;
		this.IM = IM;
		this.instructionReg = instructionReg;
	}


	public void Fetch()
	{
		instructionReg.setLoad(true);
		
		instructionReg.setData(IM.getInstruction(PC));
	
		
	    PC.incrementPC();
		
	}
	
	
	public void  Decode()
	{
		OPCode=instructionReg.data.substring(1, 5);
        D = Integer.parseInt(OPCode, 2);

        if(D == 10 || D == 11 || D == 12)
            address = instructionReg.data.substring(6);
        else if(D == 13 || D == 15)
            address = instructionReg.data.substring(7);

        Q=instructionReg.data.substring(0, 1);
        Destination=instructionReg.data.substring(5, 7);
        S2=instructionReg.data.substring(7, 9);
        S1=instructionReg.data.substring(9, 11);

	}
	public void Execute()
	{
		if(D==0)
		{
			ALU.Add();
		}
		else if(D==1)
		{
			ALU.Increment();
		}
		else if(D==2)
		{
			ALU.DBL();
		}
		else if(D==3)
		{
			ALU.DBT();
		}
		else if(D==4)
		{
			ALU.Not();
		}
		else if(D==5)
		{
			ALU.And();
		}
		else if(D==6)
		{
			int tmp,a;
			String data;
			
			
			S2 = S2.concat(S1);
			tmp = Integer.parseInt(Destination, 2);
			
			if(Q.equals("0")){
				
				data = DM.getWord(Integer.parseInt(S2,2));
				
			}else{
				data = S2;
			}
			if(data.length()!=1 && data.substring(0,1).equals("0"))
            {
                data=Integer.toString(Integer.parseInt(data,2));
                
            }
			if(tmp==0) r0.setData(data);
			if(tmp==1) r1.setData(data);
			if(tmp==2) r2.setData(data);
			if(tmp==3) outR.setData(data);
		}
		else if(D==7)//ST
		{
			int tmp;
			String data = null;
			
			
			tmp = Integer.parseInt(Destination, 2);
			
			if(tmp==0) data = r0.getData();
			if(tmp==1) data = r1.getData();
			if(tmp==2) data = r2.getData();
			if(tmp==3) data = outR.getData(); 
			
			if(Q.equals("0")){
				S2 = S2.concat(S1);
				DM.writeAdress(Integer.parseInt(S2,2), data);
				
			}else{
				tmp = Integer.parseInt(S2, 2);
				
				if(tmp==0) r0.setData(data);
				if(tmp==1) r1.setData(data);
				if(tmp==2) r2.setData(data);
				if(tmp==3) outR.setData(data); 
				
			}
			
		}
		else if(D==8) //TSF
		{
			int tmp;
			String data = null;
			
			tmp = Integer.parseInt(S2, 2);
			
			if(tmp==0) data = r0.getData();
			if(tmp==1) data = r1.getData();
			if(tmp==2) data = r2.getData();
			if(tmp==3) data = inpR.getData(); 
			
			tmp = Integer.parseInt(Destination, 2);
			
			if(tmp==0) r0.setData(data);
			if(tmp==1) r1.setData(data);
			if(tmp==2) r2.setData(data);
			if(tmp==3) outR.setData(data); 
			
		}
		
		else if(D==10) // CAL
        {
            SM.push(PC.data);
            PC.setLoad(true);
            PC.setData(address);
        }
        else if(D==11) // RET
        {
            PC.setLoad(true);
            PC.setData(SM.pop());
        }
        else if(D==12) // JMP
        {
            
        	if(Q.equals("0")){
        		PC.setLoad(true);
        		PC.setData(address);
        	}
        	else{
        		if(ALU.getOverflow().equals("1")){
        			PC.setLoad(true);
            		PC.setData(address);
        		}		
        	}
        	
        }
        else if(D==13) // JMR
        {
            if(address.substring(0, 1).equals("1")){ // negative
                int relative = Integer.parseInt(address.substring(1),2);
                relative = relative + (-8);
                int tempPc = Integer.parseInt(PC.data,2);
                tempPc = tempPc + relative;
                PC.setLoad(true);
                PC.setData(convertToBin(tempPc, 5));
            }
            else if(address.substring(0, 1).equals("0")){ // positive
                int relative = Integer.parseInt(address, 2);
                int tempPc = Integer.parseInt(PC.data, 2);
                tempPc = tempPc + relative;
                PC.setLoad(true);
                PC.setData(convertToBin(tempPc, 5));
            }

        }
        else if(D==14) // PUSH
        {
            SM.push(DM.readAdress(Integer.parseInt(address, 2)));
        }
        else if(D==15) // POP
        {
        	int tmp;
			String data = null;
			
			S2 = S2.concat(S1);
        	DM.writeAdress(Integer.parseInt(S2), SM.pop());;

        }
        else if(D==16) // HLT
        {
        	
        }
			
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
	

	public void setRegisters(Register r0,Register r1,Register r2,Register inpR, Register outR){
		this.r0 = r0; 
		this.r1 = r1;
		this.r2 = r2;
		this.inpR = inpR;
		this.outR = outR;
	}
	public  String getInstructionRegister() {
		return instructionReg.data;
	}
	public void setALU(ALU arithmeticLogicU)
	{
		ALU=arithmeticLogicU;
	}


	public  void setInstructionRegister(String instructionRegister) {
		instructionReg.data = instructionRegister;
	}


	public  String getQ() {
		return Q;
	}


	public  void setQ(String q) {
		Q = q;
	}


	public  String getS1() {
		return S1;
	}


	public  void setS1(String s1) {
		S1 = s1;
	}


	public  String getS2() {
		return S2;
	}


	public  void setS2(String s2) {
		S2 = s2;
	}


	public  String getDestination() {
		return Destination;
	}


	public  void setDestination(String destination) {
		Destination = destination;
	}


	public  String getOPCode() {
		return OPCode;
	}


	public  void setOPCode(String oPCode) {
		OPCode = oPCode;
	}


	public  int getD() {
		return D;
	}


	public  void setD(int d) {
		D = d;
	}
	
	
}


	




//programcounter.increment();