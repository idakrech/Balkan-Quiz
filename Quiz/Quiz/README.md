# Quiz Application – Java OOP Exam Project

This repository contains my final project for the Object-Oriented Programming course at Kristiania University College. The project was completed as part of the final exam in 2022.

## 📌 Project Summary

The task was to create a quiz application in Java that demonstrates object-oriented principles. The application supports two types of quizzes:

- **BinaryQuiz** (Yes/No questions)
- **MultichoiceQuiz** (Multiple choice questions)

The user can:
- Choose which type of quiz to take
- Answer the questions
- View their score at the end

Questions are stored in a **MySQL database**, which is set up via provided SQL scripts.

## 🧱 Project Structure

```
Quiz/
└── Quiz/
    ├── src/              # Java source code
    └── resources/        # SQL scripts used by the application
```

## 🛠 Technologies

- Java (OOP principles)
- MySQL (for storing questions)
- CLI (console-based interface)

## 🗃 SQL Setup

To set up the database, run the following scripts from the `resources/` folder in order:

1. `database-creation.sql`
2. `table-creation.sql`
3. `question-insertion-binaryQuiz.sql`
4. `question-insertion-multichoiceQuiz.sql`

## 🚀 How to Run

Compile and run `Main.java`. The application will:
1. Connect to the database
2. Ask which quiz type you want
3. Guide you through the quiz
4. Show your score

> Note: You need a local or remote MySQL server running with access credentials updated in the code.

## 📄 License

This project was created for educational purposes. Feel free to explore and reuse code concepts.
