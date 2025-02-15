package com.mkkabi.dev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            initialValue = 10,
            allocationSize = 1
    )
    private long id;

    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    @Column(name = "last_name", nullable = false)
    private String lastName;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // oThe mappedBy property is what we use to tell Hibernate which variable we are using to represent the parent class in our child class.
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Event> myEvents;

    @ManyToMany
    @JoinTable(name = "event_attendees",
            joinColumns = @JoinColumn(name = "attendee_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> attendingEvents;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

}
