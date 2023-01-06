# Sovtech-Assessment
 
This repo contains the source code for the solution to the Assessment related to the QA role.

The tests focuses on the Contact Us page found at https://www.sovtech.co.za/contact-us

In the pom.xml file, the plugins used for this solution is found, which are mainly the selenium, maeven-surefire, testng, rest-assured and webdriver-manager

To run the source code: 
 > Run the TestSovTechUI.java file or 
 > From the terminal use "mvn clean test" 

A total of 15 tests are executed, all focusing on different aspects around the Contact Us form.
The tests are covered by 6 test methods found in TestSovTechUI.java, some of the methods are using the concept of data-provider the reduce the number of repetitive test methods.  

All tests follow the same approach:

1. Open main site (https://www.sovtech.co.za/)
2. Navigate to Contact Us page (click 'Contact Us' button)
3. Enter test data
4. Click 'Send Message' button
5. Validate messages displayed

Test Methods:

>  test_contact_us_form_successful
   This executes the "happy path/scenario" and checks if the success messages are displayed after appropriate information has been entered and submitted. 

>  test_mandatory_field_error_label
   Test will verify the text displayed by error label, when mandatory fields are not provided

>  test_email_field_error_labels (Field validations)
   Verifiy the error text displayed for email field when provided with:
    1. valid email is provided (existing email)
    2. correct email format is entered (name@company.com)

>  test_number_field_error_label
   Verify error text displayed when incorrect data is provided to number field 

>  test_services_dropdown_field_error_label
   Verify error text displayed when user does not make a selection from the dropdown

>  negative_field_tests
   Tests checks whether the form can be submitted when:
     1. Empty form (no data provided)
     2. All fields except mandatory fields (marked fields)
     3. All fields except name field
     4. All fields except email field
     5. All fields with invalid email (non existing email)
     6. All fields with incorrect formatted email
     7. All fields with invalid phone number
     8. All fields without selecting an option from "What service are you looking for?" dropdown
     9. All fields without selecting an option from "Company Size" dropdown


Test Data (data-providers):
>  negative_test_data
>  email_test_data
>  number_test_data (initially, there was a second field validation, but at the time of drafting this solution the range validation is no longer appearing)

