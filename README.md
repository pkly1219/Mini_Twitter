# Mini Twitter Application

## Overview

This project is a Mini Twitter application developed using Java with a Graphical User Interface (GUI) built on Java Swing. The application simulates a basic social media platform, allowing users to follow each other, post tweets, and receive real-time notifications of updates. It also includes an admin control panel for managing users and groups with various design patterns applied to ensure scalability and maintainability.

## Features

- User and Group Management: Admin control panel allows for the creation and management of users and groups.
- Follow Functionality: Users can follow other users and receive updates from them.
- Post Tweets: Users can post tweets, which are shared with their followers in real-time.
- Real-Time Notifications: Followers are immediately notified when a user posts a new tweet.
- Design Patterns Implemented:
  - Singleton for the Admin Control Panel to ensure a single point of control.
  - Observer pattern to handle real-time tweet notifications to followers.
  - Visitor pattern to traverse and interact with the users and groups.
  - Composite pattern to organize users and groups hierarchically.
    
## Project Structure

The project is structured into several classes that handle different functionalities of the Mini Twitter application. Key classes include:

- AdminControlPanel.java: Centralized admin interface for managing users and groups.
- User.java: Represents individual users who can post tweets and follow others.
- Group.java: Represents groups of users, allowing hierarchical organization and management.

## Running the Application
To run the application, execute the Driver class, which will launch the GUI. From the control panel, you can:
- Create new users and groups.
- Assign users to groups.
- Follow other users.
- Post tweets and view real-time updates.

## Design Patterns

### Singleton Pattern

The Admin Control Panel follows the Singleton pattern, ensuring that there is only one instance of the control panel throughout the application’s lifecycle.

### Observer Pattern
The Observer pattern is used to notify followers in real-time when a user posts a new tweet. When a user posts a tweet, their followers (observers) are immediately updated with the new information.

### Visitor Pattern
The Visitor pattern allows operations to be performed on users and groups in a flexible and maintainable way. It provides a mechanism to separate operations from the objects on which they operate.

### Composite Pattern
The Composite pattern is utilized to manage users and groups in a tree structure, allowing the application to treat individual users and groups uniformly.

