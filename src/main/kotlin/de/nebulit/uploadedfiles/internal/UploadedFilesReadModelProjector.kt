package de.nebulit.uploadedfiles.internal

import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.UUID
import de.nebulit.events.FileUploadedEvent
import de.nebulit.uploadedfiles.UploadedFilesReadModelEntity


import mu.KotlinLogging

interface UploadedFilesReadModelRepository : JpaRepository<UploadedFilesReadModelEntity, UUID>

@Component
class UploadedFilesReadModelProjector(
    var repository: UploadedFilesReadModelRepository
) {


    @EventHandler
    fun on(event: FileUploadedEvent) {
        //throws exception if not available (adjust logic)
        val entity = this.repository.findById(event.aggregateId).orElse(UploadedFilesReadModelEntity())
        entity.apply {
            aggregateId = event.aggregateId
            filename = event.filename
            bucket = event.bucket
        }.also { this.repository.save(it) }
    }

}
