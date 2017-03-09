package ir;

import java.io.PrintStream;

public class Alloc extends Quadruple{
    Address dest;
    Address size;

    public Alloc(){
        dest = null;
        size = null;
    }

    public Alloc(Address dest, Address size){
        this.dest = dest;
        this.size = size;
    }
    public void out(PrintStream x){
        x.print("Alloc ");
        dest.out(x);
        size.out(x);
    };

    @Override public String generate(){
        Register.backup(0);
        Register.address[0] = -2;
        Register.backup(2);
        Register.address[2] = -2;
        String s1 = dest.generate();
        String s2 = size.generate();
        Mips.s += "move\t$a0,\t" + s2 + "\n";
        Mips.s += "jal\tnew\n";
        Mips.s += "div\t$a0,\t$a0,\t4\n";
        Mips.s += "sub\t$a0,\t$a0,\t1\n";
        Mips.s += "sw\t$a0,\t0($v0)\n";
        Mips.s += "move\t" + s1 + ",\t$v0\n";
        Register.change(s1);
        return null;
    }
}
