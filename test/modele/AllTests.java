package modele;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Guillaume Rochefort-Mathieu & Antoine Laplante
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({modele.ParticuleTest.class, modele.TerrainTest.class, modele.Vector2Test.class, modele.CollisionTest.class})
public class AllTests {
    
}
