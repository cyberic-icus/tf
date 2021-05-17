package com.boots.service

import com.boots.entity.FileDB
import com.boots.entity.MyUser
import com.boots.repository.FileDBRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.lang.Exception
import java.util.*
import java.util.stream.Stream
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Service
class FileStorageService {
    @Autowired
    private val fileDBRepository: FileDBRepository? = null

    @PersistenceContext
    private val em: EntityManager? = null

    @Throws(IOException::class)
    fun store(file: MultipartFile) {
        val fileName = StringUtils.cleanPath(Objects.requireNonNull(file.originalFilename))
        val FileDB = FileDB(fileName, file.contentType, file.bytes)
        val user = SecurityContextHolder.getContext().authentication.principal as MyUser
        try { user.addFile(FileDB) } catch (e: Exception) { }

        fileDBRepository!!.save(FileDB)
    }

    fun getFile(id: Long): FileDB {
        return fileDBRepository!!.findById(id).get()
    }

    val allFiles: Stream<FileDB?>?
        get() { return fileDBRepository?.findAll()?.stream() }

    val myFiles: List<FileDB>
        get() {
            val user = SecurityContextHolder.getContext().authentication.principal as MyUser
            return user.getFiles()
        }

    fun deleteFile(id: Long) {
        val user = SecurityContextHolder.getContext().authentication.principal as MyUser
        if (fileDBRepository!!.findById(id).isPresent) {
            val file = fileDBRepository.findById(id).get()
            try { user.removeFile(file) } catch (e: Exception) { }
            println("FUCK")
            fileDBRepository.deleteById(id)
            println(file)
        }
    }

    @Throws(Exception::class)
    fun getDownloadData(id: Long): ResponseEntity<ByteArray> {
        val file = getFile(id)
        val output = file.data
        val responseHeaders = HttpHeaders()
        responseHeaders["charset"] = "utf-8"
        responseHeaders.contentType = MediaType.IMAGE_JPEG
        responseHeaders.contentLength = output.size.toLong()
        responseHeaders["Content-disposition"] = "attachment; filename=" + file.name
        return ResponseEntity(output, responseHeaders, HttpStatus.OK)
    }
}