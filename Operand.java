package www.dream.vp.model;

import www.dream.vp.visitor.Visitor;

public class Operand extends Element {
	private int val;

	public Operand(int val) {
		this.val = val;
	}
	
	 @Override
	    public void accept(Visitor visitor) {
	        visitor.visit(this);
	    }
	 
		public String whoAreYou() {
			return Integer.toString(val);
		}

}
