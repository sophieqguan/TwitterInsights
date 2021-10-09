package com.project.TwitterPersonality.controller;

import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Emotions {
  // HashMap
  private TreeMap<String, Double> emotions = new TreeMap<String, Double>();

  public Emotions() {}

  public void add(String emotion, Double relevance) {
    emotions.put(emotion, relevance);
  }

  public boolean containsEmotion(String e) {
    return emotions.containsKey(e);
  }

  public double getRelevance(String emotion) {
    if (!containsEmotion(emotion))
      return 0;
    return emotions.get(emotion);
  }

  public String[] getPrimary() {
    String[] prime = new String[2];

    double max = -1;
    for (Entry<String, Double> e : entries()) {
      if (e.getValue() > max) {
        max = e.getValue();
        prime[0] = e.getKey();
        prime[1] = e.getValue().toString();
      }
    }

    return prime;
  }

  public Set<Entry<String, Double>> entries() {
    return emotions.entrySet();
  }

}
