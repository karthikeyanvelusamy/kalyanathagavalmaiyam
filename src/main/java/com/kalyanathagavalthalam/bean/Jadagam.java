package com.kalyanathagavalthalam.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Setter
public class Jadagam {
  LocalDate englishDate;
  long time;
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

  boolean isRagukethu;
  boolean isChevvai;
  boolean isParikaraChevvai;

}
