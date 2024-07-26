package com.nifi;

import com.process.HelloWorld;
import com.utils.GroovyMethods;
import groovy.json.JsonOutput;
import org.apache.nifi.processor.*;
import org.apache.nifi.processors.groovyx.ExecuteGroovyScript;
import org.apache.nifi.processors.groovyx.flow.GroovyProcessSessionWrap;
import org.apache.nifi.util.MockComponentLog;
import org.apache.nifi.util.MockFlowFile;
import org.apache.nifi.util.TestRunner;
import org.apache.nifi.util.TestRunners;
import org.junit.Before;
import org.junit.Test;


import java.util.*;


public class TestHelloWorld {
    public static final Relationship REL_SUCCESS = new Relationship.Builder().name("success").description("FlowFiles that were successfully processed").build();

    public static final Relationship REL_FAILURE = new Relationship.Builder().name("failure").description("FlowFiles that failed to be processed").build();


    public void initScript(){
        GroovyMethods.init();
    }
    @Before
    public void setup(){
        initScript();
    }

    @Test
    public void test1(){
        initScript();
    }
    @Test
    public void testHelloWorld() {

        TestRunner runner = TestRunners.newTestRunner(ExecuteGroovyScript.class);
        ProcessSession session = runner.getProcessSessionFactory().createSession();


        final Map<String,String> attrs = new LinkedHashMap<>();
        attrs.put("foo", "foo-value");
        attrs.put("bar", "bar-value");
        attrs.put("foobaz", "foobaz-value");
        runner.enqueue("body", attrs);
        HelloWorld helloWorld = new HelloWorld();
        ProcessContext processContext = runner.getProcessContext();
        GroovyProcessSessionWrap newSession = new GroovyProcessSessionWrap(session, true);
        MockComponentLog logger = runner.getLogger();
        helloWorld.start(runner,newSession,processContext,logger,REL_SUCCESS,REL_FAILURE, Collections.EMPTY_MAP,Collections.EMPTY_MAP,Collections.EMPTY_MAP,
                Collections.EMPTY_MAP);
        List<MockFlowFile> flowFilesForRelationship = runner.getFlowFilesForRelationship(REL_SUCCESS);
        System.out.println(JsonOutput.toJson(flowFilesForRelationship.get(0).getAttributes()));
    }
}
