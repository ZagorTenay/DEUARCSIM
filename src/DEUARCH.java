import java.awt.EventQueue;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DEUARCH {
	private JFrame frame;
	private JTextField register0;
	private JTextField register1;
	private JTextField register2;
	private JTextField textField_4;
	private JTextField InputRegister;
	private JTextField outputReg;
	private JTextField ProgramCounter;
	private JTextField textField_7;
	private JTextField overFlow;
	private JTextField InstructionRegister;
	private JTextField textField_11;
	private JTable labeltable;
	private JTable insttable;
	private JTable instmemory;
	private JTable datamemory;
	private JTable microOpTable;
	private String Path;
	private Register INPR = new Register();
    private Register OUTR = new Register();
	
	
	private FileOperations file = new FileOperations();
	private InstructionMemory IM=new InstructionMemory();
	private DataMemory DM = new DataMemory();
	private StackMemory SM = new StackMemory();
	private Simulator simulation = new Simulator(DM);
	
	private InstructionTable instTable = new InstructionTable();
	private ProgramCounter pc = new ProgramCounter();
	
	private Register instructionReg = new Register();
	private ControlUnit controlUnit = new ControlUnit(pc,IM,instructionReg,DM,SM);
	private Register r0 = new Register() , r1 = new Register(), r2 = new Register(), inpR = new Register()
					 ,outR = new Register();
	private ALU arithmeticLogicU = new ALU(controlUnit, r0, r1, r2);
	
	private JTextField T0;
	private JTextField T1;
	private JTextField T2;
	private JTextField T4;
	private JTextField T5;
	private int count=0;
	private JTextField FileRead;

	


	public String getPath() {
		return Path;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DEUARCH window = new DEUARCH();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DEUARCH() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		
		JButton btnNewButton_1 = new JButton("RUN");
		btnNewButton_1.setBounds(435, 15, 117, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton_1);
		
			
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.setBounds(564, 15, 117, 29);
		frame.getContentPane().add(btnExit);
		
		JLabel lblRegister = new JLabel("Register 0");
		lblRegister.setBounds(270, 91, 74, 16);
		frame.getContentPane().add(lblRegister);
		
		register0 = new JTextField();
		register0.setBounds(241, 106, 130, 26);
		frame.getContentPane().add(register0);
		register0.setColumns(10);
		
		JLabel lblRegister_1 = new JLabel("Register 1");
		lblRegister_1.setBounds(270, 130, 101, 16);
		frame.getContentPane().add(lblRegister_1);
		
		register1 = new JTextField();
		register1.setBounds(241, 152, 130, 26);
		frame.getContentPane().add(register1);
		register1.setColumns(10);
		
		JLabel lblRegister_2 = new JLabel("Register 2");
		lblRegister_2.setBounds(270, 179, 74, 16);
		frame.getContentPane().add(lblRegister_2);
		
		register2 = new JTextField();
		register2.setBounds(241, 201, 130, 26);
		frame.getContentPane().add(register2);
		register2.setColumns(10);
		
		JLabel lblAddressRegister = new JLabel("Address Register");
		lblAddressRegister.setBounds(251, 228, 117, 16);
		frame.getContentPane().add(lblAddressRegister);
		
		textField_4 = new JTextField();
		textField_4.setBounds(241, 249, 130, 26);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Input Register");
		lblNewLabel.setBounds(251, 276, 120, 16);
		frame.getContentPane().add(lblNewLabel);
		
		InputRegister = new JTextField();
		InputRegister.setBounds(241, 294, 130, 26);
		frame.getContentPane().add(InputRegister);
		InputRegister.setColumns(10);
		
		JLabel lblOutputRegister = new JLabel("Output Register");
		lblOutputRegister.setBounds(251, 323, 117, 16);
		frame.getContentPane().add(lblOutputRegister);
		
		outputReg = new JTextField();
		outputReg.setBounds(241, 340, 130, 26);
		frame.getContentPane().add(outputReg);
		outputReg.setColumns(10);
		
		JLabel lblRegisters = new JLabel("REGISTERS");
		lblRegisters.setBounds(471, 67, 103, 16);
		frame.getContentPane().add(lblRegisters);
		
		ProgramCounter = new JTextField();
		ProgramCounter.setBounds(444, 106, 130, 26);
		frame.getContentPane().add(ProgramCounter);
		ProgramCounter.setColumns(10);
		
		JLabel lblProgramCounter = new JLabel("Program Counter");
		lblProgramCounter.setBounds(444, 92, 130, 16);
		frame.getContentPane().add(lblProgramCounter);
		
		JLabel lblStackPointer = new JLabel("Stack Pointer");
		lblStackPointer.setBounds(454, 130, 98, 16);
		frame.getContentPane().add(lblStackPointer);
		
		textField_7 = new JTextField();
		textField_7.setBounds(444, 152, 130, 26);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblLabelTable = new JLabel("LABEL TABLE");
		lblLabelTable.setBounds(77, 64, 87, 16);
		frame.getContentPane().add(lblLabelTable);
		
		JLabel lblInstructionTable = new JLabel("INSTRUCTION TABLE");
		lblInstructionTable.setBounds(57, 323, 130, 16);
		frame.getContentPane().add(lblInstructionTable);
		
		JLabel lblOverflow = new JLabel("OverFlow");
		lblOverflow.setBounds(454, 179, 61, 16);
		frame.getContentPane().add(lblOverflow);
		
		overFlow = new JTextField();
		overFlow.setBounds(444, 201, 130, 26);
		frame.getContentPane().add(overFlow);
		overFlow.setColumns(10);
		
		JLabel lblInstructionRegister = new JLabel("Instruction Register");
		lblInstructionRegister.setBounds(444, 228, 130, 16);
		frame.getContentPane().add(lblInstructionRegister);
		
		InstructionRegister = new JTextField();
		InstructionRegister.setBounds(444, 249, 130, 26);
		frame.getContentPane().add(InstructionRegister);
		InstructionRegister.setColumns(10);
			
		JLabel lblMemory = new JLabel("MEMORY");
		lblMemory.setBounds(743, 67, 61, 16);
		frame.getContentPane().add(lblMemory);
		
		JLabel lblInstructionMemory = new JLabel("Instruction Memory");
		lblInstructionMemory.setBounds(707, 92, 130, 16);
		frame.getContentPane().add(lblInstructionMemory);
		
		JLabel lblDataMemory = new JLabel("Data Memory");
		lblDataMemory.setBounds(720, 345, 117, 16);
		frame.getContentPane().add(lblDataMemory);
		
		JLabel lblStackMemory = new JLabel("Stack Memory");
		lblStackMemory.setBounds(717, 504, 120, 16);
		frame.getContentPane().add(lblStackMemory);
		
		textField_11 = new JTextField();
		textField_11.setBounds(659, 535, 178, 137);
		frame.getContentPane().add(textField_11);
		textField_11.setColumns(10);
		
		JButton btnNewButton = new JButton("READ");
		btnNewButton.setBounds(241, 19, 103, 20);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblFile = new JLabel("FILE");
		lblFile.setBounds(904, 67, 61, 16);
		frame.getContentPane().add(lblFile);
		
		JPanel panel = new JPanel();
		panel.setBounds(42, 106, 164, 178);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 164, 198);
		panel.add(scrollPane);
		
		
		//labeltable
		
		
		
		//
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(47, 365, 159, 151);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 6, 184, 176);
		panel_1.add(scrollPane_1);
		
		

		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(648, 112, 186, 199);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 204, 198);
		panel_2.add(scrollPane_2);
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(648, 365, 189, 134);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(6, 6, 177, 122);
		panel_3.add(scrollPane_3);
		
		
		
		JButton btnOpen = new JButton("OPEN");
		btnOpen.setBounds(687, 15, 117, 29);
		frame.getContentPane().add(btnOpen);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(425, 356, 211, 316);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		T0 = new JTextField();
		T0.setBounds(0, 6, 205, 49);
		panel_4.add(T0);
		T0.setColumns(10);
		
		T1 = new JTextField();
		T1.setBounds(0, 57, 205, 61);
		panel_4.add(T1);
		T1.setColumns(10);
		
		T2 = new JTextField();
		T2.setBounds(1, 118, 204, 61);
		panel_4.add(T2);
		T2.setColumns(10);
		
		T4 = new JTextField();
		T4.setBounds(0, 180, 205, 61);
		panel_4.add(T4);
		T4.setColumns(10);
		
		T5 = new JTextField();
		T5.setBounds(0, 239, 205, 71);
		panel_4.add(T5);
		T5.setColumns(10);
		
		JLabel lblMicroOperations = new JLabel("Micro Operations");
		lblMicroOperations.setBounds(457, 340, 117, 16);
		frame.getContentPane().add(lblMicroOperations);
		
		JButton btnDebug = new JButton("DEBUG");
		btnDebug.setBounds(415, 310, 117, 29);
		frame.getContentPane().add(btnDebug);
		
		JButton btnRun = new JButton("RUN");
		btnRun.setBounds(528, 310, 117, 29);
		frame.getContentPane().add(btnRun);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(864, 113, 130, 199);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		FileRead = new JTextField();
		FileRead.setBounds(0, 0, 130, 193);
		panel_5.add(FileRead);
		FileRead.setColumns(10);

		JButton btnExportMif = new JButton("EXPORT MIF");
		btnExportMif.setBounds(848, 15, 117, 29);
		frame.getContentPane().add(btnExportMif);
		
		
		
btnNewButton.addActionListener(new ActionListener() { //read
	public void actionPerformed(ActionEvent e) {
		
		instTable=file.fileRead(simulation);
		controlUnit.setRegisters(r0, r1, r2, inpR, outR);
		String[] columnNamesLabelTable = { "", "Address", " Content" };
		String[] columnNamesInstructionTable = { "Instruction" };
		String[] columnNamesInstructionMemory = { "32x11","Binary" };
		String[] columnNamesDataMemory = { "16x4","Dec","Binary" };
		String[] columnNamesMicroOperations={"Time"};
		

		String instTab[] = instTable.getTable();
		String labelTab[][] = simulation.getLabelTable();
		String memoryContent[][]=simulation.getMemoryContentTable();
		
		String instTempArray[][]=new String[100][100];
		
	
		for (int j = 0; j < instTab.length; j++) {
			
			instTempArray[j][0]=instTab[j];
			//System.out.println("asda "+instTempArray[j][0]);
		
		}
		//
		
		pc.setLoad(true);
		pc.setData(Integer.toBinaryString(file.getInstructionnumber()));

		IM.setInstructions(instTab, file.getInstructionnumber());
		
		/* Run Operation */
		controlUnit.setALU(arithmeticLogicU);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int PC=Integer.parseInt(pc.data,2);
			
				
				while(IM.getInstruction(pc)!=null)
				{
					
					
					
					pc.incrementPC();
					count++;
				}

				pc.setData(Integer.toBinaryString(file.getInstructionnumber()));

				int a=0;
				
				while(count!=a)
				{
					
					
					controlUnit.Fetch();
					controlUnit.Decode();
					controlUnit.Execute();
					
					a++;
					
				}
				int pcUpdate=Integer.parseInt(pc.getData(),2)-1;

				T0.setText("TO : IR[ "+pcUpdate+"]");
					
				T1.setText("T1 : PC  "+pcUpdate);
				ProgramCounter.setText(pc.data);

				T2.setText("T2 : D0..D15 " +"D: "+controlUnit.getD() +" "+"Q"+" "
						+controlUnit.getQ()+" "+"S2"+controlUnit.getS2()+ " "+" "+"S1"+controlUnit.getS1()+" "+"Destination "+controlUnit.getDestination());               
			InstructionRegister.setText(IM.getInstruction(pc));
			
			
				if(r0.getData()!=null)
				{
					register0.setText(r0.getData());
				}
				if(r1.getData()!=null)
				{
				register1.setText(r1.getData());

				}
				if(r2.getData()!=null)
				{
				register2.setText(r2.getData());

				}
				if(outR.getData()!=null)
				{
					outputReg.setText(outR.getData());
				}
				overFlow.setText(ALU.Overflow);
					
				
				
					
						
					
				}
				
				
				

				
				
			
		});
		
		
		InputRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valueOfInputReg=InputRegister.getText();
				System.out.println("TEST ++++ " + valueOfInputReg);
				inpR.setLoad(true);
				inpR.setData(valueOfInputReg);
			}
		});
		
		
		btnExportMif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
				//exporting mif.
		String FILENAME = "/Users/berkay/Desktop/miffile.mif";

				
				try (Writer writer = new BufferedWriter(new OutputStreamWriter(
			              new FileOutputStream(FILENAME), "utf-8"))) {
					
					 writer.write("DEPTH = 32;");
						writer.append("\n");

					 writer.write("WIDTH = 11;");
						writer.append("\n");

					 writer.write("ADDRESS_RADIX = HEX;");
						writer.append("\n");

					 writer.write("DATA_RADIX = BIN;");
						writer.append("\n");

					 writer.write("CONTENT;");
						writer.append("\n");

					 writer.write("BEGIN;");
						writer.append("\n");

						int count=0;

						
					while(IM.getInstruction(pc)!=null)
					{
						
						writer.write("0"+count+" : "+IM.getInstruction(pc));
						writer.append("\n");
						
						pc.incrementPC();
						count++;
					}
			
			        writer.close();

					
					
					 
					
					 
					
					 
					
					
					
					
			  
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				

					

					System.out.println("Done");

				
				
			}
		});
		
		
		btnDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int PC=Integer.parseInt(pc.data,2);
			

				
				if(count==0)
				{
					controlUnit.Fetch();
					T0.setText("TO : IR[ "+PC+"]");
					
				}
				
				if(count==1)
				{
					T1.setText("T1 : PC  "+PC);
					ProgramCounter.setText(pc.data);
					

					
				}
				

				if(count==2)
				{
					controlUnit.Decode();
					T2.setText("T2 : D0..D15 " +"D: "+controlUnit.getD() +" "+"Q"+" "
								+controlUnit.getQ()+" "+"S2"+controlUnit.getS2()+ " "+" "+"S1"+controlUnit.getS1()+" "+"Destination "+controlUnit.getDestination());               
					InstructionRegister.setText(IM.getInstruction(pc));
					controlUnit.Execute();
					
					if(r0.getData()!=null)
					{
						register0.setText(r0.getData());
					}
					if(r1.getData()!=null)
					{
					register1.setText(r1.getData());

					}
					if(r2.getData()!=null)
					{
					register2.setText(r2.getData());

					}
					if(outR.getData()!=null)
					{
						outputReg.setText(outR.getData());
					}
					overFlow.setText(ALU.Overflow);
					
				}

				
				
				if(count==3 || count >3)
				{
					T0.setText("");
					T1.setText("");
					T2.setText("");
					if(r0.data!=null)
					register0.setText(r0.data);
					if(r1.data!=null)
						register1.setText(r0.data);
					if(r2.data!=null)
						register2.setText(r0.data);
					
					count=0;
				}
				else
				{
					count++;
				}
				
				
			}
		});
		
		
		
		
		//
		simulation.print();

		Object[][] dataLabelTable = labelTab;
		Object[][] dataInstructionTable = instTempArray;
		Object[][] dataMemory=memoryContent;
		Object[][] dataInstMemory= { {1,null}, {2,null}, {3,null}, {4,null}, {5,null}, {6,null},{7,null}, {8,null}, {9,null},{10,null}, {11,null}, {12,null},{13,null}, {14,null}, {15,null},
				{16,null}, {17,null}, {18,null},{19,null}, {20,null}, {21,null},{22,null}, {23,null}, {24,null},{25,null}, {26,null}, {27,null},
				{28,null}, {29,null}, {30,null},{31,null}, {32,null} };
		Object[][] dataMicroOp={ {1,null},{2,null},};
		Object[][] instMemoryData = IM.getInsructionArray();
		
		
		labeltable=new JTable(dataLabelTable,columnNamesLabelTable);
		labeltable.setModel(new DefaultTableModel(dataLabelTable,columnNamesLabelTable));
		scrollPane.setViewportView(labeltable);
		
		insttable = new JTable(dataInstructionTable,columnNamesInstructionTable);
		insttable.setModel(new DefaultTableModel(dataInstructionTable,columnNamesInstructionTable));
		
		scrollPane_1.setViewportView(insttable);
		
		
		instmemory = new JTable(instMemoryData,columnNamesInstructionTable);
		instmemory.setModel(new DefaultTableModel(instMemoryData,columnNamesInstructionMemory));
		
		scrollPane_2.setViewportView(instmemory);
		
		datamemory = new JTable(dataMemory,columnNamesDataMemory);
		datamemory.setModel(new DefaultTableModel(dataMemory,columnNamesDataMemory));
		scrollPane_3.setViewportView(datamemory);

		
		//label
		
	}
});
btnOpen.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		

		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Custom button");

		int returnValue = jfc.showDialog(null, "A button!");
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			System.out.println(jfc.getSelectedFile().getPath());
		}
		Path=jfc.getSelectedFile().getPath();
		file.setFilename(Path);
		
		

		//label
		
	}
});
//btnOpen

		
	}
}
