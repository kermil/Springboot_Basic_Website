package com.example.test.service;

import com.example.test.domain.Entity.MemberEntity;
import com.example.test.domain.Role;
import com.example.test.domain.repository.MemberRepository;
import com.example.test.dto.MemberDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// https://github.com/victolee93/Springboot-boardCRUD/blob/master/src/main/java/com/victolee/board/service/BoardService.java

@Service @AllArgsConstructor
public class MemberService implements UserDetailsService {

    private MemberRepository memberRepository;

    @Transactional
    public List<MemberDTO> selectMemberlist() {
        List<MemberEntity> memberEntities = memberRepository.findAll();
        List<MemberDTO> postDTOList = new ArrayList<>();

        for ( MemberEntity memberEntity : memberEntities) {
            MemberDTO memberDTO = MemberDTO.builder()
                    .idx(memberEntity.getIdx())
                    .id(memberEntity.getId())
                    .password(memberEntity.getPassword())
                    .name(memberEntity.getName())
                    .nickname(memberEntity.getNickname())
                    .created_at(memberEntity.getCreated_at())
                    .updated_at(memberEntity.getUpdated_at())
                    .build();

            postDTOList.add(memberDTO);
        }

        return postDTOList;
    }

    @Transactional
    public MemberDTO selectMember(Long idx) {
        Optional<MemberEntity> memberEntityWrapper = memberRepository.findById(idx);
        MemberEntity memberEntity = memberEntityWrapper.get();

        MemberDTO memberDTO = MemberDTO.builder()
                .idx(memberEntity.getIdx())
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .nickname(memberEntity.getNickname())
                .created_at(memberEntity.getCreated_at())
                .updated_at(memberEntity.getUpdated_at())
                .build();

        return memberDTO;
    }

    public int selectMember(String id) {
        Optional<MemberEntity> memberEntityWrapper = memberRepository.findById(id);
        if((Object)memberEntityWrapper != Optional.empty()) {
            return 1;
        } else {
            return 0;
        }
    }

    public Long getMemberIdx(String id) {
        Optional<MemberEntity> memberEntityWrapper = memberRepository.findById(id);
        MemberEntity memberEntity = memberEntityWrapper.get();

        return memberEntity.getIdx();
    }

    @Transactional
    public Long insertMember(MemberDTO memberDTO) {
//        Optional<MemberEntity> memberEntityWrapper = memberRepository.findById(memberDTO.getId());
//        MemberEntity memberEntity = memberEntityWrapper.get();
//        if(memberEntity.getIdx() == null) {
////            아이디 중복 체크
////            throw "error";
//        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        return memberRepository.save(memberDTO.toEntity()).getIdx();
    }

    @Transactional
    public void deleteMember(Long idx) {
        memberRepository.deleteById(idx);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<MemberEntity> memberEntityWrapper = memberRepository.findById(userId);
        MemberEntity memberEntity = memberEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin").equals(userId)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(memberEntity.getId(), memberEntity.getPassword(), authorities);
    }

}
