package br.com.inpe.kinect.model.gesture.detector;

import java.util.ArrayList;
import java.util.List;

public class PostureDetector {
	private List<IPosture> postures;

	public PostureDetector() {
		postures = new ArrayList<IPosture>();
	}

	public void updateAllPosture(int userId) {
		for (IPosture p : postures) {
			p.updatePosture(userId);
		}
	}

	public void addPosture(IPosture posture) {
		postures.add(posture);
	}

	public void removePosture(IPosture posture) {
		postures.remove(posture);
	}

}
