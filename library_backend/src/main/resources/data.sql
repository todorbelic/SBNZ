INSERT INTO app_user (username, role, password)
VALUES ('1', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO app_user (username, role, password)
VALUES ('2', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO app_user (username, role, password)
VALUES ('3', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO app_user (username, role, password)
VALUES ('4', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO app_user (username, role, password)
VALUES ('5', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO app_user (username, role, password)
VALUES ('6', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO app_user (username, role, password)
VALUES ('7', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO app_user (username, role, password)
VALUES ('8', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO app_user (username, role, password)
VALUES ('9', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO app_user (username, role, password)
VALUES ('10', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO app_user (username, role, password)
VALUES ('11', 0,'$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO author (first_name, last_name)
VALUES ('Harper', 'Lee');
INSERT INTO author (first_name, last_name)
VALUES ('George', 'Orwell');
INSERT INTO author (first_name, last_name)
VALUES ('J.R.R.', 'Tolkien');
INSERT INTO author (first_name, last_name)
VALUES ('F. Scott', 'Fitzgerald');
INSERT INTO author (first_name, last_name)
VALUES ('Jane', 'Austen');
INSERT INTO author (first_name, last_name) VALUES ('Stephen', 'King');
INSERT INTO author (first_name, last_name) VALUES ('Ernest', 'Hemingway');
INSERT INTO author (first_name, last_name) VALUES ('Virginia', 'Woolf');
INSERT INTO author (first_name, last_name) VALUES ('Mark', 'Twain');
INSERT INTO author (first_name, last_name) VALUES ('Charles', 'Dickens');
INSERT INTO author (first_name, last_name) VALUES ('Leo', 'Tolstoy');
INSERT INTO author (first_name, last_name) VALUES ('Agatha', 'Christie');
INSERT INTO author (first_name, last_name) VALUES ('John', 'Steinbeck');
INSERT INTO author (first_name, last_name) VALUES ('William', 'Shakespeare');
INSERT INTO author (first_name, last_name) VALUES ('Emily', 'Brontë');

/*INSERT INTO author (first_name, last_name, books)
VALUES ('J.K.', 'Rowling', '{1,2,3}');*/

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('To Kill a Mockingbird', 1, 1, 2000.00, false, CURRENT_DATE, '2023-01-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('1984', 2, 2, 3500, false, CURRENT_DATE, '2023-02-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('The Lord of the Rings', 3, 3, 1000, false, CURRENT_DATE, '2023-03-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('The Great Gatsby', 4, 1, 500, false, CURRENT_DATE, '2022-12-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('Pride and Prejudice', 5, 0, 700, false, CURRENT_DATE, '2023-04-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('Algebra', 5, 4, 8000, false, CURRENT_DATE, '2023-05-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('The Shining', 6, 1, 1500.00, false, CURRENT_DATE, '2023-06-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('The Old Man and the Sea', 7, 3, 2500, false, CURRENT_DATE, '2023-07-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('Mrs. Dalloway', 8, 4, 1200, false, CURRENT_DATE, '2023-08-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('Adventures of Huckleberry Finn', 9, 1, 800, false, CURRENT_DATE, '2023-09-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('A Tale of Two Cities', 10, 0, 900, false, CURRENT_DATE, '2023-10-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('War and Peace', 6, 3, 4000, false, CURRENT_DATE, '2023-11-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('Murder on the Orient Express', 7, 2, 1800, false, CURRENT_DATE, '2023-12-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('Of Mice and Men', 8, 1, 700, false, CURRENT_DATE, '2024-01-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('Hamlet', 9, 4, 1600, false, CURRENT_DATE, '2024-02-01');

INSERT INTO book (name, author_id, genre, price, recommended, add_date, publish_date)
VALUES ('Wuthering Heights', 10, 0, 950, false, CURRENT_DATE, '2024-03-01');

INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 1, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 2, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 3, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 4, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 5, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 6, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 7, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 8, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 9, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 10, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 11, 1);
INSERT INTO rating (value, user_id, book_id)
VALUES (5.0, 1, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 2, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 3, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 4, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 5, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (3.5, 6, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (4.5, 7, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (4.5, 8, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (4.5, 9, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (5.0, 10, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (5.0, 11, 2);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 1, 3);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 2, 3);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 3, 3);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 4, 3);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 5, 3);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 6, 3);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 7, 3);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 8, 3);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 9, 3);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 10, 3);
INSERT INTO rating (value, user_id, book_id)
VALUES (1.0, 11, 3);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 1);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 2);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 3);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 4);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 5);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 6);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 7);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 8);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 9);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 10);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (1 , 11);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 12);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 13);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 14);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 15);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 16);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 17);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 18);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 19);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 20);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 21);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (2 , 22);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 23);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 24);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 25);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 26);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 27);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 28);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 29);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 30);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 31);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 32);
INSERT INTO book_ratings (book_id, ratings_id)
VALUES (3 , 33);





