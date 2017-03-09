import ir.*;

import java.util.ArrayList;

public class ClassAccess extends Expr {
    public Expr body;
    public Symbol attribute;

    public ClassAccess() {
        body = null;
        attribute = null;
    }

    public ClassAccess(Expr body, Symbol attribute) {
        this.body = body;
        this.attribute = attribute;
    }

    @Override public boolean Round2(){
        if (!body.Round2())
            return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        ClassDecl c = (ClassDecl)SymbolTable.get(((ClassType)body.Round3(ReturnType, InLoop)).tag);
        for (int i = 0; i < c.fields.size(); i++)
            if (c.fields.get(i).name == attribute) {
                return c.fields.get(i).type;
            }
        System.exit(1);
        return null;
    }

    @Override public boolean IsLeft(){
        return body.IsLeft();
    }

    @Override public Address getValue(ArrayList<Quadruple> list) {
        Address dest = new Temp();
        Address tmp1 = body.getValue(list);
        ClassDecl c = (ClassDecl)SymbolTable.get(((ClassType)body.Round3(null, false)).tag);
        for (int i = 0; i < c.fields.size(); i++)
            if (c.fields.get(i).name == attribute) {
                list.add(new Read(dest, tmp1, new IntegerConst(4 * i)));
                break;
            }
        return dest;
    }

    @Override public void putValue(ArrayList<Quadruple> list, Address src) {
        Address tmp1 = body.getValue(list);
        ClassDecl c = (ClassDecl)SymbolTable.get(((ClassType)body.Round3(null, false)).tag);
        for (int i = 0; i < c.fields.size(); i++)
            if (c.fields.get(i).name == attribute) {
                list.add(new Write(src, tmp1, new IntegerConst(4 * i)));
                break;
            }
    }
}
