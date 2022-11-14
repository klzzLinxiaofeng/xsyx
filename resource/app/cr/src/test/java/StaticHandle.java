
public class StaticHandle {

	public static int handlePaperProperty(String questionType) {
		//主观题
		return "blank".equals(questionType)||"word".equals(questionType)?
				2:1;
	}
}
