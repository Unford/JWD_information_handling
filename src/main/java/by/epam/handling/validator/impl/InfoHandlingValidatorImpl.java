package by.epam.handling.validator.impl;

import by.epam.handling.validator.InfoHandlingValidator;

import java.io.File;

public class InfoHandlingValidatorImpl implements InfoHandlingValidator {
    private static InfoHandlingValidatorImpl instance;

    private InfoHandlingValidatorImpl(){}

    public static InfoHandlingValidatorImpl getInstance(){
        if (instance == null){
            instance = new InfoHandlingValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isValidFilepath(String filepath) {
        boolean isValid = false;
        if (filepath != null && !filepath.isBlank()){
            File file = new File(filepath);
            isValid = file.exists();
        }
        return isValid;
    }


}
