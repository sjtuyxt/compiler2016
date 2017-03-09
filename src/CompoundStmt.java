

import ir.Function;
import ir.Label;
import ir.Quadruple;
import ir.Temp;

import java.util.ArrayList;
import java.util.List;

public class CompoundStmt extends Stmt {
    public List<Stmt> stats;

    public CompoundStmt() {
        stats = null;
    }

    public CompoundStmt(List<Stmt> stats) {
        this.stats = stats;
    }

    @Override public boolean Round2(){
        for (int i = 0; i < stats.size(); i++)
            if (!stats.get(i).Round2())
                return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        for (int i = 0; i < stats.size(); i++)
            stats.get(i).Round3(ReturnType, InLoop);
        return null;
    }

    @Override public void Translate(ArrayList<Quadruple> list, Label begin, Label end) {
        for (int i = 0; i < stats.size(); i++)
            stats.get(i).Translate(list, begin, end);
    }
}
