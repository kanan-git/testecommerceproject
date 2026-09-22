package com.firstjavaapp.ecommerce.entity;

import com.firstjavaapp.ecommerce.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column()
    private String lastName = "";

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 32)
    private String password;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
