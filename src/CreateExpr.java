import ir.*;

import java.util.ArrayList;

public class CreateExpr extends Expr{
    public Type type;

    public CreateExpr(Type type){
        this.type=type;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        return type;
    }

    @Override public Address getValue(ArrayList<Quadruple> list){
        Address dest = new Temp();
        Address tmp;
        if (type instanceof ArrayType)
            tmp = ((ArrayType) type).arraySize.getValue(list);
        else {
            tmp = new IntegerConst(((ClassDecl) SymbolTable.get(((ClassType) type).tag)).fields.size());
        }
        if (tmp instanceof IntegerConst)
            list.add(new Alloc(dest, new IntegerConst(((IntegerConst) tmp).value*4+4)));
        else {
            Address tmp2 = new Temp();
            list.add(new ArithmeticExpr(tmp2, tmp, ArithmeticOp.MUL, new IntegerConst(4)));
            list.add(new ArithmeticExpr(tmp2, tmp2, ArithmeticOp.ADD, new IntegerConst(4)));
            list.add(new Alloc(dest, tmp2));
        }
        return dest;
    }
}
