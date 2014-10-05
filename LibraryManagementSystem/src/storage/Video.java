/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package storage;

//Class that is used to store the attributes of a video in VideoList.txt.
public class Video
{
	//Variables to store the title, director, video number, location, price and status(available,borrowed,borrowed & reserved,reserved), type of a video. 
	private String title;
	private String director;
	private String videoNumber;
	private int location;
	private double price;
	private String status;
	private String type;
	
	//Variable to store the maximum of videos that can be borrowed by a user at a time.
	private int videoMaxNumber;
	
	//Variable to store the maximum number of days that this video can be borrowed by a member at a time.
	private int videoMaxTime;
	
	//Variable to store the fine accrued by a video in a day.
	private double videoDailyFine;
	
	//Variable to store the id of the member who borrowed this video.
	private String memberWhoBorrowed;
	
	//Variable to store the id of the member who reserved this video.
	private String memberWhoReserved;
	
	//Constructor which initializes values of its data members.
	public Video(String videoNumber,String title,String status,String director ,String type,int location,double price,String memberWhoBorrowed,String memberWhoReserved)
	{
		this.videoNumber=videoNumber;
		this.title=title;
		this.status=status;
		this.director=director;
		this.type=type;
		this.location=location;
		this.price=price;
		this.memberWhoBorrowed=memberWhoBorrowed;
		this.memberWhoReserved=memberWhoReserved;
		
		 if(type.equals("V"))
		 {
		 this.videoMaxNumber=2;
		 this.videoMaxTime=7;
		 this.videoDailyFine=0.50;
		 }
		 
	}
	
	//Functions to set the values of a video's attributes.
	public void setMemberWhoBorrowed(String memberWhoBorrowed)
	{
		this.memberWhoBorrowed=memberWhoBorrowed;
	}
	public void setMemberWhoReserved(String memberWhoReserved)
	{
		this.memberWhoReserved=memberWhoReserved;
	}
	public void setStatus(String status)
	{
		this.status=status;
		
	}
	
	//Functions to get the values of a video's attributes.
	public double getPrice()
	{
		return this.price;
	}
	public String getVideoNumber()
	{
		return this.videoNumber;
	}
	public String getTitle()
	{
		return this.title;
	}
	public String getStatus()
	{
		return this.status;
	}
	
	public String getDirector()
	{
		return this.director;
	}
	public double getVideoDailyFine()
	{
		return this.videoDailyFine;
	}
	public String getMemberWhoReserved()
	{
		return this.memberWhoReserved;
	}
	public int getVideoMaxTime()
	{
		return this.videoMaxTime;
	}
	public String getMemberWhoBorrowed()
	{
		return this.memberWhoBorrowed;
	}

	//Function to display the attributes a video when its printed.
	public String toString()
	{  
	   return "Title =" + title + ", Video Number=" + videoNumber + ", Status=" + status +", Director="+director+",Type=" +type+", Location="+location+", Price="+price+"\n";
	}

	//Function to write into the VideoList.txt.
	public String writeToFile()
	{
		return this.videoNumber+"||"+this.title+"||"+this.status+"||"+this.director+"||"+this.type+"||"+this.location+"||"+this.price+"||"+this.memberWhoBorrowed+"||"+this.memberWhoReserved+"||";
	}

}