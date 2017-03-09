import ir.*;

import java.util.ArrayList;

public class WhileLoop extends Stmt {
    public Expr condition;
    public Stmt body;

    public WhileLoop() {
        condition = null;
        body = null;
    }

    public WhileLoop(Expr condition, Stmt body) {
        this.condition = condition;
        this.body = body;
    }

    @Override public boolean Round2(){
        SymbolTable.beginscope();
        if (!condition.Round2())
            return false;
        if (!body.Round2())
            return false;
        SymbolTable.endscope();
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        SymbolTable.beginscope();
        if (condition != null && !(((Type)condition.Round3(ReturnType, InLoop)) instanceof BoolType))
            System.exit(1);
        body.Round3(ReturnType, true);
        SymbolTable.endscope();
        return null;
    }

    @Override public void Translate(ArrayList<Quadruple> list, Label begin, Label end) {
        SymbolTable.beginscope();
        Label label1 = new Label();
        Label label2 = new Label();
        list.add(label1);
        Address cond;
        if (condition == null)
            cond = new IntegerConst(~0);
        else
            cond = condition.getValue(list);
        if (cond instanceof IntegerConst){
            if (((IntegerConst) cond).value == 0)
                list.add(new Goto(label2));
        }
        else {
            if (list.get(list.size() - 1) instanceof  RelationalExpr) {
                RelationalExpr tmp = (RelationalExpr)list.get(list.size() - 1);
                list.remove(list.size() - 1);
                list.add(new Branch(tmp.src1, RelationalOp.Reverse(tmp.op), tmp.src2, label2));
            }
            else
                list.add(new Branch(cond, RelationalOp.EQ, new IntegerConst(0), label2));
        }
        body.Translate(list, label1, label2);
        list.add(new Goto(label1));
        list.add(label2);
        SymbolTable.endscope();
    }
}
