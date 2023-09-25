import java.util.*;


public class Node{
	
	private Integer asciiChar;
	private Integer frec;
	private Node left;
	private Node right;
	public Node(Integer asciiChar, Integer frec, Node left, Node right){
		this.asciiChar = asciiChar;
		this.frec = frec;
		this.left = left;
		this.right = right;
	}
	public Node(Integer asciiChar, Integer frec){
        this(asciiChar, frec, null, null);
	}
	
	public Node leftGetter(){
		Node leftChild = left;
		return leftChild;
	}
	public Node rightGetter(){
		Node rightChild = right;
		return rightChild;
	}
	public Integer frecGetter(){
		return frec;
	}
	public Integer asciiGetter(){
		return asciiChar;
	}
}