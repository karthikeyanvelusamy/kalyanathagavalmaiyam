package com.kalyanathagavalthalam.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntry {

  //Personal Details
  String name;
  String fatherName;
  String motherName;
  Gender gender;
  String nativeDistrict;
  String nativeVillage;
  String latitude;
  String longitude;
  String livingDistrict;
  String workLocation;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate dob;
  int height;
  String profession;
  int monthlyIncome;
  boolean isWorkingProfessional;
  boolean isOwnBusiness;
  String photoFilePath;
  String qualification;
  String kulam;
  String kulaDeivamSamy;
  String kulaDeivamSamyOor;


  //Assets
  String houseType;
  int houseValue;
  boolean isAgriLandOwned;
  int agriLand;
  int agriLandValue;
  int siteLand;
  int siteLandValue;
  String otherAssets;
  int otherAssetsValue;

  int otherIncomeFromAssets;



  String time;
  String tamilYear;
  String tamilMonth;
  String tamilDate;
  String tamilDay;
  int natchathiram;
  int rasi;
  String laknam;
  String mahaDisaiIruppu;
  Kattam rasiKattam;
  Kattam navamsamKattam;
  boolean Ragukethu;
  boolean Chevvai;
  boolean ParikaraChevvai;

  //Contacts
  String contactNumber1;
  String contactNumber2;
  String contactPerson;

  String profileCreatedBy;

}
