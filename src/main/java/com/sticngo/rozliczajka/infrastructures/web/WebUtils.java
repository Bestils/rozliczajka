package com.sticngo.rozliczajka.infrastructures.web;


public class WebUtils {
  private static final String REDIRECT = "redirect:";
  private static final String FORWARD = "forward:";

  public static String redirectTo(String url) {
    return REDIRECT + url;
  }

  public static String forwardTo(String url) {
    return FORWARD + url;
  }
}
