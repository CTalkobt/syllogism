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
package net.ctalkobt.syllogism;

/****************************************************************************
 * Defines the type of association between memes.
 * 
 * @author Craig.Taylor
 ***************************************************************************/
public enum Equivalence {
    /**
     * Defines a strict equality similar to A = B in math. The objects can be
     * considered identical and equal.  THere is no hierarchical relationship
     * among them nor ownership.
     */
    EQUALITY(true),
    NOTEQUALITY(false);
    
    /**
     * Used to indicate that an equality is indiciviate of a truth. Eg: 
     * defining X op Y implies that X oop Y evaluates to true / false. 
     * 
     * Null implies no definition of logical truth is defined / known. 
     */
    private final Boolean truthEquivalency;
    
    Equivalence(Boolean truthEquivalency)
    {
        this.truthEquivalency = truthEquivalency;
    }
    
    final Boolean getTruthEquivalency()
    {
        return truthEquivalency;
    }
}
