import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public Clip explosion, victory, engine, bingBong, radioChatter, boo;
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

	public void playOnLoop(final Clip clip) {
		if (allowSound)
			if (!inArray(clip, ambient) || allowAmbient)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void play(final Clip clip) {
		if (allowSound)
			if (!inArray(clip, ambient) || allowAmbient)
				clip.start();
	}

	public void stopAmbient() {
		if (allowSound) {
			allowAmbient = false;
			engine.stop();
			bingBong.stop();
			radioChatter.stop();
		}
	}

	private boolean inArray(Clip element, Clip[] clips) {
		for (Clip clip : clips) {
			if (element.equals(clip))
				return true;
		}
		return false;
	}

}
