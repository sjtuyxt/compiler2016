import ir.*;

import java.util.ArrayList;

public class BinaryExpr extends Expr {
    public Expr left;
    public BinaryOp op;
    public Expr right;

    public BinaryExpr() {
        left = null;
        op = null;
        right = null;
    }

    public BinaryExpr(Expr left, BinaryOp op, Expr right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override public boolean Round2(){
        if (!left.Round2())
            return false;
        if (!right.Round2())
            return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        Type L = (Type)left.Round3(ReturnType, InLoop);
        Type R = (Type)right.Round3(ReturnType, InLoop);
        if (!L.equal(R))
            System.exit(1);
        if (op.equals(BinaryOp.ASSIGN) && !left.IsLeft())
            System.exit(1);
        else if (!(op.equals(BinaryOp.NE)) && !(op.equals(BinaryOp.ASSIGN)) && !(op.equals(BinaryOp.EQ)) && R instanceof NullType)
            System.exit(1);
        else if ((op.equals(BinaryOp.LOGICAL_AND) || op.equals(BinaryOp.LOGICAL_OR)) && !(L instanceof BoolType))
            System.exit(1);
        else if ((op.equals(BinaryOp.AND) || op.equals(BinaryOp.OR)|| op.equals(BinaryOp.SHL)|| op.equals(BinaryOp.SHR)|| op.equals(BinaryOp.XOR)) && !(L instanceof IntType))
            System.exit(1);
        else if ((op.equals(BinaryOp.ADD) || op.equals(BinaryOp.GE)|| op.equals(BinaryOp.GT)|| op.equals(BinaryOp.LT)|| op.equals(BinaryOp.LE)) && !(L instanceof IntType) && !(L instanceof  StringType))
            System.exit(1);
        else if ((op.equals(BinaryOp.SUB) || op.equals(BinaryOp.DIV)|| op.equals(BinaryOp.MOD)|| op.equals(BinaryOp.MUL)) && !(L instanceof IntType))
            System.exit(1);
        if (op.equals(BinaryOp.EQ) ||op.equals(BinaryOp.NE) ||op.equals(BinaryOp.GE) ||op.equals(BinaryOp.GT) ||op.equals(BinaryOp.LE) ||op.equals(BinaryOp.LT))
            return new BoolType();
        else
            return L;
    }

    @Override public Address getValue(ArrayList<Quadruple> list){
        Address dest = new Temp();
        Address R;
        if (op.equals(BinaryOp.LOGICAL_AND)){
            Label label1 = new Label();
            Label label2 = new Label();
            Address L = left.getValue(list);
            list.add(new Branch(L, RelationalOp.NE, new IntegerConst(0), label1));
            list.add(new Write(L, dest, new IntegerConst(-1)));
            list.add(new Goto(label2));
            list.add(label1);
            R = right.getValue(list);
            list.add(new ArithmeticExpr(dest, L, ArithmeticOp.AND, R));
            list.add(label2);
            return dest;
        }
        if (op.equals(BinaryOp.LOGICAL_OR)){
            Label label1 = new Label();
            Label label2 = new Label();
            Address L = left.getValue(list);
            list.add(new Branch(L, RelationalOp.EQ, new IntegerConst(0), label1));
            list.add(new Write(L, dest, new IntegerConst(-1)));
            list.add(new Goto(label2));
            list.add(label1);
            R = right.getValue(list);
            list.add(new ArithmeticExpr(dest, L, ArithmeticOp.OR, R));
            list.add(label2);
            return dest;
        }
        R = right.getValue(list);
        if (op.equals(BinaryOp.ASSIGN)) {
            left.putValue(list, R);
            return R;
        }
        else {
            Address L = left.getValue(list);
            if (L instanceof IntegerConst && R instanceof IntegerConst) {
                if (op.equals(BinaryOp.OR) || op.equals(BinaryOp.LOGICAL_OR))
                    ((IntegerConst) L).value = ((IntegerConst) L).value | ((IntegerConst) R).value;
                if (op.equals(BinaryOp.AND) || op.equals(BinaryOp.LOGICAL_AND))
                    ((IntegerConst) L).value = ((IntegerConst) L).value & ((IntegerConst) R).value;
                if (op.equals(BinaryOp.XOR))
                    ((IntegerConst) L).value = ((IntegerConst) L).value ^ ((IntegerConst) R).value;
                if (op.equals(BinaryOp.SHL))
                    ((IntegerConst) L).value = ((IntegerConst) L).value << ((IntegerConst) R).value;
                if (op.equals(BinaryOp.SHR))
                    ((IntegerConst) L).value = ((IntegerConst) L).value >> ((IntegerConst) R).value;
                if (op.equals(BinaryOp.ADD))
                    ((IntegerConst) L).value = ((IntegerConst) L).value + ((IntegerConst) R).value;
                if (op.equals(BinaryOp.SUB))
                    ((IntegerConst) L).value = ((IntegerConst) L).value - ((IntegerConst) R).value;
                if (op.equals(BinaryOp.MUL))
                    ((IntegerConst) L).value = ((IntegerConst) L).value * ((IntegerConst) R).value;
                if (op.equals(BinaryOp.DIV))
                    ((IntegerConst) L).value = ((IntegerConst) L).value / ((IntegerConst) R).value;
                if (op.equals(BinaryOp.MOD))
                    ((IntegerConst) L).value = ((IntegerConst) L).value % ((IntegerConst) R).value;
                if (op.equals(BinaryOp.GE)) {
                    if (((IntegerConst) L).value >= ((IntegerConst) R).value)
                        ((IntegerConst) L).value = ~0;
                    else
                        ((IntegerConst) L).value = 0;
                }
                if (op.equals(BinaryOp.EQ)) {
                    if (((IntegerConst) L).value == ((IntegerConst) R).value)
                        ((IntegerConst) L).value = ~0;
                    else
                        ((IntegerConst) L).value = 0;
                }
                if (op.equals(BinaryOp.GT)) {
                    if (((IntegerConst) L).value > ((IntegerConst) R).value)
                        ((IntegerConst) L).value = ~0;
                    else
                        ((IntegerConst) L).value = 0;
                }
                if (op.equals(BinaryOp.LE)) {
                    if (((IntegerConst) L).value <= ((IntegerConst) R).value)
                        ((IntegerConst) L).value = ~0;
                    else
                        ((IntegerConst) L).value = 0;
                }
                if (op.equals(BinaryOp.LT)) {
                    if (((IntegerConst) L).value < ((IntegerConst) R).value)
                        ((IntegerConst) L).value = ~0;
                    else
                        ((IntegerConst) L).value = 0;
                }
                if (op.equals(BinaryOp.NE)) {
                    if (((IntegerConst) L).value != ((IntegerConst) R).value)
                        ((IntegerConst) L).value = ~0;
                    else
                        ((IntegerConst) L).value = 0;
                }
                return L;
            } else if (left.Round3(null, false) instanceof StringType){
                ArrayList<Address> param = new ArrayList<>();
                if (op.equals(BinaryOp.ADD)){
                    param.add(L);
                    param.add(R);
                    list.add(new Call(dest, "stringAdd", param));
                }
                if (op.equals(BinaryOp.EQ)){
                    param.add(L);
                    param.add(R);
                    list.add(new Call(dest, "stringEquals", param));
                }
                if (op.equals(BinaryOp.LT)){
                    param.add(L);
                    param.add(R);
                    list.add(new Call(dest, "stringLessThan", param));
                }
                if (op.equals(BinaryOp.LE)){
                    param.add(L);
                    param.add(R);
                    list.add(new Call(dest, "stringLessThanOrEquals", param));
                }
                if (op.equals(BinaryOp.NE)){
                    param.add(L);
                    param.add(R);
                    list.add(new Call(dest, "stringEquals", param));
                    list.add(new ArithmeticExpr(dest, ArithmeticOp.MINUS, dest));
                }
                if (op.equals(BinaryOp.GT)){
                    param.add(R);
                    param.add(L);
                    list.add(new Call(dest, "stringLessThan", param));
                }
                if (op.equals(BinaryOp.GE)){
                    param.add(R);
                    param.add(L);
                    list.add(new Call(dest, "stringLessThanOrEquals", param));
                }
            } else {
                if (op.equals(BinaryOp.OR) || op.equals(BinaryOp.LOGICAL_OR))
                    list.add(new ArithmeticExpr(dest, L, ArithmeticOp.OR, R));
                if (op.equals(BinaryOp.AND) || op.equals(BinaryOp.LOGICAL_AND))
                    list.add(new ArithmeticExpr(dest, L, ArithmeticOp.AND, R));
                if (op.equals(BinaryOp.XOR)) list.add(new ArithmeticExpr(dest, L, ArithmeticOp.XOR, R));
                if (op.equals(BinaryOp.SHL)) list.add(new ArithmeticExpr(dest, L, ArithmeticOp.SHL, R));
                if (op.equals(BinaryOp.SHR)) list.add(new ArithmeticExpr(dest, L, ArithmeticOp.SHR, R));
                if (op.equals(BinaryOp.ADD)) list.add(new ArithmeticExpr(dest, L, ArithmeticOp.ADD, R));
                if (op.equals(BinaryOp.SUB)) list.add(new ArithmeticExpr(dest, L, ArithmeticOp.SUB, R));
                if (op.equals(BinaryOp.MUL)) list.add(new ArithmeticExpr(dest, L, ArithmeticOp.MUL, R));
                if (op.equals(BinaryOp.DIV)) list.add(new ArithmeticExpr(dest, L, ArithmeticOp.DIV, R));
                if (op.equals(BinaryOp.MOD)) list.add(new ArithmeticExpr(dest, L, ArithmeticOp.MOD, R));
                if (op.equals(BinaryOp.GT)) list.add(new RelationalExpr(dest, L, RelationalOp.GT, R));
                if (op.equals(BinaryOp.GE)) list.add(new RelationalExpr(dest, L, RelationalOp.GE, R));
                if (op.equals(BinaryOp.LT)) list.add(new RelationalExpr(dest, L, RelationalOp.LT, R));
                if (op.equals(BinaryOp.LE)) list.add(new RelationalExpr(dest, L, RelationalOp.LE, R));
                if (op.equals(BinaryOp.EQ)) list.add(new RelationalExpr(dest, L, RelationalOp.EQ, R));
                if (op.equals(BinaryOp.NE)) list.add(new RelationalExpr(dest, L, RelationalOp.NE, R));
            }
        }
        return dest;
    }
}
