package org.example.studentsgroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.studentsgroup.enums.FinancingEnum;
import org.example.studentsgroup.enums.GenderEnum;
import org.example.studentsgroup.enums.StatusEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {

    private Integer id;
    private String fullName;
    private GenderEnum gender;
    private StatusEnum status;
    private FinancingEnum typeOfFinancing;
    private Integer groupId;
}