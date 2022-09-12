Reproducer for <https://issues.redhat.com/browse/WFLY-16986>

On Windows:
* `gradlew startServer`
* Wait for WildFly to start
* `gradlew deployServer`
* `gradlew run`
* You get the following exception in the client:
  ```
  Hello World!
  Exception in thread "main" javax.ejb.EJBException: Failed to read response
          at org.jboss.ejb.protocol.remote.EJBClientChannel$MethodInvocation$MethodCallResultProducer.apply(EJBClientChannel.java:1347)
          at org.jboss.ejb.protocol.remote.EJBClientChannel$MethodInvocation$MethodCallResultProducer.getResult(EJBClientChannel.java:1356)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:620)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:551)
          at org.jboss.ejb.protocol.remote.RemotingEJBClientInterceptor.handleInvocationResult(RemotingEJBClientInterceptor.java:57)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:622)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:551)
          at org.jboss.ejb.client.TransactionPostDiscoveryInterceptor.handleInvocationResult(TransactionPostDiscoveryInterceptor.java:148)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:622)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:551)
          at org.jboss.ejb.client.DiscoveryEJBClientInterceptor.handleInvocationResult(DiscoveryEJBClientInterceptor.java:130)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:622)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:551)
          at org.jboss.ejb.client.NamingEJBClientInterceptor.handleInvocationResult(NamingEJBClientInterceptor.java:87)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:622)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:551)
          at org.jboss.ejb.client.AuthenticationContextEJBClientInterceptor.call(AuthenticationContextEJBClientInterceptor.java:59)
          at org.jboss.ejb.client.AuthenticationContextEJBClientInterceptor.handleInvocationResult(AuthenticationContextEJBClientInterceptor.java:52)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:622)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:551)
          at org.jboss.ejb.client.TransactionInterceptor.handleInvocationResult(TransactionInterceptor.java:212)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:622)
          at org.jboss.ejb.client.EJBClientInvocationContext.getResult(EJBClientInvocationContext.java:551)
          at org.jboss.ejb.client.EJBClientInvocationContext.awaitResponse(EJBClientInvocationContext.java:1003)
          at org.jboss.ejb.client.EJBInvocationHandler.invoke(EJBInvocationHandler.java:182)
          at org.jboss.ejb.client.EJBInvocationHandler.invoke(EJBInvocationHandler.java:116)
          at jdk.proxy2/jdk.proxy2.$Proxy2.getRecord(Unknown Source)
          at org.wildfly.bug.record.client.MyClient.main(MyClient.java:15)
  Caused by: java.io.EOFException: Read past end of file
          at org.jboss.marshalling.SimpleDataInput.eofOnRead(SimpleDataInput.java:151)
          at org.jboss.marshalling.SimpleDataInput.readUnsignedByteDirect(SimpleDataInput.java:294)
          at org.jboss.marshalling.SimpleDataInput.readUnsignedByte(SimpleDataInput.java:249)
          at org.jboss.marshalling.river.RiverUnmarshaller.start(RiverUnmarshaller.java:1373)
          at org.jboss.ejb.protocol.remote.EJBClientChannel$MethodInvocation$MethodCallResultProducer.apply(EJBClientChannel.java:1322)
          ... 27 more
  ```
* You get the following exception in the server:
  ```
  2022-09-12 18:04:58,384 ERROR [org.jboss.as.ejb3.remote] (default task-2) WFLYEJB0150: Could not write method invocation failure for method public abstract org.wildfly.bug.record.interfaces.MyRecord org.wildfly.bug.record.interfaces.MyInterface.getRecord() on bean named MyInterface for appname  modulename server distinctname  due to: java.lang.UnsupportedOperationException: can't get field offset on a record class: private final java.lang.String org.wildfly.bug.record.interfaces.MyRecord.title
    at jdk.unsupported/sun.misc.Unsafe.objectFieldOffset(Unsafe.java:648)
    at org.jboss.marshalling@2.0.12.Final//org.jboss.marshalling.reflect.SerializableField.<init>(SerializableField.java:58)
    at org.jboss.marshalling@2.0.12.Final//org.jboss.marshalling.reflect.SerializableClass.getSerializableFields(SerializableClass.java:127)
    at org.jboss.marshalling@2.0.12.Final//org.jboss.marshalling.reflect.SerializableClass.<init>(SerializableClass.java:93)
    at org.jboss.marshalling@2.0.12.Final//org.jboss.marshalling.reflect.SerializableClassRegistry$1.computeValue(SerializableClassRegistry.java:62)
    at org.jboss.marshalling@2.0.12.Final//org.jboss.marshalling.reflect.SerializableClassRegistry$1.computeValue(SerializableClassRegistry.java:59)
    at java.base/java.lang.ClassValue.getFromHashMap(ClassValue.java:228)
    at java.base/java.lang.ClassValue.getFromBackup(ClassValue.java:210)
    at java.base/java.lang.ClassValue.get(ClassValue.java:116)
    at org.jboss.marshalling@2.0.12.Final//org.jboss.marshalling.reflect.SerializableClassRegistry.lookup(SerializableClassRegistry.java:83)
    at org.jboss.marshalling.river@2.0.12.Final//org.jboss.marshalling.river.RiverMarshaller.doWriteObject(RiverMarshaller.java:180)
    at org.jboss.marshalling@2.0.12.Final//org.jboss.marshalling.AbstractObjectOutput.writeObject(AbstractObjectOutput.java:58)
    at org.jboss.marshalling@2.0.12.Final//org.jboss.marshalling.AbstractMarshaller.writeObject(AbstractMarshaller.java:111)
    at org.jboss.ejb-client@4.0.44.Final//org.jboss.ejb.protocol.remote.EJBServerChannel$RemotingInvocationRequest$1.writeInvocationResult(EJBServerChannel.java:986)
    at org.jboss.as.ejb3@26.1.2.Final//org.jboss.as.ejb3.remote.AssociationImpl.lambda$receiveInvocationRequest$0(AssociationImpl.java:291)
    at org.jboss.as.ejb3@26.1.2.Final//org.jboss.as.ejb3.remote.AssociationImpl.execute(AssociationImpl.java:344)
    at org.jboss.as.ejb3@26.1.2.Final//org.jboss.as.ejb3.remote.AssociationImpl.receiveInvocationRequest(AssociationImpl.java:297)
    at org.jboss.ejb-client@4.0.44.Final//org.jboss.ejb.protocol.remote.EJBServerChannel$ReceiverImpl.handleInvocationRequest(EJBServerChannel.java:473)
    at org.jboss.ejb-client@4.0.44.Final//org.jboss.ejb.protocol.remote.EJBServerChannel$ReceiverImpl.handleMessage(EJBServerChannel.java:208)
    at org.jboss.remoting@5.0.25.Final//org.jboss.remoting3.remote.RemoteConnectionChannel.lambda$handleMessageData$3(RemoteConnectionChannel.java:432)
    at org.jboss.remoting@5.0.25.Final//org.jboss.remoting3.EndpointImpl$TrackingExecutor.lambda$execute$0(EndpointImpl.java:991)
    at org.jboss.threads@2.4.0.Final//org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)
    at org.jboss.threads@2.4.0.Final//org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:1990)
    at org.jboss.threads@2.4.0.Final//org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1486)
    at org.jboss.threads@2.4.0.Final//org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1377)
    at org.jboss.xnio@3.8.7.Final//org.xnio.XnioWorker$WorkerThreadFactory$1$1.run(XnioWorker.java:1282)
    at java.base/java.lang.Thread.run(Thread.java:833)
  Caused by: an exception which occurred:
    in object org.wildfly.bug.record.interfaces.MyRecord@33adf623
  ```

Cleanup:
* `gradlew stopServer`
