import org.apache.nifi.controller.ControllerService
import org.apache.nifi.logging.ComponentLog
import org.apache.nifi.processor.ProcessContext
import org.apache.nifi.processor.ProcessSession
import org.apache.nifi.processor.Relationship
import org.apache.nifi.serialization.RecordReaderFactory
import org.apache.nifi.serialization.RecordSetWriterFactory

static void main(String[] args) {
  println "Hello world!"

}

void test(ProcessSession session,
          ProcessContext context,
          ComponentLog log,
          Relationship REL_SUCCESS,
          Relationship REL_FAILURE,
          Map<String, ControllerService> CTL,
          Map<String, groovy.sql.Sql> SQL,
          Map<String, RecordReaderFactory> RecordReader,
          Map<String, RecordSetWriterFactory> RecordWriter
){
  def file = session.get()

}