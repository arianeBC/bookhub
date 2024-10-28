CREATE TABLE book
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    author      VARCHAR(255) NOT NULL,
    isbn        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    cover_image VARCHAR(255),
    available   BOOLEAN      NOT NULL DEFAULT TRUE,
    archived    BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(255) NOT NULL,
    updated_by   VARCHAR(255) NOT NULL
);

CREATE TABLE bookmark
(
    id          SERIAL PRIMARY KEY,
    book_id     BIGINT,
    user_id     VARCHAR(255) NOT NULL,
    is_notified BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(255) NOT NULL,
    updated_by   VARCHAR(255) NOT NULL
    FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE book_transaction_history
(
    id                SERIAL PRIMARY KEY,
    book_id           BIGINT,
    user_id           VARCHAR(255) NOT NULL,
    returned          BOOLEAN      NOT NULL DEFAULT FALSE,
    returned_approved BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(255) NOT NULL,
    updated_by   VARCHAR(255) NOT NULL
    FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE feedback
(
    id      SERIAL PRIMARY KEY,
    book_id BIGINT,
    rating  DOUBLE PRECISION CHECK (rating >= 0 AND rating <= 5),
    comment TEXT,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(255) NOT NULL,
    updated_by   VARCHAR(255) NOT NULL
    FOREIGN KEY (book_id) REFERENCES book (id)
);

-- TODO update admin with real userId and coverImage
INSERT INTO book (title, author, isbn, description, cover_image, available, archived, created_by, updated_by)
VALUES
    ('Romeo and Juliet', 'William Shakespeare', '978-0141439556',
     'A tragic romance set in Verona, this timeless play by William Shakespeare tells the story of two young lovers whose deaths ultimately reconcile their feuding families. Romeo and Juliet’s intense and passionate relationship is doomed by the hatred between their families, making it one of literature’s most enduring love stories.',
     'romeo-and-juliet.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('Frankenstein; Or, The Modern Prometheus', 'Mary Wollstonecraft Shelley', '978-0486282114',
     'In this classic Gothic novel, Mary Wollstonecraft Shelley explores the consequences of playing God through the story of Victor Frankenstein, a scientist who creates a sentient creature in an unorthodox experiment. As the creature seeks revenge on its creator for a life of misery and isolation, the story delves into themes of ambition, humanity, and the ethical limits of scientific exploration.',
     'frankenstein.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('Moby Dick; Or, The Whale', 'Herman Melville', '978-1503280786',
     'Herman Melville’s epic tale follows the obsessive quest of Captain Ahab, who seeks vengeance against Moby Dick, a giant white whale that maimed him. Narrated by Ishmael, the novel combines adventure, philosophical musings, and detailed descriptions of whaling, making it a rich and complex narrative about the struggle between man and nature.',
     'mobydick.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('Pride and Prejudice', 'Jane Austen', '978-1503290561',
     'Jane Austen’s beloved novel revolves around Elizabeth Bennet, a sharp-witted and independent young woman, and her evolving relationship with the proud Mr. Darcy. Set in the early 19th century, the novel delves into themes of social class, marriage, and morality, offering both romance and keen social commentary.',
     'pride-and-prejudice.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('Middlemarch', 'George Eliot', '978-0199231151',
     'George Eliot’s Middlemarch is a richly detailed exploration of life in a provincial English town. The novel weaves together the lives of its characters, including the idealistic Dorothea Brooke and the ambitious Dr. Lydgate, to provide a critique of social, political, and economic issues of the time, showcasing Eliot’s masterful character development and social observation.',
     'middlemarch.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('A Room with a View', 'E. M. Forster', '978-0156664501',
     'This satirical novel follows Lucy Honeychurch, a young woman who finds herself torn between a conventional life and the liberating experiences of a more passionate, unconventional romance. Set against the backdrop of Edwardian society, the novel explores themes of love, social expectations, and personal freedom.',
     'a-room-with-a-view.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('The Complete Works of William Shakespeare', 'William Shakespeare', '978-0451526884',
     'This comprehensive collection includes all of William Shakespeare’s plays, sonnets, and poems. It offers a complete look at the bard’s extensive oeuvre, ranging from his tragic masterpieces like Hamlet and Macbeth to his comedies and histories, providing a rich resource for exploring the full spectrum of Shakespeare’s literary genius.',
     'the-complete-works-of-william-shakespeare.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('Little Women; Or, Meg, Jo, Beth, and Amy', 'Louisa May Alcott', '978-1503280298',
     'Louisa May Alcott’s cherished novel follows the lives of the March sisters—Meg, Jo, Beth, and Amy—as they navigate the challenges of growing up in post-Civil War America. With its themes of family, love, and personal growth, Little Women provides a heartwarming and insightful look at the trials and triumphs of young womanhood.',
     'little-women.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('Alice''s Adventures in Wonderland', 'Lewis Carroll', '978-0486284736',
     'Lewis Carroll''s whimsical tale follows the curious Alice as she falls down a rabbit hole into a fantastical world filled with eccentric characters and bizarre adventures. With its playful language, imaginative settings, and satirical undertones, this classic children’s book continues to enchant readers of all ages.',
     'alice-s-adventures-in-wonderland.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('The Enchanted April', 'Elizabeth Von Arnim', '978-0486284712',
     'In this charming novel, four women escape their mundane lives in England for a month-long stay in an Italian villa. The transformative experience leads them to personal revelations and newfound friendships. Elizabeth Von Arnim’s tale is a delightful exploration of self-discovery and the healing power of nature and new beginnings.',
     'the-enchanted-april.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('The Blue Castle: a novel', 'L. M. Montgomery', '978-1773232451',
     'L. M. Montgomery’s novel follows Valancy Stirling, a woman who breaks free from the constraints of her repressive life and embarks on a journey of self-discovery and love. Set in the picturesque Canadian wilderness, the story explores themes of personal freedom and transformation, all with Montgomery’s trademark warmth and charm.',
     'the-blue-castle-a-novel.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('The Adventures of Ferdinand Count Fathom — Complete', 'T. Smollett', '978-0486270356',
     'This novel by Tobias Smollett follows the exploits of the roguish Ferdinand Count Fathom, a schemer and adventurer who manipulates those around him for personal gain. The story is a satirical look at social mores and personal ambition, filled with Smollett’s sharp wit and elaborate plot twists.',
     'the-adventures-of-ferdinand-count-fathom.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('Cranford', 'Elizabeth Cleghorn Gaskell', '978-0140434414',
     'Elizabeth Gaskell’s Cranford offers a humorous and affectionate portrayal of life in a small English town through the eyes of its elderly, predominantly female residents. The novel explores themes of social change, community, and the dynamics of small-town life with a keen eye for detail and character.',
     'cranford.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('The Expedition of Humphry Clinker', 'T. Smollett', '978-0486281148',
     'Tobias Smollett’s novel chronicles the travels and adventures of Humphry Clinker and his family as they journey through 18th-century England. The narrative is presented through a series of letters and is known for its satirical humor and vivid depiction of the social landscape of the time.',
     'the-expedition-of-humphry-clinker.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('History of Tom Jones, a Foundling', 'Henry Fielding', '978-0140431253',
     'Henry Fielding’s novel follows the life of Tom Jones, an illegitimate but good-hearted young man, as he navigates the challenges of 18th-century English society. With its rich characterizations and comedic elements, the novel is a classic example of the picaresque genre and a sharp commentary on social class and morality.',
     'history-of-tom-jones-a-foundling.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('The Adventures of Roderick Random', 'T. Smollett', '978-0486283685',
     'This picaresque novel by Tobias Smollett follows the misadventures of the young Roderick Random as he travels through various social strata in 18th-century Britain. Filled with humor, satire, and vibrant characters, the book offers a lively exploration of the trials and tribulations of its hero.',
     'the-adventures-of-roderick-random.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('Twenty Years After', 'Alexandre Dumas and Auguste Maquet', '978-0375761480',
     'The sequel to The Three Musketeers, this novel by Alexandre Dumas and Auguste Maquet continues the adventures of d’Artagnan and his comrades twenty years later. The story is filled with intrigue, political machinations, and the enduring camaraderie of the musketeers as they navigate a changing world.',
     'twenty-years-after.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('My Life — Volume 1', 'Richard Wagner', '978-0870236744',
     'In this autobiographical work, Richard Wagner details his life, career, and artistic philosophy. Volume 1 covers his early years and the development of his revolutionary ideas about music and opera, offering insights into the man behind some of the most influential works in classical music.',
     'my-life-volume-1.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('Metamorphosis', 'Franz Kafka', '978-0553213690',
     'Franz Kafka’s Metamorphosis is a profound and unsettling story about Gregor Samsa, who wakes up one morning to find himself transformed into a giant insect. The novel explores themes of alienation, identity, and the human condition, making it one of Kafka’s most significant works.',
     'metamorphosis.jpg', TRUE, FALSE, 'admin', 'admin'),

    ('Beowulf: An Anglo-Saxon Epic Poem', 'J. Lesslie Hall', '978-0486288659',
     'Beowulf is an epic poem from the Anglo-Saxon period that chronicles the heroic deeds of Beowulf, a warrior who battles the monster Grendel, Grendel’s mother, and a fearsome dragon. This edition provides a classic translation of the epic, highlighting its themes of heroism, honor, and the struggle between good and evil.',
     'beowulf-an-anglo-saxon-epic-poem.jpg', TRUE, FALSE, 'admin', 'admin');

-- TODO update admin with real userId
INSERT INTO feedback (book_id, rating, comment, created_by, updated_by) VALUES
   (1, 5, 'A timeless masterpiece of love and tragedy. Shakespeare''s exploration of youthful passion and familial conflict is unparalleled. Every line is filled with emotion and depth.', 'admin', 'admin'),
   (2, 4, 'Shelley''s Gothic classic is as relevant today as ever, examining the ethical limits of science and the quest for understanding. A thought-provoking read with a chillingly memorable creature.', 'admin', 'admin'),
   (3, 4, 'Melville’s novel is a deep dive into obsession and the human condition. Though dense at times, its exploration of Captain Ahab''s vendetta against Moby Dick is compelling and profound.', 'admin', 'admin'),
   (4, 5, 'Jane Austen’s sharp wit and social commentary make this a delightful read. Elizabeth Bennet and Mr. Darcy''s evolving relationship is a charming portrayal of love and social norms.', 'admin', 'admin'),
   (5, 5, 'George Eliot''s novel is a richly detailed exploration of provincial life and social issues. The characters are deeply developed, and the narrative provides insightful commentary on societal expectations.', 'admin', 'admin'),
   (6, 5, 'Forster’s novel is a delightful exploration of love and societal expectations set in both England and Italy. The protagonist’s journey of self-discovery is both engaging and thought-provoking.', 'admin', 'admin'),
   (7, 5, 'A comprehensive collection of Shakespeare’s genius. Whether you’re a student or a lifelong fan, this complete works edition is an invaluable resource and a treasure trove of literary brilliance.', 'admin', 'admin'),
   (8, 5, 'Louisa May Alcott’s beloved story of the March sisters is heartwarming and enduring. It beautifully captures the trials and joys of growing up, making it a cherished classic for all ages.', 'admin', 'admin'),
   (9, 5, 'Lewis Carroll’s whimsical tale is a fantastical journey through a surreal world. Its playful language and imaginative characters make it a delightful read for both children and adults.', 'admin', 'admin'),
   (10, 4, 'Elizabeth Von Arnim’s novel is a charming escape to Italy that beautifully captures the transformative power of a change in scenery. The characters'' personal growth is portrayed with warmth and humor.', 'admin', 'admin'),
   (11, 4, 'L. M. Montgomery’s story of self-discovery and romance is both engaging and uplifting. Set in a picturesque Canadian setting, it’s a feel-good novel that celebrates personal freedom and transformation.', 'admin', 'admin'),
   (12, 3, 'Smollett’s satirical novel offers a humorous take on the adventures of a roguish protagonist. While entertaining, its style may feel dated to modern readers, but it’s a fascinating historical read.', 'admin', 'admin'),
   (13, 4, 'Gaskell’s portrayal of a small English town is both humorous and touching. The novel’s gentle satire and endearing characters make it a charming look at community and social change.', 'admin', 'admin'),
   (14, 3, 'Tobias Smollett’s picaresque novel offers an amusing account of travel and adventure. Its episodic nature and satirical commentary on society are entertaining but may feel episodic.', 'admin', 'admin'),
   (15, 4, 'Henry Fielding’s novel is a lively and engaging picaresque tale. The adventures of Tom Jones are filled with humor and social satire, offering a vivid portrayal of 18th-century English life.', 'admin', 'admin'),
   (16, 3, 'Smollett’s novel provides a satirical and often humorous look at the life of a roguish protagonist. Its lively narrative and social commentary offer insight into the era but may be uneven in its execution.', 'admin', 'admin'),
   (17, 4, 'Dumas’s sequel to The Three Musketeers continues the thrilling adventures of d’Artagnan and his friends. It’s filled with intrigue, action, and the camaraderie that made the original so compelling.', 'admin', 'admin'),
   (18, 3, 'Richard Wagner’s autobiography offers a fascinating glimpse into the life and thoughts of one of opera’s greatest composers. While insightful, it’s most suited for those with a deep interest in Wagnerian opera.', 'admin', 'admin'),
   (19, 5, 'Kafka’s novella is a profound exploration of alienation and identity. The surreal transformation of Gregor Samsa is both disturbing and insightful, making it a powerful and enduring work.', 'admin', 'admin'),
   (20, 4, 'This epic poem is a cornerstone of Anglo-Saxon literature, filled with heroic deeds and battles. The translation offers a glimpse into early medieval culture and heroic ideals, making it a compelling and historic read.', 'admin', 'admin');