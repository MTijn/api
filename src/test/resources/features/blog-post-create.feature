Feature: Creating blog posts
  As a consumer of the blog post service
  I need to be able to create a blog post

  Scenario: Creating a new blog post
    When I submit my request with the following content:
      | title           | content         | tags       | author       |
      | this is a test  | this is a test  | tag1, tag2 | test@test.nl |
    Then a new blog post should have been created
    And the creation date should be of today