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
abstract class NiFiGroovyWrapper {

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
    abstract void run(ProcessSession session,
                      ProcessContext context,
                      ComponentLog log,
                      Relationship REL_SUCCESS,
                      Relationship REL_FAILURE,
                      Map<String, ControllerService> CTL,
                      Map<String, groovy.sql.Sql> SQL,
                      Map<String, RecordReaderFactory> RecordReader,
                      Map<String, RecordSetWriterFactory> RecordWriter)


    void start(
            org.apache.nifi.util.TestRunner runner,
            ProcessSession session,
            ProcessContext context,
            ComponentLog log,
            Relationship REL_SUCCESS,
            Relationship REL_FAILURE,
            Map<String, ControllerService> CTL,
            Map<String, groovy.sql.Sql> SQL,
            Map<String, RecordReaderFactory> RecordReader,
            Map<String, RecordSetWriterFactory> RecordWriter) {
        run(session, context, log, REL_SUCCESS, REL_FAILURE, CTL, SQL, RecordReader, RecordWriter)
        def successFlowFile = runner.getFlowFilesForRelationship(REL_SUCCESS)
        def failedFlowFile = runner.getFlowFilesForRelationship(REL_FAILURE)
        log.info("=============SUCCESS===================")
        logFlowFile(successFlowFile, log)
        log.info("=============FAILED====================")
        logFlowFile(failedFlowFile, log)

    }

    void logFlowFile(List<org.apache.nifi.util.MockFlowFile> flowFileList, ComponentLog log) {
        log.info("flow file size:" + flowFileList.size().toString())
        for (final def flowFile in flowFileList) {
            log.info("att: ${groovy.json.JsonOutput.toJson(flowFile.getAttributes())}")
            log.info("content : ${new String(flowFile.toByteArray())}")
        }
    }

}
