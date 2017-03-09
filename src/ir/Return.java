package ir;

import java.io.PrintStream;

public class Return extends Quadruple {
    public Address value;
    public String name;

    public Return() {
        value = null;
    }

    public Return(Address value) {
        this.value = value;
    }

    public void out(PrintStream x){
        x.print("Return ");
        value.out(x);
    };

    @Override public String generate(){
        String s = value.generate();
        for (int i = 0; i < 24; i++)
            if (Register.address[i] >= 10000)
                Register.changed[i] = false;
        Register.clearall();
        Mips.s += "move\t$v0,\t" + s + "\n";
        Function func = null;
        for (int i = 0; i < Function.funclist.size(); i++)
            if (Function.funclist.get(i).name.equals(name))
                func = Function.funclist.get(i);
        Mips.s += "lw\t$ra,\t" + (4*func.tempCount+4) + "($sp)\n";
        Mips.s += "jr\t$ra,\n";
        return null;
    }
}
