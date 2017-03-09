package ir;

import java.io.PrintStream;

public class IntegerConst extends Const {
    public int value;

    public IntegerConst() {
    }

    public IntegerConst(int value) {
        this.value = value;
    }

    public void out(PrintStream x){
        x.print("Int");
        x.print(value);
        x.print(" ");
    };

    @Override public String generate(){
        String s = "$ra";
        Mips.s += "li\t" + s + ",\t" + value + "\n";
        return s;
    }
}
