/* (C)2024 */
package com.process


import groovy.sql.Sql
import org.apache.nifi.controller.ControllerService
import org.apache.nifi.logging.ComponentLog
import org.apache.nifi.processor.ProcessContext
import org.apache.nifi.processor.ProcessSession
import org.apache.nifi.processor.Relationship
import org.apache.nifi.serialization.RecordReaderFactory
import org.apache.nifi.serialization.RecordSetWriterFactory

class HelloWorld extends com.NiFiGroovyWrapper {
	@Override
	void run(ProcessSession session,
			ProcessContext context,
			ComponentLog log, Relationship REL_SUCCESS,
			Relationship REL_FAILURE,
			Map<String, ControllerService> CTL,
			Map<String, Sql> SQL,
			Map<String, RecordReaderFactory> RecordReader,
			Map<String, RecordSetWriterFactory> RecordWriter) {
		log.warn("Hello world")
		def flowFile = session.get()
		if (!flowFile) {
			return
		}
		log.warn(groovy.json.JsonOutput.toJson(flowFile))
		REL_SUCCESS << flowFile
	}
}
