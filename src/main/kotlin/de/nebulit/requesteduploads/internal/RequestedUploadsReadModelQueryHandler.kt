package de.nebulit.requesteduploads.internal

import de.nebulit.requesteduploads.RequestedUploadsReadModel
import org.springframework.stereotype.Component
import de.nebulit.requesteduploads.internal.RequestedUploadsReadModelRepository
import org.axonframework.queryhandling.QueryHandler
import de.nebulit.requesteduploads.RequestedUploadsReadModelQuery
import java.util.UUID;


@Component
class RequestedUploadsReadModelQueryHandler(private val repository: RequestedUploadsReadModelRepository) {

    @QueryHandler
    fun handleQuery(query: RequestedUploadsReadModelQuery): RequestedUploadsReadModel? {

        if (!repository.existsById(query.aggregateId)) {
            return null
        }
        return RequestedUploadsReadModel(repository.findById(query.aggregateId).get())
    }

}
