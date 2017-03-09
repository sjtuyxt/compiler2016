import ir.*;

import java.util.ArrayList;

public class UnaryExpr extends Expr {
    public UnaryOp op;
    public Expr expr;

    public UnaryExpr() {
        op = null;
        expr = null;
    }

    public UnaryExpr(UnaryOp op, Expr expr) {
        this.op = op;
        this.expr = expr;
    }

    @Override public boolean Round2(){
        if (!expr.Round2())
            return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        if (!op.equals(UnaryOp.NOT)) {
            if ((Type)expr.Round3(ReturnType, InLoop) instanceof IntType)
                return new IntType();
            else
                System.exit(1);
        }
        else {
            if (!((Type)expr.Round3(ReturnType, InLoop) instanceof BoolType))
                System.exit(1);
            return new BoolType();
        }
        return null;
    }

    @Override public Address getValue(ArrayList<Quadruple> list){
        Address src = expr.getValue(list);
        Address dest = new Temp();
        if (src instanceof IntegerConst){
            if (op.equals(UnaryOp.MINUS)) ((IntegerConst) src).value= -((IntegerConst) src).value;
            if (op.equals(UnaryOp.TILDE) || op.equals(UnaryOp.NOT)) ((IntegerConst) src).value = ~((IntegerConst) src).value;
            if (op.equals(UnaryOp.INC)) ((IntegerConst) src).value = ((IntegerConst) src).value + 1;
            if (op.equals(UnaryOp.DEC)) ((IntegerConst) src).value = ((IntegerConst) src).value - 1;
            return src;
        }
        else {
            if (op.equals(UnaryOp.MINUS)) list.add(new ArithmeticExpr(dest, ArithmeticOp.MINUS, src));
            if (op.equals(UnaryOp.TILDE) || op.equals(UnaryOp.NOT))
                list.add(new ArithmeticExpr(dest, ArithmeticOp.TILDE, src));
            if (op.equals(UnaryOp.INC)){
                list.add(new ArithmeticExpr(src, src, ArithmeticOp.ADD, new IntegerConst(1)));
                list.add(new Write(src, dest, new IntegerConst(-1)));
                expr.putValue(list, dest);
            }
            if (op.equals(UnaryOp.DEC)){
                list.add(new ArithmeticExpr(src, src, ArithmeticOp.SUB, new IntegerConst(1)));
                list.add(new Write(src, dest, new IntegerConst(-1)));
                expr.putValue(list, dest);
            }
        }
        return dest;
    }
}
