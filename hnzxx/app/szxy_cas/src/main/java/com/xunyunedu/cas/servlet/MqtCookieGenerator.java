package com.xunyunedu.cas.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xunyunedu.utils.SecretUtilTools;
import com.xunyunedu.utils.SimulationCasUtil;
import com.xunyunedu.utils.SysContantsAccessor;




/**
 * Servlet implementation class MqtCookieGenerator
 */
public class MqtCookieGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MqtCookieGenerator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String serviceUrl = request.getParameter("serviceUrl");
		if(serviceUrl == null) {
			serviceUrl = SysContantsAccessor.getStringProp("szxy.service");
		}
		
		String ssoType = request.getParameter("ssoType");
		String server = SysContantsAccessor.getStringProp("cas.server");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String schoolId = request.getParameter("schoolId");
		String encryptedPassword = request.getParameter("encryptedPassword");
		
		//判断是否加密
		String encrypType = request.getParameter("encrypType");
		if(encrypType != null && !"".equals(encrypType)) {
			try {
				password = SecretUtilTools.decryptForDES(password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");
		try {
			Map<String,String> map = SimulationCasUtil.validateFromCAS(username, password,encryptedPassword,schoolId, server, serviceUrl);
			if(map != null && map.size() > 0) {
				String tgt = map.get("tgt");
				String lt = map.get("lt");
				Cookie cookie = new Cookie("CASTGC",tgt);
				cookie.setPath("/");
				response.addCookie(cookie);
				String url = "";
				if((ssoType != null && !"".equals(ssoType)) && "oauth".equals(ssoType)) {					url = serviceUrl;
				} else {
					url = serviceUrl + "?ticket="+lt;
				}
				response.sendRedirect(url);
				/*;
				json.accumulate("status", "0");
				json.accumulate("url", url);
				response.getWriter().println(json);*/
			}
		} catch (Exception e) {
			/*json.accumulate("status", "-1");
			json.accumulate("msg", "服务器异常请联系管理员");
			response.getWriter().println(json);*/
			e.printStackTrace();
		}
		
	}

}
