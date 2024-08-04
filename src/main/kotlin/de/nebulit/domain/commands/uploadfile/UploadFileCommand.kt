package de.nebulit.domain.commands.uploadfile

import org.axonframework.modelling.command.TargetAggregateIdentifier
import de.nebulit.common.Command
import java.util.UUID;


data class UploadFileCommand(
    @TargetAggregateIdentifier override var aggregateId:UUID,
	var bucket:String,
	var filename:String
): Command
