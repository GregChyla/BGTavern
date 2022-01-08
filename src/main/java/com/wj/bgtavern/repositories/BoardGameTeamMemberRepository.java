package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.BoardGameTeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardGameTeamMemberRepository extends JpaRepository<BoardGameTeamMember, Long> {

    @Query("select bgtm from BoardGameTeamMember bgtm " +
           "left join fetch bgtm.member left join fetch bgtm.role " +
           "where bgtm.boardGameId = :boardGameId ")
    List<BoardGameTeamMember> findAllByBoardGameId(Long boardGameId);

    @Query("select case when count(bgtm) > 0 then true else false end from BoardGameTeamMember bgtm " +
           "where bgtm.boardGameId = :boardGameId and bgtm.member.id = :communityMemberId " +
           "and bgtm.role.id = :communityMemberRoleId")
    boolean existsByAllForeignIds(long boardGameId, long communityMemberId, long communityMemberRoleId);

    @Query("select case when count(bgtm) > 0 then true else false end from BoardGameTeamMember bgtm " +
            "where bgtm.boardGameId = :boardGameId and bgtm.member.id = :communityMemberId " +
            "and bgtm.role.id = :communityMemberRoleId and bgtm.id <> :id")
    boolean existsByAllForeignIdsAndNotWithId(long boardGameId, long communityMemberId, long communityMemberRoleId, Long id);
}
