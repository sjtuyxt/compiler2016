import ir.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class mo {

    public static void main(String[] args) throws Exception {
        try {
            //InputStream is = args.length > 0 ? new FileInputStream(args[0]) : System.in;

            //ANTLRInputStream input = new ANTLRInputStream(new FileInputStream("1.mx"));
            ANTLRInputStream input = new ANTLRInputStream(System.in);
            moLexer lexer = new moLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            moParser parser = new moParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            ParseTree tree = parser.program();

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new Listener(), tree);
        } catch (Exception e) {
            System.exit(1);
        }

            Program Prog = new Program();
            SymbolTable.beginscope();

        //initialize
        List<VarDecl> para = new ArrayList<>();
        para.add(new VarDecl(new StringType(), Symbol.get("str"), null));
        SymbolTable.put(Symbol.get("print"),new FunctionDecl(new VoidType(), Symbol.get("print"), para, null));

        para = new ArrayList<>();
        para.add(new VarDecl(new StringType(), Symbol.get("str"), null));
        SymbolTable.put(Symbol.get("println"),new FunctionDecl(new VoidType(), Symbol.get("println"), para, null));

        para = new ArrayList<>();
        SymbolTable.put(Symbol.get("getString"),new FunctionDecl(new StringType(), Symbol.get("getString"), para, null));

        para = new ArrayList<>();
        SymbolTable.put(Symbol.get("getInt"),new FunctionDecl(new IntType(), Symbol.get("getInt"), para, null));

        para = new ArrayList<>();
        para.add(new VarDecl(new IntType(), Symbol.get("i"), null));
        SymbolTable.put(Symbol.get("toString"),new FunctionDecl(new StringType(), Symbol.get("toString"), para, null));

        SymbolTable.beginscope();
        //Round 1
            for (int i = 0; i < Prog.list.size(); i++) {
                if (Prog.list.get(i) instanceof ClassDecl)
                    SymbolTable.put(((ClassDecl) Prog.list.get(i)).tag, Prog.list.get(i));
                else if(Prog.list.get(i) instanceof VarDecl)
                SymbolTable.put(((VarDecl) Prog.list.get(i)).name, Prog.list.get(i));
                else if (Prog.list.get(i) instanceof FunctionDecl)
                SymbolTable.put(((FunctionDecl) Prog.list.get(i)).name, Prog.list.get(i));
            }


            //Round 2
            if (SymbolTable.get(Symbol.get("main")) != null && !(((FunctionDecl)SymbolTable.get(Symbol.get("main"))).returnType instanceof IntType))
                System.exit(1);
            for (int i = 0; i < Prog.list.size(); i++) {
                if (Prog.list.get(i) instanceof ClassDecl) {
                    if (!((Prog.list.get(i)).Round2()))
                        System.exit(1);
                }
                else if (Prog.list.get(i) instanceof VarDecl){
                    if (((VarDecl) Prog.list.get(i)).type instanceof ClassType){
                        if (!(SymbolTable.get(((ClassType) ((VarDecl) Prog.list.get(i)).type).tag) instanceof ClassDecl))
                            System.exit(1);
                    }
                }
                else {
                    if (!((Prog.list.get(i)).Round2()))
                        System.exit(1);
                }
            }

            //Round 3
        for (int i = 0; i < Prog.list.size(); i++)
            if (!(Prog.list.get(i) instanceof ClassDecl))
                Prog.list.get(i).Round3(null, false);

        SymbolTable.endscope();

        SymbolTable.beginscope();
        //IR
        for (int i = 0; i < Prog.list.size(); i++) {
            if (Prog.list.get(i) instanceof ClassDecl)
                SymbolTable.put(((ClassDecl) Prog.list.get(i)).tag, Prog.list.get(i));
            else if (Prog.list.get(i) instanceof FunctionDecl)
                SymbolTable.put(((FunctionDecl) Prog.list.get(i)).name, Prog.list.get(i));
        }

        ArrayList<Quadruple> _start = new ArrayList<>();
        ArrayList<Quadruple> IR = new ArrayList<>();
        for (int i = 0; i < Prog.list.size(); i++) {
            if (Prog.list.get(i) instanceof VarDecl)
                Prog.list.get(i).Translate(_start, null, null);
        }

        int tempCount = Temp.tempCount;

        for (int i = 0; i < Prog.list.size(); i++) {
            Temp.tempCount = 10000;
            if (Prog.list.get(i) instanceof FunctionDecl)
                Prog.list.get(i).Translate(IR, null, null);
        }

        PrintStream out = new PrintStream(new FileOutputStream("1.s"));

        /*for (int i = 0; i < _start.size(); i++) {
            _start.get(i).out(out);
            Mips.s += "\n";
        }

        for (int i = 0; i < IR.size(); i++) {
            out.println('\n');
            out.println(((Function) IR.get(i)).name);
            for (int j = 0; j < ((Function) IR.get(i)).body.size(); j++) {
                ((Function) IR.get(i)).body.get(j).out(out);
                Mips.s += "\n";
            }
            out.println('\n');
        }*/


        //Mips
        Mips.s += ".data\n";

        BufferedReader lib = new BufferedReader(new FileReader("data.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        for (int i = 0; i < tempCount; i++)
            Mips.s += "var" + i + ":\t.word\t0\n";
        for (int i = 0; i < _start.size(); i++)
            if (_start.get(i) instanceof StringAddressConst) {
                Mips.s += "str" + ((StringAddressConst) _start.get(i)).num + "\t.word\t" + ((StringAddressConst) _start.get(i)).size +
                        "\n.asciiz\t" + ((StringAddressConst) _start.get(i)).value + "\n.align\t2\n";
            }
        for (int i = 0; i < IR.size(); i++) {
            for (int j = 0; j < ((Function) IR.get(i)).body.size(); j++)
                if (((Function)IR.get(i)).body.get(j) instanceof StringAddressConst)
                    Mips.s += "str" + ((StringAddressConst)((Function)IR.get(i)).body.get(j)).num +
                            ":\t.word\t" + ((StringAddressConst)((Function)IR.get(i)).body.get(j)).size +
                            "\n.asciiz\t" + ((StringAddressConst)((Function)IR.get(i)).body.get(j)).value + "\n.align\t2\n";
        }

        Mips.s += "\n.text\n";


        // link and output
        lib = new BufferedReader(new FileReader("getInt.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("getString.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("new.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("parseInt.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("print.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("println.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("stringAdd.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("stringEquals.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("stringLessThan.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("stringLessThanOrEquals.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("substring.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("toString.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("length.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("size.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        lib = new BufferedReader(new FileReader("ord.s"));
        for(String line = lib.readLine();line != null ; line = lib.readLine()) {
            Mips.s += line + "\n";
        }
        Mips.s += "\n";

        //generate


        Function func = null;
        for (int i = 0; i < Function.funclist.size(); i++)
            if (Function.funclist.get(i).name.equals("main"))
                func = Function.funclist.get(i);

        Mips.s += "\nmain:\n";
        Mips.s += "sw\t$ra\t"+ (_start.size()*4+40) +"($sp)\n";
        for (int i = 0; i < _start.size(); i++)
            _start.get(i).generate();
        Register.clearall();
        Mips.s += "sub\t$sp\t" + (4*func.tempCount+8) + "\n";
        Mips.s += "jal\tfuncmain\n";
        Mips.s += "add\t$sp\t" + (4*func.tempCount+8) + "\n";
        Mips.s += "lw\t$ra\t"+ (_start.size()*4+40) +"($sp)\n";
        Mips.s += "jr\t$ra\n\n";

        for (int i = 0; i < IR.size(); i++)
            IR.get(i).generate();
        System.out.print(Mips.s);
        //out.print(Mips.s);
            // print LISP-style tree
            // System.out.println(tree.toStringTree(parser));
    }
}
