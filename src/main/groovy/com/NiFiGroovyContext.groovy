/* (C)2024 */
package com

import org.apache.nifi.controller.ControllerService
import org.apache.nifi.logging.ComponentLog
import org.apache.nifi.processor.ProcessContext
import org.apache.nifi.processor.ProcessSession
import org.apache.nifi.processor.Relationship
import org.apache.nifi.serialization.RecordReaderFactory
import org.apache.nifi.serialization.RecordSetWriterFactory


/**
 * Just for get idea Autocomplete
 */
interface NiFiGroovyContext {

	/**
	 * @link https://nifi.apache.org/docs/nifi-docs/components/org.apache.nifi/nifi-groovyx-nar/1.27.0/org.apache.nifi.processors.groovyx.ExecuteGroovyScript/additionalDetails.html
	 *
	 * @param session
	 * @param context
	 * @param log
	 * @param REL_SUCCESS
	 * @param REL_FAILURE
	 * @param CTL
	 * @param SQL
	 * @param RecordReader
	 * @param RecordWriter
	 * @return
	 */
	void run(ProcessSession session,
			ProcessContext context,
			ComponentLog log,
			Relationship REL_SUCCESS,
			Relationship REL_FAILURE,
			Map<String, ControllerService> CTL,
			Map<String, groovy.sql.Sql> SQL,
			Map<String, RecordReaderFactory> RecordReader,
	Map<String, RecordSetWriterFactory> RecordWriter)
}
