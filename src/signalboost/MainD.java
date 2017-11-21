package signalboost;
import java.util.Scanner;
public class MainD {
    public static void main(String [] args){
        SignalBoost x = new SignalBoost();
        Scanner in = new Scanner(System.in);
        System.out.println("Masukkan jumlah data : ");
        int y = in.nextInt();
        for(int i = 0; i < y; i++){
            System.out.println("Data ke " + (i+1) + " = ");
            x.insert(in.nextInt());
            x.place(x.root);
        }
        x.toString();
    }
}
