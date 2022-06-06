DROP SEQUENCE IF EXISTS equipment_type_id_seq;
CREATE SEQUENCE IF NOT EXISTS equipment_type_id_seq;

CREATE TABLE IF NOT EXISTS "equipment_type" (
    "id" int not null DEFAULT nextval('equipment_type_id_seq'),
    "equipment_type" text NOT NULL,
    "equipment_basic_atk" int,
    "equipment_basic_def" int,
    PRIMARY KEY ("id")
);

INSERT INTO "equipment_type" VALUES (
    1,'weapon',1,0
);

INSERT INTO "equipment_type" VALUES (
    2,'armor',0,1
);