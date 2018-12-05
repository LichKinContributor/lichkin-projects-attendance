package com.lichkin.application.controllers.pages.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.LKSession;
import com.lichkin.springframework.web.beans.LKPage;

@Controller
@RequestMapping("/admin")
public class AdminAttendancePagesController extends LKPagesController {

	@GetMapping("/attendance/{module}/index" + MAPPING)
	public LKPage linkTo(@PathVariable String module) {
		if (!LKSession.checkMenuAuth(request, "/index")) {
			throw new LKRuntimeException(LKErrorCodesEnum.NOT_FOUND);
		}
		switch (module) {
			case "compAttendanceAreaConfigMgmt":
			case "employeePunchTheClockMgmt":
				LKPage mv = new LKPage(LKPage.BLANK);
				mv.putAttribute("AmapParams", "v=1.4.10&key=fb46b662ca9ba19da9e44f5cf8c2cf0d&plugin=AMap.CircleEditor");
				return mv;
			default:
				return new LKPage(LKPage.BLANK);
		}
	}


	@PostMapping("/attendance/{module}/index" + MAPPING)
	public LKPage jumpTo(@PathVariable String module) {
		return linkTo(module);
	}

}
