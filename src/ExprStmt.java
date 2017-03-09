import ir.Goto;
import ir.Label;
import ir.Quadruple;

import java.util.ArrayList;

public class ExprStmt extends Stmt {
    public Expr expr;

    public ExprStmt() {
        expr = null;
    }

    public ExprStmt(Expr expr) {
        this.expr = expr;
    }

    @Override public boolean Round2(){
        if (!expr.Round2())
            return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        expr.Round3(ReturnType, InLoop);
        return null;
    }

    @Override public void Translate(ArrayList<Quadruple> list, Label begin, Label end) {
        expr.getValue(list);
    }
}