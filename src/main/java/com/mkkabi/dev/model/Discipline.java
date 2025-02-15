package com.mkkabi.dev.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "disciplines")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discipline_sequence")
    @SequenceGenerator(
            name = "discipline_sequence",
            sequenceName = "discipline_sequence",
            initialValue = 20,
            allocationSize = 1
    )
    private long id;

    @ToString.Exclude
    @Range(min = 1, max = 10, message = "only numbers in range from 1 to 10")
    @Column(name = "semester", nullable = false)
    private int semester;

    @ToString.Exclude
    @Range(min = 1, max = 120, message = "only numbers between 1 and 200 allowed")
    @Column(name = "credits", nullable = false)
    private int credits;

    @ToString.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_name_id", nullable = false)
    private DisciplineName name;

    @ManyToOne
    @JoinColumn(name = "control_form_id", nullable = true)
    private ClassType controlForm;

    @ToString.Include
    @ManyToOne
    @JoinColumn(name = "education_form_id", nullable = true)
    private EducationForm educationForm;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group disciplineGroup;

    @ManyToMany
    @JoinTable(name = "disciplines_teachers",
            joinColumns = @JoinColumn(name = "discipline_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers;

    @ToString.Exclude
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.MERGE)
    private List<Lesson> lessons;


    @PreRemove
    public void checkReviewAssociationBeforeRemoval() {
        if (!this.teachers.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            teachers.forEach(b->sb.append(b.getId()+" "));
            throw new RuntimeException("Can't remove Discipline that has teachers. teachers IDs: "+sb);
        }
        if (!this.lessons.isEmpty()) {
            throw new RuntimeException("Can't remove Discipline that has Lessons.");
        }
    }

}
