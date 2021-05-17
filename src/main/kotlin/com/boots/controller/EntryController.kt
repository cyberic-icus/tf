package com.boots.controller

import com.boots.entity.Entry
import com.boots.service.EntryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.io.IOException
import java.lang.Exception


@Controller
class EntryController {
    @Autowired
    private val entryService: EntryService? = null
    @PostMapping("/entry")
    @Throws(IOException::class)
    fun saveEntry(
        @RequestParam(required = false, defaultValue = "") EntryId: Long?,
        @RequestParam(required = false, defaultValue = "") action: String,
        entry: Entry?,
        model: Model?
    ): String {
        if (action == "delete") {
            try {
                entryService!!.deleteEntry(EntryId!!)
            } catch (e: Exception) {
                println(e)
            }
        }
        entryService!!.save(entry!!)
        return "redirect:/entries"
    }

    @GetMapping("/entries")
    fun getListEntries(model: Model): String {
        println(entryService!!.myEntries)
        model.addAttribute("getListEntries", entryService.myEntries)
        return "entries"
    }

    @PostMapping("/entries")
    fun entriesActions(
        @RequestParam(required = false, defaultValue = "") EntryId: Long?,
        @RequestParam(required = false, defaultValue = "") action: String,
        model: Model
    ): String {
        if (action == "delete") {
            try {
                entryService!!.deleteEntry(EntryId!!)
            } catch (e: Exception) {
                println(e)
            }
        }
        println(entryService!!.myEntries)
        model.addAttribute("getListEntries", entryService.myEntries)
        return "entries"
    }

    @GetMapping("/entry")
    fun entryCreation(model: Model): String {
        model.addAttribute("entryForm", Entry())
        model.addAttribute("getListEntries", entryService!!.myEntries)
        return "entry"
    }
}