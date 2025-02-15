package com.mkkabi.dev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString(doNotUseGetters = true)
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            initialValue = 30,
            allocationSize = 1
    )
    private long id;

    @NotBlank(message = "The 'name' cannot be empty")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> users;

}
