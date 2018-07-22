package com.kimtis.blog.controller.web;

import com.kimtis.blog.model.response.ListPost;
import com.kimtis.blog.service.ListPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController {
	private final ListPostService listPostService;
	private static final long DEFAULT_PAGE_SIZE = 4L;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam(defaultValue = "0") long page, ModelAndView modelAndView) {
		ListPost listPost = listPostService.page(Math.max(0L, page), DEFAULT_PAGE_SIZE);
		modelAndView.addObject("listPost", listPost);
		modelAndView.setViewName("index");
		return modelAndView;
	}
}
