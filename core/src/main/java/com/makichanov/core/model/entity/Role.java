package com.makichanov.core.model.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_r_id_seq")
    @SequenceGenerator(name = "roles_r_id_seq", sequenceName = "roles_r_id_seq", allocationSize = 1)
    @Column(name = "ro_id")
    private Integer id;

    @Column(name = "ro_name")
    private String name;

    @OneToMany(mappedBy = "role")
    @EqualsAndHashCode.Exclude
    private List<User> users;

    @Override
    public String getAuthority() {
        return name;
    }

}
