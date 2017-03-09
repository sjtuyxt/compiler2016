import ir.Mips;
import ir.Quadruple;
import ir.Register;
import ir.RelationalOp;

/**
 * Created by xiaotian on 2016/5/8.
 */
public class Clear extends Quadruple {
    @Override public String generate(){
        Register.clearall();
        return null;
    }
}
