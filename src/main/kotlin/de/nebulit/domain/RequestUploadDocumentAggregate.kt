package de.nebulit.domain

import de.nebulit.common.CommandException
import de.nebulit.domain.commands.requestupload.RequestUploadFileCommand
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
class RequestUploadDocumentAggregate {

    @AggregateIdentifier
    lateinit var filename: String

    private var reserved: Boolean = false

    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    @CommandHandler
    fun handle(command: RequestUploadFileCommand) {
        if (reserved) {
            throw CommandException("already reserved")
        }
        AggregateLifecycle.apply(FileUploadedRequestedEvent(filename(command.bucket, command.filename), command.bucket, command.filename))
    }

    @EventSourcingHandler
    fun handle(event: FileUploadedRequestedEvent) {
        this.reserved = true
        this.filename = filename(event.bucket, event.filename)
    }

    private fun filename(bucket: String, filename: String): String {
        return "$bucket/$filename"
    }
}
