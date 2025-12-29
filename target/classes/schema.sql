DROP TABLE IF EXISTS notification;
DROP TABLE IF EXISTS template;

CREATE TABLE template (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    body TEXT NOT NULL
);

CREATE TABLE notification (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    phone_number TEXT NOT NULL,
    template_id INTEGER NOT NULL,
    personalization TEXT,
    FOREIGN KEY (template_id) REFERENCES template(id)
);