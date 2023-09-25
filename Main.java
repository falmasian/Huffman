import java.util.*;


public class Main{
	static int len;
	public Main(){
		len = 0;
	}
	
	public static void main(String args[]) { 
		 String inputFile = "Z:\\HuffmanProject\\input.txt";
		 String outputFile = "Z:\\HuffmanProject\\output.zip";
		 String structure = "Z:\\HuffmanProject\\structure.txt";		 
		 String decodedOutput  = "Z:\\HuffmanProject\\decodedOutput.zip";
		 String text = FileHandling.readFile(inputFile);
	     Node root = encode(text, structure, outputFile);
		 String encoded = FileHandling.readByteFile(outputFile, len);
		// System.out.println(encoded);
		String decodedStr = decode(encoded, root);
		FileHandling.writeFile(decodedOutput, decodedStr);
		
	}
	
	public static Node encode( String text, String structure, String outputFile){
		 ArrayList<Node> nodes = Huffman.createNodes(text);
		 Node root = Huffman.createTree(nodes);
		 Map<Integer, String> huffmanCode = new HashMap<>();  
		 Huffman.encode(root, "", huffmanCode); 
        // System.out.println("Huffman Codes of the characters are: " + huffmanCode);  		 
		 String outputStr = Huffman.convertText(text, huffmanCode);
		 writeStructureInFile(structure, huffmanCode);
		 FileHandling.writeBytesInFile(outputStr, outputFile);
		 len = outputStr.length();
		 return root; 
	}
	 
	public static String decode(String encoded, Node root){
		String decoded = "";
		//System.out.println(encoded.length());
        for (int i = 0; i < encoded.length(); ) {
            Node node = root;
            while (node.leftGetter() != null && node.rightGetter() != null && i < encoded.length()) {
                if (encoded.charAt(i) == '1'){
                    node = node.rightGetter();
				}
                else{
					node = node.leftGetter();
				}
                i++;
            }
            if (node != null){
				if(node.asciiGetter()  != null){
               if (node.asciiGetter() < 256)
					{
				    int ascii = node.asciiGetter().intValue();
					char asciiToChar = (char) ascii;
                    decoded += asciiToChar;
					}
                else
                    System.out.println("Input not Valid");
				
				}

        }
		}
        System.out.println("Decoded Text: " + decoded);
		return decoded;
	} 
	 
	public static void writeStructureInFile(String structure, Map<Integer, String> huffmanCode){
		StringBuilder sb = new StringBuilder();  
		for ( Map.Entry<Integer, String> entry : huffmanCode.entrySet()){   
				sb.append(String.valueOf(entry.getKey()) + " " + entry.getValue() + "\n" );  
			}  
		FileHandling.writeFile(structure, sb.toString());
			
	}
}