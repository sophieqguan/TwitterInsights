package com.project.TwitterPersonality.controller;

import java.util.List;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
import com.ibm.watson.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.natural_language_understanding.v1.model.KeywordsResult;
import com.ibm.watson.natural_language_understanding.v1.model.TargetedEmotionResults;
import twitter4j.Status;

public class NLPAPI {
  private static final String URL = PropertiesReader.getProperty("URL");
  private static final String API = PropertiesReader.getProperty("API");
  private static final String VERSION = PropertiesReader.getProperty("VERSION");

  private static IamAuthenticator authenticator;
  private static NaturalLanguageUnderstanding naturalLanguageUnderstanding;

  private static String text;

  public NLPAPI() {
    authenticator = new IamAuthenticator(API);
    naturalLanguageUnderstanding = new NaturalLanguageUnderstanding(VERSION, authenticator);
    naturalLanguageUnderstanding.setServiceUrl(URL);
  }

  public void setUsername(String user) {
    // if (id.charAt(0) == '@') id = id.substring(1);
    List<Status> tweets = TwitterAPI.getTweets(user, 1000);
    text = Utilities.tweetsToString(tweets);
  }

  public void setUsername(String user, int limit) {
    // if (id.charAt(0) == '@') id = id.substring(1);
    List<Status> tweets = TwitterAPI.getTweets(user, limit);
    text = Utilities.tweetsToString(tweets);
  }

  public List<TargetedEmotionResults> processTarget(List<String> targets) {

    EmotionOptions emotion = new EmotionOptions.Builder().targets(targets).build();
    Features features = new Features.Builder().emotion(emotion).build();
    AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();

    AnalysisResults response =
        naturalLanguageUnderstanding.analyze(parameters).execute().getResult();

    return response.getEmotion().getTargets();
  }

  public List<ConceptsResult> processConcepts() {
    ConceptsOptions concepts = new ConceptsOptions.Builder().limit(50).build();

    Features features = new Features.Builder().concepts(concepts).build();

    AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();

    AnalysisResults response =
        naturalLanguageUnderstanding.analyze(parameters).execute().getResult();

    return response.getConcepts();
  }

  @Deprecated
  public List<KeywordsResult> processKeywords() {
    KeywordsOptions keywords = new KeywordsOptions.Builder().sentiment(true).emotion(true).build();
    Features features = new Features.Builder().keywords(keywords).build();
    AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();

    AnalysisResults response =
        naturalLanguageUnderstanding.analyze(parameters).execute().getResult();

    return response.getKeywords();
  }
}
