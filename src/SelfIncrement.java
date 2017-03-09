import ir.*;

import java.util.ArrayList;

public class SelfIncrement extends Expr {
    public Expr body;

    public SelfIncrement() {
        body = null;
    }

    public SelfIncrement(Expr body) {
        this.body = body;
    }

    @Override public boolean Round2(){
        if (!body.Round2())
            return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        if (!((Type)body.Round3(ReturnType, InLoop) instanceof IntType))
            System.exit(1);
        return new IntType();
    }

    @Override public Address getValue(ArrayList<Quadruple> list){
        Address src = body.getValue(list);
        Address dest = new Temp();
        list.add(new Write(src, dest, new IntegerConst(-1)));
        list.add(new ArithmeticExpr(src, src, ArithmeticOp.ADD, new IntegerConst(1)));
        body.putValue(list, src);
        return dest;
    }
}
