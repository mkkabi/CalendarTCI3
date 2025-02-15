package com.mkkabi.dev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "classtypes")
public class ClassType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classtype-sequence")
    @SequenceGenerator(
            name = "classtype-sequence",
            sequenceName = "classtype-sequence",
            initialValue = 20,
            allocationSize = 1
    )
    private long Id;

    @Pattern(regexp = "[A-Z, \\u0400-\\u042F][\\u0430-\\u0457,\\w,\\-,_(,), ,\\\"]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    @Column(name="class_type", unique = true, nullable = false)
    private String type;

    @Column
    private boolean showOnSchedule;

    @Column
    private boolean doNOTCalculateHours;

    @ToString.Exclude
    @Pattern(regexp = "[\\w, #]{3,7}", message = "only colors in hex format, without leading #")
    @Column
    private String color;


}
