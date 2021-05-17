package com.boots.service

import com.boots.entity.Entry
import com.boots.entity.User
import com.boots.repository.EntryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.io.IOException
import java.lang.Exception


@Service
class EntryService {
    @Autowired
    private val entryRepository: EntryRepository? = null

    @Throws(IOException::class)
    fun save(entry: Entry) {
        val user = SecurityContextHolder.getContext().authentication.principal as User
        try {
            if(!(entry.header.equals("")&&(entry.body.equals("")))){
                user.addEntry(entry)
                entryRepository!!.save(entry)}
        } catch (e: Exception) { }
    }

    fun getEntry(id: Long): Entry {
        return entryRepository!!.findById(id).get()
    }

    val allEntries: List<Entry?>
        get() { return entryRepository?.findAll() as List<Entry?> }
    val myEntries: List<Entry>
        get() {
            val user = SecurityContextHolder.getContext().authentication.principal as User
            return user.getEntries()
        }

    fun deleteEntry(id: Long) {
        val user = SecurityContextHolder.getContext().authentication.principal as User
        if (entryRepository!!.findById(id).isPresent) {
            val entry = entryRepository.findById(id).get()
            try { user.removeEntry(entry)
            } catch (e: Exception) { }
            entryRepository.deleteById(id)
        }
    }
}
