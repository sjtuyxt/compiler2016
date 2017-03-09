import ir.Address;
import ir.Temp;

public class Buckets {
    Buckets next;
    Address src;
    AST object;


    public Buckets(Buckets next, AST object){
        this.next = next;
        this.object = object;
    }

    public Buckets(Buckets next, AST object,Address src){
        this.next = next;
        this.object = object;
        this.src = src;
    }
}
