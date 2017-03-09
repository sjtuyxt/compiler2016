

public class ArrayType extends Type {
    public Type baseType;
    public Expr arraySize;

    public ArrayType() {
        baseType = null;
        arraySize = null;
    }

    public ArrayType(Type baseType, Expr arraySize) {
        if (baseType instanceof VoidType)
            System.exit(1);
        this.baseType = baseType;
        this.arraySize = arraySize;
    }

    @Override public boolean equal(Type type){
        if (type instanceof NullType)
            return true;
        if (!(type instanceof ArrayType))
            return false;
        if (baseType.equal(((ArrayType)type).baseType))
            return true;
        return false;
    }
}
