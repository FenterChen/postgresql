ALTER TABLE "user_equipment"
ADD CONSTRAINT "user_equipment_user_id_fkey"
FOREIGN KEY ("user_id")
REFERENCES "game_user"("id")
ON DELETE RESTRICT ON UPDATE RESTRICT;