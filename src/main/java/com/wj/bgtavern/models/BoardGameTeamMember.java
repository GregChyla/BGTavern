package com.wj.bgtavern.models;

import lombok.Getter;
import lombok.Setter;
import com.wj.bgtavern.models.CommunityMember;
import com.wj.bgtavern.models.CommunityMemberRole;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="board_game_team_members")
public class BoardGameTeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long boardGameId;
//    private long communityMemberId;
//    private long communityMemberRoleId;

    @ManyToOne
    @JoinColumn(name="communityMemberId", referencedColumnName = "id")
    private CommunityMember member;

    @ManyToOne
    @JoinColumn(name="communityMemberRoleId", referencedColumnName = "id")
    private CommunityMemberRole role;

}
