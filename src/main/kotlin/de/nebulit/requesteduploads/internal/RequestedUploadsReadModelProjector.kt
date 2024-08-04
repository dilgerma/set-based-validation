package de.nebulit.requesteduploads.internal

import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.UUID
import de.nebulit.events.FileUploadedRequestedEvent
import de.nebulit.requesteduploads.RequestedUploadsReadModelEntity


import mu.KotlinLogging

interface RequestedUploadsReadModelRepository : JpaRepository<RequestedUploadsReadModelEntity, String>

@Component
class RequestedUploadsReadModelProjector(
    var repository: RequestedUploadsReadModelRepository
) {


    @EventHandler
    fun on(event: FileUploadedRequestedEvent) {
        //throws exception if not available (adjust logic)
        val entity = this.repository.findById(event.aggregateId).orElse(RequestedUploadsReadModelEntity())
        entity.apply {
            aggregateId = event.aggregateId
            bucket = event.bucket
            filename = event.filename
        }.also { this.repository.save(it) }
    }

}
