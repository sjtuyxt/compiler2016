import ir.*;

import java.util.ArrayList;

public class ForLoop extends Stmt {
    public Expr init;
    public Expr condition;
    public Expr step;
    public Stmt body;

    public ForLoop() {
        init = null;
        condition = null;
        step = null;
        body = null;
    }

    public ForLoop(Expr init, Expr condition, Expr step, Stmt body) {
        this.init = init;
        this.condition = condition;
        this.step = step;
        this.body = body;
    }

    @Override public boolean Round2(){
        SymbolTable.beginscope();
        if (init != null && !init.Round2())
            return false;
        if (condition != null && !condition.Round2())
            return false;
        if (step != null && !step.Round2())
            return false;
        if (!body.Round2())
            return false;
        SymbolTable.endscope();
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        SymbolTable.beginscope();
        if (init !=null)
            init.Round3(ReturnType, InLoop);
        if (condition != null && !(((Type)condition.Round3(ReturnType, InLoop)) instanceof BoolType))
            System.exit(1);
        if (step !=null)
            step.Round3(ReturnType, InLoop);
        body.Round3(ReturnType, InLoop||true);
        SymbolTable.endscope();
        return null;
    }

    @Override public void Translate(ArrayList<Quadruple> list, Label begin, Label end) {
        if (init != null)
            init.getValue(list);
        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        list.add(label1);
        Address cond;
        if (condition == null)
            cond = new IntegerConst(~0);
        else
            cond = condition.getValue(list);
        if (cond instanceof IntegerConst){
            if (((IntegerConst) cond).value == 0)
                list.add(new Goto(label3));
        }
        else {
            if (list.get(list.size() - 1) instanceof  RelationalExpr ) {
                RelationalExpr tmp = (RelationalExpr)list.get(list.size() - 1);
                list.remove(list.size() - 1);
                list.add(new Branch(tmp.src1, RelationalOp.Reverse(tmp.op), tmp.src2, label3));
            }
            else
                list.add(new Branch(cond, RelationalOp.EQ, new IntegerConst(0), label3));
        }
        SymbolTable.beginscope();
        if (body != null)
            body.Translate(list, label2, label3);
        SymbolTable.endscope();
        list.add(label2);
        if (step != null)
            step.getValue(list);
        list.add(new Goto(label1));
        list.add(label3);
    }
}
