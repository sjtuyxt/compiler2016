

import java.util.List;
import java.util.LinkedList;

public class ClassDecl extends Decl {
    public Symbol tag;
    public List<DeclStmt> fields;

    public ClassDecl() {
        tag = null;
        fields = null;
    }

    public ClassDecl(Symbol tag, List<DeclStmt> fields) {
        this.tag = tag;
        this.fields = fields;
    }

    @Override public boolean Round2(){
        SymbolTable.beginscope();
        for (int i = 0; i < fields.size(); i++)
            if (!fields.get(i).Round2())
                return false;
        SymbolTable.endscope();
        return true;
    }
}
