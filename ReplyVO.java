package www.dream.bbs.board.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import www.dream.bbs.framework.model.MasterEntity;
import www.dream.bbs.framework.property.anno.TargetProperty;
import www.dream.bbs.party.model.PartyVO;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReplyVO extends MasterEntity {

	@TargetProperty
	private PartyVO writer; // 게시물 작성자
	@TargetProperty
	private String content; // 내용
	private String hierarchID;
	// 대댓 구조 만들기는 어떻게?
	private List<ReplyVO> listReply;
	
	public String extractParentID() {
		String myID = super.getId();
		int len = myID.length();
		return myID.substring(0, len - ID_LENGTH);
	}

	public void appendReply(ReplyVO reply) {
		if (listReply == null)
			listReply = new ArrayList<>();
		listReply.add(reply);
	}
}
