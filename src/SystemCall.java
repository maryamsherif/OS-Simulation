import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class SystemCall {
	static Parser parser;

	public SystemCall() {
		// TODO Auto-generated constructor stub
	}
	
	public static void print(String x, PCB pcb) {
		System.out.println(parser.readMemory(pcb, x));
	}

	public static void assign(String x, String y, PCB pcb) {
		String result=x+" "+y;
		
		if (parser.memory[0].equals(pcb.getpID()+"")) {
			for (int i=5;i<8;i++) {
				if (parser.memory[i].equals("")) {
					parser.memory[i]=result;
					//System.out.println(parser.memory[i]);
					
					break;
				}
			}
		}
		else if (parser.memory[20].equals(pcb.getpID()+"")) {
			for (int i=25;i<28;i++) {
				if (parser.memory[i].equals("")) {
					parser.memory[i]=result;
					break;
				}
			}
			
		}
	}

	public static void writeFile(String fileName, String data) {
		try {
			File myObj = new File(fileName);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {
			FileWriter myWriter = new FileWriter(fileName);
			myWriter.write(data);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void readFile(String fileName) throws IOException {
		try {
			File myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);
			parser.file3 = myReader.next();

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void printFromTo(String a, String b, PCB pcb) {
		int x, y;
		
			x = Integer.parseInt(parser.readMemory(pcb, a));
			y = Integer.parseInt(parser.readMemory(pcb, b));
		
		for (int i = x; i <= y; i++) {
			System.out.println(i);
		}
		
	}
	
	
	
	

}
