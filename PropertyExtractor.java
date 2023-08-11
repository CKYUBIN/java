package www.dream.bbs.framework.property;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import www.dream.bbs.board.model.PostVO;
import www.dream.bbs.framework.property.anno.TargetProperty;
import www.dream.bbs.party.model.MemberVO;

public class PropertyExtractor {
	/**
	 * 해당 class에서 정의한 모든 속성을 상속 구조까지 추적하면서 추출.
	 * 
	 * @param cls
	 * @return
	 */
	private static List<Field> collectAllField(Class<?> cls) {
		List<Field> ret = new ArrayList<>(); // Post에 정의 되어 있는 모든 field가 담김
		do {
			Arrays.stream(cls.getDeclaredFields()).forEach(f -> ret.add(f));
			cls = cls.getSuperclass();
		} while (cls != Object.class);
		return ret;
	}

	/**
	 * 해당 object의 String 속성 중 @TargetProperty이 달린 값을 추출. Associated 속성까지 재귀적 탐색
	 * 
	 * @param obj
	 * @param ret
	 * @throws Exception
	 */
	// 재귀성을 가짐, 재귀호출
	public static void extractProperty(Object obj, List<String> ret) throws Exception {
		List<Field> fields = collectAllField(obj.getClass());
		for (Field field : fields) {
			TargetProperty dis = field.getAnnotation(TargetProperty.class); // fields에 anno가 달려있는지 확인
			if (dis != null) {
				field.setAccessible(true);
				if (field.getType() == String.class) {
					ret.add((String) field.get(obj)); // obj는 한 덩치
				} else {
					extractProperty(field.get(obj), ret);
				}
			}

		}
	}

	/**
	 * 재귀 호출 준비 단계
	 */
	public static List<String> extractProperty(Object obj) {
		List<String> ret = new ArrayList<>();
		try {
			extractProperty(obj, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	// 활용예시
	public static void main(String[] args) {
		PostVO tgt = new PostVO();
		tgt.setTitle("null");
		tgt.setContent("ccc null");
		MemberVO writer = new MemberVO("길동이", true);
		tgt.setWriter(writer);

		List<String> rrr = extractProperty(tgt); // 게시글에 관심있는 모든 문장들 달라
		for (String s : rrr)
			System.out.println(s);
	}

}
