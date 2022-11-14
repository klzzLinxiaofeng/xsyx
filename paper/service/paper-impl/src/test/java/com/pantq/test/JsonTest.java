package com.pantq.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;

public class JsonTest {

	public static void main(String[] args) {
		String str = "[\"<grid class=\"math\"><img class=\"eq\" src=\"1.png\" width=\"55\" height=\"69\"></img></grid><grid class=\"math\"><img class=\"eq\" src=\"2.png\" width=\"55\" height=\"69\"></img></grid><grid class=\"math\"><img class=\"eq\" src=\"3.png\" width=\"55\" height=\"69\"></img></grid>\"]";
		String st = "src=\"";
		System.out.println(change(str,st));
		//mapper.
	}
	
	/**change*/
	private static String change(String source,String splitStr){
		//check
		if(source == null || source.trim().length() == 0)
			return null;
		
		//init rs
		int rs = 0;
		
		//new text
		StringBuilder sb = new StringBuilder();
		
		//start text
	//	String st = "src=\"";
		String et = "\"";
		
		//handle
		int start = source.indexOf(splitStr);
		
		//check start
		if(start < 0)
			return source;

		//handle_1
		String[] array = source.split(splitStr);
		if(array.length < 2)
			return source;
		
		//handle_2
		sb.append(array[0]);
		for(int index = 1 ; index < array.length ; index++){
			//check array
			if(array[index].trim().length() == 0)
				continue;
			
			//init next
			int next = array[index].indexOf(et);
			
			//check next
			if(next < 0)
				continue;
			
			
			//handle src
			String srcText = array[index].substring(0, next);
			System.out.println("srcText : " + srcText);
			if(srcText.trim().length() == 0)
				continue;
			
			//append st
			sb.append(splitStr);
			sb.append("file:\\" + File.separator + srcText + et + array[index].substring(next + 1, array[index].length()));
		}
		
		//return 
		return sb.toString();
	}

	
}
