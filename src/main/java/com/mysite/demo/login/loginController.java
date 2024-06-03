package com.mysite.demo.login;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.mysite.demo.question.QuestionService;

public class loginController {
	


    @Autowired
    private QuestionService questionService;
    
    @PostMapping("/user/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        // 세션에 로그인 상태를 저장합니다.

        // 로그인 성공 후 리다이렉트할 URL을 반환합니다.
        return "redirect:/user/signup";
    }



}