package com.mkkabi.dev.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@NamedEntityGraph(
        name = "teacher-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("role"),
                @NamedAttributeNode("disciplines")
        }
)
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher-sequence")
    @SequenceGenerator(
            name = "teacher-sequence",
            sequenceName = "teacher_sequence",
            initialValue = 30,
            allocationSize = 1
    )
    private long id;

//    @Pattern(regexp = "[A-Z,\\u0400-\\u042FF][\\u0430-\\u0457 \\w . - `]{1,10}[A-Z,\\u0400-\\u042FF]{1}.[A-Z,\\u0400-\\u042FF]{1}.",
//            message = "First, last and father's name in format Марюхно В.В.")
    @Column(name = "fio", nullable = false)
    private String fio;

    @Column(name = "contact", nullable = true)
    private String contact;

    @Column(name = "comment", nullable = true)
    private String commentWishes;

    @Column(name = "hidden", nullable = false)
    private boolean hidden = false;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany
    @JoinTable(name = "disciplines_teachers",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id"))
    private List<Discipline> disciplines;

}
