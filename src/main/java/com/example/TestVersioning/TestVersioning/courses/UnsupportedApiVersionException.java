

package com.example.TestVersioning.TestVersioning.courses;

public class UnsupportedApiVersionException extends RuntimeException {
    public UnsupportedApiVersionException(String version) {
        super("API version '" + version + "' is not supported.");
    }
}
