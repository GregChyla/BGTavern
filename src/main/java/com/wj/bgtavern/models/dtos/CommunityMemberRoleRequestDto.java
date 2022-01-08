package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class CommunityMemberRoleRequestDto {

    @NotBlank @Length(max = 255)
    private String name;
    @NotBlank @Length(max = 255)
    private String description;
}
