package com.project.TwitterPersonality.controller;

import java.util.Map.Entry;
import com.ibm.watson.natural_language_understanding.v1.model.EmotionScores;

public class Keyword {
  private String keyword;
  private String[] primaryEmotion;
  private Emotions emotions;

  public Keyword(String k, EmotionScores list) {
    keyword = k;
    emotions = new Emotions();
    addEmotions(list);
  }

  public void addEmotions(EmotionScores list) {
    emotions.add("anger", list.getAnger());
    emotions.add("disgust", list.getDisgust());
    emotions.add("fear", list.getFear());
    emotions.add("joy", list.getJoy());
    emotions.add("sadness", list.getSadness());

    primaryEmotion = emotions.getPrimary();
  }

  public String getKeyword() {
    return keyword;
  }

  public String[] getPrimaryEmotion() {
    return primaryEmotion;
  }

  public Emotions getEmotions() {
    return emotions;
  }

  private String pct(String d) {
    return Integer.toString((int) (Double.parseDouble(d) * 100)) + "%";
  }

  @Override
  public String toString() {
    String ret = "keyword: " + keyword + "\nmain emotion: " + primaryEmotion[0] + " ("
        + pct(primaryEmotion[1]) + ") \n";
    for (Entry<String, Double> e : emotions.entries())
      ret += "\n" + e.getKey() + ": " + e.getValue();

    return ret;
  }

  public String toCSV() {
    String ret = "keyword," + keyword + "," + Utilities.NA + "," + primaryEmotion[0] + ","
        + pct(primaryEmotion[1]);
    for (Entry<String, Double> e : emotions.entries())
      ret += "," + e.getKey() + ": " + e.getValue();

    return ret;
  }

}
