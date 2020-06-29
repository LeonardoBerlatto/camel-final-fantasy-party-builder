-- USER

CREATE TABLE "user"
(
    id     BIGSERIAL,
    "name" VARCHAR(50) NOT NULL
);

ALTER TABLE "user"
    ADD CONSTRAINT pk_user PRIMARY KEY (id);

-- PARTY

CREATE TABLE party
(
    id          BIGSERIAL,
    description VARCHAR(100) NOT NULL,
    user_id     BIGINT       NOT NULL
);

ALTER TABLE party
    ADD CONSTRAINT fk_party_user FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE party
    ADD CONSTRAINT pk_party PRIMARY KEY (id);

-- MEMBER

CREATE TABLE "member"
(
    id          BIGSERIAL,
    "name"      VARCHAR(100) NOT NULL,
    "age"       INTEGER,
    gender      CHAR(10),
    job         VARCHAR(50),
    description TEXT         NOT NULL,
    party_id    BIGINT       NOT NULL
);

ALTER TABLE "member"
    ADD CONSTRAINT pk_member PRIMARY KEY (id);

ALTER TABLE "member"
    ADD CONSTRAINT fk_member_party FOREIGN KEY (party_id) REFERENCES party (id);

-- INSERTS

INSERT INTO "user"("name")
VALUES ('José');

INSERT INTO party(description, user_id)
VALUES ('My first party', 1);

SELECT *
FROM "user"