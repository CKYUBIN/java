package www.dream.bbs.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import www.dream.bbs.board.model.PostVO;
import www.dream.bbs.board.model.ReplyVO;

@Mapper //Container에 담기도록 지정
public interface PostMapper {
	// LRCUD 순서로 함수들을 배치하여 빠르게 따라갈(추적성) 수 있도록 합니다.
	public List<PostVO> listAllPost(String boardID); //게시물 개략적으로 조회
	//게시물 상세정보 조회. 첨부파일 목록, 댓글, 대댓 목록 
	public List<ReplyVO> findById(String id); 
	
	/* affected row counts 영향받은 행의 갯수 */
	public int createPost(PostVO post); 
	public int createReply(@Param("parent") ReplyVO parent, @Param("reply") ReplyVO reply); //대댓글
	
	public int updatePost(PostVO post);
	public int updateReply(ReplyVO reply);
	
	public int deleteReply(String id);
}
