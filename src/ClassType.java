

public class ClassType extends BasicType {
    public Symbol tag;

    public ClassType() {
    }

    public ClassType(Symbol tag) {
        this.tag = tag;
    }

    @Override public boolean Round2(){
        if (SymbolTable.get(tag) == null)
            return false;
        return true;
    }

    @Override public boolean equal(Type type){
        if (type instanceof NullType)
            return true;
        if (!(type instanceof  ClassType))
            return false;
        if (tag==((ClassType)type).tag)
            return true;
        return false;
    }
}
