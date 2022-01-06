package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.BoardGameTeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardGameTeamMemberRepository extends JpaRepository<BoardGameTeamMember, Long> {

    List<BoardGameTeamMember> findAllByBoardGameId(Long boardGameId);

//    boolean existsByBoardGameIdAndCommunityMemberIdAndCommunityMemberRoleId(long boardGameId, long communityMemberId, long communityMemberRoleId);

    boolean existsByBoardGameId(long boardGameId);

    boolean existsByRoleId(long id);

    boolean existsByMemberId(long id);
}
