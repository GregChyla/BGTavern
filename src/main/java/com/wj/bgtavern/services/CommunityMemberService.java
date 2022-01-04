package com.wj.bgtavern.services;

import com.wj.bgtavern.models.CommunityMember;
import com.wj.bgtavern.repositories.CommunityMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityMemberService {

    private final CommunityMemberRepository communityMemberRepository;


    public List<CommunityMember> getCommunityMembers() {
        return communityMemberRepository.findAll();
    }

    public CommunityMember addCommunityMember(CommunityMember communityMember) {
        if (communityMemberRepository.existsByNameAndNationality(communityMember.getName(), communityMember.getNationality())) {
            // TODO: Not implemented yet
            return new CommunityMember();
        }
        return communityMemberRepository.save(communityMember);
    }

    public CommunityMember editCommunityMember(CommunityMember communityMember) {
        if (!communityMemberRepository.existsById(communityMember.getId())) {
            // TODO: Not implemented yet
            return null;
        }
        if (communityMemberRepository.existsByNameAndNationality(communityMember.getName(), communityMember.getNationality()))
        {
            // TODO: Not implemented yet
            return null;
        }
        return communityMemberRepository.save(communityMember);
    }

    public void deleteCommunityMember(Long id) {
        if (!communityMemberRepository.existsById(id)) {
            // TODO: Not implemented yet
            return;
        }
        communityMemberRepository.deleteById(id);
    }
}
