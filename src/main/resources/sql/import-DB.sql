ALTER TABLE post MODIFY body TEXT null;

-- Insert Users

INSERT INTO USER (user_id, password, email, username, first_name, last_name)
VALUES (1, '12345', 'user@mail.com', 'mbrahma', 'Marci', 'Brahma');

INSERT INTO USER (user_id, password, email, username, first_name, last_name)
VALUES (2, '12345', 'johndoe@gmail.com', 'johndoe', 'John', 'Doe');

INSERT INTO USER (user_id, password, email, username, first_name, last_name)
VALUES (3, '12345', 'ana@mail.com', 'ana', 'Ana', 'Surname');

-- Insert Posts
INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (1, 'ZipZapZop First Blog', 'This is the first blog for the site! Thank you for visiting the site. We hope you enjoy the content', '2019-04-09',1);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (2, 'Little Teapot', 'I’m a little teapot, short and stout Here’s my handle (place hand on hip) Here’s my spout (stick your other arm out straight)
 When I get all steamed up, hear me shout Just tip me over and pour me out (lean over with your spout arm)', '2019-04-09',1);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (3, 'Humpty Dumpty', 'Humpty Dumpty sat on a wall, Humpty Dumpty had a great fall, All the king’s horses and all the king’s men, Couldn’t put Humpty together again.', '2019-04-09',2);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (4, 'Hey Diddle Diddle', 'Hey diddle diddle, the cat and the fiddle, The cow jumped over the moon. The little dog laughed to see such fun And the dish ran away with the spoon!', current_timestamp,3);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (5, 'Clean Code', 'It should be elegant — Clean code should be pleasing to read. Reading it should make you smile the way a well-crafted music box or well-designed car would.
Clean code is focused —Each function, each class, each module exposes a single-minded attitude that remains entirely undistracted, and unpolluted, by the surrounding details.
Clean code is taken care of. Someone has taken the time to keep it simple and orderly. They have paid appropriate attention to details. They have cared.
Runs all the tests Contains no duplication Minimize the number of entities such as classes, methods, functions, and the like.', '2019-04-09',3);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (6, '5 Tips For a Memorable Introduction in Your Job Interview', '1. Honor their time - Tardiness to a job interview gives the prospective employer the impression of laziness and lack of consideration for their time. Make an entrance no more than 10 minutes prior to the interview start time to prevent rushing the interviewer. If the meeting takes place via Skype or phone, call or go online just a minute or two before the scheduled interview — but test all equipment well beforehand. 2.- Remember that courtesy starts at the reception desk. Treat everyone you encounter at a prospective workplace with the same courtesy expected by the CEO. This behavior shows good manners. 3. Do your homework - Prepare answers to common interview questions before the interview, and practice responses with a trusted individual. Preparing answers in advance eliminates the dreaded, “Uh…” 4. Smile and shake hands - Confidence radiates from a smile, so wear one when you’re meeting an interviewer. When you’re interviewing on the phone, wear a genuine smile while greeting the prospective employer. Without visuals, a smile still gives a friendly but professional impression, even influencing your voice. 5. Have an elevator pitch - The toughest question in many job interviews is well-known: “Tell us why you’re right for this position.” Have an instant comeback by perfecting an elevator pitch that highlights your unique talents. The response will come naturally if it’s practiced in advance — just avoid sounding like a robot or someone reading a script.', current_timestamp,1);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (7,'12 Tips to Survive in a Modern World', '1.Let the baby have its bottle-or starters, letting it go will prevent you from developing a habit of resenting all of humanity, and your brain can focus on more important things. Second, douches like that are probably miserable so give them the feeling that they won, and are better than you. It really doesnt matter  2. Be sad- Buck up billy, it’ll get better! I’m writing this post on the way back from Sweden after a great couple of days with a client. I’m happy, but I decided to listen to The Saddest Song in the World by Meiko just because. 3. Go to therapy  4. Encourage your kids to be late for school 5. Hold the door open 6. Be a selfish prick 7. Don’t google it 8. Buy something you can’t afford 9. Change careers 10. Call someone on their bullshit 11. Spend time with your brain 12. Stop Improving.', current_timestamp,2);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (8,'7 Personal Growth Hacks to Survive a Coding Bootcamp', '1. Tenacity - Because it is a short program, expect it to be dense and intense. 2. Goal-oriented focus - set aggressive goals to motivate yourself. 3.Attentiveness - Genuinely be interested in learning. Don’t just go through the modules with the goal of just finishing it. 4. Meditation and mindfulness - A clear mind can give you clear thoughts and meditation will help keep anyone focused. 5. Learn to take breaks - Stuck on a problem? Your brain gets fatigued after a while, and maybe that’s the reason why you can’t get anything done right. 6. Don’t let stress bottle up; be honest about anxiety - If you let pressure build up inside, you will become more and more anxious and miss out on finding the fun and interest in actually learning. 7. Coding bootcamps are designed for optimum learning, but don’t feel bad if you don’t learn everything, because chances are, you might never truly will.  ', current_timestamp,3);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (9,'Mark''s Word of the Day : Cabotinage' , 'Cabotinage. : Behavior befitting a second-rate actor : obvious playing to the audience : Theatricality. ', current_timestamp,2);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (10, 'Wisdom on Life','The meaning of life is what you make of it. ',{ts '2016-10-19 11:10:13.247'},null);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (11, 'Fairbank''s Formulations : Sulfur Dioxide','{ S + O2 -> SO2 }. Don''t do this! ',current_timestamp ,null);

INSERT INTO POST (post_id, title, body, create_date, user_id)
VALUES (12, 'Fairbank''s Formulations : REDLICH-KWONG Equation of State','{ p=(RT)/(Vm-b) - (a/(sqrt(T)*Vm(Vm+b)) }. an empirical, algebraic equation that relates temperature, pressure, and volume of gases.',current_timestamp ,null);

-- Insert comments
INSERT INTO COMMENT (comment_id, body, create_date, post_id, user_id)
VALUES (1, 'Cool!', {ts '2016-10-19 11:10:13.247'}, 1, 3);

INSERT INTO COMMENT (comment_id, body, create_date, post_id, user_id)
VALUES (2, 'You are a little teapot lol', CURRENT_TIMESTAMP, 2, 1);

INSERT INTO COMMENT (comment_id, body, create_date, post_id, user_id)
VALUES (3, 'So sad about humpty dumpty', CURRENT_TIMESTAMP, 3, 1);

INSERT INTO COMMENT (comment_id, body, create_date, post_id, user_id)
VALUES (4, 'So cute!', CURRENT_TIMESTAMP, 2, 3);

INSERT INTO COMMENT (comment_id, body, create_date, post_id, user_id)
VALUES (5, 'Very true!', {ts '2016-10-19 11:10:13.247'}, 10, 3);


-- Insert Tags
INSERT INTO TAG (tag_id, key_word)
VALUES (1, 'Nursery Rhymes');

INSERT INTO TAG (tag_id, key_word)
VALUES (2, 'Tips');

INSERT INTO TAG (tag_id, key_word)
VALUES (3, 'Coding Advice');

INSERT INTO TAG (tag_id, key_word)
VALUES (4, 'Mark''s Word');

INSERT INTO TAG (tag_id, key_word)
VALUES (5, 'Formulations');

INSERT INTO TAG (tag_id, key_word)
VALUES (6, 'Will''s Wisdom');

-- Insert into PostTags

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (2,1);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (3,1);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (4,1);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (5,2);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (6,2);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (6,3);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (7,2);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (7,3);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (9,4);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (10,6);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (11,5);

INSERT INTO POST_TAG (post_id, tag_id)
VALUES (12,5);


