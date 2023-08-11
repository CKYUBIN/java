package www.dream.bbs.iis.model;
/** 
 * iis inverted index search 
 * */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
@Table(name="T_TAG") //T_TAG로부터 이 정보 만들 수 있어
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TagVO {
	@Id
	private String id;
	private String word;
	@Column(name="description")
	private String 단어정의_설명; //description
	private long df;
	
	public TagVO() {}
	
	public TagVO(String word, String 단어정의_설명) {
		this.word = word;
		this.단어정의_설명 = 단어정의_설명;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
