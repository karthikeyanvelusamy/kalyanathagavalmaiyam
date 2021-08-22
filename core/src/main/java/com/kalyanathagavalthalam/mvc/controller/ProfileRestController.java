package com.kalyanathagavalthalam.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kalyanathagavalthalam.bean.ProfileCard;
import com.kalyanathagavalthalam.bean.SearchCriteria;
import com.kalyanathagavalthalam.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController("/api")
public class ProfileRestController {



  @Autowired
  ProfileService profileService;


    @RequestMapping(value="/profile/fetch/api", method= RequestMethod.POST)
    public List<ProfileCard> getProfile(SearchCriteria criteria) throws JsonProcessingException {
      return  profileService
        .fetchProfiles(criteria);
    }


}
