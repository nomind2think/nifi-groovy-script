# NiFi Groovy Script

Run With NiFi Processor ExecuteGroovyScript

## Requirement

NiFi >= 1.26.0

## Manual

1. Create a class that implements **NiFiGroovyContext.class**. The method parameter is the NiFi script context.
2. Once the code is finished, copy the method body to the NiFi ExecuteGroovyScript Property (Script Body).
3. Run and test.

**Tips**

When copying the method body, you may also need to copy the import statements (import package ...). If you use JetBrains IntelliJ IDEA, you can configure it to always import as fully qualified names, allowing you to copy only the method body without the import statements.  
Settings -> Editor -> Code Style -> Groovy -> Imports (Tab), check the 'Use fully qualified class names' option. Enjoy it.
