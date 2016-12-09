package selmibenromdhane.sparta_v1.manager;

import java.io.Serializable;

public class Friend implements Serializable {
	private long id;
	private String name;
	private int photo;

	public Friend(long id, String name, int photo) {
		this.id = id;
		this.name = name;
		this.photo = photo;
	}

	public Friend(String name, int photo) {
		this.name = name;
		this.photo = photo;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPhoto() {
		return photo;
	}
}
