drop table if exists NODE;

CREATE TABLE IF NOT EXISTS NODE
( ID INTEGER PRIMARY KEY,
  PARENT INTEGER,
  TYPE TEXT,
  DISPLAYED_TEXT TEXT,
  SOUND_PATH TEXT,
  IMAGE_PATH TEXT,
  FOREIGN KEY(PARENT) REFERENCES NODE(ID) ON DELETE CASCADE ON UPDATE CASCADE
    );

insert into NODE (ID, PARENT, TYPE, DISPLAYED_TEXT, SOUND_PATH, IMAGE_PATH) values
(0, null, 'START_NODE',
 'Bienvenue dans les contrées de l''imaginaire. Vous êtes un aventurier intrépide, prêt à affronter les dangers les ' ||
 'plus terribles pour sauver le royaume.\nVous voilà parti pour une aventure épique, où vous devrez faire des choix' ||
 ' qui détermineront votre destinée.', null, null),
(1, 0, 'INNER_NODE',
 'Vous êtes dans un lieu mystérieux. Vous marchez sur un chemin sombre et tortueux. ' ||
 'Vous marchez sur un clou et vous vous blessez.\nVous perdez 1 point de vie.', null, null),
(2, 1, 'INNER_NODE',
 'Vous êtes dans un lieu mystérieux. Vous marchez sur un chemin sombre et tortueux. ' ||
 'Vous trouvez un coffre rempli de pièces d''or.\nVous gagnez 10 pièces d''or.', 'assets/sound/coin.wav', null),
(3, 2, 'DECISION_NODE',
 'Vous êtes dans un lieu mystérieux. Vous marchez sur un chemin sombre et tortueux. ' ||
 'Vous êtes face à un dragon. Que faites-vous ?\n\n' ||
 '1. Vous attaquez le dragon.\n' ||
 '2. Vous fuyez.', null, null),
(4, 3, 'INNER_NODE',
'Vous attaquez le dragon. Vous êtes brûlé par sa flamme.\nVous perdez 5 points de vie.\n', null, null),
(5, 3, 'TERMINAL_NODE',
 'Vous fuyez le dragon. Vous vous échappez sain et sauf.\n' ||
 'Mais vous avez abandonné votre quête.\n' ||
 'Vous avez échoué.', null, null),
(6, 4, 'TERMINAL_NODE',
    'Vous avez vaincu le dragon. Félicitations !\n' ||
    'Vous avez sauvé le royaume.\n' ||
    'Vous êtes un héros.', null, null);

-- Select id, displayed_text and all id of the direct children of the node in format (id, 'displayed_text', 'id1, id2, id3')
SELECT
    parent_node.ID,
    parent_node.DISPLAYED_TEXT,
    parent_node.type,
    GROUP_CONCAT(child_node.ID) AS CHILD_IDS
FROM
    NODE AS parent_node
        LEFT JOIN
    NODE AS child_node ON parent_node.ID = child_node.PARENT
WHERE parent_node.id = 0
GROUP BY
    parent_node.ID
ORDER BY RANDOM() LIMIT 1;

select * from NODE;