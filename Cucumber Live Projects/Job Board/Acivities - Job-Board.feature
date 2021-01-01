@jobBoard
Feature: Job-Board Activities

  @jobBoardActivity1
  Scenario: Visit the site’s backend and create a new user
    Given User is on Alchemy Jobs Admin webpage
    And Locate the left hand menu and click the menu item that says 'Users'
    And Locate the 'Add New' button and click it
    And Fill in the necessary details
    And Click the 'Add New User' button
    Then Verify that the user was created

  @jobBoardActivity2
  Scenario: Searching for jobs and applying to them using XPath
    Given User is on Alchemy Jobs site
    And Click on 'Jobs' menu button to navigate to Jobs page
    And Type in keywords into search input field to search for jobs
    And Find the filter using XPath and filter job type to show only 'Full Time' jobs
    And Find a job listing using XPath and it to see job details
    And Find the title of the job listing using XPath and print it to the console
    Then Find and Click on the 'Apply for job' button

  @jobBoardActivity3
  Scenario: Post a job using details passed from the Feature file
    Given User is on Alchemy Jobs site
    And Go to 'Post a Job' page
    And User is logged into Alchemy Jobs Posting site
    And Fill details on 'Post a Job' page from feature file namely "a@30.com","TestJob_30","This is a dummy test job. Do not apply!","noneedtoapply30@test.com","Fake Company 30 Inc." 
    And Submit the job listing
    Then Confirm "TestJob_30" job was posted

  @jobBoardActivity4
  Scenario Outline: Rewrite activity 3 using a Scenario Outline and Examples table to post a job
    Given User is on Alchemy Jobs site
    And Go to 'Post a Job' page
    And User is logged into Alchemy Jobs Posting site
    And Fill details on 'Post a Job' page from feature file namely "<email>","<jobTitle>","<description>","<appURL>","<companyName>" 
    And Submit the job listing
    Then Confirm "<jobTitle>" job was posted

    Examples: 
      | email    | jobTitle   | description                                | appURL                   | companyName          |
      | a@31.com | TestJob_31 | This is a dummy test job 31. Do not apply! | noneedtoapply31@test.com | Fake Company 31 Inc. |
      | a@32.com | TestJob_32 | This is a dummy test job 32. Do not apply! | noneedtoapply32@test.com | Fake Company 32 Inc. |
      | a@33.com | TestJob_33 | This is a dummy test job 33. Do not apply! | noneedtoapply33@test.com | Fake Company 33 Inc. |
      | a@24.com | TestJob_24 | This is a dummy test job 24. Do not apply! | noneedtoapply24@test.com | Fake Company 24 Inc. |
