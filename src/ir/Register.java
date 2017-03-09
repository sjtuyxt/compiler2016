package ir;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Register {
    static public List<Integer> list = new ArrayList<>();
    static public int[] address = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    static public boolean[] changed = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    static public String[] name = {"$v0", "$v1", "$a0", "$a1", "$a2", "$a3", "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$s8"};

    static public void change(String s){
        for (int i = 0; i < 24; i++)
            if (name[i].equals(s))
                changed[i] = true;
    }

    static public String get(int x){
        for (int i = 0; i < 24; i++)
            if (address[i] == x) {
                for (int j = 0; j < list.size(); j++)
                    if (list.get(j) == i){
                        list.remove(j);
                        break;
                    }
                list.add(i);
                return name[i];
            }
        if (list.size() < 24){
            for (int i = 0; i < 24; i++)
                if (address[i] == -1){
                    list.add(i);
                    address[i] = x;
                    add(name[i], x);
                    return name[i];
                }
        }
        else{
            int tmp = list.get(0);
            list.remove(0);
            put(tmp);
            address[tmp] = x;
            list.add(tmp);
            add(name[tmp], x);
            return name[tmp];
        }
        return null;
    }

    static public String gettmp(int x){
        for (int i = 0; i < 24; i++)
            if (address[i] == x) {
                for (int j = 0; j < list.size(); j++)
                    if (list.get(j) == i){
                        list.remove(j);
                        break;
                    }
                list.add(i);
                return name[i];
            }
        if (list.size() < 24){
            for (int i = 0; i < 24; i++)
                if (address[i] == -1){
                    list.add(i);
                    address[i] = x;
                    //add(name[i], x);
                    return name[i];
                }
        }
        else{
            int tmp = list.get(0);
            list.remove(0);
            put(tmp);
            address[tmp] = x;
            list.add(tmp);
            //add(name[tmp], x);
            return name[tmp];
        }
        return null;
    }

    static public void put(int tmp){
        backup(tmp);
        list.remove(list.size() - 1);
        address[tmp] = -1;
    }

    static public void add(String s, int tmp){
        if (tmp >= 10000)
            Mips.s += "lw\t" + s + ",\t" + 4*(tmp-10000) + "($sp)\n";
        else
            Mips.s += "lw\t" + s + ",\tvar" + tmp + "\n";

    }

    static public void backup(int tmp){
        for (int i = 0; i < list.size(); i++)
            if (list.get(i) == tmp){
                list.remove(i);
                break;
            }
        list.add(tmp);
        if (address[tmp] < 0)
            return;
        if (!changed[tmp])
            return;
        if (address[tmp] >= 10000)
            Mips.s += "sw\t" + name[tmp] + ",\t" + 4*(address[tmp]-10000) + "($sp)\n";
        else
            Mips.s += "sw\t" + name[tmp] + ",\tvar" + address[tmp] + "\n";
        changed[tmp] = false;
    }

    static public void clearall(){
        for (int i = 23; i >= 0; i--){
            backup(i);
            address[i] = -2;
        }
    }

    static public void clear(){
        for (int i = 5; i >= 0; i--) {
            backup(i);
            address[i] = -2;
        }
    }
}
