package by.epam.handling.reader.impl;

import by.epam.handling.exception.InformationHandlingException;
import by.epam.handling.reader.TextReader;
import by.epam.handling.validator.InformationHandlingValidator;
import by.epam.handling.validator.impl.InformationHandlingValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class TextReaderImpl implements TextReader {
    static Logger logger = LogManager.getLogger();
    private static final String LINE_DELIMITER = "\n";


    @Override
    public String read(String filename) throws InformationHandlingException {
        InformationHandlingValidator validator = InformationHandlingValidatorImpl.getInstance();
        if (!validator.isValidFilepath(filename)){
            throw new InformationHandlingException("File path is invalid: " + filename);
        }
        String text;
        Path path = Paths.get(filename);
        try (BufferedReader br = Files.newBufferedReader(path)){
            text = br.lines().collect(Collectors.joining(LINE_DELIMITER));
        } catch (IOException e) {
            logger.log(Level.ERROR,"Input error while reading file", e);
            throw new InformationHandlingException("Input error while reading file", e);
        }
        logger.log(Level.INFO, "Read file {} is successful", path.getFileName());
        return text;
    }
}
