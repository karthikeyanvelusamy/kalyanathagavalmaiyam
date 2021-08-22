package com.kalyanathagavalthalam.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCriteria {

  private Gender gender = Gender.BOTH;

  private Location location;
  private Integer radiusKM;

  private Boolean isAgriLandAvailable;
  private Integer agriLandLimit;
  private Integer assetValueMin;
  private Integer assetValueMax;

  private Boolean isWorkingPersonal;
  private Boolean isOwnBusiness;
  private Integer monthlyEarningsMin;
  private Integer monthyEarningsMax;

  private Integer natchathiram;
  private Integer rasi;
  private Boolean isChevvai;
  private Boolean isParikaraChevvai;
  private Boolean isRagukethu;

  private String matrimonyID;
}
