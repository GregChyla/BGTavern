package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommunityMemberRoleResponseDto {
    private long id;
    private String name;
    private String description;
}
