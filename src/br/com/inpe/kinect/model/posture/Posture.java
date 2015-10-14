package br.com.inpe.kinect.model.posture;


public class Posture {
	private IPosture posture;
	private IFilter filter;

	public Posture(IPosture posture, IFilter filter) {
		this.posture = posture;
		this.filter = filter;

	}

	public void updatePosture(int userId) {
		EPostureType result = posture.updatePosture(userId);
		if (!result.equals(EPostureType.UNDEFINED)) {
			filter.updatePosture(result);
		}

	}

}
