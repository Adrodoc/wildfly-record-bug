package org.wildfly.bug.record.server;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.wildfly.bug.record.interfaces.MyInterface;
import org.wildfly.bug.record.interfaces.MyRecord;

@Remote(MyInterface.class)
@Stateless(name = MyInterface.NAME)
public class MyEjb implements MyInterface {
  @Override
  public String getString() {
    return "Hello World!";
  }

  @Override
  public MyRecord getRecord() {
    return new MyRecord("Greetings", "Hello World!");
  }
}
