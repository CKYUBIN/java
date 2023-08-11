package www.dream.bbs.board.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.dream.bbs.framework.model.Entity;

@Getter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BoardVO extends Entity {
	private String name;		// varchar(255) not null,
	private String descrip; 	// varchar(255) ,
	private long postCnt = 0;   // '총 게시물 개수', 기본형이 숫자라 초기값 설정 안해도 되긴함
	
	public BoardVO(String id) {
		super(id);
	}
	
	//사용자(매니저)로부터(화면으로) 정보가 만들어질 때 사용
	public BoardVO(String name, String descrip) {
		this.name = name;
		this.descrip = descrip;
	}

	@Override
	public String toString() {
		return "BoardVO [" + super.toString()  + ", name=" + name + ", descrip=" + descrip + ", postCnt=" + postCnt + "]";
	}
	
}
