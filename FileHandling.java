import java.util.*;
import java.io.File; 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import java.io.FileWriter; 
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.BufferedOutputStream;




public class FileHandling{
	
	public static String readFile(String fileName){
		String output = "";
		try {
		  File file = new File(fileName);
		  Scanner myReader = new Scanner(file);
		  while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			output += data;
		  }
		  myReader.close();
		} catch (FileNotFoundException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
		return output;
	}
	
	public static void writeFile(String fileName, String input){
		 try {
		  File file = new File(fileName);
		  file.createNewFile();
		} catch (IOException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
		 try {
		  FileWriter myWriter = new FileWriter(fileName);
		  myWriter.write(input);
		  myWriter.close();
		//  System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	
	
	
	
	public static void writeBytesInFile(String binaryString, String filePath){
         System.out.println(binaryString);

        int remainingBits = binaryString.length() % 8;
        if (remainingBits != 0) {
            int paddingZeros = 8 - remainingBits;
            StringBuilder paddedString = new StringBuilder(binaryString);
            for (int i = 0; i < paddingZeros; i++) {
                paddedString.append( "0");
            }
            binaryString = paddedString.toString();
        }

        int numBytes = binaryString.length() / 8;
        byte[] bytes = new byte[numBytes];

         System.out.println("bytes:");
        for (int i = 0; i < numBytes; i++) {
            String chunk = binaryString.substring(i * 8, (i + 1) * 8);
			         System.out.println(chunk);

            bytes[i] = (byte) Integer.parseInt(chunk, 2);
        }


        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            bos.write(bytes);
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save the file.");
        }
	}
	
	
	
    public static String readByteFile(String filename, int len){
		
	    String result = "";
        try {
             result = convertBytesToString(filename, len);
         //   System.out.println("readed from file: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		return result;
	  }
	  
	public static String convertBytesToString(String filename, int len) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        StringBuilder stringBuilder = new StringBuilder();

        int data;
		System.out.println("every byte readed from output file:");
        while ((data = fileInputStream.read()) != -1) {
            String binaryString = String.format("%8s", Integer.toBinaryString(data & 0xFF)).replace(' ', '0');
		    System.out.println(binaryString);

            stringBuilder.append(binaryString);
        }

        fileInputStream.close();
        return stringBuilder.toString().substring(0, len);
    }
	
}