package com.ws;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import platform.szxyzxx.library.service.LibraryService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class test {
	@Autowired
	private LibraryService libraryService;

	public static void main(String[] args) {


		//String endpoint = "http://119.1.223.145:801/cms/services/IAuthService?wsdl";
		/*IAuthService authService = new IAuthService();
		IAuthServicePortType authServicePortType = authService.getIAuthServiceHttpSoap11Endpoint();
		String result = authServicePortType.login("admin", SimulationCasUtil.getSHA256StrJava("Admin12345"), "", "", "");
		String tgt = SimulationCasUtil.readXML(result) ;
		String st = authServicePortType.applyToken(tgt);
		String stS =SimulationCasUtil.readXML(st) ;
		System.out.println("单点登录:"+stS);*/
		//System.out.println(getUUID());
		//客户要求在请字后面停顿，经多次尝试后顿号可实现需求
		//textToSpeech("请、66号客户到5号窗口办理业务");
	/*	Date date=new Date(2023-10-02);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		System.out.println(formatter.format(date));*/
	}
	@Test
	public  void test(){
		String sql="INSERT into pj_library(school_id,name)value(:schoolId,:name)";
		Map map=new HashMap();
		map.put("schoolId",215);
		map.put("name","测试测试");
		//libraryService.addLibrary(sql,map);
	}
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String result = uuid.toString();
		result = result.toLowerCase().replaceAll("-", "");
		return result;
	}

	public static void textToSpeech(String text) {
		ActiveXComponent ax = null;
		try {
			ax = new ActiveXComponent("Sapi.SpVoice");

			// 运行时输出语音内容
			Dispatch spVoice = ax.getObject();
			// 音量 0-100
			ax.setProperty("Volume", new Variant(100));
			// 语音朗读速度 -10 到 +10
			ax.setProperty("Rate", new Variant(-2));
			// 执行朗读
			Dispatch.call(spVoice, "Speak", new Variant(text));

			// 下面是构建文件流把生成语音文件

			ax = new ActiveXComponent("Sapi.SpFileStream");
			Dispatch spFileStream = ax.getObject();

			ax = new ActiveXComponent("Sapi.SpAudioFormat");
			Dispatch spAudioFormat = ax.getObject();

			// 设置音频流格式
			Dispatch.put(spAudioFormat, "Type", new Variant(22));
			// 设置文件输出流格式
			Dispatch.putRef(spFileStream, "Format", spAudioFormat);
			// 调用输出 文件流打开方法，创建一个.wav文件
			Dispatch.call(spFileStream, "Open", new Variant("./text.wav"), new Variant(3), new Variant(true));
			// 设置声音对象的音频输出流为输出文件对象
			Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
			// 设置音量 0到100
			Dispatch.put(spVoice, "Volume", new Variant(100));
			// 设置朗读速度
			Dispatch.put(spVoice, "Rate", new Variant(-2));
			// 开始朗读
			Dispatch.call(spVoice, "Speak", new Variant(text));

			// 关闭输出文件
			Dispatch.call(spFileStream, "Close");
			Dispatch.putRef(spVoice, "AudioOutputStream", null);

			spAudioFormat.safeRelease();
			spFileStream.safeRelease();
			spVoice.safeRelease();
			ax.safeRelease();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
