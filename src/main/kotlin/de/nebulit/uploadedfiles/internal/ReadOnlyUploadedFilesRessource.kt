package de.nebulit.uploadedfiles.internal

import de.nebulit.uploadedfiles.UploadedFilesReadModel
import de.nebulit.uploadedfiles.UploadedFilesReadModelQuery
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import java.util.concurrent.CompletableFuture
import java.util.UUID;




@RestController
class UploadedfilesRessource(
    private var queryGateway: QueryGateway
    ) {

    var logger = KotlinLogging.logger {}

    @CrossOrigin
    @GetMapping("/uploadedfiles")
                    fun findReadModel():CompletableFuture<UploadedFilesReadModel> {
                         return queryGateway.query(UploadedFilesReadModelQuery(), UploadedFilesReadModel::class.java)  
                    }

}
