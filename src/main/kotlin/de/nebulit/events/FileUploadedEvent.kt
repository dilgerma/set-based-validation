package de.nebulit.events

import de.nebulit.common.Event

import java.util.UUID;


data class FileUploadedEvent(
    var aggregateId:UUID,
	var bucket:String,
	var filename:String
) : Event
