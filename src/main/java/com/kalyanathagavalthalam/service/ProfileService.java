package com.kalyanathagavalthalam.service;


  import com.kalyanathagavalthalam.bean.Profile;
  import com.kalyanathagavalthalam.bean.ProfileCard;
  import com.kalyanathagavalthalam.bean.ProfileEntry;
  import com.kalyanathagavalthalam.bean.SearchCriteria;
  import com.kalyanathagavalthalam.repo.ProfileRepoImpl;
  import org.springframework.beans.factory.annotation.Autowired;

  import org.springframework.stereotype.Service;

  import java.util.List;
  import java.util.stream.Collectors;

@Service
public class ProfileService {

  @Autowired
  private ProfileRepoImpl profileRepo;

  @Autowired
  private LocationService locationService;

  public String addProfile(ProfileEntry profileEntry) {

    Profile profile = Profile.from(profileEntry);
    ProfileCard profileCard = ProfileCard.from(profile);
    profileRepo.add(profile,profileCard);

    return profile.getMatrimonyID();
  }

  public List<ProfileCard> fetchProfiles(SearchCriteria criteria) {

      List<ProfileCard> cards = profileRepo.fetch(criteria);

      if(criteria.getRadiusKM() !=null ) {
        cards = cards.stream().filter(a -> {
          int distanceInKM = locationService.getDistanceInKM(a.getLocation(), criteria.getLocation());
          if (criteria.getRadiusKM() >= distanceInKM)
            return true;
          return false;
        }).collect(Collectors.toList());

      }
      return  cards;
  }

  public Profile get(String id) {

    return profileRepo.getProfile(id);
  }




}
