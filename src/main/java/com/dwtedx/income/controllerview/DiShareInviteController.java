package com.dwtedx.income.controllerview;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dwtedx.income.service.IDiVersionService;

@Controller
@RequestMapping("/invite")
public class DiShareInviteController {

	@Resource
	private IDiVersionService diVersionService;

	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public ModelAndView toShareInvite(HttpServletRequest request) {
		
		//String ip = getIpAddress(request);
		//Logger.getLogger(DiShareInviteController.class).error("浏览器请求ip====================" + ip);

		ModelAndView mvAndView = new ModelAndView();
		//mvAndView.addObject("ipAddress", ip);
		
		mvAndView.setViewName("shareinvite/share");
		return mvAndView;
	}

	public String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-real-ip");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
