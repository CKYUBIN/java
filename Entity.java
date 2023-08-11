package www.dream.bbs.framework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
// abstract 객체 생성 안된다
public abstract class Entity {
	
	/* DDL: idfunc.sql 에서의 id 길이에 맞추는 것*/
	public static final int ID_LENGTH = 4;

	public String getId() {
		return id;
	}

	//primitive type의 경우 기본값. reference type의 경우 null
	private String id;
	
	@Override
	public String toString() {
		return "id= " + id;
	}
	
}
