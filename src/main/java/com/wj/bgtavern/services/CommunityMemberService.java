package com.wj.bgtavern.services;

import com.wj.bgtavern.exceptions.communitymember.CommunityMemberAlreadyExistsException;
import com.wj.bgtavern.exceptions.communitymember.CommunityMemberNotFoundException;
import com.wj.bgtavern.models.CommunityMember;
import com.wj.bgtavern.models.dtos.CommunityMemberRequestDto;
import com.wj.bgtavern.models.dtos.CommunityMemberResponseDto;
import com.wj.bgtavern.models.dtos.mappers.CommunityMemberMapper;
import com.wj.bgtavern.repositories.CommunityMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityMemberService {

    private final CommunityMemberRepository communityMemberRepository;


    public List<CommunityMemberResponseDto> getCommunityMembers() {
        return CommunityMemberMapper.mapToCommunityMemberResponseDtos(communityMemberRepository.findAll());
    }

    public CommunityMemberResponseDto addCommunityMember(CommunityMemberRequestDto communityMemberRequestDto) {
        if (communityMemberRepository.existsByNameAndNationality(
                communityMemberRequestDto.getName(), communityMemberRequestDto.getNationality()))
            throw new CommunityMemberAlreadyExistsException(
                        communityMemberRequestDto.getName(), communityMemberRequestDto.getNationality());

        CommunityMember communityMember = communityMemberRepository.save(
                CommunityMemberMapper.mapToCommunityMember(communityMemberRequestDto));

        return CommunityMemberMapper.mapToCommunityMemberResponseDto(communityMember);
    }

    public CommunityMemberResponseDto editCommunityMember(Long id, CommunityMemberRequestDto communityMemberRequestDto) {
        if (!communityMemberRepository.existsById(id))
            throw new CommunityMemberNotFoundException(id);
        if (communityMemberRepository.existsByNameAndNationalityAndNotWithId(
                communityMemberRequestDto.getName(), communityMemberRequestDto.getNationality(), id))
            throw new CommunityMemberAlreadyExistsException(
                    communityMemberRequestDto.getName(), communityMemberRequestDto.getNationality());

        CommunityMember communityMember = CommunityMemberMapper.mapToCommunityMember(communityMemberRequestDto, id);
        communityMemberRepository.save(communityMember);

        return CommunityMemberMapper.mapToCommunityMemberResponseDto(communityMember);
    }

    public void deleteCommunityMember(Long id) {
        if (!communityMemberRepository.existsById(id))
            throw new CommunityMemberNotFoundException(id);

        communityMemberRepository.deleteById(id);
    }
}
