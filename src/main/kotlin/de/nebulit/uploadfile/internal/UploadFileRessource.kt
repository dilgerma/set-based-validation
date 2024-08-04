package de.nebulit.uploadfile.internal

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import de.nebulit.domain.commands.uploadfile.UploadFileCommand
import de.nebulit.common.CommandResult
import de.nebulit.domain.commands.requestupload.RequestUploadFileCommand

import java.util.UUID;

import java.util.concurrent.CompletableFuture


data class UploadFilePayload(
    var aggregateId: UUID,
    var bucket: String,
    var filename: String
)

@RestController
class UploadFileRessource(private var commandGateway: CommandGateway) {

    var logger = KotlinLogging.logger {}


    @CrossOrigin
    @PostMapping("/debug/requestupload")
    fun processDebugCommand(
        @RequestParam bucket: String,
        @RequestParam filename: String
    ): CompletableFuture<CommandResult> {
        return commandGateway.send(
            RequestUploadFileCommand(
                "$bucket/$filename",
                bucket,
                filename
            )
        )
    }

    @CrossOrigin
    @PostMapping("/debug/uploadfile")
    fun processDebugCommand(
        @RequestParam aggregateId: UUID,
        @RequestParam bucket: String,
        @RequestParam filename: String
    ): CompletableFuture<CommandResult> {
        return commandGateway.send(
            UploadFileCommand(
                aggregateId,
                bucket,
                filename
            )
        )
    }


    @CrossOrigin
    @PostMapping("/uploadfile/{aggregateId}")
    fun processCommand(
        @PathVariable("aggregateId") aggregateId: UUID,
        @RequestBody payload: UploadFilePayload
    ): CompletableFuture<CommandResult> {
        return commandGateway.send(
            UploadFileCommand(
                aggregateId = payload.aggregateId,
                bucket = payload.bucket,
                filename = payload.filename
            )
        )
    }


}
