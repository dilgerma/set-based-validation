package de.nebulit.domain

import de.nebulit.domain.commands.uploadfile.UploadFileCommand
import de.nebulit.events.FileUploadedEvent
import de.nebulit.events.FileUploadedRequestedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate
import java.util.UUID


@Aggregate
class DocumentAggregate {

    @AggregateIdentifier
    lateinit var aggregateId: UUID

    @CreationPolicy(AggregateCreationPolicy.ALWAYS)
    @CommandHandler
    fun handle(command: UploadFileCommand) {
        println("upload to S3")
        AggregateLifecycle.apply(FileUploadedEvent(command.aggregateId, command.bucket, command.filename))
    }

    @EventSourcingHandler
    fun handle(event: FileUploadedEvent) {
        this.aggregateId = event.aggregateId
    }

}
