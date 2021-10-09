package com.project.TwitterPersonality.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesReader {

  // properties file
  private static final String PROP_FILE = "./ibmnlp.properties";
  private static final Properties PROPERTIES;

  private PropertiesReader() {}

  static {
    PROPERTIES = new Properties();
    InputStream input;
    try {
      input = new FileInputStream("./ibmnlp.properties");
      PROPERTIES.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method getProperty.
   *
   * @param name String name file.
   * @return Return property
   */
  public static String getProperty(final String name) {
    return PROPERTIES.getProperty(name);
  }
}
