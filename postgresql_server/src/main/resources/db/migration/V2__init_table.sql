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

DROP SEQUENCE IF EXISTS user_equipment_equipment_id_seq;
CREATE SEQUENCE IF NOT EXISTS user_equipment_equipment_id_seq;

CREATE TABLE IF NOT EXISTS "user_equipment" (
    "equipment_id" int NOT NULL DEFAULT nextval('user_equipment_equipment_id_seq'),
    "equipment_name" text,
    "user_id" int NOT NULL,
    "equipment_type" int NOT NULL,
    "equipment_atk" int NOT NULL DEFAULT 0,
    "equipment_def" int NOT NULL DEFAULT 0,
    CONSTRAINT "user_equipment_equipment_type_fkey" FOREIGN KEY ("equipment_type") REFERENCES "equipment_type"("id") ON DELETE RESTRICT ON UPDATE RESTRICT,
    PRIMARY KEY ("equipment_id")
);

DROP SEQUENCE IF EXISTS game_user_id_seq;
CREATE SEQUENCE IF NOT EXISTS game_user_id_seq;

CREATE TABLE IF NOT EXISTS "game_user" (
    "id" int NOT NULL DEFAULT nextval('game_user_id_seq'),
    "user_id" text NOT NULL,
    "password" text NOT NULL,
    "user_name" text,
    "role" text,
    "weapon_slot" int,
    "armor_slot" int,
    "created_at" timestamptz NOT NULL DEFAULT now(),
    "updated_at" timestamptz NOT NULL DEFAULT now(),
    CONSTRAINT "game_user_weapon_slot_fkey" FOREIGN KEY ("weapon_slot") REFERENCES "user_equipment"("equipment_id") ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT "game_user_armor_slot_fkey" FOREIGN KEY ("armor_slot") REFERENCES "user_equipment"("equipment_id") ON DELETE SET NULL ON UPDATE RESTRICT,
    PRIMARY KEY ("id")
);

ALTER TABLE "user_equipment"
ADD CONSTRAINT "user_equipment_user_id_fkey"
FOREIGN KEY ("user_id")
REFERENCES "game_user"("id")
ON DELETE RESTRICT ON UPDATE RESTRICT;