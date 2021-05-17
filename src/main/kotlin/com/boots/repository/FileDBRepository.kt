package com.boots.repository

import com.boots.entity.FileDB
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface FileDBRepository : JpaRepository<FileDB?, Long?>