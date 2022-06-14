
public class PCB {
	int pID;
	String state;
	int pc;
	int start;
	int end;

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public PCB(int pID,String state, int pc, int start,int end) { 
		this.pID=pID;		
		this.state=state;
		this.pc=pc;
		this.start=start;
		this.end=end;
	}

	public int getpID() {
		return pID;
	}

	public String getState() {
		return state;
	}

	public int getPc() {
		return pc;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}
	

}
