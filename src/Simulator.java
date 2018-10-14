
public class Simulator {
	DataMemory dataMemory;
	String instructionTable[]=new String[50];
	String memoryContentTable[][]=new String[50][3];
	String labelTable[][]=new String[50][3];
	int cLabelTableRow=0;
	int cDataRow;
	int temp;

	public Simulator(DataMemory dataMemory) {
		super();
		this.dataMemory=dataMemory;
	}
	public void labelTableGenerator(int firstDataRow,String dataInstruction[]){

		if(firstDataRow!=-1){
			cDataRow=firstDataRow;
			temp=firstDataRow;
			dataMemory.setEnable();
			dataMemory.writeAdress(cDataRow,dataInstruction[2]);
		}	
		else
		{

			dataMemory.setEnable();
			dataMemory.writeAdress(-1,dataInstruction[2]);
		}
			
			tableGeneratorSubProcess(cDataRow, dataInstruction);
			
			cDataRow++;
			
		
	}
	public void tableGeneratorSubProcess(int firstDataRow,String dataInstruction[]){
		labelTable[cLabelTableRow][0]=dataInstruction[0];
		labelTable[cLabelTableRow][1]=Integer.toString(firstDataRow);
		labelTable[cLabelTableRow][2]=dataInstruction[2];
		cLabelTableRow++;
	}

	public String[][] getMemoryContentTable() {
		return memoryContentTable;
	}
	public String[][] getLabelTable() {
		return labelTable;
	}
	public void memoryContentTable(){

		int bound= dataMemory.getWordNumber();

		cLabelTableRow=0;
		dataMemory.setAddress(temp);
		for (int i = 0; i < bound; i++) {

			memoryContentTable[cLabelTableRow][0]=dataMemory.getIndex();
			memoryContentTable[cLabelTableRow][1]=dataMemory.readAdress2();
			memoryContentTable[cLabelTableRow][2]=convertToBinary(dataMemory.getIndex());
			cLabelTableRow++;

		}



	}
	public String findLabelValue(String label){
        for (int i = 0; i < labelTable.length; i++) {
            if(labelTable[i][0]!=null && labelTable[i][0].length() == label.length() 
            		+ 1 &&labelTable[i][0].substring(0, label.length()).equals(label))
                return labelTable[i][2];
        }
        return "";
    }
	
	public String findLabelAddress(String label){
        for (int i = 0; i < labelTable.length; i++) {
            if(labelTable[i][0]!=null && labelTable[i][0].length() == label.length() 
            		+ 1 &&labelTable[i][0].substring(0, label.length()).equals(label))
                return labelTable[i][1];
        }
        return "";
    }
	
	public void print(){
		/*String dataInst[]={"A","DEC","3"};
		String dataInst2[]={"K","HEX","5"};
		dataMemory.setEnable();
		//dataMemory.setStartAddress(cDataRow);
		labelTableGenerator(3, dataInst); 
		labelTableGenerator(-1, dataInst);
		 */
		memoryContentTable();
		for (int i = 0; i < labelTable.length; i++) {
			for (int j = 0; j < labelTable[0].length; j++) {
				if(labelTable[i][j]!=null)
					System.out.print(labelTable[i][j]+" ");
			}
		}

		for (int i = 0; i < memoryContentTable.length; i++) {
			for (int j = 0; j < memoryContentTable[0].length; j++) {
				if(memoryContentTable[i][j]!=null)
					System.out.print(memoryContentTable[i][j]+" ");
			}

		}



	}
	public String convertToBinary(String data) {

		String binary = "";
		int value = Integer.parseInt(data);

		for (int j = 0; j < 4; j++) {

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
