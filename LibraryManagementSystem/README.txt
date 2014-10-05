Project Team J (COEN 275)
Name : Meenakshy Harikumar 		SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013


VERSION INFO:
Java Environment version: 1.7 ( JRE7)
Eclipse Platform Version: 4.2.1



1)Lists of books and videos in the library are stored in 
files "BookList.txt" and "VideoList.txt".

2)The different fields in "BookList.txt" and "VideoList.txt" are separated by "||".
The first field in BookList.txt is the book id, second field is the book name and third field is the book author.
The first field in VideoList.txt is the video id, second field is the video name and the third field is the director.
This information can be used to test the options such as query by book number, query by director etc. 

3)Fiction books start with F and non -fiction books start with NF.

4) "MemberList.txt" contains list of members,
 "CheckedOutList.txt" contains list of items currently checked out and 
"HistoryList.txt" contains list of transactions made so far.

5)To try out admin options, enter username as "admin" and pssword as "admin".

6) To stimulate a situation where fine is accrued while returning an item(Return Item button), 
change the 4th field in an entry corresponding to that item in "CheckedOutList.txt" to a date before today's date.
Enter the date in the format " DD-MMM-YY". 
Eg :- For entering 25th Aug, 2013, enter in the format 25-Aug-13.

7)To stimulate a situation where total fine is accrued by a member(Pay Fine button), 
change the 4th field in an entry corresponding to that member in "CheckedOutList.txt" to a date before today's date.
Enter the date in the format " DD-MMM-YY". 
Eg :- For entering 25th August, 2013, enter in the format 25-Aug-13.

8)Anywhere date is entered, enter in the format " DD-MMM-YY". 
Eg :- For entering 25th August, 2013, enter in the format 25-Aug-13.

9)An item can only be reserved if it has already been borrowed by someone.

10) LibraryuserInterface.java contains the main().

11) The email functionality can be checked in the following way :

Checkout an item by a member. Reserve that same item through another member. 
Make the first member return that item. An email will now be sent to the 
member who reserved it saying that that item is now available. 

NOTE: If antivirus/firewall is enabled in the machine, the outgoing email 
may be blocked. Please disable the antivirus/firewall and retry if the email 
is not sent successfully.


LIMITATION of this project:

1) We don't provide the option for an user to pay partial fine which 
we feel models real world libraries, so it is not a limitation per say. 
