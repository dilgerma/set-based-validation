package de.nebulit.domain.commands.requestupload

import org.axonframework.modelling.command.TargetAggregateIdentifier


data class RequestUploadFileCommand(
    @TargetAggregateIdentifier var aggregateId:String,
	var bucket:String,
	var filename:String
)
