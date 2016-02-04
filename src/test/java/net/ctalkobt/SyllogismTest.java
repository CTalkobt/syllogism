/****************************************************************************
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ***************************************************************************/
package net.ctalkobt;

import net.ctalkobt.syllogism.Context;
import net.ctalkobt.syllogism.Equivalence;
import net.ctalkobt.syllogism.Meme;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Craig.Taylor
 */
public class SyllogismTest {
    private Context context;
    private Meme mMan;
    private Meme mMammel;
    private Meme mAnimal;
    private Meme mInanimate;
    
    @Before
    public void setUp() {
        context = new Context();

        mMan = context.createMeme("Man");
        mMammel = context.createMeme("Mammel");
        mAnimal = context.createMeme("Animal");
        mInanimate = context.createMeme("Inanimate");

        context.addSyllogism(mMan, Equivalence.EQUALITY, mMammel);
        context.addSyllogism(mMammel, Equivalence.EQUALITY, mAnimal);


    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class RunSyllogism.
     */
    @Test
    public void testDirectSyllogism() {
        /**
         * Should return true
         */
        Assert.assertTrue(context.interrogate(mMan, Equivalence.EQUALITY, mMammel));
    }
    
    @Test
    public void testInDirectSyllogism() {
        /**
         * Should return true
         */
        Assert.assertTrue(context.interrogate(mMan, Equivalence.EQUALITY, mAnimal));
    }

    @Test
    public void testUnknownSyllogims() {
        /**
         * Should return false
         */
        Assert.assertFalse(context.interrogate(mMan, Equivalence.EQUALITY, mInanimate));
    }

}
