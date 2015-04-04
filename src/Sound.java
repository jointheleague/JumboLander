import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static Clip explosion, victory, engine, bingBong, radioChatter, boo;
	private Clip[] ambient = { engine, bingBong, radioChatter };
	private boolean allowAmbient = true;
	private boolean allowSound = true;

	public Sound(boolean allowSound) {
		if (allowSound) {
			try {
				explosion = AudioSystem.getClip();
				explosion.open(AudioSystem.getAudioInputStream(this.getClass()
						.getResource("explosion.wav")));
				bingBong = AudioSystem.getClip();
				bingBong.open(AudioSystem.getAudioInputStream(this.getClass()
						.getResource("bingBong.wav")));
				engine = AudioSystem.getClip();
				engine.open(AudioSystem.getAudioInputStream(this.getClass()
						.getResource("engine.wav")));
				radioChatter = AudioSystem.getClip();
				radioChatter.open(AudioSystem.getAudioInputStream(this
						.getClass().getResource("radioChatter.wav")));
				victory = AudioSystem.getClip();
				victory.open(AudioSystem.getAudioInputStream(this.getClass()
						.getResource("victory.wav")));
				boo = AudioSystem.getClip();
				boo.open(AudioSystem.getAudioInputStream(this.getClass()
						.getResource("boo.wav")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.allowSound = allowSound;
	}

	public void init() {
		stopAll();
		allowAmbient = true;
	}

	public void playOnLoop(final Clip clip) {
		if (allowSound)
			if (!inArray(clip, ambient) || allowAmbient) {
				clip.setFramePosition(0);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
	}

	public void play(final Clip clip) {
		if (allowSound)
			if (!inArray(clip, ambient) || allowAmbient) {
				clip.setFramePosition(0);
				clip.start();
			}
	}

	public void stopAmbient() {
		allowAmbient = false;
		if (engine.isActive())
			engine.stop();
		if (bingBong.isActive())
			bingBong.stop();
		if (radioChatter.isActive())
			radioChatter.stop();
	}

	public void stopAll() {
		if (engine.isActive())
			engine.stop();
		if (bingBong.isActive())
			bingBong.stop();
		if (radioChatter.isActive())
			radioChatter.stop();
		if (explosion.isActive())
			explosion.stop();
		if (victory.isActive())
			victory.stop();
		if (boo.isActive())
			boo.stop();
	}

	private boolean inArray(Clip element, Clip[] clips) {
		for (Clip clip : clips) {
			if (element.equals(clip))
				return true;
		}
		return false;
	}

}
