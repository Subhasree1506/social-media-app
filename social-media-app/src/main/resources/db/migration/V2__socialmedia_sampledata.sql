-- Users table
INSERT INTO socialmedia_app.Users (username, email, password_hash, full_name, gender, date_of_birth, bio)
VALUES
    ('alice_smith', 'alice@example.com', 'hashed_password_1', 'Alice Smith', 'Female', '1988-03-10', 'Bio of Alice Smith'),
    ('charlie_brown', 'charlie@example.com', 'hashed_password_2', 'Charlie Brown', 'Male', '1992-07-25', 'Bio of Charlie Brown');

-- Posts table
INSERT INTO socialmedia_app.Posts (user_id, content)
VALUES
    (1, 'Hello, everyone! This is Alice.'),
    (2, 'Just enjoying a quiet evening.');

-- Comments table
INSERT INTO socialmedia_app.Comments (post_id, user_id, content)
VALUES
    (1, 2, 'Nice to see you, Alice!'),
    (2, 1, 'Sounds peaceful, Charlie.');

-- Likes table
INSERT INTO socialmedia_app.Likes (post_id, user_id)
VALUES
    (1, 2),
    (2, 1);
