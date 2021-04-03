package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Terrain3D {
	
	public static void writeToFile() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("test3D.obj"));
		
        writer.append("v 0 0 0\n");
        writer.append("v 0 0 1\n");
        writer.append("v 1 0 1\n");
        writer.append("v 1 0 0\n");
        writer.append("v 0 1 0\n");
        writer.append("v 0 1 1\n");
        writer.append("v 1 1 1\n");
        writer.append("v 1 1 0\n");

        writer.append("f 1 2 3 4\n");
        writer.append("f 1 2 6 5\n");
        writer.append("f 5 4 1\n");
        writer.append("f 3 4 5 6\n");

		writer.close();
	}
		
	public static void main(String[] args){
		try {
			Terrain3D.writeToFile();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}