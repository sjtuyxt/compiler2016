package ir;

import java.io.PrintStream;

public class Read extends Quadruple {
    public Address dest;
    public Address src;
    public IntegerConst offset;

    public Read() {
        dest = null;
        offset = null;
        src = null;
    }

    public Read(Address dest, Address src, IntegerConst offset) {
        this.dest = dest;
        this.offset = offset;
        this.src = src;
    }

    public void out(PrintStream x){
        x.print("Read ");
        dest.out(x);
        src.out(x);
        offset.out(x);
    };

    @Override public String generate(){
        String s1 = dest.generate();
        String s2 = src.generate();
        Mips.s += "lw\t" + s1 + ",\t" + offset.value + "(" + s2 + ")\n";
        Register.change(s1);
        return null;
    }
}
