package pl.entito.demo;

public class Book {

	private Long id;

	private String title;

	private String description;

	public Book() {

	}

	public Book(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + "]";
	}

}
