/* (C)2024 */
package com.process


/**
 * need config reader and writer:
 * RecordReader.reader: controlService
 * RecordWriter.writer: controlService
 */
class UpdateData extends com.NiFiGroovyWrapper {
    @Override
    void run(org.apache.nifi.processor.ProcessSession session, org.apache.nifi.processor.ProcessContext context, org.apache.nifi.logging.ComponentLog log,
             org.apache.nifi.processor.Relationship REL_SUCCESS, org.apache.nifi.processor.Relationship REL_FAILURE, Map<String, org.apache.nifi.controller.ControllerService> CTL,
             Map<String, groovy.sql.Sql> SQL, Map<String, org.apache.nifi.serialization.RecordReaderFactory> RecordReader, Map<String, org.apache.nifi.serialization.RecordSetWriterFactory> RecordWriter) {


        def flowFile = session.get()
        if (!flowFile) {
            return
        }

        def addFiledName = "newActorId"
        def removeFiledName = "last_name"
        def updateFiledName = "full_name"

        def newFlowFile = session.write(flowFile, new org.apache.nifi.processor.io.StreamCallback() {
            @Override
            void process(InputStream input, OutputStream out) throws IOException {
                org.apache.nifi.serialization.RecordReader reader = RecordReader.reader.createRecordReader(flowFile, input, log)
                def schema = reader.getSchema()
                def newFields = new ArrayList<>(schema.getFields())
                // add new filed
                def newFiled = new org.apache.nifi.serialization.record.RecordField(addFiledName, org.apache.nifi.serialization.record.RecordFieldType.
                        INT.getDataType())
                newFields.add(newFiled)
                // remove old filed
                newFields = newFields.findAll { it -> it.getFieldName() != removeFiledName }
                def newSchema = new org.apache.nifi.serialization.SimpleRecordSchema(newFields)
                org.apache.nifi.serialization.RecordWriter writer = RecordWriter.writer.createWriter(log, newSchema, out, Collections.emptyMap())
                writer.beginRecordSet()
                org.apache.nifi.serialization.record.Record record
                while ((record = reader.nextRecord()) != null) {
                    def newMap = new HashMap(record.toMap())
                    // set new filed value
                    newMap[addFiledName] = record.getValue("actor_id")**2
                    // update filed value
                    newMap[updateFiledName] = "Dear: " + record.getValue(updateFiledName)
                    writer.write(new org.apache.nifi.serialization.record.MapRecord(newSchema, newMap))
                }
                writer.flush()
                writer.finishRecordSet()
            }
        })
        REL_SUCCESS << newFlowFile
    }
}
