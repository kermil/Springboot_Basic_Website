package com.example.test.controller;

import com.example.test.dto.MemberDTO;
import com.example.test.dto.PostDTO;
import com.example.test.service.MemberService;
import com.example.test.service.PostService;
//import com.example.test.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;

@Controller @AllArgsConstructor
public class TestController {
    @Autowired
//    TestService testService;
    private PostService postService;
    private MemberService memberService;

    @GetMapping("/a")
    public String maina(){/*(Model model) {
        List<PostDTO> postDTOList = postService.selectPostlist();
        model.addAttribute("postList", postDTOList);
        System.out.print("postDTOList => ");
        System.out.println(postDTOList);
        return "blog";*/
        return "index";
    }
    @GetMapping("/")
    public ModelAndView main() {
        return new ModelAndView("index");
    }


//    @GetMapping("/admin")
//    public String Admin() {
//        return "admin";
//    }
    @GetMapping("/admin")
    public ModelAndView Admin() {
        return new ModelAndView("admin");
    }


    // 로그아웃 결과 페이지
//    @GetMapping("/user/logout/result")
//    public String dispLogout() {
//        return "user/logout";
//    }
    @GetMapping("/user/logout/result")
    public ModelAndView dispLogout() {
        return new ModelAndView("user/logout");
    }


    // 로그인 결과 페이지
//    @GetMapping("/user/login/result")
//    public String dispLoginResult() {
//        return "user/loginSuccess";
//    }
    @GetMapping("/user/login/result")
    public ModelAndView dispLoginResult() {
        return new ModelAndView("user/loginSuccess");
    }


    // 접근 거부 페이지
//    @GetMapping("/user/denied")
//    public String dispDenied() {
//        return "user/denied";
//    }
    @GetMapping("/user/denied")
    public ModelAndView dispDenied() {
        return new ModelAndView("user/denied");
    }


//    @GetMapping("/post")
//    public String post() { return "blog_post"; }
    @GetMapping("/post")
    public ModelAndView post() {
        return new ModelAndView("blog_post");
    }


//    @PostMapping("/postProc")
//    public String insertPost(PostDTO postDTO) {
//        postService.insertPost(postDTO);
//        return "redirect:/blog";
//    }
    @PostMapping("/postProc")
    public ModelAndView insertPost(PostDTO postDTO, Principal principal) {
        postDTO.setAuthor_midx(memberService.getMemberIdx(principal.getName()));
        postService.insertPost(postDTO);
        return new ModelAndView("redirect:/blog");
    }


//    @GetMapping("/user/signin")
//    public String signin()  {
//        return "user/signin";
//    }
    @GetMapping("/user/signin")
    public ModelAndView signin()  {
        return new ModelAndView("user/signin");
    }


//    @GetMapping("/user/signup")
//    public String signup() { return "user/signup"; }
    @GetMapping("/user/signup")
    public ModelAndView signup() {
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.addObject("memberDTO", new MemberDTO());
        modelAndView.setViewName("user/signup");

        return modelAndView;
    }

//    @PostMapping("/user/signinProc")
//    public ModelAndView signinproc(MemberDTO memberDTO, BindingResult bindingResult) throws Exception {
//        int check = memberService.selectMember(memberDTO.getId());
//
//        if(check == 0) {
//            bindingResult.rejectValue("id","error.id", "존재하지 않는 아이디입니다.");
//            return new ModelAndView("/user/signin");
//        }
//
//
//    }


//    @PostMapping("/user/openSignup.do")
//    public ModelAndView openSignup(CommandMap commandMap) throws Exception {
//        ModelAndView modelAndView= new ModelAndView("/user/signup");
//
//        return modelAndView;
//    }

//    @GetMapping("/user/checkUserID")
//    public int checkUserID(String id) throws Exception {
//        int checkResult = memberService.selectMember(id);
//        return checkResult;
//
//    }

//    @PostMapping("/user/signupProc")
//    public String insertMember(MemberDTO memberDTO) {
//        memberService.insertMember(memberDTO);
//        return "redirect:/user/signin";
//    }
    @PostMapping("/user/signup")//signupProc
    public ModelAndView insertMember(@Valid MemberDTO memberDTO, BindingResult bindingResult/*, ModelMap modelMap*/) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        int check = memberService.selectMember(memberDTO.getId());

        if(check == 1) {
            System.out.println("1");
            bindingResult.rejectValue("id","error.id", "해당 아이디는 사용중입니다.");
            modelAndView.setViewName("user/signup");


        } else {
            System.out.println("2");
            memberService.insertMember(memberDTO);

            modelAndView.addObject("signup",true);
            modelAndView.setViewName("redirect:/user/signin");
        }
        return modelAndView;
    }


//    @GetMapping("/user/myinfo")
//    public String myinfo() {
//        return "user/myinfo";
//    }
    @GetMapping("/user/myinfo")
    public ModelAndView myinfo(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        MemberDTO memberDTO = new MemberDTO();
        Long memberIdx = memberService.getMemberIdx(principal.getName());
        memberDTO = memberService.selectMember(memberIdx);

        modelAndView.addObject("memberDTO", memberDTO);
        modelAndView.setViewName("user/myinfo");

        return modelAndView;
    }


//    @GetMapping("/blog")
//    public String postList(Model model) {
//        List<PostDTO> postDTOList = postService.selectPostlist();
//        model.addAttribute("postList", postDTOList);
//
//        return "blog";
//    }
    @GetMapping("/blog")
    public ModelAndView postList() {
        List<PostDTO> postDTOList = postService.selectPostlist();
        ModelAndView modelAndView = new ModelAndView("blog");
        modelAndView.addObject("postList", postDTOList);

        return modelAndView;
    }


//    @GetMapping("/post/{idx}")
//    public String postDetail(@PathVariable("idx") Long idx, Model model) {
//        model.addAttribute("postDetail", postService.selectPost(idx));
//
//        return "postDetail";
//    }
    @GetMapping("/post/{idx}")
    public ModelAndView postDetail(@PathVariable("idx") Long idx) {
        PostDTO postDTO = postService.selectPost(idx);
        ModelAndView modelAndView = new ModelAndView("postDetail");
        modelAndView.addObject("postDetail", postDTO);

        return modelAndView;
    }
}
