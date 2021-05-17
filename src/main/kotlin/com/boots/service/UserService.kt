package com.boots.service

import com.boots.entity.MyUser
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
        return userRepository!!.findByUsername(username) ?: throw UsernameNotFoundException("MyUser not found")
    }

    fun findUserById(userId: Long): MyUser? {
        val userFromDb = userRepository!!.findById(userId)
        return userFromDb.orElse(MyUser())
    }

    fun allUsers(): List<MyUser?> {
        return userRepository!!.findAll()
    }

    fun saveUser(myUser: MyUser): Boolean {
        val userFromDB = userRepository!!.findByUsername(myUser.username)
        if (userFromDB != null) {
            return false
        }

        myUser.setPassword(bCryptPasswordEncoder!!.encode(myUser.password))
        userRepository!!.save(myUser)
        return true
    }

    fun deleteUser(userId: Long) {
        val user = SecurityContextHolder.getContext().authentication.principal as MyUser
        val id: Long =1
        if ((userRepository!!.findById(userId).isPresent)&&(user.id?.equals(id) == true)&&((id!=userId))) {
            userRepository!!.deleteById(userId)
        }
    }

    fun usergtList(idMin: Long?): List<MyUser> {
        return em!!.createQuery("SELECT u FROM MyUser u WHERE u.id > :paramId", MyUser::class.java)
            .setParameter("paramId", idMin).resultList
    }
}
