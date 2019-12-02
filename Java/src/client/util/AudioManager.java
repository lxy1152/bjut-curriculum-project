package client.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AudioManager {
	private final static String AUDIO_FOLDER = "Wav/";
	private static AudioClip moveSound;
	private static AudioClip turnSound;
	private static AudioClip winSound;
	private static AudioClip loseSound;
	private static AudioClip cutLineSound;
	private static AudioClip startSound;
	private static AudioClip usePropSound;
	private static AudioClip cutLineAttackSound;
	private static AudioClip hitBottomSound;

	synchronized private static AudioClip getMoveSound() {
		if (moveSound == null) {
			File file = new File(AUDIO_FOLDER + "btn.wav");
			try {
				URL url = file.toURI().toURL();
				moveSound = Applet.newAudioClip(url);
				return moveSound;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return moveSound;
		}
	}

	synchronized private static AudioClip getTurnSound() {
		if (turnSound == null) {
			File file = new File(AUDIO_FOLDER + "transform.wav");
			try {
				URL url = file.toURI().toURL();
				turnSound = Applet.newAudioClip(url);
				return turnSound;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return turnSound;
		}
	}

	synchronized private static AudioClip getWinSound() {
		if (winSound == null) {
			File file = new File(AUDIO_FOLDER + "win.wav");
			try {
				URL url = file.toURI().toURL();
				winSound = Applet.newAudioClip(url);
				return winSound;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return winSound;
		}
	}

	synchronized private static AudioClip getLoseSound() {
		if (loseSound == null) {
			File file = new File(AUDIO_FOLDER + "lost.wav");
			try {
				URL url = file.toURI().toURL();
				loseSound = Applet.newAudioClip(url);
				return loseSound;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return loseSound;
		}
	}

	synchronized private static AudioClip getCutLineSound() {
		if (cutLineSound == null) {
			File file = new File(AUDIO_FOLDER + "fadelayer.wav");
			try {
				URL url = file.toURI().toURL();
				cutLineSound = Applet.newAudioClip(url);
				return cutLineSound;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return cutLineSound;
		}
	}

	synchronized private static AudioClip getStartSound() {
		if (startSound == null) {
			File file = new File(AUDIO_FOLDER + "ReadyGo.wav");
			try {
				URL url = file.toURI().toURL();
				startSound = Applet.newAudioClip(url);
				return startSound;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return startSound;
		}
	}

	synchronized private static AudioClip getUsePropSound() {
		if (usePropSound == null) {
			File file = new File(AUDIO_FOLDER + "flystar.wav");
			try {
				URL url = file.toURI().toURL();
				usePropSound = Applet.newAudioClip(url);
				return usePropSound;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return usePropSound;
		}
	}

	synchronized private static AudioClip getCutLineAttackSound() {
		if (cutLineAttackSound == null) {
			File file = new File(AUDIO_FOLDER + "useitem.wav");
			try {
				URL url = file.toURI().toURL();
				cutLineAttackSound = Applet.newAudioClip(url);
				return cutLineAttackSound;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return cutLineAttackSound;
		}
	}

	synchronized private static AudioClip getHitBottomSound() {
		if (hitBottomSound == null) {
			File file = new File(AUDIO_FOLDER + "fixup.wav");
			try {
				URL url = file.toURI().toURL();
				hitBottomSound= Applet.newAudioClip(url);
				return hitBottomSound;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else{
			return hitBottomSound;
		}
	}

	synchronized public static void playMoveSound() {
		getMoveSound().play();
	}

	synchronized public static void playTurnSound() {
		getTurnSound().play();
	}

	synchronized public static void playWinSound() {
		getWinSound().play();
	}

	synchronized public static void playLoseSound() {
		getLoseSound().play();
	}

	synchronized public static void playCutLineSound() {
		getCutLineSound().play();
	}

	synchronized public static void playStartSound() {
		getStartSound().play();
	}

	synchronized public static void playUsePropSound() {
		getUsePropSound().play();
	}

	synchronized public static void playHitBottomSound() {
		getHitBottomSound().play();
	}

	synchronized public static void playCutLineAttackSound() {
		getCutLineAttackSound().play();
	}

}
