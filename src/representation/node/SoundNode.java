package representation.node;

import representation.Event;

/**
 * Represents a sound node in the game.
 */
public class SoundNode extends EventDecorator {
    /**
     * name of the sound file
     */
    private final String soundFileName;

    /**
     * @param event the event to decorate
     * @param soundFileName the sound file name
     */
    public SoundNode(Event event, String soundFileName) {
        super(event);
        this.soundFileName = soundFileName;
    }

    /**
     * @return the text to display
     */
    @Override
    public String display() {
        return SOUND_FLAG + this.soundFileName + SOUND_FLAG + super.display();
    }

    @Override
    public int getValue() {
        return super.getValue();
    }

    @Override
    public String getAttribute() {
        return super.getAttribute();
    }
}
