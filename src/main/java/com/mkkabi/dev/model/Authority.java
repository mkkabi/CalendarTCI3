package com.mkkabi.dev.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "autorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "authority")
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
