package com.xbo.studyspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResp implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
