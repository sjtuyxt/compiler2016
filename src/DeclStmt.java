import ir.*;

import java.util.ArrayList;

public class DeclStmt extends Stmt {
    public Type type;
    public Symbol name;
    public Expr init;

    public DeclStmt() {
        type = null;
        name = null;
        init = null;
    }

    public DeclStmt(Type type, Symbol name, Expr init) {
        this.type = type;
        this.name = name;
        this.init = init;
    }

    @Override public boolean Round2(){
        if (type instanceof VoidType)
            return false;
        if (!type.Round2())
            return false;
        SymbolTable.put(name, this);
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        if (init != null && !type.equal((Type)init.Round3(ReturnType, InLoop)))
            System.exit(1);
        SymbolTable.put(name, this);
        return null;
    }

    @Override public void Translate(ArrayList<Quadruple> list, Label begin, Label end){
        Address dest = new Temp();
        SymbolTable.put(name, this, dest);
        if (init == null) return;
        Address src = init.getValue(list);
        list.add(new Write(src, dest, new IntegerConst(-1)));
    };
}
