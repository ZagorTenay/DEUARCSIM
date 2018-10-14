import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class FileOperations {
	
	public String[] line=new String[1000];
	public String[] splited = new String[100];
	public String[] label=new String[100];
	public String[] hlt=new String[1];
	private String FILENAME;
	
	public int commentcount=0;
	public boolean flag=true;
	public int datanumber=0;
	public int labelcount=0;
	private int counterForLabel = 0;
	public String[] comments=new String[100];
	public String sCurrentLine;
	public String CurrentLine;
	
	
	public String getsCurrentLine() {
			return sCurrentLine;
		}
	public InstructionTable fileRead(Simulator simulation)
	{
		
		InstructionTable instTable=null;
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((CurrentLine = br.readLine()) != null) {
				
				CurrentLine=CurrentLine.replaceAll(",", " ");
				CurrentLine=CurrentLine.replaceAll("END", "END ");
				CurrentLine=CurrentLine.replaceAll("HLT", "HLT ");
				
				line = CurrentLine.split(" ");
				counterForLabel++;
				if(line[0].equalsIgnoreCase("ORG"))
				{
					if(line[1].equalsIgnoreCase("D"))
					{
						datanumber=Integer.parseInt(line[2]);
						
					}
					else if(line[1].equalsIgnoreCase("C"))
					{
						instructionnumber=Integer.parseInt(line[2]);
						counterForLabel = instructionnumber;
					}
					
				}
				if(line[0].endsWith(":"))
				{
					if(!line[1].equals("HEX") && !line[1].equals("DEC") && !line[1].equals("BIN")){
						label = CurrentLine.split(" ");
						counterForLabel--;
						label[2] = " ";
						simulation.tableGeneratorSubProcess(counterForLabel, label);
					}
					
					else if(labelcount==0)
					{
						label=CurrentLine.split(" ");
						//System.out.println(datanumber);
						
						simulation.labelTableGenerator(datanumber, label);
						labelcount++;
					}
					else
					{
						label=CurrentLine.split(" ");
	
						simulation.labelTableGenerator(-1, label);
						labelcount++;
					}
					
					
	
				}
				
	
			} 
			
			
			
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedReader br2 = new BufferedReader(new FileReader(FILENAME))) {
			instTable = new InstructionTable(simulation);
	
			boolean haltFlag = false, subFlag = false;
			
			while ((sCurrentLine = br2.readLine()) != null) {
				
				boolean addNewIns = false;
				sCurrentLine=sCurrentLine.replaceAll(",", " ");
				sCurrentLine=sCurrentLine.replaceAll("END", "END ");
				sCurrentLine=sCurrentLine.replaceAll("HLT", "HLT ");
	
				splited = sCurrentLine.split(" ");
				System.out.println("splited : " +splited[0]);
				
				if(splited[0].equalsIgnoreCase("SUB:"))
				{
					System.out.println("ssadd");
					String[] temparr = new String[splited.length - 1];
					
					for (int i = 0; i < splited.length - 1; i++) {
						temparr[i] = splited[i + 1];
					}
					instTable.addNewInstruction(temparr);
					addNewIns = true;
					subFlag = true;
				}
				
				if(subFlag == false && haltFlag == true)
					break;
				else if(splited[0].equalsIgnoreCase("RET")){
					instTable.addNewInstruction(splited);
					break;
				}
	
				if(addNewIns == false)
					instTable.addNewInstruction(splited);
				
				if(splited[0].equalsIgnoreCase("HLT"))
				{
					haltFlag = true;
				}
				
	
				
				
	
				/*for(int i=0; i<line.length; i++)
				{
					if(line[i].startsWith("%"))
					{
						comments[commentcount]=line[i];	
						commentcount++;
						}
	
				} */
	
				
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return instTable;
	
	}
	public void setFilename(String FILENAME) {
		this.FILENAME = FILENAME;
	}
	public String[] getLine() {
		return line;
	}
	public void setLine(String[] line) {
		this.line = line;
	}
	static int instructionnumber=0;
	public static int getInstructionnumber() {
		return instructionnumber;
	}
	public static void setInstructionnumber(int instructionnumber) {
		FileOperations.instructionnumber = instructionnumber;
	}


	
}
