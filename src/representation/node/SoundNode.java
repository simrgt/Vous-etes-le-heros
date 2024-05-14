package representation.node;

import representation.Event;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Represents a sound node in the game.
 */
public class SoundNode extends EventDecorator {
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
        try {
            // Ouvrir le fichier audio
            File soundFile = new File(soundFileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            // Obtenir un clip
            Clip clip = AudioSystem.getClip();

            // Ouvrir le clip avec l'AudioInputStream
            clip.open(audioIn);

            // Jouer le son
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return super.display();
    }
}
