package com.boots.service

import com.boots.entity.User
import com.boots.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Service
class UserService : UserDetailsService {
    @PersistenceContext
    private val em: EntityManager? = null

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository!!.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
    }

    fun findUserById(userId: Long): User? {
        val userFromDb = userRepository!!.findById(userId)
        return userFromDb.orElse(User())
    }

    fun allUsers(): List<User?> {
        return userRepository!!.findAll()
    }

    fun saveUser(user: User): Boolean {
        val userFromDB = userRepository!!.findByUsername(user.username)
        if (userFromDB != null) {
            return false
        }

        user.setPassword(bCryptPasswordEncoder!!.encode(user.password))
        userRepository!!.save(user)
        return true
    }

    fun deleteUser(userId: Long) {
        val user = SecurityContextHolder.getContext().authentication.principal as User
        val id: Long =1
        if ((userRepository!!.findById(userId).isPresent)&&(user.id?.equals(id) == true)&&((id!=userId))) {
            userRepository!!.deleteById(userId)
        }
    }

    fun usergtList(idMin: Long?): List<User> {
        return em!!.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User::class.java)
            .setParameter("paramId", idMin).resultList
    }
}
