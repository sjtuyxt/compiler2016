import ir.*;

import java.util.ArrayList;

public class StringConst extends Expr {
    public String value;

    public StringConst() {
        value = null;
    }

    public StringConst(String value) {
        this.value = value;
    }

    @Override public AST Round3(Type ReturnType, boolean InLoop){
        return new StringType();
    }

    @Override public Address getValue(ArrayList<Quadruple> list) {
        Address dest = new Temp();
        list.add(new StringAddressConst(dest, value));
        return dest;
    }
}
