INSERT INTO PUBLIC.AUTHOR(ID_AUTHOR, NAME) VALUES
(1, 'Mark Twain'),
(2, 'Victor Hugo'),
(3, 'Voltaire'),
(4, 'Albert Camus'),
(5, STRINGDECODE('\u00c9mile Zola'));

INSERT INTO PUBLIC.PUBLISHER(ID_PUBLISHER, NAME) VALUES
(1, 'J''ai lu');

INSERT INTO PUBLIC.BOOK(ID_BOOK, TITLE, PUBLICATION_YEAR, DESCRIPTION, STATE, ID_PUBLISHER, ID_AUTHOR) VALUES
(1, 'The Mysterious Stranger', 1916, 'Ceci est un livre ... si si !', 0, 1, '1'),
(2, 'Les aventures de Huckleberry Finn', 1884, 'Un classique.', 0, 1, '2'),
(3, 'Les Fleurs du mal', 1857, STRINGDECODE(' un livre qui scandalise la soci\u00e9t\u00e9 contemporaine.'), 0, 1, '3');
