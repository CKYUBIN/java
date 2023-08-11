package www.dream.bbs.board.controller;

/**
 * RestController는 client 요청에 대한 분배 기능
 * service로 처리 위임하여 그 결과를 client에게 반환한다
 * 길게 업무적 처리가 들어와 있으면 실수한 것임
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.dream.bbs.board.model.Pair;
import www.dream.bbs.board.model.PostVO;
import www.dream.bbs.board.model.ReplyVO;
import www.dream.bbs.board.service.PostService;
import www.dream.bbs.framework.nlp.pos.service.NounExtractor;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	// /post/listAll/000n
	@GetMapping("/listAll/{boardID}")
	public ResponseEntity<List<PostVO>> listAllPost(@PathVariable String boardID) {
		List<PostVO> listResult = postService.listAllPost(boardID);
		return new ResponseEntity<>(listResult, HttpStatus.OK);
	}

	/** 원글 상세. 첨부파일 목록, 댓글, 대댓 목록이 내부 변수에 채워짐 */
	// /post/getPost/{ID}
	@GetMapping("/getPost/{ID}") // 충돌 방지
	public ResponseEntity<PostVO> findByID(@PathVariable String ID) {
		PostVO post = postService.findById(ID);
		if (post == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(postService.findById(ID), HttpStatus.OK);
	}

	// /post/extractTag?docs=안녕하세요&docs=데이터분석
	// 유일한 단어 집합
	@GetMapping("/extractTag")
	public ResponseEntity<Set<String>> extractTag(String[] docs) {
		Set<String> ret = new HashSet<>();
		for (String doc : docs) {
			ret.addAll(NounExtractor.extractNoun(doc));
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@GetMapping("/extractTag")
	public ResponseEntity<List<Pair<String, Integer>>> extractTagOld(String[] docs) {
		List<Pair<String, Integer>> ret = new ArrayList<>();

		List<String> listNoun = new ArrayList<>();
		for (String doc : docs) {
			listNoun.addAll(NounExtractor.extractNoun(doc));
		}
		Map<String, Integer> mapWordCnt = new HashMap<>();
		for (String noun : listNoun) {
			if (mapWordCnt.containsKey(noun)) {
				mapWordCnt.put(noun, mapWordCnt.get(noun) + 1);
			} else {
				mapWordCnt.put(noun, 1);
			}
		}

		/*
		 * for (Entry<String, Integer> e :mapWordCnt.entrySet()) { ret.add(new
		 * Pair(e.getKey(), e.getValue())); }
		 */
		mapWordCnt.forEach((key, val) -> {
			ret.add(new Pair(key, val));
		}); // biconsumer 화살표함수
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/** 게시판에 원글 달기 /post/creatPost */
	@PostMapping("/createPost")
	public ResponseEntity<Integer> createPost(PostVO post) {
		return new ResponseEntity<>(postService.createPost(post), HttpStatus.OK);
	}

	/** 댓글 달기. parent의 hid의 연결된 hid 만들기 */
	@PostMapping("/createReply")
	public ResponseEntity<Integer> createReply(ReplyVO parent, ReplyVO reply) {
		return new ResponseEntity<>(postService.createReply(parent, reply), HttpStatus.OK);
	}

	/* */
	@PostMapping("/updatePost")
	public ResponseEntity<Integer> updatePost(PostVO post) {
		return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
	}

	@PostMapping("/updateReply")
	public ResponseEntity<Integer> updateReply(ReplyVO reply) {
		return new ResponseEntity<>(postService.updateReply(reply), HttpStatus.OK);
	}

	/** hid like로 지우기 */
	@DeleteMapping("/delete/{ID}")
	public ResponseEntity<Integer> deleteReply(@PathVariable String ID) {
		return new ResponseEntity<>(postService.deleteReply(ID), HttpStatus.OK);
	}
}