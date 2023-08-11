package www.dream.bbs.party.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.dream.bbs.framework.model.MasterEntity;
import www.dream.bbs.framework.property.anno.TargetProperty;

@Getter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class PartyVO extends MasterEntity {
	@TargetProperty
	private String name;
	private boolean sex;
	
	private List<ContactPointVO> listContactPoint = new ArrayList<>();  //연락처 목록
	
	public PartyVO(String name, boolean sex) {
		this.name = name;
		this.sex = sex;
	}
}
