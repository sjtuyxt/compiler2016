package ir;

import com.sun.org.apache.xerces.internal.util.SymbolTable;
import sun.awt.Symbol;

import java.io.PrintStream;
import java.util.ArrayList;

public class Call extends Quadruple {
    public Address dest;
    public String callee;
    public ArrayList<Address> param;

    public Call(){
        dest = null;
        callee = null;
        param = null;
    }

    public Call(Address dest, String callee, ArrayList<Address> param){
        this.dest = dest;
        this.callee = callee;
        this.param = param;
    }

    public void out(PrintStream x){
        dest.out(x);
        x.print("Call "+callee+' ');
        for (int i = 0; i < param.size(); i++)
            param.get(i).out(x);
    };

    @Override public String generate(){
        Function func = null;
        for (int i = 0; i < Function.funclist.size(); i++)
            if (Function.funclist.get(i).name.equals(callee))
                func = Function.funclist.get(i);
        if (callee.equals("main") || callee.equals("move"))
            callee = "func" + callee;
        if (func != null){
            ArrayList<String> s = new ArrayList<>();
            for (int i = 0; i < param.size(); i++) {
                if (param.get(i) instanceof IntegerConst) {
                    s.add(null);
                }
                else
                    s.add(param.get(i).generate());
            }
            Register.clearall();
            Mips.s += "sub\t$sp,\t" + (4*func.tempCount+8) + "\n";
            for (int i = 0; i < s.size(); i++) {
                if (s.get(i) == null){
                    Mips.s += "li\t$ra\t" + ((IntegerConst) param.get(i)).value + "\n";
                    Mips.s += "sw\t$ra,\t" + (i * 4) + "($sp)\n";
                }
                else
                    Mips.s += "sw\t" + s.get(i) + ",\t" + (i * 4) + "($sp)\n";
            }
            Mips.s += "jal\t" + callee + "\n";
            Mips.s += "add\t$sp,\t" + (4*func.tempCount+8) + "\n";
            String tmp = dest.generate();
            Mips.s += "move\t" + tmp + ",\t$v0\n";
            Register.change(tmp);
        }
        else{
            Register.clear();
            String s;
            for (int i = 0; i < param.size(); i++) {
                s = param.get(i).generate();
                Mips.s += "move\t" + Register.name[i + 2] + ",\t" + s + "\n";
            }
            Mips.s += "sub\t$sp,\t" + 128 + "\n";
            Mips.s += "jal\t" + callee + "\n";
            Mips.s += "add\t$sp,\t" + 128 + "\n";
            String tmp = dest.generate();
            Mips.s += "move\t" + tmp + ",\t$v0\n";
            Register.change(tmp);
        }
        return null;
    }
}
