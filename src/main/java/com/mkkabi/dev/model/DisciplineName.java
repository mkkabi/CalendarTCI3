package com.mkkabi.dev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "discipline_names")
public class DisciplineName implements Comparable<DisciplineName> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disciplinename_sequence")
    @SequenceGenerator(
            name = "disciplinename_sequence",
            sequenceName = "disciplinename_sequence",
            initialValue = 30,
            allocationSize = 1
    )
    private long Id;

    @Pattern(regexp = "[A-Z\\u0400-\\u04FF][\\u0430-\\u0457. /?'\\\":;`!()]{1,65}",
            message = "Must start with a capital Latin or Cyrillic letter followed by one or more lowercase latin or Cyrillic letters. Underscore, dash and quotations allowed")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Override
    public int compareTo(DisciplineName o) {
        return this.name.compareTo(o.name);
    }
}
