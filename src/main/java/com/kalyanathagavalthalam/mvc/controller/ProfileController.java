package com.kalyanathagavalthalam.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kalyanathagavalthalam.bean.Gender;
import com.kalyanathagavalthalam.bean.Profile;
import com.kalyanathagavalthalam.bean.ProfileCard;
import com.kalyanathagavalthalam.bean.ProfileEntry;
import com.kalyanathagavalthalam.bean.SearchCriteria;
import com.kalyanathagavalthalam.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ProfileController {


  @Autowired
  private ProfileService profileService;

  @PostMapping("/profile/add")
  public String add(@ModelAttribute("profileEntry")ProfileEntry profileEntry, Model model) {
    String id = profileService.addProfile(profileEntry);
    model.addAttribute("ID",id);

    return "add-success";
  }


  @GetMapping({"/add-profile.html"})
  public String addMapping(Model model) {
    model.addAttribute("profileEntry", new ProfileEntry());
    return "add-profile";
  }

  @GetMapping({"/search-profile.html"})
  public String searchMapping(Model model) {
    model.addAttribute("searchCriteria", new SearchCriteria());
    return "search-profile";
  }

  @GetMapping({"/profile/get/{matrimonyID}"})
  public String searchMapping(@PathVariable String matrimonyID ,Model model) throws JsonProcessingException {
    Profile p = profileService.get(matrimonyID);
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
    String value=  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(p);
    model.addAttribute("profile",value);
    return "ViewProfile";
  }
  @PostMapping(value="/profile/fetch")
  public ModelAndView getProfile(SearchCriteria criteria) throws JsonProcessingException {
    List<ProfileCard> results = profileService
      .fetchProfiles(criteria);

   ModelAndView model = new ModelAndView("search-results::data");
    model.addObject("profiles",results);

    return model;
  }

  @GetMapping("/test.html")
  public String getTest(Model model) {
    return "test";
  }
  @GetMapping("/profile/fetch/all")
  public String getAllProfiles( Model model) {
    List<ProfileCard> results = profileService.fetchProfiles(new SearchCriteria());

    model.addAttribute("cards",results);
    return "view-all";
  }



  @GetMapping({"/","/index.html"})
  public String indexMapping(SearchCriteria criteria, Model model) {
    return "index.html";
  }

}
