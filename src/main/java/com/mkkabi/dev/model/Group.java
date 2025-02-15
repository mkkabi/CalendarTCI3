package com.mkkabi.dev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Table(name = "groups")
public class Group {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_sequence")
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            initialValue = 30,
            allocationSize = 1
    )
    private long id;

    @Range(min = 2018, max = 2040,
            message = "Only 4 digit year between 2018 and 2040")
    @Column(name = "admittance_year", nullable = false)
    private int admittanceYear;

    @Column
    private boolean graduated;

    @Range(min = 1, max = 10,
            message = "Only number between 1 and 10")
    @Column(name = "course_number", nullable = false)
    private int courseNumber;

//    @Range(min = 1, max = 9999,
//            message = "Only number year between 1 and 9999")
    @Column(name = "group_number", nullable = false, unique = true)
    private String groupNumber;

    @Column(name = "alternative_name", nullable = true)
    private String alternativeName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "session_start", nullable = true)
    private LocalDate sessionStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "session_end", nullable = true)
    private LocalDate sessionEnd;

    @ManyToOne
    @JoinColumn(name = "education_form_id")
    private EducationForm educationForm;


    @JsonIgnore
    @OneToMany(mappedBy = "disciplineGroup", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Discipline> disciplines;

//    @JsonIgnore
//    @OneToMany(mappedBy = "group", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    List<Lesson> lessons;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    @PreRemove
    public void checkReviewAssociationBeforeRemoval() {
        if (!this.disciplines.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            disciplines.stream().forEach(b -> sb.append(b.getId() + " "));
            throw new RuntimeException("Can't remove Group that has Disciplines. Disciplines IDs: " + sb);
        }
        if (!this.lessons.isEmpty()) {
            throw new RuntimeException("Can't remove Group form that has Lessons.");
        }
    }

    @Override
    public String toString() {
        return "Група " + groupNumber+" ";
    }
}
