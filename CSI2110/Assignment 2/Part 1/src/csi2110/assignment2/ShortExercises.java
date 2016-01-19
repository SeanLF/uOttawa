package csi2110.assignment2;
import net.datastructures.ArrayListQueue;
import net.datastructures.BinaryTree;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;
import net.datastructures.ArrayStack;

/**
 * Short Exercises - Assignment 2
 * @author Hussein Al Osman
 *
 */
public class ShortExercises {

	/**
	 * Build a small tree composed of 7 nodes
	 * @return the binary tree created
	 */
	public static BinaryTree<String> buildTree() {
		LinkedBinaryTree<String> bTree=new LinkedBinaryTree <String>();
		
				
		bTree.addRoot("a");
		
		Position<String> a = bTree.root();
		
		Position<String> b = bTree.insertLeft(a, "b");
		Position<String> c = bTree.insertRight(a, "c");

		bTree.insertLeft(b, "d");
		bTree.insertRight(b, "e");

		bTree.insertLeft(c, "f");
		bTree.insertRight(c, "g");
		
		
		return bTree;
	}
	
	/**
	 * Visits a node by printing the String value of its element
	 * @param v: node to be visited
	 */
	public static void visit(Position <String> v){
		System.out.println(v.element());
	}
	
	/**
	 * Performs the pre-order traversal of a binary tree
	 * @param bTree: binary tree to be traversed
	 * @param v: root node
	 */
	@SuppressWarnings("unchecked")
	public static void  preOrder(BinaryTree<String> bTree, Position <String> v){
		
		ArrayStack s = new ArrayStack();
		Position<String> n;
		s.push(v);
		while(!s.isEmpty()){
			n = (Position<String>) s.pop();
			if(n!=null){
				visit(n);
				try{
				  s.push(bTree.right(n));
				}catch(Exception e1){}
				try{
				  s.push(bTree.left(n));
				} catch(Exception e2){}
			}
				
		}
		
	}
	
	
	/**
	 * Performs the level order traversal of a binary tree
	 * @param bTree: binary tree to be traversed
	 * @param v: root node
	 */
	public static void  levelOrder(BinaryTree<String> bTree, Position <String> v){
		ArrayListQueue<Position <String>> q = new ArrayListQueue<Position <String>>();
		q.enqueue(v);
		while(!q.isEmpty()){
			v=q.dequeue();
			visit(v);
			try{
			if(bTree.left(v)!=null)
				q.enqueue(bTree.left(v));
			}catch(Exception e1){}
			try{
			if(bTree.right(v)!=null)
				q.enqueue(bTree.right(v));
			}catch(Exception e2){}
		}
	}
	
	
	
	public static void main(String [] args){
	
		BinaryTree<String> bTree = buildTree();
		
		System.out.println("Pre-Order traversal:");
		preOrder(bTree, bTree.root());
		
		System.out.println("Level Order traversal:");
		levelOrder(bTree, bTree.root());

	}
}
