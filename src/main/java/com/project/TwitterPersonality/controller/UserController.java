package com.project.TwitterPersonality.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import com.ibm.watson.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.natural_language_understanding.v1.model.TargetedEmotionResults;

public class UserController {

  private static final String BASE = "./data/";
  private static final String OUT = BASE + "output/";
  private static final NLPAPI NLP = new NLPAPI();

  // JAR - MAIN
  public static void main(String[] args) throws IOException {

    String path = BASE + "/users.txt";
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

    Scanner sc = new Scanner(System.in);

    List<Database> session = new LinkedList<Database>();

    while (reader.ready()) {
      String username = reader.readLine();
      NLP.setUsername(username); // processes tweets of user
      Database db = new Database(username);
      session.add(db);

      FileWriter fw = new FileWriter(OUT + username + ".csv");
      String[] HEADERS = {"type", "word", "relevance", "primary emotion", "PE pct", "emotions"};
      CSVPrinter printer = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader(HEADERS));

      System.out.println("Analyzing for " + username + "...");


      /*-
       * List<String> targets = new LinkedList<String>();
       
        System.out.println("keyword to search for: (type 'done' when finished)");
        inq = sc.next();
      
        while (!inq.equals("done")) {
          if (!db.contains(inq))
            targets.add(inq);
          inq = sc.next();
        }
      */

      List<String> targets = Utilities.fileToList(BASE + "/keywords.txt");

      System.out.println(targets.size() + " keyword(s) to search");

      List<TargetedEmotionResults> tRes = NLP.processTarget(targets);
      List<ConceptsResult> cRes = NLP.processConcepts();

      // can maybe create ENTRY class as parent to both KEYWORD and CONCEPT
      for (ConceptsResult c : cRes)
        db.addConcept(c.getText(), c.getRelevance());
      for (TargetedEmotionResults k : tRes) {
        Keyword kw = new Keyword(k.getText(), k.getEmotion());
        db.addKeyword(kw);
      }

      for (Entry<String, Keyword> e : db.keywordEntries()) {
        String output = "\n\n" + e.getValue().toString();
        System.out.println(output);
        printer.printRecord(Utilities.stringToCSV(e.getValue().toCSV()));
      }

      for (Entry<String, Concept> e : db.conceptEntries()) {
        String output = "\n\n" + e.getValue().toString();
        System.out.println(output);
        printer.printRecord(Utilities.stringToCSV(e.getValue().toCSV()));
      }

      fw.close();
    }
  }
}
