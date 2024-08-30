package io.sm.exceptions.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persons")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String fullName;
    private int age;
}
