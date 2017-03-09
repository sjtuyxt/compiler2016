// Generated from mo.g by ANTLR 4.5.2


import ir.Address;
import ir.Label;
import ir.Quadruple;

import java.util.ArrayList;

public abstract class AST {

	public boolean Round2(){
		return true;
	}

	public AST Round3(Type ReturnType, boolean InLoop){
		return null;
	}

	public Address getValue(ArrayList<Quadruple> list){
		return null;
	};

	public void putValue(ArrayList<Quadruple> list, Address src){};

	public void Translate(ArrayList<Quadruple> list, Label begin, Label end){};
}

/**
 * This class provides an empty implementation of {@link moListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
