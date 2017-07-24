package PackageItems;
/**
 * an object which is an item
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class Book extends OfficeSupplies{
	
	private String publisherName;
	private String author;
	private int numPage;
	/**
	 * Class constructor
	 * @param cost	item's price
	 * @param releaseDate	bookd'release date
	 * @param coverTitle	book's cover titles
	 * @param publisherName	book's publisher name
	 * @param author	book's author
	 * @param numPage	book's number of pages
	 * @param ID	item id which is given by shopping system
	 */
	public Book(String cost, String releaseDate, String coverTitle, String publisherName,  String author, String numPage, String ID) {
		super.setItemId(Integer.parseInt(ID));
		super.setCost(Float.parseFloat(cost));	
		super.setReleaseDate(releaseDate);
		super.setCoverTitle(coverTitle);
		this.publisherName = publisherName;
		this.author = author;
		this.numPage = Integer.parseInt(numPage);
	}
	
	/**
	 * showing each book item's information
	 */
	public void displayBook(){
		System.out.println("Type: Book");
		System.out.println("Item ID: " + super.getItemId());
		System.out.println("Price: " + super.getCost() + "  $");
		System.out.println("Release Date: " + super.getReleaseDate());
		System.out.println("Title: " + super.getCoverTitle());
		System.out.println("Publisher: " + this.publisherName);
		System.out.println("Author: " + this.author);
		System.out.println("Page: " + this.numPage + " pages");
	}
	
}
