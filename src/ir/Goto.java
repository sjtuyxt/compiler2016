package ir;

import java.io.PrintStream;

public class Goto extends Quadruple {
    public Label label;

    public Goto() {
        label = null;
    }

    public Goto(Label label) {
        this.label = label;
    }

    public void out(PrintStream x){
        x.print("Goto ");
        label.out(x);
    };

    @Override public String generate(){
        Register.clearall();
        Mips.s += "j\tlabel" + label.num + "\n";
        return null;
    }
}
