package www.dream.bbs.party.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MemberVO extends PartyVO {

	public MemberVO(String ID) {
	}

	public MemberVO(String name, boolean sex) {
		super(name, sex);
	}

	@Override
	public String toString() {
		return "MemberVO [" + super.toString() + "]";
	}

}
