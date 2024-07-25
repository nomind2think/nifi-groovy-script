/* (C)2024 */
package com


import org.apache.nifi.controller.ControllerService
import org.apache.nifi.logging.ComponentLog
import org.apache.nifi.processor.ProcessContext
import org.apache.nifi.processor.ProcessSession
import org.apache.nifi.processor.Relationship
import org.apache.nifi.processor.io.StreamCallback
import org.apache.nifi.serialization.RecordReader as RecordReaderCls
import org.apache.nifi.serialization.RecordReaderFactory
import org.apache.nifi.serialization.RecordSetWriterFactory
import org.apache.nifi.serialization.RecordWriter as RecordWriterCls
import org.apache.nifi.serialization.SimpleRecordSchema
import org.apache.nifi.serialization.record.MapRecord
import org.apache.nifi.serialization.record.Record
import org.apache.nifi.serialization.record.RecordField
import org.apache.nifi.serialization.record.RecordFieldType
