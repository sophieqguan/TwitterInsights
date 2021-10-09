package com.project.TwitterPersonality.controller;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterAPI {

  /**
   * Get Tweets of Given User
   *
   * @param args String user - username
   * @return List<Status> - list of tweets
   */
  public static List<Status> getTweets(String user) {
    // gets Twitter instance with default credentials from .properties
    Twitter twitter = new TwitterFactory().getInstance();

    int pageno = 1;
    List<Status> statuses = new ArrayList<>();
    // iterate through all pages of tweets
    while (true) {
      try {
        int size = statuses.size();
        Paging page = new Paging(pageno++, 100);
        statuses.addAll(twitter.getUserTimeline(user, page));
        if (statuses.size() == size || statuses.size() >= 1000) // set limit to ~2000
          break;
      } catch (TwitterException te) {
        te.printStackTrace();
        System.out.println("Failed to get timeline: " + te.getMessage());
        System.exit(-1);
      }
    }
    // remove retweets
    statuses.removeIf(s -> s.isRetweet());
    return statuses;
  }

  /**
   * Get Tweets of Given User with max
   *
   * @param args String user - username
   * @param args int max - maximum number of tweets to get
   * @return List<Status> - list of tweets
   */
  public static List<Status> getTweets(String user, int max) {
    // gets Twitter instance with default credentials from .properties
    Twitter twitter = new TwitterFactory().getInstance();

    int pageno = 1;
    List<Status> statuses = new ArrayList<>();
    // iterate through all pages of tweets
    while (true) {
      try {
        int size = statuses.size();
        Paging page = new Paging(pageno++, 100);
        statuses.addAll(twitter.getUserTimeline(user, page));
        if (statuses.size() == size || statuses.size() >= max) // set limit to ~2000
          break;
      } catch (TwitterException te) {
        te.printStackTrace();
        System.out.println("Failed to get timeline: " + te.getMessage());
        System.exit(-1);
      }
    }
    // remove retweets
    statuses.removeIf(s -> s.isRetweet());
    return statuses;
  }
}
