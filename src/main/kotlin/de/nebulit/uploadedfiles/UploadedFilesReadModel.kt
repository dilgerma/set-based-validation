package de.nebulit.uploadedfiles

import jakarta.persistence.ElementCollection
import jakarta.persistence.CollectionTable
import jakarta.persistence.JoinColumn
import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType

import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.UUID;


class UploadedFilesReadModelQuery()

@Entity
class UploadedFilesReadModelEntity {
		@Id @JdbcTypeCode(Types.VARCHAR)  @Column(name="aggregateId") var aggregateId:UUID? = null;
	 @Column(name="filename") var filename:String? = null;
	 @Column(name="bucket") var bucket:String? = null;
}

data class UploadedFilesReadModel(val data: List<UploadedFilesReadModelEntity>)
