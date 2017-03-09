public class StringType extends BasicType {
    @Override public boolean equal(Type type){
        if (!(type instanceof StringType))
            return false;
        return true;
    }
}