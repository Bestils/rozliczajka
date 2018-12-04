package com.sticngo.rozliczajka.infrastructure.web.tasks;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@UtilityClass
public class TaskViews {

  public static final String TASKS_ALL = "/tasks/all";
  public static final String TASK_DETAILS = "/tasks/details";
  public static final String TASK_EDIT_FORM = "/tasks/edit_form";
}
