package com.boots.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import java.util.ArrayList
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table
open class MyUser : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    private var username: @Size(min = 2, message = "Не меньше 5 знаков") String? = null
    private var password: @Size(min = 2, message = "Не меньше 5 знаков") String? = null

    @Transient
    var passwordConfirm: String? = null

    @OneToMany(mappedBy = "myUser", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val files: MutableList<FileDB> = ArrayList()

    @OneToMany(mappedBy = "myUser", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val entries: MutableList<Entry> = ArrayList()
    @Transactional
    fun addFile(file: FileDB) {
        files.add(file)
        file.myUser = this
    }

    fun getFiles(): List<FileDB> {
        return files
    }

    fun removeFile(file: FileDB) {
        for (f in files) {
            if (file.id == f.id) {
                files.remove(f)
            }
        }
        file.myUser = null
    }

    @Throws(Exception::class)
    fun removeEntry(entry: Entry) {
        for (f in entries) {
            println("AA")
            println(entry.id)
            if (entry.id == f.id) {
                entries.remove(f)
            }
        }
        entry.myUser = null
    }

    fun getEntries(): List<Entry> {
        return entries
    }

    fun addEntry(entry: Entry) {
        entries.add(entry)
        entry.myUser = this
    }

    override fun getUsername(): @Size(message = "Не меньше 5 знаков", min = 2) String? {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return null
    }

    override fun getPassword(): @Size(message = "Не меньше 5 знаков", min = 2) String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }
}