CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE market (
   id UUID NOT NULL DEFAULT uuid_generate_v4()
 , registration_number CHARACTER VARYING(32) NOT NULL
 , name CHARACTER VARYING(256) NOT NULL
 , active BOOLEAN NOT NULL DEFAULT TRUE
 , PRIMARY KEY (id)
);

CREATE TABLE smartphone (
   id UUID NOT NULL DEFAULT uuid_generate_v4()
 , phone_number CHARACTER VARYING(32) NOT NULL
 , active BOOLEAN NOT NULL DEFAULT TRUE
 , market_id UUID NOT NULL
 , PRIMARY KEY(id)
 , FOREIGN KEY(market_id) REFERENCES market(id)
);

CREATE UNIQUE INDEX ON smartphone(phone_number, market_id) WHERE active;
