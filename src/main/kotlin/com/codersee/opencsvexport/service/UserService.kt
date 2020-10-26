package com.codersee.opencsvexport.service

import com.codersee.opencsvexport.model.User
import org.springframework.stereotype.Service

@Service
class UserService {
    fun findAllUsers() =
        listOf(
            User(1, "piotr@codersee.com", "Piotr"),
            User(2, "adam@codersee.com", "Adam"),
            User(3, "john@codersee.com", "John"),
            User(4, "karim@codersee.com", "Karim")
        )
}