package org.wildfly.bug.record.client;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;
import javax.naming.InitialContext;
import org.wildfly.bug.record.interfaces.MyInterface;
import org.wildfly.bug.record.interfaces.MyRecord;

public class MyClient {
  public static void main(String[] args) throws Throwable {
    System.setProperty(INITIAL_CONTEXT_FACTORY, org.wildfly.naming.client.WildFlyInitialContextFactory.class.getName());
    System.setProperty(PROVIDER_URL, "remote+http://localhost:8080");
    MyInterface session = InitialContext.doLookup("server/" + MyInterface.NAME + "!" + MyInterface.class.getName());
    System.out.println(session.getString());
    MyRecord record = session.getRecord();
    System.out.println(record.title() + ": " + record.message());
  }
}
