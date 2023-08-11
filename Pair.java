package www.dream.bbs.board.model;

public class Pair<F, S> {
	private F firstVal;
	private S secondVal;
	public Pair(F firstVal, S secondVal) {
		this.firstVal = firstVal;
		this.secondVal = secondVal;
	}
	public F getFirstVal() {
		return firstVal;
	}
	public S getSecondVal() {
		return secondVal;
	}

}
