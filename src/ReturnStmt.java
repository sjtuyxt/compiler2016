import ir.*;

import java.util.ArrayList;

public class ReturnStmt extends Stmt {
    public Expr expr;

    public ReturnStmt() {
        expr = null;
    }

    public ReturnStmt(Expr expr) {
        this.expr = expr;
    }

    @Override public boolean Round2(){
        if (expr != null && !expr.Round2())
            return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        if (expr == null && !(ReturnType instanceof VoidType))
            System.exit(1);
        else if (expr != null && !ReturnType.equal((Type)expr.Round3(ReturnType, InLoop)))
            System.exit(1);

        return null;
    }

    @Override public void Translate(ArrayList<Quadruple> list, Label begin, Label end) {
        if (expr == null)
            list.add(new Return(new IntegerConst(0)));
        else
            list.add(new Return(expr.getValue(list)));
    }
}
