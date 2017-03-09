package ir;

import java.io.PrintStream;

public class Temp extends Address {
    public static int tempCount = 0;
    public boolean first;

    public int num;

    public Temp() {
        num = tempCount++;
        first = true;
    }

    public void out(PrintStream x){
        x.print("$");
        x.print(num);
        x.print(" ");
    };

    @Override public String generate(){
        String s;
        if (num < 10000 || !first)
            s = Register.get(num);
        else {
            s = Register.gettmp(num);
            first = false;
        }
        return s;
    }
}
