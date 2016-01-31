package net.ctalkobt.syllogism;

/****************************************************************************
 *
 * @author Craig.Taylor
 ***************************************************************************/
public class Meme {
    private final String textRepresentation;

    Meme(String text)
    {
        this.textRepresentation = text;
    }

    @Override
    public String toString()
    {
        return "m:["+textRepresentation+"]";
    }

}
