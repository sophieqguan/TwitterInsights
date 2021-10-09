package com.project.TwitterPersonality;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.project.TwitterPersonality.controller.NLPAPI;

public class NLPAPITest {
  @Test
  public void test(String[] args) {
    NLPAPI nlp = new NLPAPI();
    String id = "BarackObama";
    nlp.setUsername(id);

    List<String> targets = new ArrayList<>();
    targets.add("young");
    targets.add("democracy");

    System.out.println(nlp.processConcepts().toString());
    try {
      System.out.println(nlp.processTarget(targets).toString());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
