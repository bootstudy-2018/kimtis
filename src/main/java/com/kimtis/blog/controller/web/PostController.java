package com.kimtis.blog.controller.web;

import com.kimtis.blog.model.response.DetailPost;
import com.kimtis.blog.service.DetailPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Min;

@Validated
@Controller
@RequestMapping(value = "/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {
	private final DetailPostService detailPostService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView board(@Min(0) @PathVariable Long id, ModelAndView modelAndView) {
		DetailPost post = detailPostService.findOne(id);
		modelAndView.addObject("post", post);
		modelAndView.setViewName("post/index");
		return modelAndView;
	}
}
