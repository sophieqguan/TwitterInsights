package com.project.TwitterPersonality;

import java.util.List;
import org.junit.Test;
import com.project.TwitterPersonality.controller.TwitterAPI;
import twitter4j.Status;

public class TwitterAPITest {
  @Test
  public void test() {
    String user = "BarackObama";

    // get tweets
    List<Status> statuses = TwitterAPI.getTweets(user);

    // print
    System.out.println("Showing @" + user + "'s user timeline.");
    for (Status status : statuses) {
      // if (status.isRetweet())
      // continue;
      String text = status.getText().replaceAll("@([a-zA-Z0-9_]*)+ ", "");
      System.out.println(text);
    }
  }
}
