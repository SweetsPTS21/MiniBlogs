Feature: Create Blog

  Scenario Outline: Successful Create Blog
    Given user request to create blog with "<title>" "<summary>" "<publicDate>" "<source>" "<image>" "<authorId>"
    When the user do action create blog
    Then user action is successful with "<code>"
    Examples:
      | code  |  title            |  summary          | publicDate          | source          |  image         | authorId |
      | 200   |  Blog Name 1      | Title 1           | 12-12-2020          | google.com      |  image         | 1        |
      | 200   |  Blog Name 2      | Title 2           | 12-03-2021          | google.com      |  image         | 2        |
      | 200   |  Blog Name 3      | Title 3           | 12-04-2022          | google.com      |  image         | 3        |
      | 200   |  Blog Name 4      | Title 4           | 12-04-2023          | google.com      |  image         | 4        |

  Scenario Outline: Create Blog Failed
    Given user request to create blog with "<title>" "<summary>" "<publicDate>" "<source>" "<image>" "<authorId>"
    When the user do action create blog
    Then user action is failed with "<code>"
    Examples:
      | code  |  title            |  summary          | publicDate           | source     | image         | authorId |
      | 400   |                   | Title 1           | RESIDENTIAL          | 1500       | image         | 1        |