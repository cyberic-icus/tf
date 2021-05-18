package com.boots.controller

import com.boots.service.FileStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse


@Controller
class FileController {
    @Autowired
    private val storageService: FileStorageService? = null

    @PostMapping("/files")
    fun uploadFile(
        @RequestParam(required = false, defaultValue = "") FileId: Long?,
        @RequestParam(required = false, defaultValue = "") action: String?,
        @RequestParam(required = false, name = "file") file: MultipartFile?, model: Model
    ): String {
        if (action == "delete") {
            try {
                storageService!!.deleteFile(FileId!!)
            } catch (e: Exception) { }
        } else if (action == "download") {
            try {
                storageService!!.getDownloadData(FileId!!)
            } catch (e: Exception) { }
        } else {
            try {
                if (!(file?.name == "file" && file?.size == 0L && file?.contentType == "application/octet-stream")) file?.let {
                    storageService!!.store(
                        it
                    )
                }
            } catch (e: Exception) {
                return "files"
            }
        }
        model.addAttribute("getListFiles", storageService?.myFiles)
        return "files"
    }

    @GetMapping("/files")
    fun getListFiles(
        @RequestParam(required = false, defaultValue = "") FileId: Long?,
        @RequestParam(required = false, defaultValue = "") action: String,
        model: Model,
        response: HttpServletResponse
    ): String {
        if (action == "download") {
            try {
                val file = storageService!!.getFile(FileId!!)
                response.contentType = "application/octet-stream"
                response.setHeader("Content-Disposition", "attachment; filename=" + file.name)
                val os = response.outputStream
                os.write(file.data)
                os.close()
            } catch (e: Exception) { }
        } else {
            model.addAttribute("getListFiles", storageService?.myFiles)
        }
        return "files"
    }
}