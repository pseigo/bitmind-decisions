# Personal Project: Codename *Bitmind*

## Domain

This software helps people use logical reasoning for making decisions. Mark Manson explains that the “path of least regret” is a good indicator for comparing the importance of decisions. The purpose of this Java desktop application is to give users a way to put all their thoughts down “on paper” to distance themselves from irrational emotions and biases.

## Functionality

A new *Problem* guides the user through stating their problem and defining their choices. The user can create an arbitrary number of choices. Each choice is detailed properties: pros and cons, long-shot success (high reward but low chance of happening) and potential regrets for not pursuing that choice. These properties are modular and interchangeable. After each choice has been detailed, the Decision summarizes each choice in one view.

- Program allows user to create entries representing a problem, detailed with choices containing various properties such as pros/cons, potential regrets, tags, etc.
- Presents choices to user to make a relatively more objective decision.
- User can see their history of decisions at a glance.
  - A nice to have feature: visualizations of decisions.
- User can register an account and sync their data in a database on a remote server.
  - State (entries, account settings, user info) will be saved locally as JSON and synced with a backend accessed with the user’s credentials.
- Interim package structure:
  - entry: Entry, Choice, Date, [Entry]Status, abstract Property, abstract CollectionProperty extends Property, abstract ValueProperty extends Property. All the properties extend CollectionProperty or ValueProperty. Examples include Pros, Cons, Tags, Regrets, RegretScale, etc.
  - util: parser to serialize state to and from JSON data
  - tests: JUnit 5 unit tests
  - ui: modular component-based UI elements. Will flesh this out more as I learn how Swing works.
  - util: tools for communicating with the backend server.
  - server (divorced): will flesh this out as I learn about creating servers in Java. Will have tools for authentication, a public API, and database communication.
- Other potential additions/improvements:
  - Reflections on past decisions
  - Reminders to follow-up on past decisions or to finish an incomplete entry
  - Entry creation templates
