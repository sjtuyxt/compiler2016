package ir;

import java.io.PrintStream;
import java.util.ArrayList;

public class StringAddressConst extends Const {
    public Address src;
    static public int cnt = 0;
    static public ArrayList<String> list = new ArrayList<>();
    public int num;
    public String value;
    public int size;

    public StringAddressConst(Address src, String value) {
        this.src = src;
        this.value = value;
        num = cnt++;
        list.add(value);
        int tmp = 0;
        for (int i = 0; i < value.length(); i++)
            if (value.charAt(i) == '\\'){
                i++;
                tmp++;
                continue;
            }
        size = value.length() - 2 - tmp;
    }

    public void out(PrintStream x){
        x.print("String");
        x.print(num);
        src.out(x);
        x.print(":"+value);
    };

    @Override public String generate(){
        String s = src.generate();
        Register.change(s);
        Mips.s += "la\t" + s + ",\tstr" + num + "\n";
        return s;
    }
}
