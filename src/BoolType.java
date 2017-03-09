public class BoolType extends BasicType {
    @Override public boolean equal(Type type){
        if (!(type instanceof BoolType))
            return false;
        return true;
    }
}