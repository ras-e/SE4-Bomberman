package dk.sdu.mmmi.bomberman.OSGiBomb;

import dk.sdu.mmmi.bomberman.OSGiCommonBomb.entities.Bomb;
import dk.sdu.mmmi.bomberman.OSGiCommonPlayer.Player;
import dk.sdu.mmmi.bomberman.OSGiPlayer.data.Entity.PositionPart;
import dk.sdu.mmmi.bomberman.OSGiCommonBomb.services.BombSPI;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.*;
import javafx.geometry.Pos;

public class NormalBomb implements BombSPI {

    private final String name = "Bomb";
    private final String iconPath = "bomb.png";
    private final String texturePath = "bomb.png";
    private final String explosionTexturePath = "explosion.png";
    private final int explosionTextureFrameRows = 4;
    private final int explosionTextureFrameCols = 4;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getIconPath() {
        return iconPath;
    }

    @Override
    public void createBomb(Entity player, GameData gameData, float explosionPower, World world) {
        Bomb bomb = new Bomb();

        PositionPart playerLocation = player.getPart(PositionPart.class);

        bomb.add(new PositionPart(playerLocation.getX(), playerLocation.getY()));

        bomb.add(new BombExpirationPart(3));
        grenade.add(new ShapePart());
        grenade.add(new CirclePart(cannonCentre.getX(),cannonCentre.getY(),5));
        grenade.add(new PhysicsPart(30, -90.82f));
        grenade.add(new GrenadeCollisionPart(true,0));
        grenade.add(new DamagePart(7,25));
        grenade.add(new TexturePart(this.texturePath));
        grenade.add(new ExplosionTexturePart(explosionTextureFrameCols,explosionTextureFrameRows,explosionTexturePath));
        SoundPart sounds = new SoundPart(shootSoundPath, explosionSoundPath);
        grenade.add(sounds);
        gameData.getEventManager().addEvent(new SoundEvent(grenade, sounds.getShootSoundPath()));
        world.addEntity(grenade);
    }
}
