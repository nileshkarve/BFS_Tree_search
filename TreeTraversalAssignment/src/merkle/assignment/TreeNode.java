/**
 * 
 */
package merkle.assignment;

/**
 * @author Nilesh
 *
 */
public class TreeNode {

	private int value;
	private TreeNode leftChild;
	private TreeNode rightChild;
	private Integer level;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public TreeNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}
	public TreeNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TreeNode [value=").append(value).append(", leftChild=").append(leftChild)
				.append(", rightChild=").append(rightChild).append(", level=").append(level).append("]");
		return builder.toString();
	}
}
