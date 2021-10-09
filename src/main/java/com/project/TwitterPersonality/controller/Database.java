package com.project.TwitterPersonality.controller;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Database {
  private String username;

  private HashMap<String, Keyword> keywords;
  private HashMap<String, Concept> concepts;

  public Database(String u) {
    keywords = new HashMap<String, Keyword>();
    concepts = new HashMap<String, Concept>();
    username = u;
  }

  public void addConcept(String concept, Double relevance) {
    concepts.put(concept, new Concept(concept, relevance));
  }

  public void addConcept(Concept c) {
    concepts.put(c.getKeyword(), c);
  }

  public void addKeyword(Keyword k) {
    keywords.put(k.getKeyword(), k);
  }

  public Keyword searchKeyword(String keyword) {
    // if null, return null
    return keywords.get(keyword);
  }

  public boolean contains(String keyword) {
    return keywords.containsKey(keyword);
  }

  // additional methods like, get all happy words, get all sad words, etc.
  // TBI - to be implemented

  public int size() {
    return keywords.size();
  }

  public Set<Entry<String, Keyword>> keywordEntries() {
    return keywords.entrySet();
  }

  public Set<Entry<String, Concept>> conceptEntries() {
    return concepts.entrySet();
  }

  public String user() {
    return username;
  }
}
