import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Scheduler {
	static Parser parser;
	static PCB pcb1 = null;
	static PCB pcb2 = null;
	static PCB pcb3 = null;
	static int p;

	public Scheduler() {

	}

	public void schedule(int t1, int t2, int t3, int Q) throws IOException {

		if (t1 < t2 && t1 < t3) {
			parser.Ready.add(1);
			pcb1 = new PCB(1, "Ready", 0, 0, 19);
			parser.saveToMemory("Program_1.txt", pcb1);
		} else if (t2 < t1 && t2 < t3) {
			parser.Ready.add(2);
			pcb2 = new PCB(2, "Ready", 0, 0, 19);
			parser.saveToMemory("Program_2.txt", pcb2);
		} else if (t3 < t1 && t3 < t2) {
			parser.Ready.add(3);
			pcb3 = new PCB(3, "Ready", 0, 0, 19);
			parser.saveToMemory("Program_3.txt", pcb3);
		}
		
		while ((parser.Ready.isEmpty()== false)) {
			
			int processID = parser.Ready.peek();
			//parser.Ready.remove();


			System.out.print("Ready Queue: ");
			parser.printQueues(parser.Ready);
			System.out.print("General Blocked Queue: ");
			parser.printQueues(parser.GeneralBlocked);
			System.out.print("userInput Blocked Queue: ");
			parser.printQueues(parser.userInputBlocked);
			System.out.print("userOutput Blocked Queue: ");
			parser.printQueues(parser.userOutputBlocked);
			System.out.print("file Blocked Queue: ");
			parser.printQueues(parser.fileBlocked);
		
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Memory Part1 Contains:");
			System.out.println("PCB of the process : ");
			System.out.print("The process ID is " + parser.memory[0] + " / ");
			System.out.print("The process State is " + parser.memory[1] + " / ");
			System.out.print("The program counter of the process is " + parser.memory[2] + " / ");
			System.out.print("The lower boundry of the process in the memory is " + parser.memory[3] + " / ");
			System.out.print("The upper boundry of the process in the memory is "+ parser.memory[4] );
			System.out.println();
			System.out.println("Variables  of the process : ");
			System.out.print("First variable : " + parser.memory[5] + " / ");
			System.out.print("Second variable :  " + parser.memory[6] + " / ");
			System.out.print("Third variable : " + parser.memory[7] );
			System.out.println();
			System.out.println("Instructions of the process : ");
			for(int i=8;i<20;i++) {
				System.out.print(parser.memory[i] + " / ");
			}
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Memory Part2 Contains:");
			System.out.println("PCB of the process : ");
			System.out.print("The process ID is " + parser.memory[20] + " / ");
			System.out.print("The process State is " + parser.memory[21] + " / ");
			System.out.print("The program counter of the process is " + parser.memory[22] + " / ");
			System.out.print("The lower boundry of the process in the memory is " + parser.memory[23] + " / ");
			System.out.print("The upper boundry of the process in the memory is "+ parser.memory[24] );
			System.out.println();
			System.out.println("Variables  of the process : ");
			System.out.print("First variable : " + parser.memory[25] + " / ");
			System.out.print("Second variable : " + parser.memory[26] + " / ");
			System.out.print("Third variable : " + parser.memory[27]);
			System.out.println();
			System.out.println("Instructions of the process : ");
			for(int i=28;i<40;i++) {
				System.out.print(parser.memory[i] + " / ");
			}
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
			
			System.out.println("Hard Disk Contents:");
			BufferedReader br = new BufferedReader(new FileReader(parser.HardDisk.getName()));
			 String line;
			 while ((line = br.readLine()) != null) {
			   System.out.print(line+" ");
			 }
			 System.out.println();
			 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
				

			if (processID == 1) {
				if (parser.memory[0].equals("1") || parser.memory[20].equals("1")) {
					//parser.changeState(pcb1,"Running");
					parser.Ready.remove(1);
					System.out.println("Process " + processID + " is Running.");
					p=parser.execute(pcb1);
				
					
				
			}		  
				else {
					parser.swapTemp(parser.temp);
					parser.SwapDiskToMem();
					parser.swapFiletoFile(parser.temp);
				}
				
			}
			 
		      if (processID == 2) {
				 if (parser.memory[0].equals("2") || parser.memory[20].equals("2")) {
					//parser.changeState(pcb2,"Running");
					 parser.Ready.remove(2);
					System.out.println("Process " + processID + " is Running.");
					p=parser.execute(pcb2);
				
				}	
				else {
					parser.swapTemp(parser.temp);
					parser.SwapDiskToMem();
					parser.swapFiletoFile(parser.temp);
				}
			}
		      
		      else if (processID == 3) {
		    	 

					
				if (parser.memory[0].equals("3") || parser.memory[20].equals("3")) {
					//parser.changeState(pcb3,"Running");
		
					parser.Ready.remove(3);
					System.out.println("Process " + processID + " is Running.");
					p= parser.execute(pcb3);
					
			}
				else {
					parser.swapTemp(parser.temp);
					parser.SwapDiskToMem();
					parser.swapFiletoFile(parser.temp);
				}
			}


			if (processID == 1) {
//				if (pcb1.getPc() < pcb1.getEnd() && !(parser.GeneralBlocked.contains(processID)&&!(parser.Ready.contains(processID)))) {
//					parser.Ready.add(processID);
				 if (pcb1.getPc() == pcb1.getEnd()) {
					parser.changeState(pcb1,"Finished");
					//pcb1.setState("Finished");
					parser.clearMem(pcb1);
					System.out.println("Process " + processID + " is Finished. ##########################");
					parser.Ready.remove(1);
					System.out.print("Ready Queue: ");
					parser.printQueues(parser.Ready);
					System.out.print("General Blocked Queue: ");
					parser.printQueues(parser.GeneralBlocked);
					System.out.print("userInput Blocked Queue: ");
					parser.printQueues(parser.userInputBlocked);
					System.out.print("userOutput Blocked Queue: ");
					parser.printQueues(parser.userOutputBlocked);
					System.out.print("file Blocked Queue: ");
					parser.printQueues(parser.fileBlocked);

				}
			
				 }
			else if (processID == 2) {
//				if (pcb2.getPc() < pcb2.getEnd() && !(parser.GeneralBlocked.contains(processID))&&!(parser.Ready.contains(processID))) {
//					parser.Ready.add(processID);
				 if (pcb2.getPc() == pcb2.getEnd()) {
					 parser.changeState(pcb2,"Finished");
				
					parser.clearMem(pcb2);
					System.out.println("Process " + processID + " is Finished. ##########################");
					 parser.Ready.remove(2);
					System.out.print("Ready Queue: ");
					parser.printQueues(parser.Ready);
					System.out.print("General Blocked Queue: ");
					parser.printQueues(parser.GeneralBlocked);
					System.out.print("userInput Blocked Queue: ");
					parser.printQueues(parser.userInputBlocked);
					System.out.print("userOutput Blocked Queue: ");
					parser.printQueues(parser.userOutputBlocked);
					System.out.print("file Blocked Queue: ");
					parser.printQueues(parser.fileBlocked);
				}
				 

			}
			else if (processID == 3) {
//				if (pcb3.getPc() < pcb3.getEnd() && !(parser.GeneralBlocked.contains(processID))&&!(parser.Ready.contains(processID))) {
//					parser.Ready.add(processID);
				   if (pcb3.getPc() >= 11) {
					 parser.changeState(pcb3,"Finished");
					//pcb3.setState("Finished");
					parser.clearMem(pcb3);
					parser.Ready.remove(3);
					System.out.println("Process " + processID + " is Finished. #############################");
		
					System.out.print("Ready Queue: ");
					parser.printQueues(parser.Ready);
					System.out.print("General Blocked Queue: ");
					parser.printQueues(parser.GeneralBlocked);
					System.out.print("userInput Blocked Queue: ");
					parser.printQueues(parser.userInputBlocked);
					System.out.print("userOutput Blocked Queue: ");
					parser.printQueues(parser.userOutputBlocked);
					System.out.print("file Blocked Queue: ");
					parser.printQueues(parser.fileBlocked);

				}
				 
			
				}
			}
		
	
	
			System.out.println("-----------------------------------------------");
//			for (String x : parser.memory) {
//				System.out.print(x + " ");
//			}
//			System.out.println();

				}

	

	public static void arrivedWhileExecute(int id) throws IOException {
		//parser.Ready.add(id);
		int o = parser.ifSpace(parser.memory);
		if (o == 0) {
			if (id == 1) {
				pcb1 = new PCB(1, "Ready", 0, 0, 19);
				parser.saveToMemory("Program_1.txt", pcb1);
			} else if (id == 2) {
				pcb2 = new PCB(2, "Ready", 0, 0, 19);
				parser.saveToMemory("Program_2.txt", pcb2);
			} else if (id == 3) {
				pcb3 = new PCB(3, "Ready", 0, 0, 19);
				parser.saveToMemory("Program_3.txt", pcb3);
			}
		}
		else if (o == 20) {
			if (id == 1) {
				pcb1 = new PCB(1, "Ready", 0, 20, 39);
				parser.saveToMemory("Program_1.txt", pcb1);
			} else if (id == 2) {
				pcb2 = new PCB(2, "Ready", 0, 20, 39);
				parser.saveToMemory("Program_2.txt", pcb2);
			} else if (id == 3) {
				pcb3 = new PCB(3, "Ready", 0, 20, 39);
				parser.saveToMemory("Program_3.txt", pcb3);
			}
		} 
		
		else {
		   int start = parser.SwapMemToDisk();

			if (id == 1) {
				pcb1 = new PCB(1, "Ready", 0, start, (start + 19));
				parser.saveToMemory("Program_1.txt", pcb1);
				
			} else if (id == 2) {
				pcb2 = new PCB(2, "Ready", 0, start, (start + 19));
				parser.saveToMemory("Program_2.txt", pcb2);
			} else if (id == 3) {
				pcb3 = new PCB(3, "Ready", 0, start, (start + 19));
				parser.saveToMemory("Program_3.txt", pcb3);
			}

		}

	}
	
	 

}
