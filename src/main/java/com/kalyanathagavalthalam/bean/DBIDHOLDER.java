package com.kalyanathagavalthalam.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@Document("ID_GEN")
public class DBIDHOLDER {


  public  DBIDHOLDER() {
    todayId = 1;
    id = "current_id";
  }

  @Id
  private String id;
  private int todayId;
}
