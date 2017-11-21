package signalboost;
public class  Node{
    //paket data terlihat anggota
    int element;
    Node left;
    Node right;
    //konstruktor
    public Node(){
    }
    public Node(int theElement){
        element = theElement;
    }
    public Node(int theElement, Node theleftChild, Node therightChild){
        element = theElement;
        left = theleftChild;
        right = theleftChild;
    }
}
