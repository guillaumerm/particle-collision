/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author 1141678
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({modele.ParticuleTest.class, modele.TerrainTest.class})
public class AllTests {

    @Before
    public void setUp() throws Exception {
    }
    
}
