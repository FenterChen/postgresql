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
