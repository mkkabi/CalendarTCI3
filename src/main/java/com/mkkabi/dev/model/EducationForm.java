package com.mkkabi.dev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@Table(name = "education_form")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EducationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "educationform_sequence")
    @SequenceGenerator(
            name = "educationform_sequence",
            sequenceName = "educationform_sequence",
            initialValue = 30,
            allocationSize = 1
    )
    private  Long Id;

    @Pattern(regexp = "[A-Z, \\u0400-\\u042F][\\u0430-\\u0457,\\w,\\-,_(,), ,\\\"]+",
            message = "Must start with a capital Latin or Cyrillic letter followed by one or more lowercase latin or Cyrillic letters. Underscore, dash and quotations allowed")
    @Column(name = "title", nullable = false, unique = true)
    private String title;
}
