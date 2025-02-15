package com.mkkabi.dev.tools;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface ToDo {
    String description() default ""; // To provide a description of the task
    String assignedTo() default "";  // To assign the task to someone
    Priority priority() default Priority.MEDIUM; // To set a priority level for the task
    String createdBy() default "unknown"; // To specify who created the ToDo
    String lastModified() default ""; // To track the last modification date

    enum Priority {
        LOW, MEDIUM, HIGH
    }
}
