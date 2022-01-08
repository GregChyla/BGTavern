package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.CommunityMemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityMemberRoleRepository extends JpaRepository<CommunityMemberRole, Long> {

    boolean existsByName(String name);

    @Query("select case when count(cmr) > 0 then true else false end from CommunityMemberRole cmr " +
            "where cmr.id <> :id and cmr.name = :name")
    boolean existsByNameAndNotWithId(String name, Long id);
}
