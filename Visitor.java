package www.dream.vp.visitor;

import www.dream.vp.model.Composer;
import www.dream.vp.model.Operand;
/**
 * strategy pattern 전략 패턴 공통기능에 대한 선언, 각자 알아서 할일 함
 */
public abstract class Visitor {
	private StringBuilder sb = new StringBuilder(); 
	
	public void visit(Composer composer) {
		
	}
	public void visit(Operand operand) {}
	
	public String getHistory() {
		return sb.toString();
	}
	protected void recordHistory(String hist) {
		sb.append(hist);
	}
}
