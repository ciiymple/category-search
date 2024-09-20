-- Sample data insertion (optional)
INSERT INTO Category (name) VALUES ('상의'), ('아우터'), ('바지'), ('스니커즈'), ('가방'), ('모자'), ('양말'), ('액세서리');
INSERT INTO Brand (name) VALUES ('A'), ('B'), ('C'), ('D'), ('E'), ('F'), ('G'), ('H'), ('I');

-- Brand A
INSERT INTO Product (name, price, category_id, brand_id)
VALUES ('A 상의', 11200, 1, 1),
       ('A 아우터', 5500, 2, 1),
       ('A 바지', 4200, 3, 1),
       ('A 스니커즈', 9000, 4, 1),
       ('A 가방', 2000, 5, 1),
       ('A 모자', 1700, 6, 1),
       ('A 양말', 1800, 7, 1),
       ('A 액세서리', 2300, 8, 1);

-- Brand B
INSERT INTO Product (name, price, category_id, brand_id)
VALUES ('B 상의', 10500, 1, 2),
       ('B 아우터', 5900, 2, 2),
       ('B 바지', 3800, 3, 2),
       ('B 스니커즈', 9100, 4, 2),
       ('B 가방', 2100, 5, 2),
       ('B 모자', 2000, 6, 2),
       ('B 양말', 2000, 7, 2),
       ('B 액세서리', 2200, 8, 2);

-- Brand C
INSERT INTO Product (name, price, category_id, brand_id)
VALUES ('C 상의', 10000, 1, 3),
       ('C 아우터', 6200, 2, 3),
       ('C 바지', 3300, 3, 3),
       ('C 스니커즈', 9200, 4, 3),
       ('C 가방', 2200, 5, 3),
       ('C 모자', 1900, 6, 3),
       ('C 양말', 2200, 7, 3),
       ('C 액세서리', 2100, 8, 3);

-- Brand D
INSERT INTO Product (name, price, category_id, brand_id)
VALUES ('D 상의', 10100, 1, 4),
       ('D 아우터', 5100, 2, 4),
       ('D 바지', 3000, 3, 4),
       ('D 스니커즈', 9500, 4, 4),
       ('D 가방', 2500, 5, 4),
       ('D 모자', 1500, 6, 4),
       ('D 양말', 2400, 7, 4),
       ('D 액세서리', 2000, 8, 4);

-- Brand E
INSERT INTO Product (name, price, category_id, brand_id)
VALUES ('E 상의', 10700, 1, 5),
       ('E 아우터', 5000, 2, 5),
       ('E 바지', 3800, 3, 5),
       ('E 스니커즈', 9900, 4, 5),
       ('E 가방', 2300, 5, 5),
       ('E 모자', 1800, 6, 5),
       ('E 양말', 2100, 7, 5),
       ('E 액세서리', 2100, 8, 5);

-- Brand F
INSERT INTO Product (name, price, category_id, brand_id)
VALUES ('F 상의', 11200, 1, 6),
       ('F 아우터', 7200, 2, 6),
       ('F 바지', 4000, 3, 6),
       ('F 스니커즈', 9300, 4, 6),
       ('F 가방', 2100, 5, 6),
       ('F 모자', 1600, 6, 6),
       ('F 양말', 2300, 7, 6),
       ('F 액세서리', 1900, 8, 6);

-- Brand G
INSERT INTO Product (name, price, category_id, brand_id)
VALUES ('G 상의', 10500, 1, 7),
       ('G 아우터', 5800, 2, 7),
       ('G 바지', 3900, 3, 7),
       ('G 스니커즈', 9000, 4, 7),
       ('G 가방', 2200, 5, 7),
       ('G 모자', 1700, 6, 7),
       ('G 양말', 2100, 7, 7),
       ('G 액세서리', 2000, 8, 7);

-- Brand H
INSERT INTO Product (name, price, category_id, brand_id)
VALUES ('H 상의', 10800, 1, 8),
       ('H 아우터', 6300, 2, 8),
       ('H 바지', 3100, 3, 8),
       ('H 스니커즈', 9700, 4, 8),
       ('H 가방', 2100, 5, 8),
       ('H 모자', 1600, 6, 8),
       ('H 양말', 2000, 7, 8),
       ('H 액세서리', 2000, 8, 8);

-- Brand I
INSERT INTO Product (name, price, category_id, brand_id)
VALUES ('I 상의', 11400, 1, 9),
       ('I 아우터', 6700, 2, 9),
       ('I 바지', 3200, 3, 9),
       ('I 스니커즈', 9500, 4, 9),
       ('I 가방', 2400, 5, 9),
       ('I 모자', 1700, 6, 9),
       ('I 양말', 1700, 7, 9),
       ('I 액세서리', 2400, 8, 9);