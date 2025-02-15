package com.mkkabi.dev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "events")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Event implements Comparable<Event>{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_sequence")
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            initialValue = 30,
            allocationSize = 1
    )
    private long id;

    @NotBlank(message = "The 'title' cannot be empty")
    @Column(name = "title", nullable = false)
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

    @Column(name = "week_number", nullable = false)
    private int week;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany
    @JoinTable(name = "event_attendees",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id"))
    private List<User> attendees;

    @Column(name="durationMinutes", nullable = true)
    private long durationMinutes;

    public void setDuration(LocalDateTime start, LocalDateTime end){
        Duration tmp = Duration.between(start, end);
        this.durationMinutes = tmp.toMinutes();
    }

    public Event(LocalDateTime start, LocalDateTime end){
        this.startTime = start;
        this.endTime = end;
        Duration tmp = Duration.between(start, end);
        this.durationMinutes = tmp.toMinutes();
    }

    @Override
    public int compareTo(Event o) {
        return this.getStartTime().compareTo(o.getStartTime());
    }
}
