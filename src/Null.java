import ir.Address;
import ir.IntegerConst;
import ir.Quadruple;

import java.util.ArrayList;

public class Null extends Expr{

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        return new NullType();
    }

    @Override public Address getValue(ArrayList<Quadruple> list) {
        return new IntegerConst(0);
    }
}
