package org.finartz.homework.web.controller;

import java.util.List;

import org.finartz.homework.form.SearchForm;
import org.finartz.homework.model.TrackModel;
import org.finartz.homework.service.AuthService;
import org.finartz.homework.service.SearchService;
import org.finartz.homework.web.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private SearchService searchService;

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("messag	e", message);

		return "index";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public String authenticate(Model model) {

		authService.createAccessToken();
		model.addAttribute("accessToken", authService.getAccessToken());

		return "index";
	}
	
	@RequestMapping(value = "/doSearch", method = RequestMethod.GET)
	public String showSearchForm(Model model) {
		SearchForm searchForm= new SearchForm();
		model.addAttribute("searchForm",searchForm);
		return "doSearch";
	}

	@RequestMapping(value = "/doSearch", method = RequestMethod.POST)
	public ModelAndView doSearch(@ModelAttribute("searchForm")SearchForm searchForm) {
		SearchDTO searchDTO= new SearchDTO();
		searchDTO.setAccessToken(searchForm.getAccessToken());
		searchDTO.setQuery(searchForm.getQuery());
		searchDTO.setLimit(searchForm.getLimit());
		searchDTO.setOffSet(searchForm.getOffSet());
		searchDTO.setType(searchForm.getType());
		List<TrackModel> trackModelList=searchService.getTracks(searchDTO);
		ModelAndView mav=new ModelAndView("result");
		mav.addObject("trackModelList",trackModelList);
		return mav;
	}

}