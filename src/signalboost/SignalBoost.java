package signalboost;
public class SignalBoost {
    int fromleaf, fromparent;
    boolean booster;
    public Node root;
    public SignalBoost(){
    }
    public SignalBoost(int a){
        fromparent = a;
    }
    @Override
    public String toString(){
        return booster + " " + fromleaf + " " + fromparent;
    }
    public void insert(int id){
        Node newnode = new Node();
        newnode.element = id;
        if(root == null)
            root = newnode;
        else{
            Node current = root;
            Node parent;
            while(true){
                parent = current;
                if(id < current.element){
                    current = current.left;
                    if(current == null){
                        parent.left = newnode;
                        return;
                    }
                }
                else{
                    current = current.right;
                    if(current == null){
                        parent.right = newnode;
                        return;
                    }
                }
            }
        }
    }
    public void place(Node x){
        SignalBoost b = new SignalBoost(x.element);
        b.fromleaf = 0;
        Node y = x.left;
        if(y != null){
            SignalBoost boo = new SignalBoost(y.element);
            int tolerance = 2;
            int degrade = boo.fromleaf + boo.fromparent;
            if(degrade > tolerance){
                boo.booster = true;
                boo.fromleaf = boo.fromparent;
            }
            else
                boo.fromleaf = degrade;
        }
        y = x.right;
        if(y != null){
            SignalBoost bo = new SignalBoost(y.element);
            int tolerance = 2;
            int degrade = bo.fromleaf + bo.fromparent;
            if(degrade > tolerance){
                bo.booster = true;
                degrade = bo.fromparent;
            }
            b.fromleaf = Math.max(b.fromleaf, degrade);
        }
    }
}
