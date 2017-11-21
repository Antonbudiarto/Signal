package SignalBooster;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LinkedBinaryTree implements BinaryTree {

    BinaryTreeNode root;
    static Method visit;
    static Object[] visitArgs = new Object[1];
    static int count;
    static Class[] paramType = {BinaryTreeNode.class};
    static Method theAdd1;
    static Method theOutput;

    static {
        try {
            Class lbt = LinkedBinaryTree.class;
            theAdd1 = lbt.getMethod("add1", paramType);
            theOutput = lbt.getMethod("output", paramType);
        } catch (Exception e) {
        }
    }

    @Override
    public int size() {
        count = 0;
        preOrder(theAdd1);
        return count;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Object root() {
        return root;
    }

    public static void output(BinaryTreeNode t) {
        System.out.println(t.element + " ");
    }

    public static void add1(BinaryTreeNode t) {
        count++;
    }

    public void makeTree2(Object element, Object left, Object right) {

        root = new BinaryTreeNode(element);
        count = 1;
        if (left != null) {
            count = count + ((LinkedBinaryTree) left).size();
            root.leftChild = ((LinkedBinaryTree) left).root;
        } else {
            root.leftChild = null;
        }
        if (right != null) {
            count = count + ((LinkedBinaryTree) left).size();
            root.rightChild = ((LinkedBinaryTree) left).root;
        } else {
            root.rightChild = null;
        }

    }

    @Override
    public void makeTree(Object root, Object left, Object right) {
        this.root = new BinaryTreeNode(root, ((LinkedBinaryTree) left).root, ((LinkedBinaryTree) right).root);
    }

    @Override
    public BinaryTree removeLeftSubtree() {
        if (root == null) {
            throw new IllegalArgumentException("tree is empty");
        }
        LinkedBinaryTree leftSubtree = new LinkedBinaryTree();
        leftSubtree.root = root.leftChild;
        root.leftChild = null;

        return (BinaryTree) leftSubtree;
    }

    @Override
    public BinaryTree removeRightSubtree() {
        if (root == null) {
            throw new IllegalArgumentException("tree is empty");
        }
        LinkedBinaryTree rightSubtree = new LinkedBinaryTree();
        rightSubtree.root = root.rightChild;
        root.rightChild = null;

        return (BinaryTree) rightSubtree;
    }

    public void preOrderOutput() {
        preOrder(theOutput);
    }

    @Override
    public void preOrder(Method visit) {
        LinkedBinaryTree.visit = visit;
        thePreOrder(root);
    }

    static void thePreOrder(BinaryTreeNode t) {
        if (t != null) {
            visitArgs[0] = t;
            try {
                visit.invoke(null, visitArgs);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                System.out.println(e);
            }
            thePreOrder(t.leftChild);
            thePreOrder(t.rightChild);
        }
    }

    public void levelOrderOutput() {
        levelOrder(theOutput);
    }

    @Override
    public void levelOrder(Method visit) {
        LinkedBinaryTree.visit = visit;
        thelevelOrder(root);
    }

    static int thelevelOrder(BinaryTreeNode t) {
        if (t == null) {
            return 0;
        }
        int hl = thelevelOrder(t.leftChild);
        int hr = thelevelOrder(t.rightChild);
        if (hl > hr) {
            return ++hl;
        } else {
            return ++hr;
        }
    }

    public void postOrderOutput() {
        postOrder(theOutput);
    }

    @Override
    public void postOrder(Method visit) {
        LinkedBinaryTree.visit = visit;
        thepostOrder(root);
    }

    static void thepostOrder(BinaryTreeNode t) {
        if (t != null) {
            thepostOrder(t.leftChild);
            thepostOrder(t.rightChild);
            visitArgs[0] = t;
            try {
                visit.invoke(null, visitArgs);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                System.out.println(e);
            }
        }
    }

    public void inOrderOutput() {
        inOrder(theOutput);
    }

    @Override
    public void inOrder(Method visit) {
        LinkedBinaryTree.visit = visit;
        theinOrder(root);
    }

    static void theinOrder(BinaryTreeNode t) {
        if (t != null) {
            theinOrder(t.leftChild);
            visitArgs[0] = t;
            try {
                visit.invoke(null, visitArgs);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                System.out.println(e);
            }
            theinOrder(t.rightChild);
        }
    }
}
