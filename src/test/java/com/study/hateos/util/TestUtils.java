package com.study.hateos.util;

import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
public class TestUtils {

  /**
   * The getFileInStringFromClasspath method is used to read a file from
   * classpath in string format.
   *
   * @param classpath
   * @return
   */
  public static String getFileInStringFromClasspath(String classpath) {
    try {

      return FileUtils
          .readFileToString(new ClassPathResource(classpath).getFile(), "utf-8");
    } catch (IOException e) {
      throw new RuntimeException(
          "Error while reading json file in String format from classpath : " + classpath);
    }
  }
}
