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

import java.util.Optional;
import net.ctalkobt.syllogism.Context;
import net.ctalkobt.syllogism.Copula;
import net.ctalkobt.syllogism.Term;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Craig.Taylor
 */
public class SyllogismTest {
    private Context context;
    private Term mMan;
    private Term mMammel;
    private Term mAnimal;
    private Term mInanimate;

    /**
     * Test setup.
     */
    @Before
    public void setUp() {
        context = new Context();

        mMan = context.createMeme("Man");
        mMammel = context.createMeme("Mammel");
        mAnimal = context.createMeme("Animal");
        mInanimate = context.createMeme("Inanimate");

        context.addSyllogism(mMan, Copula.IS, mMammel);
        context.addSyllogism(mMammel, Copula.IS, mAnimal);
    }
    
    /**
     * Given:  X is Y. 
     * ? X is Y implies true
     */
    @Test
    public void testDirectSyllogism() {
        /**
         * Should return true
         */
        Optional<Boolean> result = context.interrogate(mMan, Copula.IS, mMammel);
        Assert.assertTrue(result.isPresent() && result.get().equals(true));
    }
    
    /**
     * Given X is Y, Y is Z
     * ? X is Z implies true. 
    */
    @Test
    public void testInDirectSyllogism() {
        /**
         * Should return true
         */
        Optional<Boolean> result = context.interrogate(mMan, Copula.IS, mAnimal);
        Assert.assertTrue(result.isPresent() && result.get().equals(true));

    }

    /**
     * Given X is Y, Y is Z
     * ? X is A implies (unknown).
     */
    @Test
    public void testUnknownSyllogims() {
        /**
         * Should return false
         */
        Optional<Boolean> result = context.interrogate(mMan, Copula.IS, mInanimate);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testSimpleNotEquivality() {
        Term mA = context.createMeme("A");
        Term mB = context.createMeme("B");
        
        context.addSyllogism(mA, Copula.ISNOT, mB);
        
        Optional<Boolean> result = context.interrogate(mA, Copula.ISNOT, mB);
        Assert.assertTrue(result.isPresent());
        Assert.assertTrue(result.get().equals(false));
    }

    @Test
    public void testIndirectNonEquivality() {
        Term mA = context.createMeme("A");
        Term mB = context.createMeme("B");
        Term mC = context.createMeme("C");
        
        context.addSyllogism(mA, Copula.ISNOT, mB);
        context.addSyllogism(mB, Copula.ISNOT, mC);
        
        Optional<Boolean> result = context.interrogate(mA, Copula.ISNOT, mC);
        Assert.assertTrue(result.isPresent());
        Assert.assertTrue(result.get().equals(false));
    }
    
}
