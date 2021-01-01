@CRM
Feature: CRM Activities

  @CRMActivity1
  Scenario: Open the homepage and count the number of the dashlets on the page
    Given User is on Alchemy CRM site and logged in
    Then Count the number of Dashlets on the homepage
    And Print the number and title of each Dashlet

  @CRMActivity2
  Scenario: 
    Given User is on Alchemy CRM site and logged in
    Then Navigate to Sales -> Leads -> Create Lead
    And Fill in the necessary details to create lead accounts using first name as "Fname31" , last name as "Lname31" , lead status as "New"
    And Click Save to finish
    Then Navigate to the View Leads page to see results for first name as "Fname31" , last name as "Lname31"

  @CRMActivity3
  Scenario: To schedule a meeting and include at least 3 invitees
    Given User is on Alchemy CRM site and logged in
    Then Navigate to Activities -> Meetings -> Schedule a Meeting
    And Enter the details of the meeting
    And Search for below users and add them to the meeting
      | firstName | lastName |
      | Tury      | Subh     |
      | Test      | Surname  |
      | Test      | Test     |
    And Click Save to save the meeting
    Then Navigate to View Meetings page and confirm creation of the meeting

  @CRMActivity4
  Scenario Outline: To use an Examples table to add products
    Given User is on Alchemy CRM site and logged in
    Then Navigate to All -> Products-> Create Product
    And Enter the details of the product like "<productName>" and "<price>"
    And Click Save to save the product
    Then Go to the View Products page to see all products listed

    Examples: 
      | productName   | price |
      | testProduct31 | 10.90 |
      | testProduct32 | 99.99 |
      | testProduct33 | 78.00 |
