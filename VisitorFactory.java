package www.dream.vp.factory;

import www.dream.vp.visitor.InfixVisitor;
import www.dream.vp.visitor.PostfixVisitor;
import www.dream.vp.visitor.PrefixVisitor;
import www.dream.vp.visitor.Visitor;

public class VisitorFactory {
	public static Visitor createVisitor(String type) {
		if ("InfixVisitor".equals(type))
			return new InfixVisitor();
		if ("PrefixVisitor".equals(type))
			return new PrefixVisitor();
		return new PostfixVisitor();
			
	}
}
