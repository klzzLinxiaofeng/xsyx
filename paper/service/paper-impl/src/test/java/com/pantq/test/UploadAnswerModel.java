package com.pantq.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class UploadAnswerModel {
	
	private  List<Question> questions;
	private  String fileUuid; 
	
	public static void main(String[] args) {
		List<UploadAnswerModel> l = new ArrayList<UploadAnswerModel>();
		List<Question> list = new ArrayList<Question>();
		ObjectMapper mapper = new ObjectMapper();
		UploadAnswerModel u = new UploadAnswerModel();
		Question q = new Question();
		q.setQuestionUuid("fdbb65bf-2838-4404-b076-62ccb1db6192");
		q.setPos(1);
		list.add(q);
		
		/*Question q1 = new Question();
		q1.setQuestionUuid("fdbb65bf-2838-4404-b076-62ccb1db6192");
		q1.setPos(8);
		list.add(q1);
		
		Question q2 = new Question();
		q2.setQuestionUuid("fdbb65bf-2838-4404-b076-62ccb1db6192");
		q2.setPos(3);
		list.add(q2);*/
		u.setFileUuid("fdbb65bf-2838-4404-b076-62ccb1db6192");
		u.setQuestions(list);
		l.add(u);
		try {
			String result = mapper.writeValueAsString(l);
			//System.out.println(result);
			result = "[{\"fileUuid\":\"68a5d63aa2eb413f84834e86e4f050c0\",\"questions\":[{\"questionUuid\":\"71c83ddd-aeca-45f6-984b-7ef178818e6c\",\"pos\":1}]}]";
			//System.out.println(result);
			List<UploadAnswerModel> u1 = mapper.readValue(result, new TypeReference<List<UploadAnswerModel>>() {});
			for(UploadAnswerModel uu:u1){
				
				System.out.println(uu.getFileUuid());
				List<Question> question = uu.getQuestions();
				for(Question qq:question){
					System.out.println(qq.getPos());
				}
			}
			//System.out.println(mapper.writeValueAsString(l));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}


}
