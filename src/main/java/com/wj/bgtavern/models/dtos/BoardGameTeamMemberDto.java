package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardGameTeamMemberDto {

    private long id;
    private String memberName;
    private String memberNationality;
    private String roleName;

}
