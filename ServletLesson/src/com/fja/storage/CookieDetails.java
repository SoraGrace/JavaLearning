package com.fja.storage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie的有效路径：
 * 	是指浏览器通过有效的路径访问服务器时，会在请求头中携带cookie数据，如果不是有效的路径则请求不带cookie数据。
 * 
 * 默认的cookie有效路径：
 * 	当前的web应用，比如目前就是在/ServletLesson
 * 
 * 开发者可以通过Cookie类的setPath(String uri)方法设置cookie的有效路径。
 * 
 * 
 * Cookie的局限：
 * 	1.Cookie的数据只有字符串类型
 * 	2.Cookie的数据不支持中文字符串，只能保存英文数据
 * 	3.Cookie大小限制是4KB，每个站点最多存放20个Cookie数据
 * 	4.Cookie可以被用户清除
 */
public class CookieDetails extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie cookie = new Cookie("user","meimei");
		
		//设置有效路径
		cookie.setPath("/WebTestUnit");
		
		//返回cookie,【注意】所有cookie的设置必须要在addCookie()之前
		resp.addCookie(cookie);
		
		//获取cookie值，注意这里是否能获取到cookie
		Cookie[] cookies = req.getCookies();
		if(cookies != null){
			for(Cookie c : cookies){
				System.out.println(c.getName()+" = "+c.getValue());
			}
		}else{
			System.out.println("没有cookie数据");
		}
		
		//在将有效路径设置为/WebTestUnit之后，第二次访问目前的cookie无法获取cookie信息，但是访问WebTestUnit时可以访问到meimei的cookie数据
		
		
		/**
		 * cookie的有效时间
		 * 我们可以通过setMaxAge(int expiry)方法设置cookie的有效时间
		 * 
		 * 传入参数expiry有三种：
		 * 正整数：该cookie数据保存到浏览器的缓存中（硬盘中），数值大小表示保存的时间,单位是秒
		 * 负整数：该cookie数据保存到浏览器的内存中，数值大小没有作用，只要浏览器关闭cookie就会丢失
		 * 零：表示删除对应的cookie
		 */
		Cookie _cookie = new Cookie("password","123456");
		Cookie $cookie = new Cookie("hobby","study");
		
		//最后一次访问的20秒后过期
		_cookie.setMaxAge(20);
		//关闭浏览器后过期，数值没有任何意义
		$cookie.setMaxAge(-100);
		
		resp.addCookie(_cookie);
		resp.addCookie($cookie);
		
		String cookieString = req.getHeader("cookie");
		
		//删除_cookie
		//_cookie.setMaxAge(0);
		
		System.out.println(cookieString);
	}
}
