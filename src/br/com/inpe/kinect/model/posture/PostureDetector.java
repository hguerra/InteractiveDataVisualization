package br.com.inpe.kinect.model.posture;

import java.util.ArrayList;
import java.util.List;

public class PostureDetector {
	private List<Posture> postures;

	public PostureDetector() {
		postures = new ArrayList<Posture>();
	}

	public void updateAllPosture(int userId) {
		for (Posture p : postures) {
			p.updatePosture(userId);
		}
	}

	public void addPosture(Posture posture) {
		postures.add(posture);
	}

	public void removePosture(Posture posture) {
		postures.remove(posture);
	}

}
