INSERT INTO testing_app_db.roles (id, name)
VALUES (1, 'ROLE_USER');
INSERT INTO testing_app_db.roles (id, name)
VALUES (2, 'ROLE_MODERATOR');
INSERT INTO testing_app_db.roles (id, name)
VALUES (3, 'ROLE_ADMIN');

-- INSERT INTO testing_app_db.users (email, first_name, last_name, password)
-- VALUES ('just@testing.com', 'fhgfgh', 'fghfgh', '$2a$10$5xOmg6K6ru1iW7UlgQOBMu/p8RUXFbqCc7rufrl8vfKCp9eheeMmC');
-- INSERT INTO testing_app_db.users (email, first_name, last_name, password)
-- VALUES ('lol@lol.lol', 'newFirst', 'newLast', '$2a$10$ItfkPF79IXniRG8v51yCMuOKTpvo24Rq/gV7K4QinyyyEBcXZxRsm');
-- INSERT INTO testing_app_db.users (email, first_name, last_name, password)
-- VALUES ('dfg@dgf.com', 'xzvdvsd', 'xcvxc', '$2a$10$xUaccl.T87iPlJo8npAo6eKZapkbMFwI3jr0VrWwbzT48ZSHYepqm');
-- INSERT INTO testing_app_db.users (email, first_name, last_name, password)
-- VALUES ('admin@mail.com', 'admin', 'admin', '$2a$10$8n1K5H4A8i1yT8sx7tEyQeEmWmZ52FgWjxlKwrVoGlxGk2oP1LzPi');
-- INSERT INTO testing_app_db.users (email, first_name, last_name, password)
-- VALUES ('new@user.com', 'to', 'show', '$2a$10$XfSJ1GZM5c0HjJ428XpNkeaAENZpf6SVpEJ22RiCXEjcyJb96lTGO');

b.users (email, first_name, last_name, password)
VALUES ('just@testing.com', 'fhgfgh', 'fghfgh', 'password');
INSERT INTO testing_app_db.users (email, first_name, last_name, password)
VALUES ('lol@lol.lol', 'newFirst', 'newLast', 'password');
INSERT INTO testing_app_db.users (email, first_name, last_name, password)
VALUES ('dfg@dgf.com', 'xzvdvsd', 'xcvxc', 'password');
INSERT INTO testing_app_db.users (email, first_name, last_name, password)
VALUES ('admin@mail.com', 'admin', 'admin', 'password');
INSERT INTO testing_app_db.users (email, first_name, last_name, password)
VALUES ('new@user.com', 'to', 'show', 'password');

INSERT INTO testing_app_db.user_roles (user_id, role_id)
VALUES (1, 1);
INSERT INTO testing_app_db.user_roles (user_id, role_id)
VALUES (2, 1);
INSERT INTO testing_app_db.user_roles (user_id, role_id)
VALUES (3, 1);
INSERT INTO testing_app_db.user_roles (user_id, role_id)
VALUES (5, 1);
INSERT INTO testing_app_db.user_roles (user_id, role_id)
VALUES (4, 3);

INSERT INTO testing_app_db.tests (difficulty, subject, title, duration)
VALUES (2, 'MATH', 'First test', 45);
INSERT INTO testing_app_db.tests (difficulty, subject, title, duration)
VALUES (1, 'MATH', 'Second test', 3);
INSERT INTO testing_app_db.tests (difficulty, subject, title, duration)
VALUES (2, 'ENGLISH', 'Third test', 63);
INSERT INTO testing_app_db.tests (difficulty, subject, title, duration)
VALUES (0, 'MATH', 'Fourth test', 76);
INSERT INTO testing_app_db.tests (difficulty, subject, title, duration)
VALUES (0, 'ENGLISH', 'Fifth test', 23);

INSERT INTO testing_app_db.required_tests (test_id, user_id)
VALUES (2, 1);
INSERT INTO testing_app_db.required_tests (test_id, user_id)
VALUES (1, 1);
INSERT INTO testing_app_db.required_tests (test_id, user_id)
VALUES (4, 1);
INSERT INTO testing_app_db.required_tests (test_id, user_id)
VALUES (1, 3);
INSERT INTO testing_app_db.required_tests (test_id, user_id)
VALUES (2, 2);
INSERT INTO testing_app_db.required_tests (test_id, user_id)
VALUES (3, 2);

insert into testing_app_db.questions(test_id, question_text)
values (2, 'First Question for second test');
insert into testing_app_db.questions(test_id, question_text)
values (2, 'Second Question for second test');