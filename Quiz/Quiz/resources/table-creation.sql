USE quizDb;

CREATE TABLE MultichoiceQuiz(
    id INT NOT NULL AUTO_INCREMENT,
    question VARCHAR(100),
    answerA VARCHAR(100),
    answerB VARCHAR(100),
    answerC VARCHAR(100),
    answerD VARCHAR(100),
    correctAnswer VARCHAR(2),
    PRIMARY KEY (id)
);

CREATE TABLE BinaryQuiz(
    id INT NOT NULL AUTO_INCREMENT,
    question VARCHAR(100),
    correctAnswer BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE Score(
    id INT NOT NULL AUTO_INCREMENT,
    `user` VARCHAR(10),
    score INT,
    topic VARCHAR(30),
    PRIMARY KEY (id)
);