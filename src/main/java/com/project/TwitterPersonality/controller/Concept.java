package com.project.TwitterPersonality.controller;

public class Concept {
  private String keyword;
  private Double relevance;

  public Concept(String w, Double d) {
    keyword = w;
    relevance = d;
  }

  public String getKeyword() {
    return keyword;
  }

  public Double getRelevance() {
    return relevance;
  }

  @Override
  public String toString() {
    return keyword + ": " + relevance;
  }

  public String toCSV() {
    // concept, keyword, relevance, na, na, na
    String tmp = keyword.replaceAll(",", "");
    String ret = "concept," + tmp + "," + relevance + "," + Utilities.NA + "," + Utilities.NA + ","
        + Utilities.NA;
    return ret;
  }
}
