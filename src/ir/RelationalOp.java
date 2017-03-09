package ir;

public enum RelationalOp {
    EQ, NE, GT, GE, LT, LE;

    static public RelationalOp Reverse(RelationalOp op){
        if (op == EQ) return NE;
        else if (op == NE) return EQ;
        else if (op == GT) return LE;
        else if (op == LE) return GT;
        else if (op == GE) return LT;
        else return GE;
    }
}
