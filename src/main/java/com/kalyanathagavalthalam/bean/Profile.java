package com.kalyanathagavalthalam.bean;

import com.fasterxml.jackson.databind.node.ContainerNode;
import com.kalyanathagavalthalam.util.MatrimonyIDGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.annotation.Id;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

  @Id
  private String id;

  String matrimonyID;
  String name;
  String fatherName;
  String motherName;
  Gender gender;
  String nativeDistrict;
  String livingDistrict;
  String nativeVillage;
  Location location;
  String postCode;
  LocalDate dob;
  int age;
  int height;
  String qualification;
  String bodyComplex;
  String kulam;
  String kulaDeivamSamy;
  String kulaDeivamSamyOor;
  String profession;
  boolean isOwnBusiness;
  boolean isWorkingProfessional;
  int monthlyIncome;
  Jadagam jadagam;
  String photoFilePath;

  String houseType;
  int houseValue;
  int agriLand;
  boolean isAgriLandOwned;
  int agriLandValue;
  int siteLand;
  int siteLandValue;
  String otherAssests;
  int otherAssestsValue;

  int otherIncomeFromAssets;

  int totalAssetsValue;

  ContactDetails contactDetails;

  String profileCreatedBy;

  public static Profile from(ProfileEntry entry) {
    Profile profile = new Profile();
    try {
      BeanUtils.copyProperties(profile,entry);
      profile.setMatrimonyID(MatrimonyIDGenerator.getInstance().generateID());
      profile.setAge(Period.between(entry.getDob(), LocalDate.now()).getYears());
      profile.setTotalAssetsValue(entry.getHouseValue() + entry.getAgriLandValue()+entry.getOtherAssetsValue()+entry.getSiteLandValue());
      double lt = Double.parseDouble(entry.getLatitude());
      double lg = Double.parseDouble(entry.getLongitude());

      profile.setLocation(new Location(lt,lg));
      //Jadagam details yet to be implemented.
      Jadagam jadagam = new Jadagam();

      jadagam.setEnglishDate(entry.getDob());
      //jadagam.setTime(entry.getTime());
      jadagam.setNatchathiram(entry.getNatchathiram());
      jadagam.setRasi(entry.getRasi());
      jadagam.setChevvai(entry.isChevvai());
      jadagam.setRagukethu(entry.isRagukethu());
      jadagam.setLaknam(entry.getLaknam());
      jadagam.setParikaraChevvai(entry.isParikaraChevvai());

      profile.setJadagam(jadagam);

      ContactDetails contactDetails = new ContactDetails();
      contactDetails.setContactNumber1(entry.getContactNumber1());
      contactDetails.setContactNumebr2(entry.getContactNumber2());
      contactDetails.setContactPersonName(entry.getContactPerson());

      profile.setContactDetails(contactDetails);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return profile;
  }


}
