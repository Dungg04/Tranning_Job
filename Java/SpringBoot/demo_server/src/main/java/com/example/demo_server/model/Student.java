package com.example.demo_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    private Integer id;

    @Nationalized
    @Column(name = "name_student")
    private String name;

    @Nationalized
    @Column(name = "gender")
    private String gender;
}
