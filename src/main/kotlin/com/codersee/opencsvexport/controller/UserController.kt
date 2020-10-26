package com.codersee.opencsvexport.controller

import com.codersee.opencsvexport.service.CsvService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
class UserController(
    private val csvService: CsvService
) {

    @PostMapping("/api/user/csv")
    fun getAllUsersCsvExport(response: HttpServletResponse) {
        csvService.exportUserListToCsv(response.writer)
    }

    @PostMapping("/api/user/csv/string")
    fun getAllUsersCsvExportWihStringWriter(): ResponseEntity<String> {
        val result = csvService.exportUserListToCsvWithStringWriter()

        return ResponseEntity.ok(result.toString())
    }
}