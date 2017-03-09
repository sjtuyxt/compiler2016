package ir;

import java.io.PrintStream;

public class ArithmeticExpr extends Quadruple {
    public ArithmeticOp op;
    public Address dest;
    public Address src1;
    public Address src2;

    public ArithmeticExpr() {
        op = null;
        dest = null;
        src1 = null;
        src2 = null;
    }

    public ArithmeticExpr(Address dest, Address src1, ArithmeticOp op, Address src2) {
        this.dest = dest;
        this.src1 = src1;
        this.op = op;
        this.src2 = src2;
    }

    public ArithmeticExpr(Address dest, ArithmeticOp op, Address src1) {
        this.dest = dest;
        this.src1 = src1;
        this.op = op;
        this.src2 = null;
    }

    public void out(PrintStream x){
        x.print("ArithmeticExpr ");
        dest.out(x);
        src1.out(x);
        x.print(op);
        x.print(' ');
        src2.out(x);
    };

    @Override public String generate(){
        if (src2 != null) {
            String s3;
            if (src2 instanceof IntegerConst)
                s3 = ((IntegerConst) src2).value + "";
            else
                s3 = src2.generate();
            String s1 = dest.generate();
            String s2 = src1.generate();
            if (op.equals(ArithmeticOp.ADD)) Mips.s += "add\t" + s1 + ",\t" + s2 + ",\t" + s3 + "\n";
            if (op.equals(ArithmeticOp.SUB)) Mips.s += "sub\t" + s1 + ",\t" + s2 + ",\t" + s3 + "\n";
            if (op.equals(ArithmeticOp.MUL)) Mips.s += "mul\t" + s1 + ",\t" + s2 + ",\t" + s3 + "\n";
            if (op.equals(ArithmeticOp.DIV)) Mips.s += "div\t" + s1 + ",\t" + s2 + ",\t" + s3 + "\n";
            if (op.equals(ArithmeticOp.MOD)) Mips.s += "rem\t" + s1 + ",\t" + s2 + ",\t" + s3 + "\n";
            if (op.equals(ArithmeticOp.SHL)) Mips.s += "sll\t" + s1 + ",\t" + s2 + ",\t" + s3 + "\n";
            if (op.equals(ArithmeticOp.SHR)) Mips.s += "srl\t" + s1 + ",\t" + s2 + ",\t" + s3 + "\n";
            if (op.equals(ArithmeticOp.AND)) Mips.s += "and\t" + s1 + ",\t" + s2 + ",\t" + s3 + "\n";
            if (op.equals(ArithmeticOp.OR)) Mips.s += "or\t" + s1 + ",\t" + s2 + ",\t" + s3 + "\n";
            if (op.equals(ArithmeticOp.XOR)) Mips.s += "xor\t" + s1 + ",\t" + s2 + ",\t" + s3 + "\n";
            Register.change(s1);
        }
        else {
            String s1 = dest.generate();
            String s2 = src1.generate();
            if (op.equals(ArithmeticOp.MINUS)) Mips.s += "neg\t" + s1 + ",\t" + s2 + "\n";
            if (op.equals(ArithmeticOp.TILDE)) Mips.s += "not\t" + s1 + ",\t" + s2 + "\n";
            Register.change(s1);
        }
        return null;
    }
}
