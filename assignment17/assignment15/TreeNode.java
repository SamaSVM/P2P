package com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment15;

import com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment16.MyArrayList;

import java.util.Objects;

/**
 * A class that implements the structure of a tree node.
 */
class TreeNode implements Comparable<TreeNode> {
    Byte value;
    int weight;
    TreeNode leftBranch;
    TreeNode rightBranch;

    private static final StringBuilder treeStructure = new StringBuilder();
    private static final MyArrayList<Byte> codedElements = new MyArrayList<>();                                         // ArrayList

    TreeNode() {}

    TreeNode(Byte value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    TreeNode(Byte value, int weight, TreeNode leftBranch, TreeNode rightBranch) {
        this.value = value;
        this.weight = weight;
        this.leftBranch = leftBranch;
        this.rightBranch = rightBranch;
    }

    /**
     * A method that compares two nodes.
     * @param o node that is passed to us.
     * @return if the number is negative, then the object that was given to us is larger.
     *  If positive, the opposite is true.
     */
    @Override
    public int compareTo(TreeNode o) {
        return o.weight - weight;
    }

    /**
     * A method that encodes in a binary code the path along the tree to a specific byte.
     *
     * @param bt         the encoded byte.
     * @param parentPath folded path.
     * @return ready path in binary code.
     */
    String getCodeForByte(Byte bt, String parentPath) {
        if (Objects.equals(value, bt)) {
            return parentPath;
        } else {
            if (leftBranch != null) {
                String path = leftBranch.getCodeForByte(bt, parentPath + 0);
                if (path != null) {
                    return path;
                }
            }
            if (rightBranch != null) {
                String path = rightBranch.getCodeForByte(bt, parentPath + 1);
                if (path != null) {
                    return path;
                }
            }
        }
        return null;
    }

    /**
     * A method that derives a tree from binary code and elements.
     *
     * @return the resulting tree.
     */
    TreeNode getTreeFromString(StringBuilder treeStructure, MyArrayList<Byte> codedElements) {                            // ArrayList
        TreeNode result = new TreeNode();
        if (treeStructure.charAt(0) == '1') {
            result.leftBranch = new TreeNode(null, 1);
            result.rightBranch = new TreeNode(null, 1);
            treeStructure.deleteCharAt(0);
        } else {
            result.value = codedElements.get(0);
            codedElements.remove(0);
            treeStructure.deleteCharAt(0);
            return result;
        }
        if (result.leftBranch.weight == 1 && result.rightBranch.weight == 1) {
            result.leftBranch = getTreeFromString(treeStructure, codedElements);
            result.rightBranch = getTreeFromString(treeStructure, codedElements);
        }
        return result;
    }

    /**
     * The method which from a tree forms the binary code and coded elements
     * on which further it will be possible to construct it.
     *
     * @param tree tree that is encrypted.
     */
    static void getStringFromTree(TreeNode tree) {
        if (tree.value == null) {
            treeStructure.append("1");
            getStringFromTree(tree.leftBranch);
            getStringFromTree(tree.rightBranch);
        } else {
            treeStructure.append("0");
            codedElements.add(tree.value);
        }
    }

    /**
     * The method that returns treeStructure.
     */
    static StringBuilder getTreeStructure() {
        return treeStructure;
    }

    /**
     * The method that returns codedElements.
     */
    static MyArrayList<Byte> getCodedElements() {                                                                       // ArrayList
        return codedElements;
    }
}
