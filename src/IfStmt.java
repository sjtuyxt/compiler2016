import ir.*;

import java.util.ArrayList;

public class IfStmt extends Stmt {
    public Expr condition;
    public Stmt consequent;
    public Stmt alternative;

    public IfStmt(Expr condition, Stmt consequent, Stmt alternative) {
        this.condition = condition;
        this.consequent = consequent;
        this.alternative = alternative;
    }

    @Override public boolean Round2(){
        SymbolTable.beginscope();
        if (!condition.Round2())
            return false;
        if (!consequent.Round2())
            return false;
        SymbolTable.endscope();
        SymbolTable.beginscope();
        if (alternative != null && !alternative.Round2())
            return false;
        SymbolTable.endscope();
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        SymbolTable.beginscope();
        if (!(((Type)condition.Round3(ReturnType, InLoop)) instanceof BoolType))
            System.exit(1);
        consequent.Round3(ReturnType, InLoop);
        SymbolTable.endscope();
        SymbolTable.beginscope();
        if (alternative != null)
            alternative.Round3(ReturnType, InLoop);
        SymbolTable.endscope();
        return null;
    }

    @Override public void Translate(ArrayList<Quadruple> list, Label begin, Label end) {
        Label label1 = new Label();
        Label label2 = new Label();
        Address cond;
        if (condition == null)
            cond = new IntegerConst(~0);
        else
            cond = condition.getValue(list);
        if (cond instanceof IntegerConst){
            if (((IntegerConst) cond).value == 0)
                list.add(new Goto(label1));
        }
        else {
            if (list.get(list.size() - 1) instanceof  RelationalExpr) {
                RelationalExpr tmp = (RelationalExpr)list.get(list.size() - 1);
                list.remove(list.size() - 1);
                list.add(new Branch(tmp.src1, RelationalOp.Reverse(tmp.op), tmp.src2, label1));
            }
            else
                list.add(new Branch(cond, RelationalOp.EQ, new IntegerConst(0), label1));
        }
        SymbolTable.beginscope();
        consequent.Translate(list, begin, end);
        SymbolTable.endscope();
        list.add(new Goto(label2));
        list.add(label1);
        SymbolTable.beginscope();
        if (alternative != null)
            alternative.Translate(list, begin, end);
        SymbolTable.endscope();
        list.add(label2);
    }
}
