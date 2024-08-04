package de.nebulit.requestupload

import de.nebulit.common.Event
import de.nebulit.common.support.RandomData
import de.nebulit.domain.DocumentAggregate
import de.nebulit.common.CommandException
import de.nebulit.domain.RequestUploadDocumentAggregate
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import de.nebulit.domain.commands.requestupload.RequestUploadFileCommand;
import de.nebulit.events.FileUploadedRequestedEvent
import java.util.UUID

class RequestuploadAggregateTest {

    private lateinit var fixture: FixtureConfiguration<RequestUploadDocumentAggregate>

    val bucketName = "bucket"
    val fileName = "file"

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(RequestUploadDocumentAggregate::class.java)
    }

    @Test
    fun `RequestuploadAggregateTest`() {
        //GIVEN
        val events = mutableListOf<Event>()


        //WHEN
        val command = RequestUploadFileCommand(
            aggregateId = "$bucketName/$fileName",
            bucket = bucketName,
            filename = fileName
        )

        //THEN
        val expectedEvents = mutableListOf<Event>()

        expectedEvents.add(RandomData.newInstance<FileUploadedRequestedEvent> {
            this.aggregateId = command.aggregateId
            this.bucket = command.bucket
            this.filename = command.filename
        })


        fixture.given(events)
            .`when`(command)
            .expectSuccessfulHandlerExecution()
            .expectEvents(*expectedEvents.toTypedArray())
    }

    @Test
    fun `RequestuploaderrorAggregateTest`() {
        //GIVEN
        val events = mutableListOf<Event>()

        events.add(RandomData.newInstance<FileUploadedRequestedEvent> {
            aggregateId = "$bucketName/$fileName"
            bucket = RandomData.newInstance { }
            filename = RandomData.newInstance { }
        })


        //WHEN
        val command = RequestUploadFileCommand(
            aggregateId = "$bucketName/$fileName",
            bucket = RandomData.newInstance { },
            filename = RandomData.newInstance { }
        )

        //THEN
        val expectedEvents = mutableListOf<Event>()


        fixture.given(events)
            .`when`(command)
            .expectException(CommandException::class.java)
    }

}
