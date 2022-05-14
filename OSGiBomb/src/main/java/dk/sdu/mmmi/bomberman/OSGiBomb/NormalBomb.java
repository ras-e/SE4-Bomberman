package dk.sdu.mmmi.bomberman.OSGiBomb;

import dk.sdu.mmmi.bomberman.OSGiCommonBomb.entities.Explosion;
import dk.sdu.mmmi.bomberman.common.data.Entity;

public class NormalBomb implements BombSPI {

    private final String name = "Bomb";
    private final String iconPath = "bomb.png";
    private Entity explosion;

    //explosion raidus
    private final int bulletVelocity = 300;

    //amount of bombs 3?
    private int ammo = Integer.MAX_VALUE;
    private final int ammoCapacity = Integer.MAX_VALUE;
    private float reloadTime = 0;
    private float fireRate = 0.7f;
    private ArrayList<Entity> bulletArray = new ArrayList();
}
