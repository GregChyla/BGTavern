package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.CommunityMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityMemberRepository extends JpaRepository<CommunityMember, Long> {
    boolean existsByNameAndNationality(String name, String nationality);
}
