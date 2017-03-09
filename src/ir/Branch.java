package ir;

import java.io.PrintStream;

public class Branch extends Quadruple{
    Address src1;
    RelationalOp op;
    Address src2;
    Label label;

    public Branch(Address src1, RelationalOp op, Address src2, Label label){
        this.src1 = src1;
        this.op = op;
        this. src2 = src2;
        this.label = label;
    }

    public void out(PrintStream x){
        x.print("Branch ");
        src1.out(x);
        x.print(op);
        x.print(' ');
        src2.out(x);
        label.out(x);
    };

    @Override public String generate(){
        String s1 = src1.generate();
        String s2 = src2.generate();
        Register.clearall();
        if (op.equals(RelationalOp.EQ)) Mips.s += "beq\t" + s1 + ",\t" +s2 + ",\tlabel" + label.num + "\n";
        if (op.equals(RelationalOp.NE)) Mips.s += "bne\t" + s1 + ",\t" +s2 + ",\tlabel" + label.num + "\n";
        if (op.equals(RelationalOp.GE)) Mips.s += "bge\t" + s1 + ",\t" +s2 + ",\tlabel" + label.num + "\n";
        if (op.equals(RelationalOp.GT)) Mips.s += "bgt\t" + s1 + ",\t" +s2 + ",\tlabel" + label.num + "\n";
        if (op.equals(RelationalOp.LE)) Mips.s += "ble\t" + s1 + ",\t" +s2 + ",\tlabel" + label.num + "\n";
        if (op.equals(RelationalOp.LT)) Mips.s += "blt\t" + s1 + ",\t" +s2 + ",\tlabel" + label.num + "\n";
        return null;
    }
}
