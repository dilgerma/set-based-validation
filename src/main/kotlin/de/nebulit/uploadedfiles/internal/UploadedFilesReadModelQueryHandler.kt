package de.nebulit.uploadedfiles.internal

import de.nebulit.uploadedfiles.UploadedFilesReadModel
import org.springframework.stereotype.Component
import de.nebulit.uploadedfiles.internal.UploadedFilesReadModelRepository
import org.axonframework.queryhandling.QueryHandler
import de.nebulit.uploadedfiles.UploadedFilesReadModelQuery
import java.util.UUID;


@Component
class UploadedFilesReadModelQueryHandler(private val repository:UploadedFilesReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: UploadedFilesReadModelQuery): UploadedFilesReadModel? {
      return UploadedFilesReadModel(repository.findAll())
  }

}
