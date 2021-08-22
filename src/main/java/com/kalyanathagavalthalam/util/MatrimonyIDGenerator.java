package com.kalyanathagavalthalam.util;

import com.kalyanathagavalthalam.repo.IDFetcher;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MatrimonyIDGenerator {

  private static final MatrimonyIDGenerator INSTANCE = new MatrimonyIDGenerator();

  AtomicInteger idGen = new AtomicInteger();

  @Autowired
  private IDFetcher idFetcher;

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
}
