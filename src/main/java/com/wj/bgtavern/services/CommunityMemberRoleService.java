package com.wj.bgtavern.services;

import com.wj.bgtavern.exceptions.communitymember.CommunityMemberNotFoundException;
import com.wj.bgtavern.exceptions.communitymemberrole.CommunityMemberRoleAlreadyExistsException;
import com.wj.bgtavern.exceptions.communitymemberrole.CommunityMemberRoleNotFoundException;
import com.wj.bgtavern.models.CommunityMember;
import com.wj.bgtavern.models.CommunityMemberRole;
import com.wj.bgtavern.models.dtos.CommunityMemberRoleRequestDto;
import com.wj.bgtavern.models.dtos.CommunityMemberRoleResponseDto;
import com.wj.bgtavern.models.dtos.mappers.CommunityMemberRoleMapper;
import com.wj.bgtavern.repositories.CommunityMemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityMemberRoleService {

    private final CommunityMemberRoleRepository communityMemberRoleRepository;


    public List<CommunityMemberRoleResponseDto> getCommunityMemberRoles() {
        return CommunityMemberRoleMapper.mapToCommunityMemberRoleResponseDtos(communityMemberRoleRepository.findAll());
    }

    public CommunityMemberRoleResponseDto addCommunityMemberRole(CommunityMemberRoleRequestDto communityMemberRoleRequestDto) {
        if (communityMemberRoleRepository.existsByName(communityMemberRoleRequestDto.getName()))
            throw new CommunityMemberRoleAlreadyExistsException(communityMemberRoleRequestDto.getName());

        CommunityMemberRole communityMemberRole = communityMemberRoleRepository.save(
                CommunityMemberRoleMapper.mapToCommunityMemberRole(communityMemberRoleRequestDto));

        return CommunityMemberRoleMapper.mapToCommunityMemberRoleResponseDto(communityMemberRole);
    }

    public CommunityMemberRoleResponseDto editCommunityMemberRole(
            Long id, CommunityMemberRoleRequestDto communityMemberRoleRequestDto) {
        if (!communityMemberRoleRepository.existsById(id))
            throw new CommunityMemberNotFoundException(id);
        if (communityMemberRoleRepository.existsByNameAndNotWithId(communityMemberRoleRequestDto.getName(), id))
            throw new CommunityMemberRoleAlreadyExistsException(communityMemberRoleRequestDto.getName());

        CommunityMemberRole communityMemberRole =
                CommunityMemberRoleMapper.mapToCommunityMemberRole(communityMemberRoleRequestDto, id);
        communityMemberRoleRepository.save(communityMemberRole);
        return CommunityMemberRoleMapper.mapToCommunityMemberRoleResponseDto(communityMemberRole);
    }

    public void deleteCommunityMemberRole(Long id) {
        if (!communityMemberRoleRepository.existsById(id))
            throw new CommunityMemberRoleNotFoundException(id);

        communityMemberRoleRepository.deleteById(id);
    }
}
