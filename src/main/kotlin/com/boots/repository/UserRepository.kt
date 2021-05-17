package com.boots.repository

import com.boots.entity.MyUser
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<MyUser?, Long?> {
    fun findByUsername(username: String?): MyUser?
}
