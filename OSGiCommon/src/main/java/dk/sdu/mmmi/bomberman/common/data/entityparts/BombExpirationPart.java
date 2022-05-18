package dk.sdu.mmmi.bomberman.common.data.entityparts;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;

public class BombExpirationPart implements EntityPart {

    private float remainingDuration;

    public float getRemainingDuration() {
        return remainingDuration;
    }

    // prevents remainingLifeTime to go below 0
    public void setRemainingDuration(float remainingDuration) {
        this.remainingDuration = remainingDuration;
        if (this.remainingDuration < 0){
            this.remainingDuration = 0;
        }
    }

    @Override
    public void processPart(Entity entity, GameData gameData, World world) {
        this.setRemainingDuration(this.getRemainingDuration() - gameData.getDelta());

        if (getRemainingDuration() == 0){
            PositionPart positionPart = entity.getPart(PositionPart.class);
            //DamagePart damagePart = entity.getPart(DamagePart.class);
            ExplosionTexturePart explosionTexturePart = entity.getPart(ExplosionTexturePart.class);

            if (positionPart != null && explosionTexturePart != null) {
                Event animationEvent = new ExplosionAnimationEvent(entity, new Vector2D(positionPart.getX(), positionPart.getY()), explosionTexturePart, damagePart.getExplosionRadius());
                Event explosionEvent = new ExplosionEvent(entity, new Vector2D(positionPart.getX(), positionPart.getY()), damagePart.getExplosionRadius());
                Event mapDestructionEvent = new MapDestructionEvent(entity,new Vector2D(positionPart.getX(),positionPart.getY()),damagePart.getExplosionRadius());
                Event shakeEvent = new ShakeEvent(entity,damagePart.getExplosionRadius() * 5);
                gameData.getEventManager().addEvent(explosionEvent);
                gameData.getEventManager().addEvent(animationEvent);
                gameData.getEventManager().addEvent(mapDestructionEvent);
                world.removeEntity(entity);
                gameData.getEventManager().addEvent(shakeEvent);
                gameData.getEventManager().addEvent(new SoundEvent(entity, sound.getOnHitSoundPath()));
            }
        }
    }
}
