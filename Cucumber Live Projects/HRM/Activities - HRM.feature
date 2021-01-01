@HRM
Feature: HRM Activities

  @HRMActivity1
  Scenario: To create a job vacancy for “DevOps Engineer”
    Given User is on OrangeHRM page and logged in
    Then Navigate to the 'Recruitment' page
    And Click on the 'Vacancies' menu item to navigate to the vacancies page
    And Click on the 'Add' button to navigate to the 'Add Job Vacancy' form
    And Fill out the necessary details on 'Add Job Vacancy' page
    And Click the 'Save' button to save the vacancy
    Then Verify that the vacancy was created

  @HRMActivity2
  Scenario: Add information about a candidate for recruitment
    Given User is on OrangeHRM page and logged in
    Then Navigate to the 'Recruitment' page
    And Click on the 'Add' button to navigate to the 'Add Candidate' form
    And Fill out the necessary details and upload a resume
    And Click the 'Save' button to save the candidate
    Then Verify that the candidacy was created

  @HRMActivity3
  Scenario Outline: Add multiple employees using the Examples table
    Given User is on OrangeHRM page and logged in
    Then Navigate to the 'PIM' page
    And Click on the 'Add' button to navigate to the 'Add Employee' form
    And Fill out the necessary details on 'Add Employee' page namely "<firstName>","<lastName>","<userName>"
    And Click the 'Save' button to save the employee
    Then Verify that the "<firstName>" employee was created

    Examples: 
      | firstName | lastName | userName   |
      | Tury31    | Test31   | TuryTest31 |
      | Tury32    | Test32   | TuryTest32 |
      | Tury33    | Test33   | TuryTest33 |

  @HRMActivity4
  Scenario Outline: Creating multiple vacancies using data from an Examples table
    Given User is on OrangeHRM page and logged in
    Then Navigate to the 'Recruitment' page
    And Click on the 'Vacancies' menu item to navigate to the vacancies page
    And Click on the 'Add' button to navigate to the 'Add Job Vacancy' form
    And Fill out the necessary details on 'Add Job Vacancy' page namely "<jobTitle>","<vacancyName>","<numPositions>"
    And Click the 'Save' button to save the vacancy
    Then Verify that the vacancy was created

    Examples: 
      | jobTitle                 | vacancyName | numPositions | jobDescription                            |
      | Android Developer        | Test31      |           31 | This a test case, please apply elsewhere! |
      | Automation Test Engineer | Test32      |           32 | This a test case, please apply elsewhere! |
      | DevOps Engineer          | Test33      |           33 | This a test case, please apply elsewhere! |
      | Java Developer           | Test34      |           34 | This a test case, please apply elsewhere! |
      | Rust Engineer            | Test35      |           35 | This a test case, please apply elsewhere! |
    