INSERT INTO COUNTRY (uid, name) VALUES  (1, 'Country_A'), (2, 'Country_B'), (3, 'Country_C');
INSERT INTO CITY (uid, country_uid, name)
VALUES
    (1, 1, 'City_1_A'),
    (2, 2, 'City_1_B'),
    (3, 2, 'City_2_B'),
    (4, 3, 'City_1_C'),
    (5, 3, 'City_2_C'),
    (6, 3, 'City_3_C');
