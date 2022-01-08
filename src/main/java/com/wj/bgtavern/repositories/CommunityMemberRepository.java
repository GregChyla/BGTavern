package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.CommunityMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityMemberRepository extends JpaRepository<CommunityMember, Long> {
    boolean existsByNameAndNationality(String name, String nationality);

    @Query("select case when count(cm) > 0 then true else false end from CommunityMember cm " +
            "where cm.id <> :id and cm.name = :name and cm.nationality = :nationality")
    boolean existsByNameAndNationalityAndNotWithId(String name, String nationality, Long id);
}
