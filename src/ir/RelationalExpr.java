package ir;

import java.io.PrintStream;

public class RelationalExpr extends Quadruple {
    public RelationalOp op;
    public Address dest;
    public Address src1;
    public Address src2;

    public RelationalExpr() {
        op = null;
        dest = null;
        src1 = null;
        src2 = null;
    }

    public RelationalExpr(Address dest,  Address src1, RelationalOp op, Address src2) {
        this.dest = dest;
        this.op = op;
        this.src1 = src1;
        this.src2 = src2;
    }

    public void out(PrintStream x){
        x.print("RelationalExpr ");
        dest.out(x);
        src1.out(x);
        x.print(op);
        x.print(' ');
        src2.out(x);
    };

    @Override public String generate(){
        String s1 = dest.generate();
        String s2 = src1.generate();
        String s3 = src2.generate();
        if (op.equals(RelationalOp.EQ)) Mips.s += "seq\t" + s1 + ",\t" +s2 + ",\t" + s3 + "\n";
        if (op.equals(RelationalOp.NE)) Mips.s += "sne\t" + s1 + ",\t" +s2 + ",\t" + s3 + "\n";
        if (op.equals(RelationalOp.LT)) Mips.s += "slt\t" + s1 + ",\t" +s2 + ",\t" + s3 + "\n";
        if (op.equals(RelationalOp.LE)) Mips.s += "sle\t" + s1 + ",\t" +s2 + ",\t" + s3 + "\n";
        if (op.equals(RelationalOp.GT)) Mips.s += "sgt\t" + s1 + ",\t" +s2 + ",\t" + s3 + "\n";
        if (op.equals(RelationalOp.GE)) Mips.s += "sge\t" + s1 + ",\t" +s2 + ",\t" + s3 + "\n";
        Mips.s += "neg\t" + s1 + "\t" + s1 + "\n";
        Register.change(s1);
        return null;
    }
}
