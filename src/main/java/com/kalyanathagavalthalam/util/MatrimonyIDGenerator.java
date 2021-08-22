package com.kalyanathagavalthalam.util;

import com.kalyanathagavalthalam.repo.IDFetcher;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MatrimonyIDGenerator {

  @Autowired
  private IDFetcher idFetcher;

  private static MatrimonyIDGenerator INSTANCE;

  AtomicInteger idGen = new AtomicInteger();

  public synchronized String generateID() {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int date = calendar.get(Calendar.DATE);
    ClassLoader classLoader = MatrimonyIDGenerator.class.getClassLoader();
    idGen.set(idFetcher.fetch());
    String zero = (idGen.get() >= 10)?"":"0";
    idGen.toString();
    String id = "KVG-"+ zero + idGen.toString() + String.valueOf(year).substring(2)
      + String.valueOf(month) + String.valueOf(date);
    return id;
  }

  public static MatrimonyIDGenerator getInstance() {
    return INSTANCE;
  }

  @PostConstruct
  public void setInstance() {
    INSTANCE =  this;
  }
}
