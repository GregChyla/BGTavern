package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class BoardGameTeamMemberRequestDto {
    /* boardGameId nie jest potrzebne ani przy dodawaniu [POST] ani przy edycji [PUT]
     * ponieważ gdy dodajemy nowego członka to i tak dodajemy do konkretnej gry, więc
     * identyfikator tej gry przekazujemy jako PathVariable, natomiast przy edycji członka
     * zespołu, jest on identyfikowany po identyfikatorze gry oraz swoim identyfikatorze
     * które są przekazywane jako PathVariable /board_game/{boardGameId}/team_member/{teamMemberId}
     */
//    private long boardGameId;
    @NotNull @Min(1)
    private long communityMemberId;
    @NotNull @Min(1)
    private long communityMemberRoleId;
}
