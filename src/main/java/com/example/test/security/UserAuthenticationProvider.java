//package com.example.test.security;
//
//import com.example.test.dto.MemberDTO;
//import com.example.test.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//
//public class UserAuthenticationProvider implements AuthenticationProvider {
//    @Autowired
//    MemberService memberService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String id = authentication.getName();
//        String password = (String) authentication.getCredentials();
//
//        MemberDTO memberDTO = memberService.au
//
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
