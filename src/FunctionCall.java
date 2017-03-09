

import ir.Address;
import ir.Call;
import ir.Quadruple;
import ir.Temp;

import java.util.ArrayList;
import java.util.List;

public class FunctionCall extends Expr {
    public Symbol body;
    public List<Expr> args;

    public FunctionCall() {
        body = null;
        args = null;
    }

    public FunctionCall(Symbol body, List<Expr> args) {
        this.body = body;
        this.args = args;
    }

    @Override public boolean Round2(){
        if (SymbolTable.get(body) == null || !(SymbolTable.get(body) instanceof FunctionDecl))
            return false;
        for (int i = 0; i < args.size(); i++)
            if (!args.get(i).Round2())
                return false;
        return true;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        FunctionDecl f = (FunctionDecl) SymbolTable.get(body);
        if (f.params.size() != args.size())
            System.exit(1);
        for (int i = 0; i < f.params.size(); i++)
            if (!f.params.get(i).type.equal((Type)args.get(i).Round3(ReturnType, InLoop)))
                System.exit(1);
        return f.returnType;
    }

    @Override public Address getValue(ArrayList<Quadruple> list){
        Temp dest = new Temp();
        ArrayList<Address> param = new ArrayList<>();
        if (body == Symbol.get("print")){
            Expr tmp = args.get(0);
            while (tmp instanceof BinaryExpr){
                param = new ArrayList<>();
                param.add(((BinaryExpr) tmp).left.getValue(list));
                list.add(new Call(dest, "print", param));
                tmp = ((BinaryExpr) tmp).right;
            }
            param = new ArrayList<>();
            param.add(tmp.getValue(list));
            list.add(new Call(dest, "print", param));
        }
        else if (body == Symbol.get("println")){
            Expr tmp = args.get(0);
            while (tmp instanceof BinaryExpr){
                param = new ArrayList<>();
                param.add(((BinaryExpr) tmp).left.getValue(list));
                list.add(new Call(dest, "print", param));
                tmp = ((BinaryExpr) tmp).right;
            }
            param = new ArrayList<>();
            param.add(tmp.getValue(list));
            list.add(new Call(dest, "println", param));
        }
        else {
            for (int i = 0; i < args.size(); i++)
                param.add(args.get(i).getValue(list));
            list.add(new Call(dest, body.toString(), param));
        }
        return dest;
    }
}
