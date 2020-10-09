/**
 * 
 */
package merkle.assignment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Nilesh
 *
 */
public class Assignment {
	
	private static final Integer LEVEL_0 = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode rootNode = new TreeNode();
		rootNode.setValue(5);
		rootNode.setLevel(LEVEL_0);
		
		TreeNode node2 = new TreeNode();
		node2.setValue(2);
		rootNode.setLeftChild(node2);
		
		TreeNode node3 = new TreeNode();
		node3.setValue(3);
		rootNode.setRightChild(node3);
		
		TreeNode node7 = new TreeNode();
		node7.setValue(7);
		node2.setLeftChild(node7);
		
		TreeNode node1 = new TreeNode();
		node1.setValue(1);
		node3.setRightChild(node1);
		
		TreeNode node9 = new TreeNode();
		node9.setValue(9);
		node7.setLeftChild(node9);
		
		TreeNode node4 = new TreeNode();
		node4.setValue(4);
		node1.setLeftChild(node4);
		
		TreeNode node6 = new TreeNode();
		node6.setValue(6);
		node1.setRightChild(node6);
		
		Map<Integer, List<TreeNode>> levelNodesMap = new HashMap<>();
		calculateChildNodeLevelsAndPopulateLevelMap(rootNode, levelNodesMap);
		printTreeDetails(levelNodesMap);
	}
	
	private static void printTreeDetails(Map<Integer, List<TreeNode>> levelNodesMap) {
		Integer emptyNodeCount = 0;
		for(Entry<Integer, List<TreeNode>> entry : levelNodesMap.entrySet()) {
			Integer emptyNodesOnLevel = printDisctanceForEachNode(entry.getValue());
			System.out.println("Empty node present on level " + entry.getKey() + " are : " + emptyNodesOnLevel);
			emptyNodeCount += emptyNodesOnLevel;
		}
		System.out.println("Total empty nodes present in tree : " + emptyNodeCount);
	}

	private static void calculateChildNodeLevelsAndPopulateLevelMap(TreeNode root, Map<Integer, List<TreeNode>> levelNodesMap) {
		levelNodesMap.computeIfAbsent(root.getLevel(), k -> new LinkedList<TreeNode>());
		levelNodesMap.get(root.getLevel()).add(root);
		Integer parentLevel = root.getLevel();
		
		if(haveNoChildren(root)) {
			return;
		}
		else if(hasLeftChildButNoRightChild(root)) {
			root.getLeftChild().setLevel((parentLevel + 1));
			calculateChildNodeLevelsAndPopulateLevelMap(root.getLeftChild(), levelNodesMap);
			levelNodesMap.get((parentLevel + 1)).add(root.getRightChild());
		}
		else if(hasRightChildButNoLeftChild(root)) {
			levelNodesMap.computeIfAbsent((parentLevel + 1), k -> new LinkedList<TreeNode>());
			levelNodesMap.get((parentLevel + 1)).add(root.getLeftChild());
			root.getRightChild().setLevel((parentLevel + 1));
			calculateChildNodeLevelsAndPopulateLevelMap(root.getRightChild(), levelNodesMap);
		}
		else {
			root.getLeftChild().setLevel((parentLevel + 1));
			calculateChildNodeLevelsAndPopulateLevelMap(root.getLeftChild(), levelNodesMap);
			root.getRightChild().setLevel((parentLevel + 1));
			calculateChildNodeLevelsAndPopulateLevelMap(root.getRightChild(), levelNodesMap);
		}
	}

	private static boolean hasRightChildButNoLeftChild(TreeNode node) {
		return (null == node.getLeftChild() && null != node.getRightChild());
	}

	private static boolean hasLeftChildButNoRightChild(TreeNode node) {
		return (null != node.getLeftChild() && null == node.getRightChild());
	}

	private static boolean haveNoChildren(TreeNode node) {
		return (null == node.getLeftChild() && null == node.getRightChild());
	}

	private static Integer printDisctanceForEachNode(List<TreeNode> nodes) {
		Integer emptyNodeCount = 0;
		for(int i=0; i<nodes.size(); i++) {
			TreeNode firstNode = nodes.get(i);
			if(null != firstNode) {
				for(int j=i+1; j<nodes.size(); j++) {
					TreeNode secondNode = nodes.get(j);
					if(null != secondNode)
						System.out.println("Distance between node " + firstNode.getValue() + " and node " + secondNode.getValue() + " is : " + (j - i - 1));
				}
			}
			else {
				emptyNodeCount++;
			}
		}
		return emptyNodeCount;
	}
}
