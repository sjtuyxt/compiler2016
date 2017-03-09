import ir.Address;
import ir.IntegerConst;
import ir.Quadruple;

import java.util.ArrayList;

public class BoolConst extends Expr{
    Symbol bool;

    public BoolConst(Symbol bool){
        this.bool = bool;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        return new BoolType();
    }

    @Override public Address getValue(ArrayList<Quadruple> list) {
        if (bool == Symbol.get("true")) return new IntegerConst(~0);
        else return new IntegerConst(0);
    }
}
