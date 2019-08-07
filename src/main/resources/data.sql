INSERT INTO CATEGORIES(ID, NAME) VALUES 
	(1, 'TERROR'), 
	(2, 'COMEDIA'), 
	(3, 'ACCIÓN');
    
INSERT INTO TV_SHOWS(ID, NAME, SHORT_DESC, LONG_DESC, YEAR, RECOMMENDED_AGE, CATEGORY_ID) VALUES 
	(1, 'Juego de tronos', 'Descripción corta', 'Descripción larga', '2012', 16, 3), 
	(2, 'American horror Story', NULL, NULL, '2010', 16, 1), 
	(3, 'Big Bang', NULL, NULL, '2008', 7, 2);
    
INSERT INTO SEASONS(ID, NUMBER, NAME, TV_SHOW_ID) VALUES 
	(1, 1, 'One', 1), 
	(2, 2, 'Two', 1), 
	(3, 1, 'One', 2), 
	(4, 2, 'Two', 2), 
	(5, 3, 'Three', 2), 
	(6, 1, 'One', 3);

INSERT INTO CHAPTERS(ID, NUMBER, NAME, DURATION, SEASON_ID) VALUES 
	(1, 1, 'Chapter 1', 43, 1), 
	(2, 2, 'Chapter 2', 45, 1), 
	(3, 3, 'Chapter 3', 44, 1),
	(4, 1, 'Chapter 0', 50, 2);
	
	
INSERT INTO ACTORS(ID, NAME, SURNAME, DAY_OF_BIRTH) VALUES 
	(2, 'EMILIA', 'CLARKE', '1990-08-22');
	
INSERT INTO AWARDS(ID, NAME) VALUES 
	(1, 'EMMY MEJOR SERIE'),
	(2, 'EMMY MEJOR ACTOR');
    
INSERT INTO TV_SHOWS_AWARDS(ID, TV_SHOWS_ID, AWARDS_ID, YEAR) VALUES
	(1, 1, 1, '2018'),
	(2, 1, 2, '2018'),
	(3, 2, 1, '2019');
    
INSERT INTO CHAPTERS_ACTORS(ID, CHAPTER_ID, ACTOR_ID) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 2, 1),
	(4, 2, 2),
	(5, 4, 2);