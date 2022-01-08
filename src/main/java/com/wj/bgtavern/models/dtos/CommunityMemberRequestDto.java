package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class CommunityMemberRequestDto {

    @NotBlank @Length(max = 255)
    private String name;
    @NotBlank @Length(max = 255)
    private String nationality;
}
