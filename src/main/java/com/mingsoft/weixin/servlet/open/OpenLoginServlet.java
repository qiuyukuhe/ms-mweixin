package com.mingsoft.weixin.servlet.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingsoft.util.StringUtil;
import com.mingsoft.weixin.biz.IOauthBiz;
import com.mingsoft.weixin.biz.IWeixinBiz;
import com.mingsoft.weixin.constant.Const;
import com.mingsoft.weixin.entity.OauthEntity;
import com.mingsoft.weixin.entity.WeixinEntity;
import com.mingsoft.weixin.servlet.BaseServlet;
import com.mingsoft.weixin.util.WeixinOpenLoginUtil;

/**
 * 
 * 微信开放平台登录请求入口
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年8月28日 下午4:55:40<br/>
 * 历史修订：<br/>
 */
@WebServlet("/weixin/open/login")
public class OpenLoginServlet extends BaseServlet{
	
	/**
	 * 接受登录请求</br>
	 * 重定向到微信请求地址</br>
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前授权实体Id
		String oauthId = request.getParameter("id");
		if(!StringUtil.isInteger(oauthId)){
			logger.debug("----------oauth err");
			//返回错误地址
			response.sendRedirect(request.getContextPath()+"/500/error.do");
			return ;
		}
		
		//获取微信ID
		IOauthBiz oauthBiz = (IOauthBiz) this.getBean(request.getServletContext(), "oauthBiz");
		OauthEntity oauth = (OauthEntity) oauthBiz.getEntity(Integer.parseInt(oauthId));
		if(oauth == null){
			logger.debug("----------OauthEntity null err");
			//返回错误地址
			response.sendRedirect(request.getContextPath()+"/500/error.do");
			return ;			
		}
		int weixinId = oauth.getOauthWeixinId();
		
		//根据微信ID查询授权地址
		IWeixinBiz weixinBiz = (IWeixinBiz) this.getBean(request.getServletContext(),"weixinBiz");
		WeixinEntity weixin = weixinBiz.getEntityById(weixinId);
		if(oauth == null){
			logger.debug("----------WeixinEntity null err");
			//返回错误地址
			response.sendRedirect(request.getContextPath()+oauth.getOauthErrorUrl());
			return ;			
		}
		String weixinOauthUrl = weixin.getWeixinOauthUrl();
		if(StringUtil.isBlank(weixinOauthUrl)){
			logger.debug("----------weixinOauthUrl null err");
			//返回错误地址
			response.sendRedirect(request.getContextPath()+oauth.getOauthErrorUrl());
			return ;				
		}
		
		//拼装微信从定向授权地址
		String oauthUrl = WeixinOpenLoginUtil.getLoginUrl(weixin.getWeixinAppID(),weixinOauthUrl+Const.OAUTH_OPEN_UEL,oauthId);
		logger.debug("weixin open oauth:"+oauthUrl);
		//发起重定向请求
		response.sendRedirect(oauthUrl);
	}

}
