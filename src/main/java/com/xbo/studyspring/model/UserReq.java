package com.xbo.studyspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {

    String id;

    @NotBlank(message = "名称不能为空")
    String name;

}