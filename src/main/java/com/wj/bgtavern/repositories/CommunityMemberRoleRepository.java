package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.CommunityMemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityMemberRoleRepository extends JpaRepository<CommunityMemberRole, Long> {
    boolean existsByName(String name);
}
