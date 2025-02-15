package com.mkkabi.dev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "timeframes")
public class TimeFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timeframe_sequence")
    @SequenceGenerator(
            name = "timeframe_sequence",
            sequenceName = "timeframe_sequence",
            initialValue = 30,
            allocationSize = 1
    )
    private long id;

    @EqualsAndHashCode.Exclude
    @Range(min = 1, max = 90, message = "Only number between 1 and 90 allowed")
    @Column(name = "order_number", nullable = false, unique = true)
    private int orderNumber;

    @EqualsAndHashCode.Exclude
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @EqualsAndHashCode.Exclude
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @EqualsAndHashCode.Include
    @Pattern(regexp = "[A-Z \\u0400-\\u04FF \\u0430-\\u0457. /?'\\\":;`!()]{2,65}",
            message = "Must start with a capital Latin or Cyrillic letter followed by one or more lowercase latin or Cyrillic letters. " +
                    "Underscore, dash and quotations allowed. up to 65 characters")
    @Column(name = "title", nullable = false, unique = true)
    private String title;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Pattern(regexp = "[\\w, #]{3,7}", message = "only colors in hex format, without leading #")
    @Column
    private String color;

}
