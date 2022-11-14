package platform.education.generalTeachingAffair.vo;

public class GradeEnum {
	public static String[] gradeArray = {"一","二","三","四","五","六","七","八","九"};
	
	public static String convertGrade(String grade) {
		grade = grade.replaceAll("年级", "");
		if(grade.contains("初")) {
			grade = grade.replaceAll("初", "");
			grade = numberToChinese(grade);
			if("一".equals(grade)) {
				grade = "七";
			} else if("二".equals(grade)) {
				grade = "八";
			} else if("三".equals(grade)){
				grade = "九";
			}
		} else if(grade.contains("高")) {
			grade = grade.replaceAll("高", "");
			grade = numberToChinese(grade);
			grade = "高" + grade;
			return grade;
		} else {
			grade = numberToChinese(grade);
		}
		
		grade += "年级";
		
		return grade;
	}
	
	public static String numberToChinese(String number) {
		for (int i = 1; i < 10; i++) {
			String tmp = String.valueOf(i);
			if(number.equals(tmp)){
				i=i-1;
				number = gradeArray[i];
				break;
			}
		}
		return number;
	}
	
}
