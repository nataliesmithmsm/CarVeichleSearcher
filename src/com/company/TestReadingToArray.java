package com.company;

import com.company.dataobjects.Address;
import com.company.dataobjects.CarDetails;
import com.company.dataobjects.PersonalDetails;
import com.company.filehelpers.CsvReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestReadingToArray {

    @Test
    public void testObjectBuilder() throws Exception
    {
        Address a1 = new Address(2, "Street", "Town", "City", "Postcode");
        CarDetails c1 = new CarDetails("Registration", "Make", "Model", 2.0);
        PersonalDetails testPerson = new PersonalDetails("Name", "Surname", a1, c1);

        String [] testPersonInfo = {"Name", "Surname", "2" , "Street", "Town", "City", "Postcode", "Registration", "Make", "Model", "2.0"};

        CsvReader csvReader = new CsvReader();
        PersonalDetails personalDetails = csvReader.personBuilder(testPersonInfo);


        assertAll("PersonalDetails Object should match",
                () -> assertEquals(personalDetails.getSurname(), testPerson.getSurname()),
                () -> assertEquals(personalDetails.getFirstName(), testPerson.getFirstName()),

                () -> assertEquals(personalDetails.getAddress().getHouseNumber(), testPerson.getAddress().getHouseNumber()),
                () -> assertEquals(personalDetails.getAddress().getStreet(), testPerson.getAddress().getStreet()),
                () -> assertEquals(personalDetails.getAddress().getTown(), testPerson.getAddress().getTown()),
                () -> assertEquals(personalDetails.getAddress().getCity(), testPerson.getAddress().getCity()),
                () -> assertEquals(personalDetails.getAddress().getPostcode(), testPerson.getAddress().getPostcode()),

                () -> assertEquals(personalDetails.getCarDetails().getCarRegistration(), testPerson.getCarDetails().getCarRegistration()),
                () -> assertEquals(personalDetails.getCarDetails().getCarMake(), testPerson.getCarDetails().getCarMake()),
                () -> assertEquals(personalDetails.getCarDetails().getCarModel(), testPerson.getCarDetails().getCarModel()),
                () -> assertEquals(personalDetails.getCarDetails().getEngineSize(), testPerson.getCarDetails().getEngineSize())

                );
    }

    @Test
    public void testIfValueNullValidation() //missing engine size, only 10 items in String Array
    {
        CsvReader csvReader = new CsvReader();
        String [] testPersonInfo = {"Name", "Surname", "2" , "Street ", "Town ", "City", "Postcode", "Registration", "Make", "Model"};

        PersonalDetails personalDetails = csvReader.personBuilder(testPersonInfo);

        assertNull(personalDetails);
    }

    @Test
    public void testIfValueNotNullValidation() //all fields are filed in
    {
        CsvReader csvReader = new CsvReader();
        String [] testPersonInfo = {"Name", "Surname", "2" , "Street ", "Town ", "City", "Postcode", "Registration", "Make", "Model", "2.0"};

        PersonalDetails personalDetails = csvReader.personBuilder(testPersonInfo);

        assertNotNull(personalDetails);
    }

    @Test
    public void testIfValueIsCorrectType() throws Exception  //Should Fail all Strings No int/double
    {
        CsvReader csvReader = new CsvReader();
        String[] testPersonInfo = {"Name", "Surname", "Test" , "Street", "Town", "City", "Postcode", "Registration", "Make", "Model", "Test"};

        PersonalDetails personalDetails = csvReader.personBuilder(testPersonInfo);

        assertNull(personalDetails);
    }



}