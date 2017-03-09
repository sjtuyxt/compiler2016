import ir.Address;
import ir.IntegerConst;
import ir.Quadruple;

import java.util.ArrayList;

public class IntConst extends Expr {
    public int value;

    public IntConst() {
        value = 0;
    }

    public IntConst(int value) {
        this.value = value;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        return new IntType();
    }

    @Override public Address getValue(ArrayList<Quadruple> list) {
        return new IntegerConst(value);
    }
}
