package platform.education.paper.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import platform.education.paper.constants.QuestionType;

public class ReadFileUtil {
	
	
	
	/**
	 * 通过路径获取文件的内容，这个方法因为用到了字符串作为载体，为了正确读取文件（不乱码），只能读取文本文件，安全方法！
	 */
	public static String readFile(String path){
		String data = null;
		FileInputStream fis = null;
		
		// 判断文件是否存在
		File file = new File(path);
		if(!file.exists()){
			return data;
		}
		// 获取文件编码格式
		String code = getFileEncode(path);
		InputStreamReader isr = null;
		try{
			// 根据编码格式解析文件
			if("asci".equals(code)){
				// 这里采用GBK编码，而不用环境编码格式，因为环境默认编码不等于操作系统编码 
				// code = System.getProperty("file.encoding");
				code = "GBK";
			}
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis,code);
			// 读取文件内容
			int length = -1 ;
			char[] buffer = new char[1024];
			StringBuffer sb = new StringBuffer();
			while((length = isr.read(buffer, 0, 1024) ) != -1){
				sb.append(buffer,0,length);
			}
			data = new String(sb);
		}catch(Exception e){
			e.printStackTrace();
			//log.info("getFile IO Exception:"+e.getMessage());
		}finally{
			try {
				if(isr != null){
					isr.close();
				}
				if(fis != null){
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			//	log.info("getFile IO Exception:"+e.getMessage());
			}
		}
		return data;
	}
	
	public static String getFileEncode(String path) {
		String charset ="asci";
		FileInputStream fis = null;
        byte[] first3Bytes = new byte[3];
        BufferedInputStream bis = null;
        try {
            boolean checked = false;
            fis = new FileInputStream(path);
            bis = new BufferedInputStream(fis);
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1)
                return charset;
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "Unicode";//UTF-16LE
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = "Unicode";//UTF-16BE
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF8";
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int len = 0;
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) //单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) 
                        //双字节 (0xC0 - 0xDF) (0x80 - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) { //也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
                //TextLogger.getLogger().info(loc + " " + Integer.toHexString(read));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException ex) {
                }
            }
            
            if(fis != null){
            	try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
        return charset;
	}
	
	
	
	
	
	
	
	
	

	/**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file){
    	String charsetName = "UTF-8"; 
    	FileReader fileReader = null;
    	BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try{
        	fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }finally {
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fileReader != null){
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return result.toString();
    }
    
    
    public static String readFileByLines(String fileName) {
    	String charsetName = "UTF-8"; 
        FileInputStream file = null;
        BufferedReader reader = null;
        InputStreamReader inputFileReader = null;
        String content = "";
        String tempString = null;
        try {
            file = new FileInputStream(fileName);
            inputFileReader = new InputStreamReader(file, charsetName);
            reader = new BufferedReader(inputFileReader);
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                content += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
        	 if(file != null){
             	try {
 					file.close();
 				} catch (IOException e) {
 					e.printStackTrace();
 				}
             }
        	
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
           
            
            if(inputFileReader != null){
            	try {
            		inputFileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
        return content;
    }
    
    
    
    public static String readString(String filePath)
    {
        String str="";
        File file=new File(filePath);
        try {
            FileInputStream in=new FileInputStream(file);
            // size  为字串的长度 ，这里一次性读完
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            str=new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    
    
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException{
        File file = new File("D:/2017/0223/document.json");
      //  System.out.println(txt2String(file));
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(txt2String(file), JsonNode.class);
        JsonNode jsonNode = rootNode.path("questions");
        for(JsonNode newJsonNode :jsonNode){
        	String type = newJsonNode.path("type").asText();
        	if(type == QuestionType.COMPLEX){
        		String answer = newJsonNode.path("answer").asText();
        		System.out.println(answer);
        	}
        	
        }
        
    }
}
