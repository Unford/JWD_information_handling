package by.epam.handling.reader;

import by.epam.handling.exception.InformationHandlingException;

public interface TextReader {

    String read(String filename) throws InformationHandlingException;
}
