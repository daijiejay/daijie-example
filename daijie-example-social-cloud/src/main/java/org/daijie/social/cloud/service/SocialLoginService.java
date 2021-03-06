package org.daijie.social.cloud.service;

import javax.servlet.http.HttpServletResponse;

import org.daijie.api.social.feign.SocialLoginCloud;
import org.daijie.social.login.LoginTool;
import org.daijie.social.login.SocialLoginType;
import org.daijie.social.login.weixin.callback.WeixinLoginCallback;
import org.daijie.social.login.weixin.model.WeixinError;
import org.daijie.social.login.weixin.model.WeixinUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 第三方登录测试
 * @author daijie_jay
 * @since 2017年11月28日
 */
@Controller
public class SocialLoginService implements SocialLoginCloud {
	
	private static final Logger logger = LoggerFactory.getLogger(SocialLoginService.class);

	/**
	 * 访问微信二维码
	 * @param response
	 */
	@Override
	public String loadQrcode(String state, HttpServletResponse response){
		return LoginTool.loadQrcode(state, SocialLoginType.WEIXIN);
	}
	
	/**
	 * 微信扫码回调登录业务处理
	 * @param code
	 * @param state
	 * @return
	 */
	@Override
	public String wxCallback(@RequestParam String code, String state){
		return LoginTool.login(code, SocialLoginType.WEIXIN, new WeixinLoginCallback() {
			@Override
			public void handle(WeixinUserInfo userInfo) {
				logger.info("登录成功业务处理");
			}
			@Override
			public void errer(WeixinError error) {
				logger.info("登录失败业务处理");
				logger.error(error.getErrmsg());
			}
		});
	}
}
