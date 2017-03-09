

import ir.*;

import java.util.ArrayList;
import java.util.List;

public class FunctionDecl extends Decl {
    public Type returnType;
    public Symbol name;
    public List<VarDecl> params;
    public CompoundStmt body;

    public FunctionDecl() {
        returnType = null;
        name = null;
        params = null;
        body = null;
    }

    public FunctionDecl(Type returnType, Symbol name, List<VarDecl> params, CompoundStmt body) {
        this.returnType = returnType;
        this.name = name;
        this.params = params;
        this.body = body;
    }

    @Override public boolean Round2(){
        SymbolTable.beginscope();
        if (!returnType.Round2())
            return false;
        for (int i = 0; i < params.size(); i++) {
            if (!params.get(i).Round2())
                return false;
            else
                SymbolTable.put(params.get(i).name, params.get(i));
        }
        if (!body.Round2())
            return false;
        SymbolTable.endscope();
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        SymbolTable.beginscope();
        for (int i = 0; i < params.size(); i++)
            SymbolTable.put(params.get(i).name, params.get(i));
        body.Round3(returnType, InLoop);
        SymbolTable.endscope();
        return null;
    }

    @Override public void Translate(ArrayList<Quadruple> list, Label begin, Label end) {
        Function tmp = new Function();
        tmp.name = name.toString();
        tmp.funclist.add(tmp);
        SymbolTable.beginscope();
        for (int i = 0; i < params.size(); i++) {
            Address tmparg = new Temp();
            ((Temp)tmparg).first = false;
            SymbolTable.put(params.get(i).name, params.get(i), tmparg);
            tmp.args.add(tmparg);
        }
        body.Translate(tmp.body, begin, end);
        for (int i = 0; i < tmp.body.size(); i++)
            if (tmp.body.get(i) instanceof Return)
                ((Return) tmp.body.get(i)).name = tmp.name;
        tmp.tempCount = Temp.tempCount - 10000;
        SymbolTable.endscope();
        list.add(tmp);
    }
}
