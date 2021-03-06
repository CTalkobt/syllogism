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
 * A word that coveys a specific meaning.  Note the distinction in a
 * specific meaning - written or spoken words may have meaning based upon
 * the context in which they are spoken.  The use of Meme here is used to
 * convey a specific representation of the given text.
 *
 * @author Craig.Taylor
 ***************************************************************************/
public class Term {
    private final String textRepresentation;

    Term(String text)
    {
        this.textRepresentation = text;
    }

    @Override
    public String toString()
    {
        return "term:["+textRepresentation+"]";
    }

}
