import java.util.*;

public class Huffman{
	
	public static ArrayList<Node> createNodes(String text){
        Map<Integer, Integer> freq = new HashMap<>();  
		for (char character: text.toCharArray())   
        {   
	        int ascii = (int)character;
            freq.put(ascii, freq.getOrDefault(ascii, 0) + 1);  
        } 
		//Map<Integer, Integer> sortedFreq = sortByValue(freq);
        List<Node> nodes = new ArrayList<>();
        for (var entry: freq.entrySet())   
        {  
            nodes.add(new Node(entry.getKey(), entry.getValue()));  
        }
        return new ArrayList<Node>(nodes);
        
	}
	
	public static Node createTree(ArrayList<Node> nodes){
		//System.out.println("loop is start :");
		while(nodes.size() > 1){
		//	System.out.println("befor sort");
          //  for(Node n: nodes){		System.out.print(n.asciiGetter()+":"+ n.frecGetter()+" , ");}
	

			sortNodes(nodes);
			//System.out.println("after sort");
           // for(Node n: nodes){		System.out.print(n.asciiGetter()+":"+ n.frecGetter()+" , ");}
			Node left = nodes.get(0);
			Node right = nodes.get(1);
			
			int sum = left.frecGetter() + right.frecGetter();
			nodes.remove(0);
		//	System.out.println("delete 0");
          //  for(Node n: nodes){		System.out.print(n.asciiGetter()+":"+ n.frecGetter()+" , ");};
			nodes.remove(0);
		//	System.out.println("delete 1");

		 //           for(Node n: nodes){		System.out.print(n.asciiGetter()+":"+ n.frecGetter()+" , ");}
			Node newNode = new Node(null, sum, left, right);
			//nodes.add(newNode);
			int i = 0;
			for(i=0 ; i < nodes.size() ; i++ ){
				if (nodes.get(i).asciiGetter() != null){
					break;
				}
			}
			nodes.add(i, newNode);

			}
		Node root = nodes.get(0);	
		return root;
	}
	
	public static void sortNodes(ArrayList<Node> list) {
 
        list.sort((o1, o2)
                  -> o1.frecGetter().compareTo(
                      o2.frecGetter()));
    }
	
	 public static boolean isLeaf(Node node)   
    {   
        return node.leftGetter() == null && node.rightGetter() == null;  
    }  
	public static void encode(Node root, String str, Map<Integer, String> huffmanCode){
		if (root == null){  
            return;  
        }  
        if (isLeaf(root)){  
            huffmanCode.put(root.asciiGetter(), str.length() > 0 ? str : "1");  
        }
        else{		
        encode(root.leftGetter(), str + '0', huffmanCode);  
        encode(root.rightGetter(), str + '1', huffmanCode); 
		}
	}
	
    public static String convertText( String inputText, Map<Integer, String> huffmanCode){
		StringBuilder sb = new StringBuilder();  
        for (char c: inputText.toCharArray())   
        {   
            sb.append(huffmanCode.get((int)c));  
        }  
        String convertedStr = sb.toString();	
        return convertedStr;		
	}
	
	
	}