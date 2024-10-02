package application;

public class TreeNode <T> {

	private TreeNode<T>leftChild, rightChild;
	private T data;
	
	public TreeNode(T dataNode)
	{
		this.data = dataNode;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public TreeNode(TreeNode<T> node)
	{
		//create new tree node with deep copies
		this.data = node.data;
	
		if(node.leftChild!=null)
		{
			this.leftChild = new TreeNode<>(node.leftChild);
		}
		else
		{
			this.leftChild = null;
		}
		
		if(node.rightChild!=null)
		{
			this.leftChild = new TreeNode<>(node.rightChild);
		}
		else
		{
			this.rightChild = null;
		}
		
	}
	
	//getter
	public T getData()
	{
		return this.data;
	}
	
	public TreeNode<T> getLeftChild()
	{
		return this.leftChild;
	}
	
	public TreeNode<T> getRightChild()
	{
		return this.rightChild;
	}
	
	//setter
	public void setLeftChild(TreeNode<T> left)
	{
		this.leftChild = left;
	}
	
	public void setRightChild(TreeNode<T> right)
	{
		this.rightChild = right;
	}
}
