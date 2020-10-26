package com.codersee.opencsvexport.service

import com.codersee.opencsvexport.model.User
import com.opencsv.CSVWriter
import com.opencsv.bean.ColumnPositionMappingStrategy
import com.opencsv.bean.StatefulBeanToCsvBuilder
import org.springframework.stereotype.Service
import java.io.PrintWriter
import java.io.StringWriter


@Service
class CsvService(
    private val userService: UserService
) {

    /*
        "1","piotr@codersee.com","Piotr"
        "2","adam@codersee.com","Adam"
        "3","john@codersee.com","John"
        "4","karim@codersee.com","Karim"
     */
    fun exportUserListToCsv(writer: PrintWriter) {
        val users = userService.findAllUsers()
        val mappingStrategy = prepareMappingStrategy()

        val bean = StatefulBeanToCsvBuilder<User>(writer)
            .withMappingStrategy(mappingStrategy)
            .build()

        bean.write(users)
    }

    /*
        1,piotr@codersee.com,Piotr
        2,adam@codersee.com,Adam
        3,john@codersee.com,John
        4,karim@codersee.com,Karim
     */
    fun exportUserListToCsvWithoutQuoteChar(writer: PrintWriter) {
        val users = userService.findAllUsers()
        val mappingStrategy = prepareMappingStrategy()

        val bean = StatefulBeanToCsvBuilder<User>(writer)
            .withMappingStrategy(mappingStrategy)
            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
            .build()

        bean.write(users)
    }

    /*
        "1";"piotr@codersee.com";"Piotr"
        "2";"adam@codersee.com";"Adam"
        "3";"john@codersee.com";"John"
        "4";"karim@codersee.com";"Karim"
     */
    fun exportUserListToCsvCustomSeparator(writer: PrintWriter) {
        val users = userService.findAllUsers()
        val mappingStrategy = prepareMappingStrategy()

        val bean = StatefulBeanToCsvBuilder<User>(writer)
            .withMappingStrategy(mappingStrategy)
            .withSeparator(';')
            .build()

        bean.write(users)
    }

    /*
        "1","piotr@codersee.com","Piotr"||
        "2","adam@codersee.com","Adam"||
        "3","john@codersee.com","John"||
        "4","karim@codersee.com","Karim"||
     */
    fun exportUserListToCsvCustomLineEnd(writer: PrintWriter) {
        val users = userService.findAllUsers()
        val mappingStrategy = prepareMappingStrategy()

        val bean = StatefulBeanToCsvBuilder<User>(writer)
            .withMappingStrategy(mappingStrategy)
            .withLineEnd("||\n")
            .build()

        bean.write(users)
        writer.close()
    }

    fun exportUserListToCsvWithStringWriter(): StringWriter {
        val users = userService.findAllUsers()
        val mappingStrategy = prepareMappingStrategy()

        val writer = StringWriter()
        val bean = StatefulBeanToCsvBuilder<User>(writer)
            .withMappingStrategy(mappingStrategy)
            .build()

        bean.write(users)
        writer.close()

        return writer
    }

    private fun prepareMappingStrategy(): ColumnPositionMappingStrategy<User> {
        val columns = arrayOf("id", "email", "name")

        val strategy = ColumnPositionMappingStrategy<User>()
        strategy.setColumnMapping(*columns)
        strategy.type = User::class.java

        return strategy
    }
}
