Feature: User credit rating
  Scenario: Check rating is ok
    Given that the user Vasya is given a task to check his/her credit rating
    When Vasya got 500 marks in rating
    Then Vasya is know as well rating
