package by.epam.handling.reader;

import by.epam.handling.exception.InfoHandlingException;

public interface InfoHandlingReader {

    String read(String filename) throws InfoHandlingException;
}
