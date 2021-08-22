package com.kalyanathagavalthalam.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCard {

  String name;
  int age;
  String photoData;
  String nativeVillage;
  Location location;
  String nativeDistrict;
  String livingDistrict;
  String qualification;
  String profession;
  int height;

  int annualIncome;
  String kulam;

  String matrimonyID;

  public static ProfileCard from(Profile profile) {

    ProfileCard profileCard = new ProfileCard();
    try {
      BeanUtils.copyProperties(profileCard, profile);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    return profileCard;
  }
}
