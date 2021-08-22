package com.kalyanathagavalthalam.repo;

import com.kalyanathagavalthalam.bean.Gender;
import com.kalyanathagavalthalam.bean.Profile;
import com.kalyanathagavalthalam.bean.ProfileCard;
import com.kalyanathagavalthalam.bean.SearchCriteria;
import com.kalyanathagavalthalam.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProfileRepoImpl  {

  private String PROFILE_COLLECTION = "profiles";
  private String PROFILE_CARDS_COLLECTION ="profile_cards";


  @Autowired
  private MongoOperations mongoOperations;

  @Autowired
  private LocationService locationService;

  public void add(Profile profile, ProfileCard profileCard) {
    mongoOperations.insert(profile,PROFILE_COLLECTION);
    mongoOperations.insert(profileCard, PROFILE_CARDS_COLLECTION);
  }

  public List<ProfileCard> fetch(SearchCriteria criteria) {
    Criteria dbCriteria;
    if(Gender.BOTH.equals(criteria.getGender())){
      dbCriteria = Criteria.where("gender").in(Gender.FEMALE,Gender.MALE);
    }else {
      dbCriteria = Criteria.where("gender").is(criteria.getGender());
    }

    buildCriteria(criteria,dbCriteria);
    Query query = new Query();
    query.addCriteria(dbCriteria);
    query.fields().include("matrimonyID");
    List<String> matIds = mongoOperations.find(query,Profile.class,PROFILE_COLLECTION)
      .stream().map(Profile::getMatrimonyID).collect(Collectors.toList());

    query = new Query();
    query.addCriteria(Criteria.where("matrimonyID").in(matIds));
    List<ProfileCard> profileCards = mongoOperations
      .find(query,ProfileCard.class,"profile_cards");

    return profileCards;
  }


  public Profile getProfile(String id) {
    Query query = new Query();
    query.addCriteria(Criteria.where("matrimonyID").is(id));

   return mongoOperations.findOne(query,Profile.class, "profiles");
  }

  private void buildCriteria(SearchCriteria criteria, Criteria dbCriteria) {

    if(criteria.getIsAgriLandAvailable()!=null) {
      dbCriteria.where("isAgriLandOwned").is(criteria.getIsAgriLandAvailable());
    }
    if(criteria.getIsChevvai()!=null) {
      dbCriteria.where("jadagam.isChevvai").is(criteria.getIsChevvai());
    }
    if (criteria.getIsRagukethu()!=null){
      dbCriteria.where("jadagam.isRagukethu").is(criteria.getIsRagukethu());
    }
    if(criteria.getIsParikaraChevvai()!=null) {
      dbCriteria.where("jadagam.isParikaraChevvai").is(criteria.getIsParikaraChevvai());
    }
    if(criteria.getIsOwnBusiness()!=null){
      dbCriteria.where("isOwnBusiness").is(criteria.getIsOwnBusiness());
    }
    if(criteria.getIsOwnBusiness()!=null){
      dbCriteria.where("isWorkingProfessional").is(criteria.getIsWorkingPersonal());
    }
    if(criteria.getNatchathiram()!=null){
      dbCriteria.where("jadagam.natchathiram").is(criteria.getNatchathiram());
    }
    if(criteria.getMonthlyEarningsMin()!=null) {
      dbCriteria.where("monthlyIncome").gte(criteria.getMonthlyEarningsMin());
    }
    if(criteria.getMonthyEarningsMax()!=null) {
      dbCriteria.where("monthlyIncome").lte(criteria.getMonthlyEarningsMin());
    }
    if(criteria.getAssetValueMin()!=null) {
      dbCriteria.where("totalAssetsValue").gte(criteria.getAssetValueMin());
    }
    if(criteria.getAssetValueMax()!=null) {
      dbCriteria.where("totalAssetsValue").lt(criteria.getAssetValueMin());
    }
    if(criteria.getRasi()!=null){
      dbCriteria.where("jadagam.rasi").is(criteria.getRasi());
    }
    if(criteria.getIsAgriLandAvailable()!=null){
      dbCriteria.where("isAgriLandOwned").is(criteria.getIsAgriLandAvailable());
    }
    if(criteria.getAgriLandLimit()!=null){
      dbCriteria.where("agriLand").lte(criteria.getAgriLandLimit());
    }

  }

  public ProfileCard getCard(String id) {
    Criteria dbCriteria = Criteria.where("matrimonyID").is(id);
    Query query = new Query();
    query.addCriteria(dbCriteria);
    return mongoOperations.findOne(query, ProfileCard.class);
  }
}
