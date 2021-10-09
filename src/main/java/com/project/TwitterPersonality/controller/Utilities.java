package com.project.TwitterPersonality.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import twitter4j.Status;

public class Utilities {
  private static final String MENTION = "@([a-zA-Z0-9_]*)+ ";
  public static final String NA = "NA";

  public static String tweetsToString(List<Status> tweets) {
    StringBuilder builder = new StringBuilder();
    for (Status tweet : tweets) {
      if (tweet.isRetweet())
        continue;
      String text = tweet.getText().replaceAll(MENTION, "");
      builder.append(text + "\n");
    }
    return builder.toString();
  }

  public static List<String> fileToList(String path) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
    List<String> res = new LinkedList<String>();
    while (reader.ready())
      res.add(reader.readLine());

    return res;
  }

  public static List<String> stringToCSV(String str) {
    return Arrays.asList(str.split(","));
  }

}
