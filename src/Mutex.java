
public class Mutex {
	private  int flag;
	private  int owner;
	static Parser parser;
	
	public Mutex() {
		this.flag=1;
		this.owner=owner;
		
	}
	public  int getOwner() {
		return owner;
	}

	public  void setOwner(int owner) {
		this.owner = owner;
	}


	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public static void semWait(String s, PCB pcb) {
		if (s.equals("userInput")) {
			//System.out.println("USER INPUT FLAG: "+userInput.getFlag());
			if (parser.userInput.getFlag() == 1) {
				parser.userInput.setFlag(0);
				parser.userInput.setOwner(pcb.getpID());
			} else {
				parser.changeState(pcb,"Blocked");
				parser.userInputBlocked.add(pcb.getpID());
				parser.GeneralBlocked.add(pcb.getpID());
				
			}	
//				//pcb.setState("Blocked");
//				if (parser.memory[0].equals(pcb.getpID() +"")) {
//				 parser.memory[1]="Blocked";	
//				}
//				else if (parser.memory[20].equals(pcb.getpID()+"")) {
//					 parser.memory[21]="Blocked";	
//					}
			}
		   else if (s.equals("userOutput")) {
			//System.out.println("USER OUTPUT FLAG: "+userOutput.getFlag());
			if (parser.userOutput.getFlag() == 1) {
				parser.userOutput.setFlag(0);
				parser.userOutput.setOwner(pcb.getpID());
			} else {
				parser.changeState(pcb,"Blocked");
				parser.userOutputBlocked.add(pcb.getpID());
				parser.GeneralBlocked.add(pcb.getpID());
			
			}	
//				pcb.setState("Blocked");
//				if (parser.memory[0].equals(pcb.getpID()+"")) {
//					 parser.memory[1]="Blocked";	
//					}
//					else if (parser.memory[20].equals(pcb.getpID()+"")) {
//						 parser.memory[21]="Blocked";	
//						}
//				
//			}
		}  
		   else if (s.equals("file")) {
			//System.out.println("FILE FLAG: "+file.getFlag());
			if (parser.file.getFlag() == 1) {
				parser.file.setFlag(0);
				parser.file.setOwner(pcb.getpID());
			} else {
				parser.changeState(pcb, "Blocked");
				parser.fileBlocked.add(pcb.getpID());
				parser.GeneralBlocked.add(pcb.getpID());
				
			}	
//				pcb.setState("Blocked");
//				if (parser.memory[0].equals(pcb.getpID()+"")) {
//					 parser.memory[1]="Blocked";	
//					}
//					else if (parser.memory[20].equals(pcb.getpID()+"")) {
//						 parser.memory[21]="Blocked";	
//						}
			}

		

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



	public static void semSignal(String s, PCB pcb) {
		if (s.equals("userInput")) {
			if (parser.userInput.getOwner() == pcb.getpID()) {
				if (parser.userInputBlocked.isEmpty()) {
					parser.userInput.setFlag(1);
				} else {
					parser.userInput.setOwner(parser.userInputBlocked.peek()); 
					parser.userInputBlocked.remove();
					parser.GeneralBlocked.remove();
					//pcb.setState("Ready");
					parser.changeState(pcb, "Ready");
					parser.Ready.add(parser.userInput.getOwner());
					parser.userInput.setFlag(0);
					
				}	
//					if (parser.memory[0].equals(pcb.getpID()+"")) {
//						 parser.memory[1]="Ready";	
//						}
//						else if (parser.memory[20].equals(pcb.getpID()+"")) {
//							 parser.memory[21]="Ready";	
//							}
				}
			}
		   else if (s.equals("userOutput")) {
			if (parser.userOutput.getOwner() == pcb.getpID()) {
				if (parser.userOutputBlocked.isEmpty()) {
					parser.userOutput.setFlag(1);
				} else {
					parser.userOutput.setOwner(parser.userOutputBlocked.peek());
					parser.userOutputBlocked.remove();
					parser.GeneralBlocked.remove();
					//pcb.setState("Ready");
					parser.changeState(pcb, "Ready");
					parser.Ready.add(parser.userOutput.getOwner());
					parser.userOutput.setFlag(0);
					
				}	
//					if (parser.memory[0].equals(pcb.getpID()+"")) {
//						 parser.memory[1]="Ready";	
//						}
//						else if (parser.memory[20].equals(pcb.getpID()+"")) {
//							 parser.memory[21]="Ready";	
//							}
				}
			}
		      else if (s.equals("file")) {
			if (parser.file.getOwner() == pcb.getpID()) {
				if (parser.fileBlocked.isEmpty()) {
					parser.file.setFlag(1);
				} else {
					parser.file.setOwner(parser.fileBlocked.peek());
					parser.fileBlocked.remove();
					parser.GeneralBlocked.remove();
//					pcb.setState("Ready");
					parser.changeState(pcb,"Ready");
					parser.Ready.add(parser.file.getOwner());
					parser.file.setFlag(0);
					
//					if (parser.memory[0].equals(pcb.getpID()+"")) {
//						 parser.memory[1]="Ready";	
//						}
//						else if (parser.memory[20].equals(pcb.getpID()+"")) {
//							 parser.memory[21]="Ready";	
//							}
//				}
			}
		}

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
	
	
	
	
	


