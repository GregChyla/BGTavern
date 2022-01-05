package com.wj.bgtavern.services;

import com.wj.bgtavern.exceptions.communitymember.CommunityMemberNotFoundException;
import com.wj.bgtavern.exceptions.communitymemberrole.CommunityMemberRoleAlreadyExistsException;
import com.wj.bgtavern.models.CommunityMemberRole;
import com.wj.bgtavern.repositories.CommunityMemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityMemberRoleService {

    private final CommunityMemberRoleRepository communityMemberRoleRepository;


    public List<CommunityMemberRole> getCommunityMemberRoles() {
        return communityMemberRoleRepository.findAll();
    }

    public CommunityMemberRole addCommunityMemberRole(CommunityMemberRole communityMemberRole) {
        if (communityMemberRoleRepository.existsByName(communityMemberRole.getName())) {
            throw new CommunityMemberRoleAlreadyExistsException(communityMemberRole.getName());
        }
        return communityMemberRoleRepository.save(communityMemberRole);
    }

    @Transactional
    public CommunityMemberRole editCommunityMemberRole(CommunityMemberRole communityMemberRole) {
        if (!communityMemberRoleRepository.existsById(communityMemberRole.getId())) {
            throw new CommunityMemberNotFoundException(communityMemberRole.getId());
        }
        if (communityMemberRoleRepository.existsByName(communityMemberRole.getName())) {
            throw new CommunityMemberRoleAlreadyExistsException(communityMemberRole.getName());
        }
        return communityMemberRoleRepository.save(communityMemberRole);
    }
}
