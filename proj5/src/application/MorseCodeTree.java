package application;

import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{

	private TreeNode<String> root;
	
	public MorseCodeTree()
	{
		//calls buildTree method
		buildTree();
		//done for now
	}
	
	//all LinkedConverterTreeInterface methods as below
	@Override
	public TreeNode<String> getRoot() {
		return this.root;
		//done for now
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		//set root of the Tree
		this.root = newNode;
		//done for now
	}

	@Override
	public void insert(String code, String result) {
		//method calls addNode, which is the recursive method
		//add result to correct position in the tree
		addNode(root, code, result);
		//done for now
		
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		//recursive method that adds the correct element to correct position
		//root = root of tree for this particular recursive instance
		//code = morse code for this particular instance
		//letter = for new TreeNode to add

		//base case
		if(code.length() ==1)
		{
			if(code.equals("."))
			{
				root.setLeftChild(new TreeNode<>(letter));
			}
			else if(code.equals("-"))
			{
				root.setRightChild(new TreeNode<>(letter));
			}
		}
		else
		{
			if(code.charAt(0) == '.')
			{
				if(root.getLeftChild() != null)
				{
					addNode(root.getLeftChild(), code.substring(1), letter);
				}
				else
				{
					root.setLeftChild(new TreeNode<>(null));
				}
			}
			else if(code.charAt(0) == '-')
			{
				if(root.getRightChild() != null)
				{
					addNode(root.getRightChild(), code.substring(1), letter);
				}
				else
				{
					root.setRightChild(new TreeNode<>(null));
				}
			}
		}
		
	}

	@Override
	public String fetch(String code) {
		//method calls fetchNode which the recursive method mentioned
		//method returns letter (refer addNode)
		String letter = fetchNode(root, code);
		return letter;
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		//recursive that fetches data from TreeNode
		//root = root of tree this instance of addNode
		//code = code for this instance of addNode
		
		if(code.length() == 1)
		{
			if(code.equals("."))
			{
				return root.getLeftChild().getData();
			}
			else if(code.equals("-"))
			{
				return root.getRightChild().getData();
			}
		}
		else
		{
			if(code.charAt(0) == '.')
			{
				if(root.getLeftChild() != null)
				{
					return fetchNode(root.getLeftChild(), code.substring(1));
				}
			
			}
			else if(code.charAt(0) == '-')
			{
				if(root.getRightChild() != null)
				{
					return fetchNode(root.getRightChild(), code.substring(1));
				}
			}
		}
		return null;
	}

	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		//not supported
		throw new UnsupportedOperationException();
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		//not supported
		throw new UnsupportedOperationException();
	}

	@Override
	public void buildTree() {
		//root will have value of "" (empty string)
		//level 0
		root = new TreeNode<String>("");
		
		// "." traverse left
		// "-" traverse right
		//follow the tree from doc; go by level
		//level 1
		insert(".", "e");
		insert("-", "t");
		
		//level 2
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		//level 3
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		//level 4
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
		
	}

	@Override
	public ArrayList<String> toArrayList() {
		// return an ArrayList of items in linked Tree 
		//in LNR order (call LTRoutputTraversal)
		ArrayList<String> array = new ArrayList<>();
		LNRoutputTraversal(root, array);
		return array;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		//left root right
		//method is recursive
		if(root.getLeftChild() == null && root.getRightChild() == null)
		{
			list.add(root.getData());
		}
		else
		{
			if(root.getLeftChild()!= null)
			{
				LNRoutputTraversal(root.getLeftChild(), list);
				
			}
			//add only after traversing left
			list.add(root.getData());
			if(root.getRightChild()!= null)
			{
				LNRoutputTraversal(root.getRightChild(), list);
			}
		}
		
	}

}
