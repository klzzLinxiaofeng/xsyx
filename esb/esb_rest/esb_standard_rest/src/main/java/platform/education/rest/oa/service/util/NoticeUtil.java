package platform.education.rest.oa.service.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

import framework.generic.dao.Page;
import platform.education.notice.model.Notice;

public final class NoticeUtil {
	
	/**
	 * 
	 * @author Ken
	 * @date 2017年3月1日 上午10:51:28
	 * @param notices
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public static List<Notice> getNoticeListByPage(Collection<Notice> notices, int currentPage, int pageSize) {
		List<Notice> items = new ArrayList<Notice>();
		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize - 1;
		int i = 0;
		for (Notice vo : notices) {
			if (i >= start && i <= end) {
				items.add(vo);
			}
			i++;
		}
		return items;
	}
	
	/**
	 * 
	 * @author Ken
	 * @date 2017年3月1日 上午10:46:36
	 * @param totalRows
	 * @param page
	 * @return
	 */
	public static Page resetPage(int totalRows, Page page) {
		page.setTotalRows(totalRows);
		int pageSize = page.getPageSize();
		int totalPages = (totalRows % pageSize) == 0 ? (totalRows / pageSize)
				: (totalRows / pageSize) + 1;
		page.setTotalPages(totalPages);
		return page;
	}
	
	public static Collection<Notice> unescapeNoticeListHtml(Collection<Notice> notices) {
		if (notices != null && notices.size() > 0) {
			for (Notice notice : notices) {
				notice = unescapeNoticeHtml(notice);
			} 
		}
		return notices;
	}

	public static Notice unescapeNoticeHtml(Notice notice) {
		String title = StringEscapeUtils.unescapeHtml4(delHTMLTag(notice.getTitle()));
		notice.setTitle(title);
		String content = StringEscapeUtils.unescapeHtml4(delHTMLTag(notice.getContent()));
		notice.setContent(content);
		return notice;
	}
	
	/**
	 * 使用正则表达式删除HTML标签。 
	 * @author Ken
	 * @date 2017年3月1日 下午3:41:57
	 * @param htmlStr
	 * @return
	 */
    public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符串 
    } 
    
    


	/**
	 * Java中去掉网页HTML标记的方法：
	 * 
	 * 去掉字符串里面的html代码。<br>
	 * 要求数据要规范，比如大于小于号要配套,否则会被集体误杀。
	 * 
	 * @param content
	 *            内容
	 * @return 去掉后的内容
	 */

	public static String stripHtml(String content) { 
		// <p>段落替换为换行 
		content = content.replaceAll("<p .*?>", "\r\n"); 
		// <br><br/>替换为换行 
		content = content.replaceAll("<br\\s*/?>", "\r\n"); 
		// 去掉其它的<>之间的东西 
		content = content.replaceAll("\\<.*?>", ""); 
		// 还原HTML 
		// content = HTMLDecoder.decode(content); 
		return content; 
	}
	
}
