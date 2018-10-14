
public class ALU {

	private ControlUnit CU;
	private Register r0, r1, r2;
	static int DestinationReg;
	static String Overflow;
	static String tempDestination;
	static int sum;
	static String S1;
	static String S2;
	static String contentOfLabelTable[][];
	private Simulator simulation;

	public ALU(ControlUnit CU, Register r0, Register r1, Register r2){

			super();
			this.CU = CU;
			this.r0 = r0;
			this.r1 = r1;
			this.r2 = r2;
	
		
			//String[][] contentOfLabelTable = simulation.getLabelTable();
			
	}
	
	
	public void Add()
	{
		tempDestination=CU.getDestination();

		
		/*add operation
		 * 
		 * 
		 */
		DestinationReg=Integer.parseInt(tempDestination, 2); //binary to decimal
		int a,b;
		
		if(DestinationReg==0) //r0 a yazýlacaksa
		{
			S1=r1.data; //content of r1
			S2=r2.data; //content of r2
			if(S1.length()!=1 && S1.substring(0,1).equals("0"))
			{
				a=Integer.parseInt(S1,2);
				b=Integer.parseInt(S2,2);
				sum = a+b;
			}
			else
			{
				sum=Integer.parseInt(S1)+Integer.parseInt(S2);
				
			}
		
		}
		else if(DestinationReg==1) //r1 a yazýlacaksa
		{
			S1=r0.data; //content of r1
			S2=r2.data; //content of r2
			if(S1.length()!=1 && S1.substring(0,1).equals("0"))
			{
				a=Integer.parseInt(S1,2);
				b=Integer.parseInt(S2,2);
				sum = a+b;
			}
			else
			{
				sum=Integer.parseInt(S1)+Integer.parseInt(S2);
				
			}
		
		}
		else if(DestinationReg==2) //r2 a yazýlacaksa
		{
			S1=r0.getData(); //content of r1
			S2=r1.getData(); //content of r2
			if(S1.length()!=1 && S1.substring(0,1).equals("0"))
			{
				a=Integer.parseInt(S1,2);
				b=Integer.parseInt(S2,2);
				sum = a+b;
			}
			else
			{
				sum=Integer.parseInt(S1)+Integer.parseInt(S2);
				
			}
		
			
		}
		
		
		String data = null;
		
		if(sum > 15 )
		{
			Overflow = "1";
			sum = sum % 15;
		}
		else
		{
			Overflow="0";
		}
		if(DestinationReg==0)
		{
			data=Integer.toString(sum);
			r0.setData(data);
		}
		else if(DestinationReg==1)
		{
			data=Integer.toString(sum);
			r1.setData(data);
		}
		else if(DestinationReg==2)
		{
			data=Integer.toString(sum);
			r2.setData(data);
		}
		
		
	}
	public void Increment()
	{
		tempDestination=CU.getDestination();

		//increment operation
		String whichReg=CU.getS1();
		int a=Integer.parseInt(whichReg,2); //binaryi int e çevirdik, 0 ise r0 1 ise r1 2 ise r2 artacak.
		if(a==0) // r0
		{
			S1=r0.data;
			
		}
		if(a==1) // r0
		{
			S1=r1.data;
			
		}
		if(a==2) // r0
		{
			S1=r2.data;
			
		}
		int b=Integer.parseInt(S1);
		b+=1;
		
		S1=Integer.toString(b); 
		
		DestinationReg=Integer.parseInt(tempDestination, 2); //binary to decimal
		
		//DestinationReg 0 ise R0=S1,
		//1 ise R1=S1
		//2 ise , R2=S1
		if(DestinationReg==0)
		{
		
			r0.setData(S1);
		}
		else if(DestinationReg==1)
		{
			r1.setData(S1);
		}
		else if(DestinationReg==2)
		{
			r2.setData(S2);
		}
		if(S1.length()==5 && S1.substring(0, 1)=="1")
		{
			Overflow="1";
		}
		else
		{
			Overflow="0";
		}
	}

	public void DBL()
	{
		tempDestination=CU.getDestination();

		//double content of S1 and Store at destination reg (DBL)
		String whichReg=CU.getS1();
		int a=Integer.parseInt(whichReg,2); //binaryi int e çevirdik, 0 ise r0 1 ise r1 2 ise r2 artacak.
		if(a==0) // r0
		{
			S1=r0.data;
			
		}
		if(a==1) // r0
		{
			S1=r1.data;
			
		}
		if(a==2) // r0
		{
			S1=r2.data;
			
		}
		S1+=S1; //((double content))
		
		int b=Integer.parseInt(S1);
		b+=b;
		S1=Integer.toString(b);
		
		DestinationReg=Integer.parseInt(tempDestination, 2);
		
		/*
		 * DestinationReg 0 ise R0=S1, 
		 * DestinationReg 1 ise R1=S1,
		 * DestinationReg 2 ise R2=S1,  
		 * 
		 */
		if(DestinationReg==0)
		{
			
			r0.setData(S1);
		}
		else if(DestinationReg==1)
		{
			
			r1.setData(S1);
		}
		else if(DestinationReg==2)
		{
			r2.setData(S1);
		}
		if(S1.length()==5 && S1.substring(0, 1)=="1")
		{
			Overflow="1";
		}
		else
		{
			Overflow="0";
		}

	}
	public void DBT()
	{
		tempDestination=CU.getDestination();

		/*Divide content of S1 by 2 and store result into D (DBT)
		 * 
		 */
		String whichReg=CU.getS1();
		int a=Integer.parseInt(whichReg,2); //binaryi int e çevirdik, 0 ise r0 1 ise r1 2 ise r2 artacak.
		
		if(a==0) // r0
		{
			S1=r0.data;
			
		}
		if(a==1) // r0
		{
			S1=r1.data;
			
		}
		if(a==2) // r0
		{
			S1=r2.data;
			
		}
		int b=Integer.parseInt(S1);
		b=b/2;
		
		S1=Integer.toString(b);
		
		DestinationReg=Integer.parseInt(tempDestination, 2);
		/*
		 * DestinationReg 0 ise R0=S1, 
		 * DestinationReg 1 ise R1=S1,
		 * DestinationReg 2 ise R2=S1,  
		 * 
		 */
		
		if(DestinationReg==0)
		{
			r0.setData(S1);
		}
		else if(DestinationReg==1)
		{
			r1.setData(S1);
		}
		else if(DestinationReg==2)
		{
			r2.setData(S1);
		}
		if(S1.length()==5 && S1.substring(0, 1)=="1")
		{
			Overflow="1";
		}
		else
		{
			Overflow="0";
		}
	}
	public void Not()
	{
		//Complement S1 content and load the result into D
		/*
		 * not operation
		 */
		tempDestination=CU.getDestination();

		String whichReg=CU.getS1();
		int a=Integer.parseInt(whichReg,2); 
		
		if(a==0) // r0
		{
			S1=r0.data;
			
		}
		if(a==1) // r0
		{
			S1=r1.data;
			
		}
		if(a==2) // r0
		{
			S1=r2.data;
			
		}
		
		String binaryS1;
		binaryS1=S1;
			if(binaryS1.length()==3 || binaryS1.length()==2 || binaryS1.length()==1 )
			{
				//baþýna 0 ekle
				binaryS1=String.format("%0"+ (4 - binaryS1.length() )+"d%s",0 ,binaryS1);	
			}
			
			
		//ones complement al binaryS1'in
			String temp="";
			if(binaryS1.substring(0, 1)=="0")
			{
				temp+="1";
			}
			else if(binaryS1.substring(0, 1)=="1")
			{
				temp+="0";
			}
			if(binaryS1.substring(1, 2)=="0")
			{
				System.out.println("binary s1 " +binaryS1);
				temp+="1";

			}
			else if(binaryS1.substring(1, 2)=="1")
			{
				temp+="0";

			}
			if(binaryS1.substring(2, 3)=="0")
			{
				temp+="1";

			}
			else if(binaryS1.substring(2, 3)=="1")
			{
				temp+="0";

			}
			if(binaryS1.substring(3, 4)=="0")
			{
				temp+="1";

			}
			else if(binaryS1.substring(3, 4)=="1")
			{
				temp+="0";

			}
			binaryS1=temp;

			DestinationReg=Integer.parseInt(tempDestination, 2);
			/*
			 * DestinationReg 0 ise R0=S1, 
			 * DestinationReg 1 ise R1=S1,
			 * DestinationReg 2 ise R2=S1,  
			 * 
			 */
			if(DestinationReg==0)
			{
				r0.setData(binaryS1);
			}
			else if(DestinationReg==1)
			{
				
				r1.setData(binaryS1);
			}
			else if(DestinationReg==2)
			{
				r2.setData(binaryS1);
			}
			
	}
	
	public void And()

	{
		tempDestination=CU.getDestination();

		//101 011 her bir biti tek tek andle.
		
		String whichReg=CU.getS1();
		int a=Integer.parseInt(whichReg,2); 
		
		if(a==0) // r0
		{
			S1=r0.data;
			
		}
		else if(a==1) // r0
		{
			S1=r1.data;
			
		}
		else if(a==2) // r0
		{
			S1=r2.data;
			
		}
		
		String whichReg2=CU.getS2();
		a=Integer.parseInt(whichReg2,2); 
		if(a==0) // r0
		{
			S2=r0.data;
			
		}
		else if(a==1) // r0
		{
			S2=r1.data;
			
		}
		else if(a==2) // r0
		{
			S2=r2.data;
			
		}
		
		String binaryS1;
		binaryS1=S1;
			if(binaryS1.length()==3 || binaryS1.length()==2)
			{
				//baþýna 0 ekle
				binaryS1=String.format("%0"+ (4 - binaryS1.length() )+"d%s",0 ,binaryS1);	
			}
			String binaryS2;
			binaryS2=S2;
				if(binaryS2.length()==3 || binaryS2.length()==2)
				{
					//baþýna 0 ekle
					binaryS2=String.format("%0"+ (4 - binaryS2.length() )+"d%s",0 ,binaryS2);	
				}
				
		String resultData="";
		
		if(binaryS1.substring(0,1) == binaryS2.substring(0, 1))
		{
			resultData+="1";
		}
		else 
		{
			resultData+="0";
		}
		if(binaryS2.substring(1,2) == binaryS2.substring(1, 2))
		{
			resultData+="1";
		}
		else 
		{
			resultData+="0";
		}
		if(binaryS2.substring(2,3) == binaryS2.substring(2, 3))
		{
			resultData+="1";
		}
		else 
		{
			resultData+="0";
		}
		if(binaryS2.substring(3,4) == binaryS2.substring(3, 4))
		{
			resultData+="1";
		}
		else 
		{
			resultData+="0";
		}
		
		DestinationReg=Integer.parseInt(tempDestination, 2);
		
		if(DestinationReg==0)
		{
			r0.setData(resultData);
		}
		else if(DestinationReg==1)
		{
			
			r1.setData(resultData);
		}
		else if(DestinationReg==2)
		{
			r2.setData(resultData);
		}
		
		
	}
	public static String getOverflow() {
        return Overflow;
    }
	
}
