import ir.Goto;
import ir.Label;
import ir.Quadruple;

import java.util.ArrayList;

public class ContinueStmt extends Stmt {
    @Override public AST Round3(Type ReturnType, boolean InLoop){
        if (!InLoop)
            System.exit(1);
        return null;
    }

    @Override public void Translate(ArrayList<Quadruple> list, Label begin, Label end) {
        list.add(new Goto(begin));
    }
}
