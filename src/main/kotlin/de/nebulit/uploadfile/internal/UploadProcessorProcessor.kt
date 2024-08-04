package de.nebulit.uploadfile.internal

import com.fasterxml.jackson.annotation.JsonIgnore
import de.nebulit.requesteduploads.RequestedUploadsReadModel
import de.nebulit.requesteduploads.RequestedUploadsReadModelQuery
import de.nebulit.common.Processor
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.axonframework.eventhandling.EventHandler
import de.nebulit.domain.commands.uploadfile.UploadFileCommand
import java.util.UUID;

import de.nebulit.events.FileUploadedRequestedEvent
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga

@Saga
@Component
class UploadProcessorProcessor : Processor {
    @JsonIgnore
    var logger = KotlinLogging.logger {}

    @JsonIgnore
    @Autowired
    lateinit var commandGateway: CommandGateway

    @JsonIgnore
    @Autowired
    lateinit var queryGateway: QueryGateway


    @StartSaga
    @SagaEventHandler(associationProperty = "aggregateId")
    fun on(event: FileUploadedRequestedEvent) {
        queryGateway.query(
            RequestedUploadsReadModelQuery(event.aggregateId),
            RequestedUploadsReadModel::class.java
        ).thenAccept {
            commandGateway.sendAndWait<UploadFileCommand>(
                UploadFileCommand(
                    aggregateId = UUID.randomUUID(),
                    bucket = it.data.bucket!!,
                    filename = it.data.filename!!
                )
            )
        }
    }

}

