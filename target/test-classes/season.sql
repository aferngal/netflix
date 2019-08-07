INSERT INTO CATEGORIES(ID, NAME) VALUES 
	(1, 'TERROR'), 
	(2, 'COMEDIA'), 
	(3, 'ACCIÓN');
	
INSERT INTO TV_SHOWS(ID, NAME, SHORT_DESC, LONG_DESC, YEAR, RECOMMENDED_AGE) VALUES 
	(1, 'Juego de tronos', 'Descripción corta', 'Descripción larga', '2012', 16), 
	(2, 'American horror Story', NULL, NULL, '2010', 16), 
	(3, 'Big Bang', NULL, NULL, '2008', 7);
	
	INSERT INTO SEASONS(ID, NUMBER, NAME, TV_SHOW_ID) VALUES 
	(1, 1, 'One', 1), 
	(2, 2, 'Two', 1), 
	(3, 1, 'One', 2), 
	(4, 2, 'Two', 2), 
	(5, 3, 'Three', 2), 
	(6, 1, 'One', 3);
	
INSERT INTO CATEGORIES_TV_SHOWS(ID, CATEGORY_ID, TV_SHOW_ID) VALUES
	(1, 3, 1),
	(2, 1, 2),
	(3, 2, 3);