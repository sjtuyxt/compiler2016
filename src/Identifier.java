import ir.*;

import java.util.ArrayList;

public class Identifier extends Expr {
    public Symbol symbol;

    public Identifier() {
        symbol = null;
    }

    public Identifier(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override public boolean Round2(){
        if (SymbolTable.get(symbol) == null)
            return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        AST x = SymbolTable.get(symbol);
        if (x instanceof DeclStmt)
            return ((DeclStmt) x).type;
        else if (x instanceof VarDecl)
            return ((VarDecl) x).type;
        else if (x instanceof FunctionDecl)
            return ((FunctionDecl) x).returnType;
        return null;
    }

    @Override public boolean IsLeft(){
        AST x = SymbolTable.get(symbol);
        if (x instanceof DeclStmt)
            return true;
        else if (x instanceof VarDecl)
            return true;
        return false;
    }

    @Override public Address getValue(ArrayList<Quadruple> list){
        return SymbolTable.getAddress(symbol);
    }

    @Override public void putValue(ArrayList<Quadruple> list, Address src) {
        list.add(new Write(src, SymbolTable.getAddress(symbol), new IntegerConst(-1)));
    }
}
