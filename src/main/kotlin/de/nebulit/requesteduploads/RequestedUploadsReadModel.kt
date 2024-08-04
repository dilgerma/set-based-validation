package de.nebulit.requesteduploads

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


data class RequestedUploadsReadModelQuery(val aggregateId: String)


@Entity
class RequestedUploadsReadModelEntity {
    @Id
    @Column(name = "aggregateId")
    var aggregateId: String? = null;
    @Column(name = "bucket")
    var bucket: String? = null;
    @Column(name = "filename")
    var filename: String? = null;
}

data class RequestedUploadsReadModel(val data: RequestedUploadsReadModelEntity)
