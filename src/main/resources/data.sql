INSERT INTO users (name, description)
VALUES ('Martha', 'Balettlehrerin'),
       ('Petra', 'Contemporarylehrerin'),
       ('Ulrike', 'Boxtrainerin'),
       ('Christopher', 'Ballettlehrer');
    
INSERT INTO courses (title, description, category, date, duration, user_id)
VALUES ('Ballett Anfänger', 'Ballett für Anfänger', 'Ballett', '2022-12-02T15:00:00Z', '90', 1),
       ('Contemporary Mittelstufe', 'Contemporary für Mittelstufe', 'Contemporary', '2022-12-16T18:00:00Z', '90', 2),
       ('Boxen', 'Boxtraining', 'Stand-Up', '2022-12-20T19:00:00Z', '60', 3),
       ('Ballett Mittelstufe', 'Ballett für Mittelstufe', 'Ballett', '2022-12-23T17:00:00Z', '90', 4);
