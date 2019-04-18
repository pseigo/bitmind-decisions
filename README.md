# Personal Project: Codename *Bitmind*

## Motivation

This was a course project for [CPSC 210](https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-course&dept=CPSC&course=210) at the University of British Columbia. I wanted to create something beyond the course's standard project—a partially completed to-do list application—and gain the experience of building a system from scratch. I designed the system from the ground up and utilized the course's lessons on Objected Oriented Programming in Java.

## Domain

This software helps people use logical reasoning for making decisions. Mark Manson explains that the “path of least regret” is a good indicator for comparing the importance of decisions. The purpose of this Java desktop application is to give users a way to put all their thoughts down “on paper” to distance themselves from irrational emotions and biases.

## Functionality

A new entry guides the user through stating their problem and defining their choices. The user can create an arbitrary number of choices for this problem. Each choice is detailed with properties: pros and cons, long-shot success (high reward but low chance of happening) and potential regrets for not pursuing that choice. These properties are modular and interchangeable. After each choice has been detailed, the Decision summarizes each choice in one view.

## Key Skills Learned

The program was written in Java with a GUI designed using JavaFX. I learned several new concepts and put them into practice, including but not limited to,

- Designing, testing, and implementing an objected-oriented system from scratch
  - Writing robust classes by testing boundary cases and throwing exceptions when appropriate
  - Documenting methods with Javadocs to describe their (1) requirements, (2) mutations, and (3) side effects
  - Designing the system with important design principles in mind such as
    - Coupling,
    - Cohesion and the Single Responsibility Principle, and
    - the Liskov Substitution Principle.
- Applying several design patterns, namely the iterator and builder patterns
- Using a bare-bones JSON library for application state persistence
- Applying Test-Driven Development by writing extensive JUnit 5 unit tests before implementing
  - > **Aside**: From the start of the project, I was unsure whether I had the correct design in mind for the system I was trying to build. Writing unit tests and visualizing the use cases of my software *really* helped shape the system into what it is today.
- Designing a GUI using the JavaFX platform and FXML
  - I also used the ControlsFX library to more easily create  paginated wizards

## The Course's Project Requirements

Because the personal project was an alternative route to the standard project, which most students (413 out of 448, or ~92%) opted for, those interested in their own projects had to meet similar requirements. These requirements are listed below, but each phase also had more detailed requirements to meet.

> - must be written in Java and use JUnit 5 for testing
> - must have at least `model`, `parsers` and `ui` packages
> - all methods must be appropriately documented
> - you must design tests that achieve at least 95% coverage of the code in your `model` and `parsers` packages
> - your code must pass the CPSC 210 checkstyle test
> - the `model` package must contain at least 4 classes that model your domain of interest
> - the `ui` package must contain classes that allow the user to interact with the model using a *graphical* user interface
> - your `parsers` package must contain classes that save and/or load the state of your model to/from JSON data
>   - your JSON parsers must throw exceptions, as appropriate, when required data is missing
> - your code must make appropriate use of at least one of the following design patterns or include a README file with your project to justify why their use is not appropriate:
>   - observer
>   - composite
>   - iterator
