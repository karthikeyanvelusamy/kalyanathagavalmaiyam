package com.kalyanathagavalthalam.controller;


import com.kalyanathagavalthalam.bean.Book;
import com.kalyanathagavalthalam.bean.ProfileEntry;
import com.kalyanathagavalthalam.bean.SearchCriteria;
import com.kalyanathagavalthalam.service.ProfileService;
import com.kalyanathagavalthalam.util.MatrimonyIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

public class ProfileController {

  @Autowired
  private ProfileService profileService;

  @RequestMapping(value = "/profile/add", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE )
  public ResponseEntity<?> add(ProfileEntry profileEntry) {
   return ResponseEntity.ok(profileService.addProfile(profileEntry));
  }

  @RequestMapping(value="/profile/get",method = RequestMethod.POST)
  public ResponseEntity<?> getProfiles(@RequestBody SearchCriteria criteria){
   return ResponseEntity.ok(profileService.fetchProfiles(criteria));
  }


}
