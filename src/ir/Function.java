package ir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class Function extends Quadruple{
    public String name;
    public ArrayList<Address> args;
    public ArrayList<Quadruple> body;
    public int tempCount;
    public static ArrayList<Function> funclist = new ArrayList<>();

    public Function() {
        name = null;
        args = new ArrayList<>();
        body = new ArrayList<>();
    }

    public Function(String name, ArrayList<Address> args, ArrayList<Quadruple> body) {
        this.name = name;
        this.args = args;
        this.body = body;
        for (int i = 0; i < body.size(); i++)
            if (body.get(i) instanceof Return)
                ((Return) body.get(i)).name = name;
        funclist.add(this);
    }

    @Override public String generate(){
        if (name.equals("main") || name.equals("move"))
            Mips.s += "func";
        Mips.s += name + ":\n";
        Mips.s += "sw\t$ra,\t" + (4*tempCount+4) + "($sp)\n";
        for (int i = 0; i < body.size(); i++)
            body.get(i).generate();
        for (int i = 0; i < 24; i++)
            if (Register.address[i] >= 10000)
            Register.changed[i] = false;
        Register.clearall();
        Mips.s += "lw\t$ra,\t" + (4*tempCount+4) + "($sp)\n";
        Mips.s += "jr\t$ra,\n";
        return null;
    }
}
