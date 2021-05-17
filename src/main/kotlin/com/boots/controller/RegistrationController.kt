package com.boots.controller

import com.boots.entity.MyUser
import com.boots.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping


@Controller
class RegistrationController {
    @Autowired
    private val userService: UserService? = null
    @GetMapping("/registration")
    fun registration(model: Model): String {
        model.addAttribute("userForm", MyUser())
        return "registration"
    }

    @PostMapping("/registration")
    fun addUser(@ModelAttribute("myUserForm") myUserForm: MyUser, bindingResult: BindingResult, model: Model): String {
        if (bindingResult.hasErrors()) {
            return "registration"
        }
        if (myUserForm.password != myUserForm.passwordConfirm) {
            model.addAttribute("passwordError", "Пароли не совпадают")
            return "registration"
        }
        if (!userService!!.saveUser(myUserForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует")
            return "registration"
        }
        return "redirect:/login"
    }
}