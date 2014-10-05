/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package storage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.lang.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//Class that is used to store the attributes of a member in MemberList.txt.
class Member
{
	//Variables to store the member id, name,email,phone,address,day they became a member, county, total fine accrued by this member.
	private String memberId;
	private String name;
	private String email;
	private String phone;
	private String address;
	private Date membershipStartDate;
	private String county;
	private int fine;
	
	//Constructor which initializes values of its data members.
	public Member(String memberId,String name,String email,String phone,String address,Date membershipStartDate,String county)
	{
		this.fine=0;
		this.memberId=memberId;
		this.name=name;
		this.email=email;
		this.phone=phone;
		this.address=address;
		this.membershipStartDate=membershipStartDate;
		this.county=county;
		
	}
	
	//Function to write into the MemberList.txt.
	public String writeToFile()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		return this.memberId+"||"+this.name+"||"+this.email+"||"+this.phone+"||"+this.address+"||"+dateFormat.format(membershipStartDate)+"||"+this.county+"||";
	}
	
	//Function to display the attributes of a member when its printed.
	public String toString()
	{  
	  return "MemberId =" + memberId+", Member name="+name+", Email="+email+", Phone="+phone+", Address"+address+", Membership Start Date"+membershipStartDate+", County"+county+"\n";
	
	}
	
	//Functions to get the values of a member's attributes.
	public String getMemberId()
	{
		return this.memberId;
	}
	public String getCounty()
	{
		return this.county;
	}
	public String getName()
	{
		return this.name;
	}
	public String getEmail()
	{
		return this.email;
	}
}