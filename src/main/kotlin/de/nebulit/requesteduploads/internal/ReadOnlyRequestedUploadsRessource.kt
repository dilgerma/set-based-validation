package de.nebulit.requesteduploads.internal

import de.nebulit.requesteduploads.RequestedUploadsReadModel
import de.nebulit.requesteduploads.RequestedUploadsReadModelQuery
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import java.util.concurrent.CompletableFuture
import java.util.UUID;


@RestController
class RequesteduploadsRessource(
    private var queryGateway: QueryGateway
) {

    var logger = KotlinLogging.logger {}

    @CrossOrigin
    @GetMapping("/requesteduploads/{aggregateId}")
    fun findReadModel(@PathVariable("aggregateId") aggregateId: String): CompletableFuture<RequestedUploadsReadModel> {
        return queryGateway.query(RequestedUploadsReadModelQuery(aggregateId), RequestedUploadsReadModel::class.java)
    }

}
