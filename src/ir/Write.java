package ir;

import java.io.PrintStream;

public class Write extends Quadruple {
    public Address src;
    public Address dest;
    public IntegerConst offset;

    public Write() {
        src = null;
        offset = null;
        dest = null;
    }

    public Write(Address src, Address dest, IntegerConst offset) {
        this.offset = offset;
        this.src = src;
        this.dest = dest;
    }

    public void out(PrintStream x){
        x.print("Write ");
        dest.out(x);
        src.out(x);
        offset.out(x);
    };

    @Override public String generate(){
        String s1 = src.generate();
        String s2 = dest.generate();
        if (offset.value != -1)
            Mips.s += "sw\t" + s1 + ",\t" + offset.value + "(" + s2 + ")\n";
        else
            Mips.s += "move\t" + s2 + ",\t" + s1 + "\n";
        Register.change(s2);
        return null;
    }
}
