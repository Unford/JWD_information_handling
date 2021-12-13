package test.epam.handling.reader;

import by.epam.handling.exception.InfoHandlingException;
import by.epam.handling.reader.InfoHandlingReader;
import by.epam.handling.reader.impl.InfoHandlingReaderImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class InfoHandlingReaderTest {
    private InfoHandlingReader reader = new InfoHandlingReaderImpl();

    @Test(description = "read all lines in correct file",
            dataProvider = "dataForCorrectTextReader")
    public void testReadText(String filepath, String expected)throws InfoHandlingException {
        String actual = reader.read(filepath);
        Assert.assertEquals(actual, expected);

    }

    @DataProvider(name = "dataForCorrectTextReader")
    public Object[][] dataForCorrectTextReader(){
        return new Object[][]{
                {"input/shortText.txt",
                        "It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n    Bye."},
                {"input/empty.txt", ""}

        };
    }

    @Test(description = "test reader throws InfoHandlingException",
            dataProvider = "dataForIncorrectReader",
            expectedExceptions = InfoHandlingException.class)
    public void testReadAllException(String filepath)throws InfoHandlingException {
        reader.read(filepath);
    }

    @DataProvider(name = "dataForIncorrectReader")
    public Object[][] dataForIncorrectReader(){
        return new Object[][]{
                {"input/notExist.txt"},
                {"input/\\escapeChar.txt"},
        };
    }
}
