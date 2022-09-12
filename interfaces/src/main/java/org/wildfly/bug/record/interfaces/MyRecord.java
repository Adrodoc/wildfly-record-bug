package org.wildfly.bug.record.interfaces;

import java.io.Serializable;

public record MyRecord(String title, String message) implements Serializable {
}
