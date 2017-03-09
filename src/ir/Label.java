package ir;

import java.io.PrintStream;

public class Label extends Quadruple {
    private static int labelCount = 0;

    public int num;

    public Label() {
        num = labelCount++;
    }

    public void out(PrintStream x){
        x.print("Label");
        x.print(num);
        x.print(" ");
    };

    @Override public String generate(){
        Register.clearall();
        Mips.s += "label" + num + ":\n";
        return null;
    }
}
