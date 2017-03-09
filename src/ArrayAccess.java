import ir.*;

import java.util.ArrayList;

public class ArrayAccess extends Expr {
    public Expr body;
    public Expr subscript;

    public ArrayAccess() {
        body = null;
        subscript = null;
    }

    public ArrayAccess(Expr body, Expr subscript) {
        this.body = body;
        this.subscript = subscript;
    }

    @Override public boolean Round2(){
        if (!body.Round2())
            return false;
        if (!subscript.Round2())
            return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        Type tmp = (Type) body.Round3(ReturnType, InLoop);
        if (!(tmp instanceof ArrayType))
            System.exit(1);
        if (!(subscript.Round3(ReturnType, InLoop) instanceof IntType))
            System.exit(1);
        return ((ArrayType)tmp).baseType;
    }

    @Override public boolean IsLeft(){
        return body.IsLeft();
    }

    @Override public Address getValue(ArrayList<Quadruple> list) {
        Address dest = new Temp();
        Address tmp1 = body.getValue(list);
        Address tmp2 = subscript.getValue(list);
        if (tmp2 instanceof IntegerConst) {
            list.add(new Read(dest, tmp1, new IntegerConst(((IntegerConst) tmp2).value*4+4)));
        }
        else {
            Address tmp3 = new Temp();
            list.add(new ArithmeticExpr(tmp3, tmp2, ArithmeticOp.MUL, new IntegerConst(4)));
            list.add(new ArithmeticExpr(tmp3, tmp3, ArithmeticOp.ADD, tmp1));
            list.add(new Read(dest, tmp3, new IntegerConst(4)));
        }
        return dest;
    }

    @Override public void putValue(ArrayList<Quadruple> list, Address src) {
        Address tmp1 = body.getValue(list);
        Address tmp2 = subscript.getValue(list);
        if (tmp2 instanceof IntegerConst) {
            list.add(new Write(src, tmp1, new IntegerConst(((IntegerConst) tmp2).value*4+4)));
        }
        else {
            Address tmp3 = new Temp();
            list.add(new ArithmeticExpr(tmp3, tmp2, ArithmeticOp.MUL, new IntegerConst(4)));
            list.add(new ArithmeticExpr(tmp3, tmp3, ArithmeticOp.ADD, tmp1));
            list.add(new Write(src, tmp3, new IntegerConst(4)));
        }
    }
}
