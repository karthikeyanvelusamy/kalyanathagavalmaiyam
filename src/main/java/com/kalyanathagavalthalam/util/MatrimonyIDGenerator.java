package com.kalyanathagavalthalam.util;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;


public class MatrimonyIDGenerator {

  private static final MatrimonyIDGenerator INSTANCE = new MatrimonyIDGenerator();

  AtomicInteger idGen = new AtomicInteger();

  public synchronized String generateID() {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int date = calendar.get(Calendar.DATE);
    ClassLoader classLoader = MatrimonyIDGenerator.class.getClassLoader();
    String fileName = "static/idfile.txt";
    File file = new File(classLoader.getResource(fileName).getFile());
    try {
      int curret = Integer.parseInt(FileUtils.readFileToString(file, "UTF-8"));
      idGen.set(curret);

      FileUtils.write(file, String.valueOf(curret+1), "UTF-8");
    }catch (Exception e) {
      throw new RuntimeException("Error while creating Unique ID ");
    }

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
