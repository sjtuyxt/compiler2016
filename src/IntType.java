

public class IntType extends BasicType {
    @Override public boolean equal(Type type){
        if (!(type instanceof IntType))
            return false;
        return true;
    }
}
