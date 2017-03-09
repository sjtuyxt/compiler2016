import ir.Address;
import ir.Temp;

public class SymbolTable {
    private static java.util.Map<Symbol, Buckets> dict = new java.util.HashMap<>();
    private static java.util.Stack<java.util.ArrayList<Symbol> > stack = new java.util.Stack<>();


    public static AST get(Symbol t) {
        Buckets ret = dict.get(t);
        if (ret != null)
            return ret.object;
        else
            return null;
    }

    public static Address getAddress(Symbol t) {
        Buckets ret = dict.get(t);
        if (ret != null)
            return ret.src;
        else
            return null;
    }

    public static void put(Symbol t, AST ret) {
        Buckets tmp = dict.get(t);
        java.util.ArrayList<Symbol> s = stack.pop();
        if (s.contains(t))
            System.exit(1);
        s.add(t);
        stack.push(s);
        dict.put(t, new Buckets(tmp, ret));
    }

    public static void put(Symbol t, AST ret, Address temp) {
        Buckets tmp = dict.get(t);
        java.util.ArrayList<Symbol> s = stack.pop();
        if (s.contains(t))
            System.exit(1);
        s.add(t);
        stack.push(s);
        dict.put(t, new Buckets(tmp, ret, temp));
    }



    public static void pop(Symbol t){
        Buckets tmp = dict.get(t);
        dict.put(t, tmp.next);
    }

    public static void beginscope(){
        stack.push(new java.util.ArrayList<>());
    }

    public static void endscope(){
        java.util.ArrayList<Symbol> s = stack.pop();
        for (int i = 0; i < s.size(); i++)
            pop(s.get(i));
    }
}
