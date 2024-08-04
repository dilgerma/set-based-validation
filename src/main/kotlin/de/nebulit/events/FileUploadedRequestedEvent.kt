package de.nebulit.events

import de.nebulit.common.Event

import java.util.UUID;


data class FileUploadedRequestedEvent(
    var aggregateId:String,
	var bucket:String,
	var filename:String
) : Event
