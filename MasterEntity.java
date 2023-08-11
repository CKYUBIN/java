package www.dream.bbs.framework.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
		
@Getter
@NoArgsConstructor
@AllArgsConstructor
// abstract 객체 생성 안된다
public abstract class MasterEntity extends Entity {
	private Date regDt;   
	private Date uptDt;

	public MasterEntity(String id) {
		super(id);
	}

	@Override
	public String toString() {
		return super.toString() + ", regDt= " + regDt + ", uptDt= " + uptDt ;
	}
	
	
	
}
