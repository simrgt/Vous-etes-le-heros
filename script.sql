drop table if exists NODE;
drop table if exists GAME_STATE;

CREATE TABLE IF NOT EXISTS NODE
(   ID INTEGER PRIMARY KEY,
    PARENT INTEGER,
    TYPE TEXT,
    DISPLAYED_TEXT TEXT,
    SOUND_PATH TEXT,
    ATTRIBUTE TEXT,
    VALUEATTRIBUTE INTEGER,
    FOREIGN KEY(PARENT) REFERENCES NODE(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE GAME_STATE (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    NAME TEXT,
    CURRENT_NODE_ID INTEGER,
    HEALTH INTEGER,
    CHARACTER TEXT,
    FOREIGN KEY(CURRENT_NODE_ID) REFERENCES NODE(ID)
);

insert into NODE (ID, PARENT, TYPE, DISPLAYED_TEXT, SOUND_PATH, ATTRIBUTE, VALUEATTRIBUTE) values
(0, null, 'START_NODE',
'Bienvenue dans les contrées de l''imaginaire. Vous êtes un aventurier intrépide, prêt à affronter les dangers les ' ||
'plus terribles pour sauver le royaume.#n#Vous voilà parti pour une aventure épique, où vous devrez faire des choix' ||
' qui détermineront votre destinée.', null, null, null),

(1, 0, 'INNER_NODE',
'Vous êtes dans un lieu mystérieux. Vous marchez sur un chemin sombre et tortueux. ' ||
'Vous marchez sur un clou et vous vous blessez.#n#Vous perdez #&# points de vie.', 'nail.wav', 'DEXTERITY', 10),

(2, 1, 'INNER_NODE',
'Vous apercevez une lumière vacillante au loin. En vous approchant, vous trouvez une petite entrée et vous engouffrer dedans.', null, null, null),

(3, 2, 'DECISION_NODE',
'Vous êtes face à un dragon endormi. Que faites-vous ?#n##n#' ||
'#t#1. Vous essayez de le voler.#n#' ||
'#t#2. Vous essayez de le tuer.', 'snoring.wav', null, null),

(4, 3, 'INNER_NODE',
'Vous essayez de voler le dragon, mais il se réveille. Il vous attaque avec ses griffes.#n#Vous perdez #&# points de vie.', 'dragon.wav', 'DEXTERITY', 12),

(5, 3, 'INNER_NODE',
'Vous essayez de tuer le dragon, mais il est trop puissant. Il vous brûle avec son souffle de feu.#n#Vous perdez #&# points de vie.', 'dragon.wav', 'STRENGTH', 15),

(6, 4, 'TERMINAL_NODE',
'Le dragon vous achève rapidement. Votre quête prend fin ici.#n#Vous avez échoué.', null, null, null),

(7, 5, 'INNER_NODE',
'Vous réussissez à blesser le dragon et il prend la fuite, vous laissant un trésor derrière. Il ne contient rien.', 'coin.wav', null, null),

(8, 7, 'INNER_NODE',
'Vous avancez plus loin dans la grotte du dragon et trouvez une épée magique.#n#Vous gagnez #&# points de vie.', null, 'HEALTH', 5),

(9, 8, 'DECISION_NODE',
'Vous rencontrez un vieil ermite qui vous propose de vous enseigner une puissante magie. Que faites-vous ?#n##n#' ||
'#t#1. Vous acceptez son offre.#n#' ||
'#t#2. Vous refusez et continuez votre chemin.', null, null, null),

(10, 9, 'INNER_NODE',
'Vous acceptez l''offre de l''ermite et il vous enseigne un sort puissant. Vous vous sentez plus fort et en meilleure santé.#n#Vous gagnez #&# points de vie.', null, 'HEALTH', 7),

(11, 9, 'INNER_NODE',
'Vous refusez l''offre de l''ermite et continuez votre chemin, mais vous vous sentez étrangement observé.#n#Vous perdez #&# points de vie.', null, 'LUCK', 8),

(12, 10, 'TERMINAL_NODE',
'Grâce à la nouvelle magie apprise, vous sentez une puissance immense vous envahir.#n#Vous décidez de rentrez chez vous avec cette nouvelle puissance et vous abandonner votre mission.', null, null, null),

(13, 11, 'INNER_NODE',
'En avançant, vous tombez dans une embuscade tendue par des gobelins. Vous combattez bravement mais êtes blessé.#n#Vous perdez #&# points de vie.', null, 'STRENGTH', 10),

(14, 13, 'CHANCE_NODE',
'Après l''embuscade, vous trouvez une potion de guérison.', null, null, null),

(15, 14, 'INNER_NODE',
'Vous buvez la potion et vous sentez immédiatement mieux.#n#Vous gagnez #&# points de vie.', null, 'HEALTH', 7),

(16, 14, 'INNER_NODE',
'Vous gardez la potion pour plus tard, mais elle se brise accidentellement.#n#Vous perdez #&# points de vie.', 'glass_break.wav', 'DEXTERITY', 6),

(17, 15, 'INNER_NODE',
'Ragaillardi par la potion, vous continuez votre aventure et trouvez un parchemin ancien.#n#Vous gagnez #&# points de vie.', null, 'HEALTH', 8),

(18, 16, 'TERMINAL_NODE',
'En continuant votre chemin, vous tombez sur un piège sournois qui vous blesse gravement.#n#Vous finissez le reste de vos jours dans ce piège seul et affamé', null, null, null),

(19, 17, 'DECISION_NODE',
'Vous trouvez une amulette magique. Que faites-vous ?#n##n#' ||
'#t#1. Vous la portez.#n#' ||
'#t#2. Vous la laissez.', null, null, null),

(20, 19, 'INNER_NODE',
'Vous portez l''amulette et sentez une force mystique vous protéger.#n#Vous gagnez #&# points de vie.', null, 'HEALTH', 8),

(21, 19, 'INNER_NODE',
'Vous laissez l''amulette et continuez votre chemin, ressentant une légère tristesse.#n#Vous perdez #&# points de vie.', null, 'LUCK', 6),

(22, 20, 'INNER_NODE',
'Avec l''amulette, vous vous sentez invincible et avancez plus profondément dans la forêt noire.#n#Vous gagnez #&# points de vie.', null, 'HEALTH', 7),

(23, 21, 'TERMINAL_NODE',
'En avançant, vous êtes attaqué par une créature des ténèbres.#n#La créature est un sbire du roi démon et vous bannis du monde', null, null, null),

(24, 22, 'DECISION_NODE',
'Vous arrivez devant un pont suspendu au-dessus d''un gouffre. Que faites-vous ?#n##n#' ||
'#t#1. Vous traversez le pont.#n#' ||
'#t#2. Vous cherchez un autre chemin.', null, null, null),

(25, 24, 'INNER_NODE',
'Vous traversez le pont, mais il s''écroule sous vos pieds. Vous tombez et vous blessez.#n#Vous perdez #&# points de vie.', null, 'DEXTERITY', 8),

(26, 24, 'INNER_NODE',
'Vous cherchez un autre chemin et trouvez un passage secret qui vous mène en sécurité.#n#Vous gagnez #&# points de vie.', null, 'HEALTH', 6),

(27, 25, 'INNER_NODE',
'Après avoir traversé le pont, vous trouvez un sanctuaire où vous pouvez vous reposer.#n#Vous gagnez #&# points de vie.', null, 'HEALTH', 7),

(28, 26, 'INNER_NODE',
'Le passage secret vous mène à une ancienne bibliothèque remplie de connaissances.#n#Vous gagnez #&# points de vie.', null, 'HEALTH', 5),

(29, 27, 'TERMINAL_NODE',
'Vous quittez le sanctuaire, revitalisé et prêt à affronter de nouveaux défis.#n#Votre aventure continue dans la partie 2', null, null, null),

(30, 28, 'TERMINAL_NODE',
'Avec la connaissance acquise, vous parvenez à déjouer les pièges du monde sombre et sortez du donjon.#n#Votre aventure continue dans la partie 2.', null, null, null);



select * from NODE;

select * from GAME_STATE;
