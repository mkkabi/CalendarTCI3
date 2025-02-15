package com.mkkabi.dev.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_sequence")
    @SequenceGenerator(
            name = "lesson_sequence",
            sequenceName = "lesson_sequence",
            initialValue = 420,
            allocationSize = 1
    )
    private long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lesson_group",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name = "online")
    private boolean online;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classtype_id", nullable = false)
    private ClassType classType;

    @Column(name = "week_number", nullable = false)
    private int weekNumber;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

    @Column(name = "auditorium_number")
    private String auditoriumNumber = null;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name="comment")
    private String comment;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;


    public void setLessonDataFromStartDateTime(LocalDateTime startDateTime) {
        WeekFields weekFields = WeekFields.of(Locale.GERMANY);
        setWeekNumber(startDateTime.get(weekFields.weekOfWeekBasedYear()));
        setDayOfWeek(startDateTime.getDayOfWeek().name());
        setMonth(startDateTime.getMonthValue());
        setYear(startDateTime.getYear());
    }

}
