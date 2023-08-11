package www.dream.bbs.board.service;

/**
 * @Service : 업무처리
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.dream.bbs.board.mapper.PostMapper;
import www.dream.bbs.board.model.BoardVO;
import www.dream.bbs.board.model.PostVO;
import www.dream.bbs.board.model.ReplyVO;
import www.dream.bbs.framework.nlp.pos.service.NounExtractor;
import www.dream.bbs.framework.property.PropertyExtractor;
import www.dream.bbs.party.model.MemberVO;

//Container에 담기도록 지정
@Service
public class PostService {
	@Autowired
	private PostMapper postMapper;

	/** 게시판의 모든 원글 목록 조회 */
	public List<PostVO> listAllPost(String boardID) {
		return postMapper.listAllPost(boardID);
	}

	/** 원글 상세. {첨부파일 목록}(아직 못함), 댓글 목록이 내부 변수에 채워짐 */
	public PostVO findById(String ID) {
		// postMapper.findById(ID)는 id의 primary key 특성으로 사전 순서가 보장되어 있음
		List<ReplyVO> oneDimList = postMapper.findById(ID);
		if (oneDimList.isEmpty()) {
			return null;
		}
		PostVO ret = (PostVO) oneDimList.get(0);
		Map<String, ReplyVO> map = new HashMap<>();
		for (ReplyVO reply : oneDimList) {
			map.put(reply.getId(), reply);

			ReplyVO parent = map.get(reply.extractParentID());
			if (parent != null) {
				parent.appendReply(reply);
			}
		}
		return ret;
	}

	/* affected row counts */
	/**
	 * 게시판에 원글 달기 신규 tag면 등록 모든 tag와 TF 등재 및 tag의 DF 수정
	 */
	@Transactional // AOP 단위작업처리
	public int createPost(PostVO post) {

		Map<String, Integer> mapTF = buildTF(post);

		return postMapper.createPost(post);
	}

	/** 댓글 달기. parent의 hid의 연결된 hid 만들기 */
	public int createReply(ReplyVO parent, ReplyVO reply) {
		return postMapper.createReply(parent, reply);
	}

	/** tf, df 정보 수정도 고려해야 함 */
	public int updatePost(PostVO post) {
		return postMapper.updatePost(post);
	}

	/**  */
	public int updateReply(ReplyVO reply) {
		return postMapper.updateReply(reply);
	}

	/** hid like로 지우기 */
	public int deleteReply(String ID) {
		return postMapper.deleteReply(ID);
	}

	private Map<String, Integer> buildTF(PostVO post) {
		List<String> docs = PropertyExtractor.extractProperty(post);

		List<String> listNoun = new ArrayList<>();
		for (String doc : docs) {
			listNoun.addAll(NounExtractor.extractNoun(doc));
		}

		List<String> listTag = post.getListTag();
		Map<String, Integer> mapWordCnt = new HashMap<>();
		listTag.forEach(tag -> mapWordCnt.put(tag, 1));
		// listNoun에서 있는 것만 유지시킬거야
		listNoun.retainAll(listTag);

		for (String noun : listNoun) {
			mapWordCnt.put(noun, mapWordCnt.get(noun) + 1);
		}
		return mapWordCnt;
	}

	// listTag에 담긴 단어에 대한 처리는 ...
	// 기존 단어와 새로운 단어로 구분
	// TF - IDF
	// 우리집에는 강아지 방울이가 있습니다. 방울이는 활발한 성격이고 ....
	// (TF) 방울 : 2
	// 우리집 : 1
	// 강아지 : 1
	// 성격 : 1
	public int cPost() {
		PostVO post = new PostVO();
		post.setTitle("aaaa");
		post.setContent("asdgas");
		post.setBoardVO(new BoardVO("000n")); // sql에서 직접 찾아서 입력
		post.setWriter(new MemberVO("000f"));
		return postMapper.createPost(post);
	}
}
