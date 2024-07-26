package com.ashokIt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokIt.model.MyUser;
import com.ashokIt.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class userController {
	@Autowired
	UserRepository userRepo;

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("user", new MyUser());
		return "login";
	}

	@PostMapping("/loginSubmit")
	public String homeRedirectLogin(@Valid @ModelAttribute(value = "user") MyUser user, BindingResult result,
			HttpSession session) {
		Optional<MyUser> savedUser = userRepo.findOne(Example.of(user));
		if (result.hasErrors()) {
			return "login";
		}
		if (savedUser.isEmpty()) {
			userRepo.save(user);
			session.setAttribute("user", user);
		} else {
			session.setAttribute("user", savedUser.get());
		}

		return "redirect:home";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:login";
	}
}
