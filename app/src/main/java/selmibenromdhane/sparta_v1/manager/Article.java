package selmibenromdhane.sparta_v1.manager;

public class Article {
	public String Image;
	public String Titre;
	public String Desc;
	
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Article(String image, String titre, String desc) {
		super();
		Image = image;
		Titre = titre;
		Desc = desc;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	
	
}
