package SignalBooster;

import java.lang.reflect.*;

public class SignalBoosters {

    private static class Booster {

        int degradeToLeaf, degradeFromParent;
        boolean boosterHere;

        public Booster(int fromParent) {
            degradeFromParent = fromParent;
        }

        public String toString() {
            return boosterHere + " " + degradeToLeaf + " "
                    + degradeFromParent;
        }
    }

    static int tolerance;

    public static void placeBoosters(BinaryTreeNode x) {
        Booster elementX = (Booster) x.getElement();
        elementX.degradeToLeaf = 0;

        BinaryTreeNode y = x.getLeftChild();
        if (y != null) {
            Booster elementY = (Booster) y.getElement();
            int degradation = elementY.degradeToLeaf
                    + elementY.degradeFromParent;
            if (degradation > tolerance) {
                elementY.boosterHere = true;
                elementX.degradeToLeaf = elementY.degradeFromParent;
            } else {
                elementX.degradeToLeaf = degradation;
            }
        }

        y = x.getRightChild();
        if (y != null) {
            Booster elementY = (Booster) y.getElement();
            int degradation = elementY.degradeToLeaf
                    + elementY.degradeFromParent;
            if (degradation > tolerance) {
                elementY.boosterHere = true;
                degradation = elementY.degradeFromParent;
            }
            elementX.degradeToLeaf = Math.max(elementX.degradeToLeaf,
                    degradation);
        }
    }

    public static void main(String[] args) {

        LinkedBinaryTree u = new LinkedBinaryTree();
        LinkedBinaryTree x = new LinkedBinaryTree();
        u.makeTree(new Booster(4), x, x);
        LinkedBinaryTree v = new LinkedBinaryTree();
        v.makeTree(new Booster(2), u, x);
        u.makeTree(new Booster(3), x, x);
        LinkedBinaryTree w = new LinkedBinaryTree();
        w.makeTree(new Booster(1), u, x);
        u.makeTree(new Booster(2), v, w);
        v.makeTree(new Booster(3), x, x);
        w.makeTree(new Booster(0), x, x);
        LinkedBinaryTree y = new LinkedBinaryTree();
        y.makeTree(new Booster(2), v, w);
        w.makeTree(new Booster(1), x, x);
        LinkedBinaryTree t = new LinkedBinaryTree();
        t.makeTree(new Booster(0), y, w);
        v.makeTree(new Booster(1), t, u);

        tolerance = 3;
        Class[] paramType = {BinaryTreeNode.class};
        // type of parameter for visit
        Method thePlaceMethod = null;   // method to place boosters
        System.out.println("Placement Signal Booster");
        try {
            Class pb = SignalBoosters.class;
            thePlaceMethod = pb.getMethod("placeBoosters", paramType);
        } catch (Exception e) {
        }

        v.postOrder(thePlaceMethod);
        v.postOrderOutput();
    }
}