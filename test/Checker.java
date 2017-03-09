import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by abcdabcd987 on 2016-04-02.
 */
@RunWith(Parameterized.class)
public class Checker {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList<>();
        for (File f : new File("C:\\dabian\\test\\passed\\").listFiles()) {
            if (f.isFile() && f.getName().endsWith(".mx")) {
                params.add(new Object[] { "C:\\dabian\\test\\passed\\" + f.getName(), true });
            }
        }
        for (File f : new File("C:\\dabian\\test\\compile_error\\").listFiles()) {
            if (f.isFile() && f.getName().endsWith(".mx")) {
                params.add(new Object[] { "C:\\dabian\\test\\compile_error\\" + f.getName(), false });
            }
        }
        return params;
    }

    private String filename;
    private boolean shouldPass;

    public Checker(String filename, boolean shouldPass) {
        this.filename = filename;
        this.shouldPass = shouldPass;
    }

    @Test
    public void testPass() throws IOException {
        System.out.println(filename);

        try {
            InputStream is = new FileInputStream(filename);
            try {
//                InputStream is = args.length > 0 ? new FileInputStream(args[0]) : System.in;

                ANTLRInputStream input = new ANTLRInputStream(is);
                //ANTLRInputStream input = new ANTLRInputStream(System.in);
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

            SymbolTable.put(Symbol.get("getString"),new FunctionDecl(new StringType(), Symbol.get("getString"), null, null));

            SymbolTable.put(Symbol.get("getInt"),new FunctionDecl(new IntType(), Symbol.get("getInt"), null, null));

            para = new ArrayList<>();
            para.add(new VarDecl(new IntType(), Symbol.get("i"), null));
            SymbolTable.put(Symbol.get("toString"),new FunctionDecl(new StringType(), Symbol.get("toString"), para, null));


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


            if (!shouldPass) fail("Should not pass.");
        } catch (Exception e) {
            if (shouldPass) throw e;
            else e.printStackTrace();
        }
    }
}
