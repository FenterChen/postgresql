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
