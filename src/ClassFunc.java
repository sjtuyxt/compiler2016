import ir.*;

import java.util.ArrayList;
import java.util.List;

public class ClassFunc extends Expr {
    public Expr pro;
    public Symbol body;
    public List<Expr> args;

    public ClassFunc() {
        pro = null;
        body = null;
        args = null;
    }

    public ClassFunc(Expr pro, Symbol body, List<Expr> args) {
        this.pro = pro;
        this.body = body;
        this.args = args;
    }

    @Override public boolean Round2(){
        if (!pro.Round2())
            return false;
        for (int i = 0; i < args.size(); i++)
            if (!args.get(i).Round2())
                return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        Type type = (Type)pro.Round3(ReturnType, InLoop);
        if (type instanceof ArrayType && args.isEmpty() && body == Symbol.get("size"))
            return new IntType();
        else if (type instanceof StringType && args.isEmpty() && body == Symbol.get("length"))
            return new IntType();
        else if (type instanceof StringType && args.size()==2 && (Type)args.get(0).Round3(ReturnType, InLoop) instanceof IntType&& (Type)args.get(1).Round3(ReturnType, InLoop) instanceof IntType && body == Symbol.get("substring"))
            return new StringType();
        else if (type instanceof StringType && args.isEmpty() && body == Symbol.get("parseInt"))
            return new IntType();
        else if (type instanceof StringType && args.size()==1 && (Type)args.get(0).Round3(ReturnType, InLoop) instanceof IntType&& body == Symbol.get("ord"))
            return new IntType();
        return null;
    }

    @Override public Address getValue(ArrayList<Quadruple> list){
        Temp dest = new Temp();
        ArrayList<Address> param = new ArrayList<>();
        param.add(pro.getValue(list));
        for (int i = 0; i < args.size(); i++)
            param.add(args.get(i).getValue(list));
        list.add(new Call(dest, body.toString(), param));
        return dest;
    }
}
