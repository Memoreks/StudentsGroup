package org.example.studentsgroup.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.studentsgroup.enums.FinancingEnum;
import org.example.studentsgroup.enums.GenderEnum;
import org.example.studentsgroup.enums.StatusEnum;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private GenderEnum gender;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @Column(name = "type_of_financing")
    @Enumerated(EnumType.ORDINAL)
    private FinancingEnum typeOfFinancing;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}