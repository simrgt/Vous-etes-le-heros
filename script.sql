drop table if exists NODE;
drop table if exists PLAYER;

CREATE TABLE IF NOT EXISTS PLAYER
( ID INTEGER PRIMARY KEY autoincrement,
    NAME TEXT,
    LIFE_POINT INTEGER,
    GOLD INTEGER
);

CREATE TABLE IF NOT EXISTS NODE
(   ID INTEGER PRIMARY KEY,
    PARENT INTEGER,
    TYPE TEXT,
    DISPLAYED_TEXT TEXT,
    SOUND_PATH TEXT,
    IMAGE_PATH TEXT,
    ATTRIBUTE TEXT,
    VALUEATTRIBUTE INTEGER,
    FOREIGN KEY(PARENT) REFERENCES NODE(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into NODE (ID, PARENT, TYPE, DISPLAYED_TEXT, SOUND_PATH, IMAGE_PATH, ATTRIBUTE, VALUEATTRIBUTE) values
(0, null, 'START_NODE',
 'Bienvenue dans les contrées de l''imaginaire. Vous êtes un aventurier intrépide, prêt à affronter les dangers les ' ||
 'plus terribles pour sauver le royaume.\nVous voilà parti pour une aventure épique, où vous devrez faire des choix' ||
 ' qui détermineront votre destinée.', null, null, null, null),
(1, 0, 'INNER_NODE',
 'Vous êtes dans un lieu mystérieux. Vous marchez sur un chemin sombre et tortueux. ' ||
 'Vous marchez sur un clou et vous vous blessez.\nVous perdez 1 point de vie.', null, null, null, null),
(2, 1, 'INNER_NODE',
 'Vous êtes dans un lieu mystérieux. Vous marchez sur un chemin sombre et tortueux. ' ||
 'Vous trouvez un coffre rempli de pièces d''or.\nVous gagnez 10 pièces d''or.', 'assets/sound/coin.wav', null, null, null),
(3, 2, 'DECISION_NODE',
 'Vous êtes dans un lieu mystérieux. Vous marchez sur un chemin sombre et tortueux. ' ||
 'Vous êtes face à un dragon. Que faites-vous ?\n\n' ||
 '1. Vous attaquez le dragon.\n' ||
 '2. Vous fuyez.', null, null, null, null),
(4, 3, 'INNER_NODE',
 'Vous attaquez le dragon. Vous êtes brûlé par sa flamme.\nVous perdez 5 points de vie.\n', null, null, null, null),
(5, 3, 'INNER_NODE',
 'Vous fuyez devant le dragon. En courant, vous tombez dans un trou profond.\nVous perdez 3 points de vie.\n', null, null, null, null),
(7, 4, 'TERMINAL_NODE',
 'Le dragon vous rattrape et vous dévore. Votre quête prend fin ici.\n' ||
 'Vous avez échoué.', null, null, null, null),
(8, 5, 'INNER_NODE',
 'Vous essayez de grimper hors du trou. Après de nombreux efforts, vous réussissez à sortir.\n' ||
 'Vous continuez votre aventure, déterminé à sauver le royaume.', null, null, null, null),
(10, 8, 'INNER_NODE',
 'Vous avez survécu à cette chute miraculeusement.\n' ||
 'Vous continuez votre aventure, déterminé à sauver le royaume.', null, null, null, null),
(11, 10, 'DECISION_NODE',
 'Vous voyez une bifurcation dans le chemin. Lequel choisissez-vous ?\n\n' ||
 '1. Le chemin de gauche qui semble sombre et sinistre.\n' ||
 '2. Le chemin de droite qui semble éclairé et accueillant.', null, null, null, null),
(12, 11, 'INNER_NODE',
 'Vous empruntez le chemin de gauche. Après quelques pas, vous vous rendez compte qu''il est truffé de pièges.\n' ||
 'Vous évitez de justesse un piège à pointes.\n' ||
 'Vous perdez 2 points de vie.', null, null, null, null),
(13, 11, 'INNER_NODE',
 'Vous empruntez le chemin de droite. Il vous mène à un magnifique jardin fleuri.\n' ||
 'Vous vous reposez un moment, récupérant ainsi 3 points de vie.', null, null, null, null),
(14, 13, 'TERMINAL_NODE',
 'Vous continuez votre avancée, mais un piège sournois vous attend.\n' ||
 'Vous succombez à vos blessures.\n' ||
 'Votre quête prend fin ici.\n' ||
 'Vous avez échoué.', null, null, null, null),
(15, 12, 'INNER_NODE',
 'Vous apercevez une clairière au loin. Vous décidez de vous y rendre.\n' ||
 'En chemin, vous rencontrez une fée qui vous guérit complètement.\n' ||
 'Vous récupérez tous vos points de vie.', null, null, null, null),
(16, 15, 'TERMINAL_NODE',
 'Avec votre santé restaurée, vous êtes prêt à affronter de nouveaux défis.\n' ||
 'Vous continuez votre quête pour sauver le royaume.', null, null, null, null);


-- Select id, displayed_text and all id of the direct children of the node in format (id, 'displayed_text', 'id1, id2, id3')
/*
SELECT parent_node.ID, parent_node.DISPLAYED_TEXT, parent_node.type,
GROUP_CONCAT(child_node.ID) AS CHILD_ID, parent_node.sound_path, parent_node.image_path,
parent_node.attribute, parent_node.valueattribute
FROM NODE AS parent_node LEFT JOIN NODE AS child_node ON parent_node.ID = child_node.PARENT
WHERE parent_node.id = ?
GROUP BY parent_node.ID
ORDER BY RANDOM() LIMIT 1;*/

select * from NODE;
