package com.boots.repository

import com.boots.entity.Entry
import org.springframework.data.jpa.repository.JpaRepository


interface EntryRepository : JpaRepository<Entry?, Long?>
