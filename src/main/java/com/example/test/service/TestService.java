//package com.example.test.service;
//
//import com.example.test.dao.*;
//import com.example.test.mapper.TestMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TestService {
//    @Autowired
//    TestMapper testMapper;
//
//    public void insertPost(postDTO postDTO) {
//        testMapper.insertPost(postDTO);
//    }
//
//    public void insertMember(memberDTO memberDTO) { testMapper.insertMember(memberDTO); }
//
//    public List<postDTO> postList() { return testMapper.postList(); }
//
//    public postDTO postDetail(int idx) { return testMapper.postDetail(idx); }
//}
