package by.epam.handling.validator.impl;

import by.epam.handling.validator.InformationHandlingValidator;

import java.io.File;

public class InformationHandlingValidatorImpl implements InformationHandlingValidator {//todo name's too long
    private static InformationHandlingValidatorImpl instance;

    private InformationHandlingValidatorImpl(){}

    public static InformationHandlingValidatorImpl getInstance(){
        if (instance == null){
            instance = new InformationHandlingValidatorImpl();
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
