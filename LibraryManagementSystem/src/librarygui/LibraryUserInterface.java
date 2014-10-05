package librarygui;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import storage.Book;
import storage.CheckedOutItems;
import storage.History;
import storage.Video;
//import Storage.Member;

import backend.Bookmarkers;
import backend.CheckOut;



 public class LibraryUserInterface extends JFrame {
	 //main heading label
	private JLabel label;
	//main container
	private Container contentPane;
	// Buttons for various users options
	private JButton search,checkoutItem, returnItem, becomeMember, payFine, seeTransactions, reserveitem;
	//Button for admin login
	private JButton loginButton;
	//Label for admin username and password
	private JLabel admnName, admnWord;
	//Label for admin username 
	private JTextField adminname ;
	// password field for admin
	private JPasswordField adminpassword;
	//Actionlistener for user options like search, return book etc
	private ActionListener userOptionhandler;
	//New frame popped up when Search button is clicked
	private JFrame frame;
	//Search button in new window.
	private JButton buttonSearch;
	//text area where to enter the search text
	private JTextField searchfield ;
	//combobox to select the type of search
	private JComboBox searchtype;
	//text area to display the search results
	private JTextArea searchResults;
	//handler for search option
	private ActionListener searchActionhandler;
	//handler for video search handler
	private ActionListener searchVideoActionhandler;
	//handler for login button of admin
	private ActionListener loginHandler;
	//Admin specific options
	private JFrame adminoptionsframe;
	private JButton admintotalmemberlookup, admintotalfineslookup, admintotalitemscheckedlookup;
	private JButton adminhighestcheckoutitemlookup, adminitemspercatlookup, adminviewasgraph;
	private JLabel adminlabel;
	private JButton adminlogoffbutton;
	private JPanel adminpanel;
	private JPanel adminlogoutpanel;
	//handler for logoff admin
	private ActionListener logoffActionhandler;
	//textarea to display admin find results
	private JTextArea adminFindResults;
	//User selects checkout item screen 
	private JFrame checkoutFrame;
	private JPanel checkoutinfo;
	private JPanel checkoutdisplay;
	private JLabel memberId, bookId;
	private JTextField memberIdField, BookIdField;
	private JButton checkoutButton;
	private JTextArea CheckoutInfo;
	private ActionListener buttonClickHandler;
	private JButton returnItemButton;
	//action handler for admin specific options
	private ActionListener adminspecificoptionsActionhandler;
	
	//Video search items
	private JLabel bookSearch;
	private JLabel videoSearch;
	private JComboBox searchVideoType;
	private JTextField videoSearchField;
	private JTextArea videoSearchResults;
	private JButton videosearchbutton;
	//claim lost item by member
	private JButton lostbutton;
	//Ok button in claim lost item.
	private JButton okbutton;
	//send item reservation available email notification 
	private JButton itemAvailablEmail;
	//member email label
	private JLabel memberEmail;
	//member phone number
	private JLabel memberPhone;
	//phone field
	private JTextField memberPhoneField;
	//membership date label
	private JLabel membershipDate;
	//member email field
	private JTextField memberemailField;
	//membership date field
	private JTextField membershipDateFielddate;
	private JComboBox membershipmonth;
	private JTextField membershipDateFieldyear;
	//transaction history date
	private JLabel fromDate;
	private JLabel toDate;
	private JTextField transactionfromDateFielddate;
	//private JTextField transactionfromDateFieldmonth;
	private JTextField transactionfromDateFieldyear; 
	
	private JTextField transactiontoDateFielddate;
	//private JTextField transactiontoDateFieldmonth;
	private JTextField transactiontoDateFieldyear; 
	private JLabel  memberAddress;
	private JTextField memberAddressField;
	
	//reserveitem 
	private JPanel bookPanel;
	private JPanel videoPanel;
	private JLabel memberIDbook;
	private JLabel bookIdLabel;
	private JTextField memberbookreservationIdField;
	private JTextField bookIdField;
	private JButton reserveBookButton;
	private JTextArea 	booktextArea;
	private JLabel memberIDvideo;
	private JLabel videoIdLabel ;
	private JTextField membervideoreservationIdField;
	private JTextField videoIdField;
	private JButton reserveVideoButton;
	private JTextArea videotextArea;
	private ReserveItemActionHandler reserveItemActionhandler;
	//checkout video items
	private JPanel videocheckoutpanel;
	private JPanel videodisplaypanel;
	private JLabel videocheckoutmemberid;
	private JTextField videocheckoutmemberidfield;
	private JLabel videoidlabel;
	private JTextField videoidfield;
	private JButton videocheckoutbutton;
	private JTextArea videocheckoutdisplayarea;
	private JButton okbuttonpayfinescreen;
	
	private JFrame enterfineframe;
	private JLabel enterfine;
	private JTextField enterfinefield;
	private JButton payfine;
	private JTextArea finestatusarea;
	String [] month;
	JComboBox membershipmonthfrom;
	JComboBox membershipmonthto;
	private JButton OkreturnButton;

	
	Bookmarkers bookmarkers; 
	Map<String,Book> tempBookMap;
	Map<String, Video> tempVideoMap;
	//admin total fine and most popular item screen
	JFrame commonframe;
	JLabel commonlabel;
	JLabel commondatefromlabel;
	JLabel commondatetolabel;
	JTextField commondatefromfield;
	JComboBox commonmonthfrom;
	JTextField commonyearfrom;
	JTextField commondatetofield;
	JComboBox commonmonthto;
	JTextField commonyearto;
	JButton commonbutton;

	JTextArea commontextarea;
	private double successLost;
	
    		
	/** 
	 * The LibraryUserInterface makes the entire UI for the library application
	 * @param none
	 */
	
	public LibraryUserInterface() {
		bookmarkers = new Bookmarkers();
		tempBookMap=new HashMap<String,Book>();
		tempVideoMap = new HashMap<String, Video>();
		contentPane = getContentPane();
		// Construct a Label
		label = new JLabel("Welcome to the BookMarkers Library",SwingConstants.CENTER);
		label.setForeground(Color.BLUE);
		Font labelFont = new Font("Serif", Font.PLAIN, 50);
		label.setFont(labelFont);
		String [] month = { "Jan", "Feb", "Mar", "Apr","May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

		// add the label. By default, the layout manager for a JFrame is a BorderLayout.
		add(label, BorderLayout.NORTH);

		userOptionhandler = new UserOptionEventHandler();
		searchActionhandler = new SearchActionHandler();
		searchVideoActionhandler = new VideoSearchActionHandler();
		
		reserveItemActionhandler = new  ReserveItemActionHandler();

		loginHandler = new LoginActionHandler();
		logoffActionhandler = new LogoffActionhandler();
		buttonClickHandler = new ButtonClickHandler();
		adminspecificoptionsActionhandler = new adminSpecificOptionsActionhandler();

		createControlPanel();
		setSize(1500, 500);


		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/** 
	 * creates the first screen of the library GUI
	 * @param none
	 */
	public void createControlPanel()
	{
		JPanel userPanel = createUseroptions();
		JPanel adminPanel = createAdminOptions();
		bookmarkers = null;
		Bookmarkers bookmarkers = new Bookmarkers();
		Book book = bookmarkers.getLatestBook();
		String bookname = book.getTitle();
		Video video = bookmarkers.getLatestVideo();
		String videoname  = video.getTitle();
		
		AdvertisementManager animationpanel = new AdvertisementManager ( 400, 400, bookname, videoname  );
		
		

		// Arrange the panels in a single column

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.add(adminPanel, BorderLayout.EAST);
		 controlPanel.add(userPanel, BorderLayout.WEST);

		//controlPanel.add(userPanel);
		//controlPanel.add(adminPanel);
		controlPanel.add(animationpanel, BorderLayout.CENTER);

		contentPane.add(controlPanel, BorderLayout.CENTER);
	}
	
	/** 
	 * creates the options for the user of library
	 * @param none
	 */
	public JPanel createUseroptions()
	   {
		search = new JButton("SEARCH");
		search.setActionCommand("s");
		checkoutItem = new JButton("CHECKOUT ITEM");
		checkoutItem.setActionCommand("c");
		returnItem = new JButton("RETURN ITEM");
		returnItem.setActionCommand("ri");
		becomeMember = new JButton("BECOME MEMBER");
		becomeMember.setActionCommand("bm");
		payFine = new JButton("PAY FINE");
		payFine.setActionCommand("pf");
		seeTransactions = new JButton("SEE TRANSACTIONS");
		seeTransactions.setActionCommand("st");
		reserveitem = new JButton("RESERVE ITEM");
		reserveitem.setActionCommand("r");
		lostbutton = new JButton("CLAIM ITEM LOST");
		lostbutton.setActionCommand("claim_lost_item");
		
		search.addActionListener(userOptionhandler);
		checkoutItem.addActionListener(userOptionhandler);
		returnItem.addActionListener(userOptionhandler);
		becomeMember.addActionListener(userOptionhandler);
		payFine.addActionListener(userOptionhandler);
		seeTransactions.addActionListener(userOptionhandler);
		reserveitem.addActionListener(userOptionhandler);
		lostbutton.addActionListener(userOptionhandler);

		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new GridLayout(8, 1));
		
		buttonpanel.add(search);
		buttonpanel.add(checkoutItem);
		buttonpanel.add(returnItem);
		buttonpanel.add(becomeMember);
		buttonpanel.add(payFine);
		buttonpanel.add(seeTransactions);
		buttonpanel.add(reserveitem);
		buttonpanel.add(lostbutton);
		buttonpanel.setBorder(new TitledBorder(new EtchedBorder(), "User Options"));
		buttonpanel.setSize(400, 250);
		
		return buttonpanel;
	   }
	
	/** 
	 * creates the admin options for the library GUI
	 * @param none
	 */	
	public JPanel createAdminOptions() {
		
	      JPanel adminpanel = new JPanel();
	      admnName = new JLabel("Username");
	      //admnName.setBounds(100, 10, 80, 25);
	      admnWord = new JLabel("Password");
	      //admnWord.setBounds(100, 40, 80, 25);
	      loginButton = new JButton("Log in");
	      loginButton.setSize(20, 20);
	      //loginButton.setBounds(500, 80, 80, 25);
	     // adminpanel1.add(loginButton);
	      
	      adminname = new JTextField(15);
	     // adminname.setBounds(100, 10, 160, 25);
	      adminpassword = new JPasswordField(15);
	      adminpassword.setBounds(100, 40, 160, 25);
	     // adminpanel.setLayout(new GridLayout(3, 2));
	      //adminpanel.setLayout(null);
	      
	      adminpanel.add(admnName);
	      adminpanel.add(adminname);
	      adminpanel.add(admnWord);
	      adminpanel.add(adminpassword);
	      adminpanel.add(loginButton);
	      
	      loginButton.addActionListener(loginHandler);
	      //adminpanel.add(adminpanel1);
	      
	      
	      adminpanel.setBorder(new TitledBorder(new EtchedBorder(), "Admin Login"));

	      return adminpanel;
		
	}
	
	/** 
	 * creates the common screen shared by various options
	 * @param none
	 */	
private void createCommonScreen() {
	checkoutFrame = new JFrame();
	checkoutinfo = new JPanel();
	checkoutdisplay = new JPanel();
	//checkoutinfo.setLayout(new BoxLayout(null, BoxLayout.X_AXIS));
	memberId = new JLabel("Member Id");
	bookId = new JLabel("Book Id");
	memberIdField = new JTextField(20);
	BookIdField = new JTextField(20);
	CheckoutInfo = new JTextArea(10, 50);
	CheckoutInfo.setEditable(false);
	checkoutinfo.add(memberId);
	checkoutinfo.add(memberIdField);
	checkoutinfo.add(bookId);
    checkoutinfo.add(BookIdField);
}

/** 
 * creates the return item screen for  the library GUI
 * @param none
 */	
private void createReturnItemScreen() {
	
	createCommonScreen();
	bookId.setText("Item Id");
	OkreturnButton = new JButton("OK");
	OkreturnButton.setActionCommand("OkReturn");
	OkreturnButton.addActionListener(buttonClickHandler);
	returnItemButton = new JButton("Return Item");
	returnItemButton.setActionCommand("Return");
	returnItemButton.addActionListener(buttonClickHandler);
	checkoutinfo.add(returnItemButton);
	checkoutdisplay.add(CheckoutInfo);
	checkoutdisplay.add(OkreturnButton);
    checkoutFrame.setLayout(new BorderLayout());
 
	
    checkoutFrame.add(checkoutinfo, BorderLayout.NORTH);
    checkoutFrame.add(checkoutdisplay, BorderLayout.CENTER);
	checkoutFrame.setSize(800, 600);
	checkoutFrame.setVisible(true);
	checkoutFrame.setLocationRelativeTo(null);
}

/** 
 * creates checkout screen for  the library GUI member
 * @param none
 */	
private void createCheckoutScreen() {
	createCommonScreen();

	videocheckoutpanel = new JPanel();
	videodisplaypanel = new JPanel();
	videocheckoutmemberid = new JLabel("Member Id");
	videocheckoutmemberidfield = new JTextField(20);
	
	videoidlabel = new JLabel("Video Id");
	videoidfield = new JTextField(20);
	videocheckoutbutton = new JButton("Checkout Video");
	videocheckoutbutton.setActionCommand("VideoCheckout");
	videocheckoutbutton.addActionListener(buttonClickHandler);
	videocheckoutdisplayarea = new JTextArea(10, 50);
	
	videocheckoutpanel.add(videocheckoutmemberid);
	videocheckoutpanel.add(videocheckoutmemberidfield);
	videocheckoutpanel.add(videoidlabel);
	videocheckoutpanel.add(videoidfield);
	videocheckoutpanel.add(videocheckoutbutton);
	videodisplaypanel.add(videocheckoutdisplayarea);
	
	
	checkoutButton = new JButton("CheckOut Book");
	checkoutButton.setActionCommand("CheckOut");
	checkoutButton.addActionListener(buttonClickHandler);
	
    checkoutinfo.add(checkoutButton);
    checkoutdisplay.add(CheckoutInfo);
   
    
    checkoutFrame.setLayout(new FlowLayout());
	
    
    checkoutFrame.add(checkoutinfo);
    checkoutFrame.add(checkoutdisplay);
    checkoutFrame.add(videocheckoutpanel);
    checkoutFrame.add(videodisplaypanel);
    		
	checkoutFrame.setSize(800, 600);
	checkoutFrame.setVisible(true);
	checkoutFrame.setLocationRelativeTo(null);
}

public void checkoutvideo(){
	String memberid = videocheckoutmemberidfield.getText();
	String itemid = videoidfield.getText();
	String result = "";
	
	bookmarkers=null;
	bookmarkers=new Bookmarkers();
	if(bookmarkers.checkIfMember(memberid) == 1)
	{
		
		CheckOut C1=new CheckOut();
		Map<String,CheckedOutItems> checkOutMap=bookmarkers.checkOutVideo(memberid, itemid, C1);
				if(C1.getReason() == 1) {
			videocheckoutdisplayarea.setText("Invalid Video Number");
		}
		if(C1.getReason() == 0) {
			for(Map.Entry<String,CheckedOutItems> entry : checkOutMap.entrySet()) {
				result += entry.getValue()+"\n";
			}
			videocheckoutdisplayarea.setText(result);
			
		}
		if(C1.getReason() == 2) {
			videocheckoutdisplayarea.setText("Pay Fine");
		}
		

		if(C1.getReason() == 3) {
			videocheckoutdisplayarea.setText("The Video is not available");
		}
		if(C1.getReason() == 4) {
			videocheckoutdisplayarea.setText("You have exceeded the number of video limit.\nPlease return some videos to issue new ones");
		}

	
	}
	if(bookmarkers.checkIfMember(memberid) == 0){
		CheckoutInfo.setText("Invalid MemberId");
	}
	
}

/** 
 * creates the claim item lost screen for  the library GUI
 * @param none
 */	

public void createclaimItemLostScreen() {
	checkoutFrame = new JFrame("Claim Item Lost");
	checkoutinfo = new JPanel();
	checkoutdisplay = new JPanel();
	memberId = new JLabel("Member Id");
	bookId = new JLabel("Item Id");
	memberIdField = new JTextField(20);
	BookIdField = new JTextField(20);
	CheckoutInfo = new JTextArea(10, 80);
	okbutton = new JButton("OK");
	okbutton.setActionCommand("OKItemLost");
	okbutton.addActionListener(buttonClickHandler);
	CheckoutInfo.setEditable(false);
	checkoutinfo.add(memberId);
	checkoutinfo.add(memberIdField);
	checkoutinfo.add(bookId);
    checkoutinfo.add(BookIdField);
    
    checkoutButton = new JButton("Claim Item Lost");
	checkoutButton.setActionCommand("Claim_Item_Lost");
	checkoutButton.addActionListener(buttonClickHandler);
	
    checkoutinfo.add(checkoutButton);
    checkoutdisplay.add(CheckoutInfo);
    checkoutdisplay.add(okbutton);
    checkoutFrame.setLayout(new BorderLayout());
	
    checkoutFrame.add(checkoutinfo, BorderLayout.NORTH);
    checkoutFrame.add(checkoutdisplay, BorderLayout.CENTER);
	checkoutFrame.setSize(900, 600);
	checkoutFrame.setVisible(true);
	checkoutFrame.setLocationRelativeTo(null);
	
}


/** 
 * creates the become member screen for  the library GUI
 * @param none
 */	
private void createBecomeMemberScreen() {
	JButton okbutton = new JButton("OK");
	okbutton.setActionCommand("ok");
	checkoutFrame = new JFrame("Become Member");
	checkoutinfo = new JPanel();
	checkoutdisplay = new JPanel();
	String [] month = { "Jan", "Feb", "Mar", "Apr","May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		
	memberId = new JLabel("Name");
	bookId = new JLabel("County");
	memberIdField = new JTextField(25);
	BookIdField = new JTextField(30);
	CheckoutInfo = new JTextArea(10, 50);
	CheckoutInfo.setEditable(false);

	memberEmail = new JLabel("Email");
	//membership date label
	membershipDate = new JLabel("Date(ddmmmyy)");
	//member email field
	memberemailField = new JTextField(20);

	memberPhone = new JLabel("Phone");
	memberPhoneField = new JTextField(10);
	memberAddress = new JLabel("Address");
    memberAddressField = new JTextField(10);
	

	membershipDateFielddate = new JTextField(5);
	membershipDateFieldyear = new JTextField(5);
	membershipmonth = new JComboBox(month);
	membershipmonth.setSelectedIndex(0);
	
	checkoutinfo.add(memberId);
	checkoutinfo.add(memberIdField);
	checkoutinfo.add(bookId);
	checkoutinfo.add(BookIdField);
	checkoutinfo.add(memberEmail);
	checkoutinfo.add(memberemailField);
	checkoutinfo.add(memberPhone);
	checkoutinfo.add(memberPhoneField);
	checkoutinfo.add(memberAddress);
	checkoutinfo.add(memberAddressField);
	checkoutinfo.add(membershipDate);
	checkoutinfo.add(membershipDateFielddate);
	checkoutinfo.add(membershipmonth);
	checkoutinfo.add(membershipDateFieldyear);


	checkoutButton = new JButton("Become Member");
	checkoutButton.setActionCommand("Becomemember");
	checkoutButton.addActionListener(buttonClickHandler);

	checkoutinfo.add(checkoutButton);
	checkoutdisplay.add(CheckoutInfo);
	checkoutdisplay.add(okbutton);
	checkoutFrame.setLayout(new BorderLayout());

	checkoutFrame.add(checkoutinfo, BorderLayout.CENTER);
	checkoutFrame.add(checkoutdisplay, BorderLayout.SOUTH);
	checkoutFrame.setSize(1000, 600);
	checkoutFrame.setVisible(true);
	checkoutFrame.setLocationRelativeTo(null);

}

/** 
 * creates the pay fine screen for  the library GUI
 * @param none
 */	
private void createPayfineScreen() {
	checkoutFrame = new JFrame("Pay Fine");
	checkoutinfo = new JPanel();
	checkoutdisplay = new JPanel();
	
	memberId = new JLabel("memberId");
	okbuttonpayfinescreen = new JButton("OK");
	okbuttonpayfinescreen.setActionCommand("OKFINE");
	okbuttonpayfinescreen.addActionListener(buttonClickHandler);
	bookId = new JLabel("Amount");
	memberIdField = new JTextField(20);
	BookIdField = new JTextField(20);
	CheckoutInfo = new JTextArea(10, 50);
	CheckoutInfo.setEditable(false);
	checkoutinfo.add(memberId);
	checkoutinfo.add(memberIdField);
	checkoutinfo.add(okbuttonpayfinescreen);
    checkoutinfo.add(BookIdField);
    
    
    checkoutFrame.setLayout(new BorderLayout());
	
    checkoutFrame.add(checkoutinfo, BorderLayout.NORTH);
    checkoutFrame.setSize(800, 600);
	checkoutFrame.setVisible(true);
	checkoutFrame.setLocationRelativeTo(null);
	
}

public void createtotalfinescreen() {
	enterfineframe = new JFrame("Pay Fine");
	enterfine = new JLabel("Enter Fine");
	enterfinefield = new JTextField(5);
	payfine  = new JButton("PAY FINE");
	payfine.setActionCommand("PAYTOTALFINE");
	payfine.addActionListener(buttonClickHandler);

	
	finestatusarea = new JTextArea(5, 20);

	enterfineframe.setLayout(new FlowLayout());
	enterfineframe.add(enterfine);
	enterfineframe.add(enterfinefield);
	enterfineframe.add(payfine);
	enterfineframe.add(finestatusarea);
	
	
	enterfineframe.setSize(500, 400);
	enterfineframe.setVisible(true);
	enterfineframe.setLocationRelativeTo(null);

}

public void paytotalfinehandler() {
	double totalFine = 0.0;
	Date currentDate = new Date();
	String memberidtext = memberIdField.getText();
	
	bookmarkers=null;
	bookmarkers=new Bookmarkers();
	totalFine = bookmarkers.calcTotalFine(memberidtext);
	String fine = String.valueOf(totalFine);
	if(enterfinefield.getText().equals(fine)) {
		
		bookmarkers.createNewTransaction(memberidtext, currentDate, "null", totalFine, "F");
	}
	int successpaytotalfine = bookmarkers.returnAllLateItems(memberidtext);
	if(successpaytotalfine == 1) {
		finestatusarea.setText("Fines paid Successfully");
	}
	

}

public void totalfinecalculator() {
	
	double totalFine = 0.0;
	
	bookmarkers=null;
	bookmarkers=new Bookmarkers();
	if(bookmarkers.checkIfMember(memberIdField.getText()) == 1) {
		totalFine = bookmarkers.calcTotalFine(memberIdField.getText());
		if(totalFine > 0) {
			BookIdField.setText("Pay $" + totalFine + " fine");
			createtotalfinescreen();
		}
		else {
			BookIdField.setText("No fine due");
		}
	}
	else {
		BookIdField.setText("Invalid member Id");
	}
}

/** 
 * creates the transaction history screen for  the library GUI
 * @param none
 */	
private void createtransactionHistroyScreen() {
	checkoutFrame = new JFrame("Transaction History");
	checkoutinfo = new JPanel();
	checkoutdisplay = new JPanel();
	
	memberId = new JLabel("memberId");
	memberIdField = new JTextField(20);
	String []monthfrom  = { "Jan", "Feb", "Mar", "Apr","May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	String []monthto = { "Jan", "Feb", "Mar", "Apr","May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	membershipmonthfrom = new JComboBox(monthfrom); 
	membershipmonthfrom.setSelectedIndex(0);
	membershipmonthto = new JComboBox(monthto);
	membershipmonthto.setSelectedIndex(0);

	CheckoutInfo = new JTextArea(10, 50);
	CheckoutInfo.setEditable(false);
	checkoutinfo.add(memberId);
	checkoutinfo.add(memberIdField);

	fromDate = new  JLabel("Datefrom(DDMMMYY)");
	toDate = new JLabel("Dateto(DDMMMYY)");
	transactionfromDateFielddate = new JTextField(5);
	transactionfromDateFieldyear = new JTextField(5); 

	transactiontoDateFielddate = new JTextField(5);
	transactiontoDateFieldyear = new JTextField(5); 

	checkoutinfo.add(fromDate);
	checkoutinfo.add(transactionfromDateFielddate);
	checkoutinfo.add(membershipmonthfrom);
	checkoutinfo.add(transactionfromDateFieldyear);
	checkoutinfo.add(toDate);
	checkoutinfo.add(transactiontoDateFielddate);
	checkoutinfo.add(membershipmonthto);
	checkoutinfo.add(transactiontoDateFieldyear);

	checkoutButton = new JButton("Transaction History");
	checkoutButton.setActionCommand("TransactionHistory");
	checkoutButton.addActionListener(buttonClickHandler);

	checkoutinfo.add(checkoutButton);
	checkoutdisplay.add(CheckoutInfo);
	checkoutFrame.setLayout(new BorderLayout());

	checkoutFrame.add(checkoutinfo, BorderLayout.CENTER);
	checkoutFrame.add(checkoutdisplay, BorderLayout.SOUTH);
	checkoutFrame.setSize(850, 600);
	checkoutFrame.setVisible(true);
	checkoutFrame.setLocationRelativeTo(null);
	
}

public void becomemember() {
	String datenum = membershipDateFielddate.getText();
	String membershipmonthvalue = membershipmonth.getSelectedItem().toString();
	String year = membershipDateFieldyear.getText();
	String totaldate = datenum+"-"+membershipmonthvalue+"-"+year;
	Date date = new Date(totaldate);
	String displaystring = "";
	String textdisplay = "Your library membership id is ";
	if(BookIdField.getText().equalsIgnoreCase("Springfield")) 
	{
		displaystring = "Free Membership" +"\n";
	}
	else {
		displaystring = "Pay $ 10 Membership Fee" + "\n";
	}
	
	bookmarkers=null;
	bookmarkers=new Bookmarkers();
	CheckoutInfo.setText(displaystring + textdisplay+ bookmarkers.createNewMember(memberIdField.getText(), memberemailField.getText(), memberPhoneField.getText(), memberAddressField.getText(), date, BookIdField.getText()));

}


public void checkoutbook(){
	String memberid = memberIdField.getText();
	String itemid = BookIdField.getText();
	String result = "";
	bookmarkers=null;
	bookmarkers=new Bookmarkers();
	if(bookmarkers.checkIfMember(memberid) == 1)
	{
		
		CheckOut C1=new CheckOut();
		Map<String,CheckedOutItems> checkOutMap=bookmarkers.checkOutBook(memberid, itemid, C1);
		
		if(C1.getReason() == 1) {
			CheckoutInfo.setText("Invalid Book Number");
		}
		if(C1.getReason() == 0) {
			for(Map.Entry<String,CheckedOutItems> entry : checkOutMap.entrySet()) {
				result += entry.getValue()+"\n";
			}
			CheckoutInfo.setText(result);
			
		}
		if(C1.getReason() == 2) {
			CheckoutInfo.setText("Pay Fine");
		}
		

		if(C1.getReason() == 3) {
			CheckoutInfo.setText("The Book is not available");
		}
		if(C1.getReason() == 4) {
			CheckoutInfo.setText("You have exceeded the number of book limit.\nPlease return some books to issue new ones");
		}

	
	}
	if(bookmarkers.checkIfMember(memberid) == 0){
		CheckoutInfo.setText("Invalid MemberId");
	}

}

public void showTransactionHistory() {
	try
	{
		String result = "";
		Date date1,date2;
		DateFormat formatter ;
		String datefrom, dateto;
		datefrom = transactionfromDateFielddate.getText()+"-"+membershipmonthfrom.getSelectedItem().toString()+ "-"+transactionfromDateFieldyear.getText();
		dateto = transactiontoDateFielddate.getText()+"-"+membershipmonthto.getSelectedItem().toString()+"-"+transactiontoDateFieldyear.getText();
		formatter = new SimpleDateFormat("dd-MMM-yy");	        
		date1 = formatter.parse(datefrom);
		date2 = formatter.parse(dateto);
		
		bookmarkers=null;
    	bookmarkers=new Bookmarkers();
		
		Map<String, History> historymap = bookmarkers.queryTransactionHistory(memberIdField.getText(),date1,date2);
		for(Map.Entry<String,History> entry : historymap.entrySet()) {
			result += entry.getValue()+"\n";
		}
		
		if(result == ""){
			result = "No transaction done in the dates mentioned.";
		}
		CheckoutInfo.setText(result );	
	}catch(Exception e){}

}

public void returnItem(){
	double fine=0.0;
	
	bookmarkers=null;
	bookmarkers=new Bookmarkers();
	fine = bookmarkers.calcFineWhileReturning(BookIdField.getText(), memberIdField.getText());
	if(fine > 0.0) {
		CheckoutInfo.setText("Pay $" + fine +" fine");
		Date currentDate = new Date();
		bookmarkers.createNewTransaction(memberIdField.getText() ,currentDate,"null",fine, "F");
	}
	else {
		CheckoutInfo.setText("No fine due on this item.Please return it");
	}
}
/** 
 * creates the handler for buttonclick for  the library GUI
 * @param none
 */	
class ButtonClickHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Return") {
			returnItem();
			
		}
		
		if(e.getActionCommand() == "CheckOut") {
			checkoutbook();
		}
		if(e.getActionCommand() == "VideoCheckout") {
			checkoutvideo();
		}
		if(e.getActionCommand() == "Becomemember") {
			becomemember();
		}
		if(e.getActionCommand() == "Payfine") {
		
			CheckoutInfo.setText("Thanks!!!Payment Done successfully");
			
		}
		if(e.getActionCommand() == "OKFINE")
		{
			totalfinecalculator();
		}
		if(e.getActionCommand() == "PAYTOTALFINE") {
			paytotalfinehandler();
		}
		if(e.getActionCommand() == "TransactionHistory") {
			showTransactionHistory();
						
		}
		if(e.getActionCommand() == "Claim_Item_Lost") {
			
			bookmarkers=null;
	    	bookmarkers=new Bookmarkers();
			successLost = bookmarkers.claimLostItem(memberIdField.getText(),BookIdField.getText());
			if(successLost < 0) {
				CheckoutInfo.setText("You did not borrow this book");
			}
			else {
				CheckoutInfo.setText("Pay $ " + successLost + " fine");
			}
			
		}
		if(e.getActionCommand() == "OkReturn") {
			
			bookmarkers=null;
	    	bookmarkers=new Bookmarkers();
			int returnSuccess = bookmarkers.returnItem(memberIdField.getText(), BookIdField.getText());
			CheckoutInfo.setText("Item with ItemId " +BookIdField.getText()+" Successfully Return");
		}
		if(e.getActionCommand() == "OKItemLost") {
			
			bookmarkers=null;
	    	bookmarkers=new Bookmarkers();
			Date currentDate = new Date();
			
			int  successRemoved = bookmarkers.removeLostItem(memberIdField.getText(),currentDate,  BookIdField.getText(), successLost);
			CheckoutInfo.setText("Successfully claimed lost");
			
		}

	
}
	
}

/** 
 * creates the search screen for  the library GUI
 * @param none
 */	
public void createSearchComponent() {
	frame = new JFrame("Search");
	String[] searchtypes = { "Booknumber", "Title", "Author"};
	String[] videosearchtypes = { "Videonumber", "Title", "Director"};
	searchVideoType = new JComboBox(videosearchtypes);
	searchVideoType.setSelectedIndex(0);
	
	videoSearchField = new JTextField(50);
	videosearchbutton = new JButton("Video Search");
	videosearchbutton.addActionListener(searchVideoActionhandler);
	
	bookSearch = new JLabel("Book Search");
	videoSearch = new JLabel("Video Search");
	videoSearchResults = new JTextArea(5, 40);
	videoSearchResults.setEditable(false);
	
    searchtype = new JComboBox(searchtypes);
	searchtype.setSelectedIndex(0);
	buttonSearch = new JButton("Book Search");
	searchfield = new JTextField(50);
	buttonSearch.addActionListener(searchActionhandler);
	
	searchResults = new JTextArea(5,60);
	searchResults.setText("");
	searchResults.setEditable(false);
	frame.setLayout(new FlowLayout());
	frame.add(bookSearch);
	frame.add(searchtype);
	frame.add(searchfield);
	frame.add(buttonSearch);
	frame.add(searchResults);
	frame.add(videoSearch);
	frame.add(searchVideoType);
	frame.add(videoSearchField);
	frame.add(videosearchbutton);
	frame.add(videoSearchResults);
	
	
	
	frame.setSize(800, 600);
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
		
}

/** 
 * inner class for handling admin login button click
 * @param none
 */	
class LoginActionHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		
		if(adminname.getText().equals("admin") && adminpassword.getText().equals("admin"))
		{
			createAdminSpecificOptions();
			adminpassword.setText("");
			
		}
		else {
			JOptionPane.showMessageDialog(null,"Invalid username/password");
		}
		
	}
	
}

/** 
 * creates admin specific options for the library GUI
 * @param none
 */	
public void createAdminSpecificOptions() {
	
	adminoptionsframe = new JFrame();

	adminpanel = new JPanel();
	adminpanel.setLayout(new GridLayout(6, 1));
	adminpanel.setSize(400, 250);
	adminlogoutpanel = new JPanel();

	admintotalmemberlookup = new JButton("Find total Members");
	admintotalmemberlookup.setActionCommand("FindtotalMembers");
	admintotalmemberlookup.addActionListener(adminspecificoptionsActionhandler);
	
	admintotalfineslookup = new JButton("Find total fines");
	admintotalfineslookup.setActionCommand("Findtotalfines");
	admintotalfineslookup.addActionListener(adminspecificoptionsActionhandler);
	
	admintotalitemscheckedlookup = new JButton("Find total items checked out");
	admintotalitemscheckedlookup.setActionCommand("Findtotalitemscheckedout");
	admintotalitemscheckedlookup.addActionListener(adminspecificoptionsActionhandler);
	
	adminhighestcheckoutitemlookup = new JButton("Find highest checkout item");
	adminhighestcheckoutitemlookup.setActionCommand("Findhighestcheckoutbook");
	adminhighestcheckoutitemlookup.addActionListener(adminspecificoptionsActionhandler);
	
	adminitemspercatlookup = new JButton("Find number of items per category");
	adminitemspercatlookup.setActionCommand("Findnumberofitemspercategory");
	adminitemspercatlookup.addActionListener(adminspecificoptionsActionhandler);
	
	adminviewasgraph = new JButton("View Info As Graph");
	adminviewasgraph.setActionCommand("ViewAsGraph");
	adminviewasgraph.addActionListener(adminspecificoptionsActionhandler);
	
	
	adminpanel.setBorder(new TitledBorder(new EtchedBorder(), "Admin Options"));
	adminpanel.add(admintotalmemberlookup);
	adminpanel.add(admintotalfineslookup);
	adminpanel.add(admintotalitemscheckedlookup);
	adminpanel.add(adminhighestcheckoutitemlookup);
	adminpanel.add(adminitemspercatlookup);
	adminpanel.add(adminviewasgraph);
	
	
	adminlabel = new JLabel("You are logged in as Admin");
	adminlogoffbutton = new JButton("Log Off");
	adminlogoutpanel.add(adminlabel);
	adminlogoutpanel.add(adminlogoffbutton);
	adminlogoffbutton.addActionListener(logoffActionhandler);
	

	adminFindResults = new JTextArea(5, 20);
	adminoptionsframe.setLayout(new BorderLayout());
	adminoptionsframe.add(adminpanel, BorderLayout.WEST);
	adminoptionsframe.add(adminlogoutpanel, BorderLayout.EAST);
	adminoptionsframe.add(adminFindResults,BorderLayout.CENTER );
	adminFindResults.setEditable(false);
	adminoptionsframe.setSize(800, 600);
	adminoptionsframe.setVisible(true);
	adminoptionsframe.setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

/** 
 * creates the admin specific options handler
 * @param none
 */	
class adminSpecificOptionsActionhandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		
		bookmarkers=null;
    	bookmarkers=new Bookmarkers();
		if(e.getActionCommand() == "FindtotalMembers") {
			int totalmembers = bookmarkers.totalNoOfMembers();
			String total = "Total members = " + totalmembers;
			adminFindResults.setText(total);		
		}
		
		if(e.getActionCommand() == "Findtotalfines") {
			adminFindResults.setText("Total fines = ");
			createadmintotalfinescreen();
			
		}
		if(e.getActionCommand() == "Totallibfine") {
			totallibfinecollected();
		}
		if(e.getActionCommand() == "Findtotalitemscheckedout") {
			int itemscheckedout = 	bookmarkers.totalNoOfItemsCheckedOut();
			String total = "Total items checked out items = " + itemscheckedout;
			adminFindResults.setText(total);
		}
		if(e.getActionCommand() == "Findhighestcheckoutbook") {
			
			createadminmostpopularitemcreen();
		}
		if(e.getActionCommand() == "MostPopularitem") {
			Mostpopularitem();
		}

		if(e.getActionCommand() == "Findnumberofitemspercategory") {
			int fictionbooks = bookmarkers.totalNoOfFicBooks();
			int nonfictionbooks = bookmarkers.totalNoOfNonFicBooks();
			int videos = bookmarkers.totalNoOfVideos();
			String result = "Total no of items per category \n" +"Total no of fiction books = " + fictionbooks+
							"\n Total no of Non Fiction Books = " + nonfictionbooks 
							+"\n Total No of Videos = " + videos;
			adminFindResults.setText(result);
		}
		if(e.getActionCommand() == "ViewAsGraph") {
			viewAsPieChart();
		}
		
	}
	
}

/** 
 * creates the pie chart screen for admin
 * @param none
 */	


public  void viewAsPieChart() {
	JFrame piechartframe = new JFrame("Library Item Pie Chart");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
	bookmarkers=null;
	bookmarkers=new Bookmarkers();
    int fictionbooks = bookmarkers.totalNoOfFicBooks();
	int nonfictionbooks = bookmarkers.totalNoOfNonFicBooks();
	int videos = bookmarkers.totalNoOfVideos();
	int checkedout = bookmarkers.totalNoOfItemsCheckedOut();
    
	PieChartManager piechart = new PieChartManager(fictionbooks, nonfictionbooks, videos, checkedout);
	piechartframe.add(piechart);

	piechartframe.setSize(600, 400);
	piechartframe.setVisible(true);
	piechartframe.setLocationRelativeTo(null);
}

/** 
 * creates the logoff action handler for the library GUI
 * @param none
 */	
class LogoffActionhandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		adminoptionsframe.dispose();
		
	}
}

/** 
 * creates the search action handler screen for  the library GUI
 * @param none
 */	
class SearchActionHandler implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		
		bookmarkers=null;
    	bookmarkers=new Bookmarkers();
		String searchtypestr = searchtype.getSelectedItem().toString();
		String result = "";
		if(searchtypestr == "Booknumber") {
			tempBookMap = bookmarkers.queryByBookNumber(searchfield.getText());
			for( Map.Entry<String, Book> entry : tempBookMap.entrySet())
			{
				result += entry.getValue()+"\n";
			}
			searchResults.setText(result);
			
		}
		if(searchtypestr == "Title") {
			tempBookMap = bookmarkers.queryByBookTitle(searchfield.getText());
			for( Map.Entry<String, Book> entry : tempBookMap.entrySet())
			{
				result += entry.getValue() +"\n";
			}
			searchResults.setText(result);

			
		}
		if(searchtypestr == "Author") {
			tempBookMap = bookmarkers.queryByAuthor(searchfield.getText());
			for( Map.Entry<String, Book> entry : tempBookMap.entrySet())
			{
				result += entry.getValue() +"\n";
			}
			searchResults.setText(result);
			
	
		}
		
	}
}

/** 
 * creates the video search action handler screen for  the library GUI
 * @param none
 */	
class VideoSearchActionHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		
		bookmarkers=null;
    	bookmarkers=new Bookmarkers();
		String searchtypestr = searchVideoType.getSelectedItem().toString();
		String result = "";
								
		if(searchtypestr == "Videonumber") {
			tempVideoMap = bookmarkers.queryByVideoNumber(videoSearchField.getText());
			for( Map.Entry<String, Video> entry : tempVideoMap.entrySet())
			{
				result += entry.getValue() + "\n";
			}
			videoSearchResults.setText(result);
		}
		if(searchtypestr == "Title") {
			tempVideoMap = bookmarkers.queryByVideoTitle(videoSearchField.getText());
			for( Map.Entry<String, Video> entry : tempVideoMap.entrySet())
			{
				result += entry.getValue() + "\n";
			}
			videoSearchResults.setText(result);
		}
		if(searchtypestr == "Director") {
			tempVideoMap = bookmarkers.queryByDirector(videoSearchField.getText());
			for( Map.Entry<String, Video> entry : tempVideoMap.entrySet())
			{
				result += entry.getValue() + "\n";
			}
			videoSearchResults.setText(result);
			
	
		}
		
	}
	
}

public void createReserveItemScreen() {
	
	
	frame = new JFrame("Reserve Item");
	
	bookPanel = new JPanel();
	videoPanel = new JPanel();
	
	memberIDbook = new JLabel("Member Id");
	bookIdLabel = new JLabel("Book Id");
	memberbookreservationIdField = new JTextField(20);
	bookIdField = new JTextField(20);
	reserveBookButton = new JButton("Reserve Book");
	reserveBookButton.addActionListener(reserveItemActionhandler);
	reserveBookButton.setActionCommand("ReserveBook");
	booktextArea = new JTextArea(5, 20);
	
	bookPanel.add(memberIDbook);
	bookPanel.add(memberbookreservationIdField);
	bookPanel.add(bookIdLabel);
	bookPanel.add(bookIdField);
	bookPanel.add(reserveBookButton);
	bookPanel.add(booktextArea);
	
	//reserve video attributes
	memberIDvideo = new JLabel("Member Id");
	videoIdLabel = new JLabel("Video Id");
	membervideoreservationIdField = new JTextField(20);
	videoIdField = new JTextField(20); 
	reserveVideoButton = new JButton("Reserve Video");
	reserveVideoButton.addActionListener(reserveItemActionhandler);
	reserveVideoButton.setActionCommand("ReserveVideo");
	videotextArea = new  JTextArea(5, 20);
	
	videoPanel.add(memberIDvideo);
	videoPanel.add(membervideoreservationIdField);
	videoPanel.add(videoIdLabel);
	videoPanel.add(videoIdField);
	videoPanel.add(reserveVideoButton);
	videoPanel.add(videotextArea);
	
	frame.setLayout(new FlowLayout());
	
	frame.add(bookPanel, BorderLayout.CENTER);
	frame.add(videoPanel, BorderLayout.SOUTH);

	
	frame.add(bookPanel);
	frame.add(videoPanel);

	frame.setSize(1000, 600);
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	
}

class ReserveItemActionHandler implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		bookmarkers=null;
    	bookmarkers=new Bookmarkers();
		if(e.getActionCommand() == "ReserveBook"){
			String memberid = memberbookreservationIdField.getText();
			String bookid = bookIdField.getText();
			if(bookmarkers.checkIfMember(memberid) == 1)
			{
				
				bookmarkers=null;
		    	bookmarkers=new Bookmarkers();
		    	
				if(bookmarkers.reserveBook(memberid, bookid) == 1) {
					
						booktextArea.setText("Book with id " + bookid + " is reserved by you.");
				}
				else {
					booktextArea.setText("Unable to reserve Book with id " + bookid);
				}
			}
			if(bookmarkers.checkIfMember(memberid) == 0) {
				booktextArea.setText("Invalid memberId");
			}

		}
		if(e.getActionCommand() == "ReserveVideo"){
			String memberid = membervideoreservationIdField.getText();
			String videoid = videoIdField.getText();
			if(bookmarkers.checkIfMember(memberid) == 1)
			{
				if(bookmarkers.reserveVideo(memberid, videoid) == 1) {
					videotextArea.setText("Book with id " + videoid + "is reserved by you.");
				}
				else {
					videotextArea.setText("Unable to reserve video with id " + videoid);
				}
			}
			if(bookmarkers.checkIfMember(memberid) == 0) {
				videotextArea.setText("Invalid memberId");
			}
			
		}
	}
	
}

public void createadmintotalfinescreen() {
	
	String [] monthfrom = { "Jan", "Feb", "Mar", "Apr","May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	String [] monthto = { "Jan", "Feb", "Mar", "Apr","May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	commonframe = new JFrame("Total Fine Collected");
	commonlabel = new JLabel("Total Fine");
	commondatefromlabel = new JLabel("Datefrom(DDMMMYY)");
	commondatefromfield = new JTextField(5);
	commonmonthfrom = new JComboBox(monthfrom);
	commonyearfrom = new JTextField(5);
	commondatetolabel = new JLabel("Dateto(DDMMMYY)");
	commondatetofield = new JTextField(5);
	commonmonthto = new JComboBox(monthto);
	commonyearto = new JTextField(5);
	commontextarea = new JTextArea(5, 20);
	commonbutton = new JButton("Show Results");
	commonbutton.setActionCommand("Totallibfine");
	commonbutton.addActionListener(adminspecificoptionsActionhandler);
	commonframe.setLayout(new FlowLayout());
	
	commonframe.add(commonlabel);
	commonframe.add(commondatefromlabel);
	commonframe.add(commondatefromfield);
	commonframe.add(commonmonthfrom);
	commonframe.add(commonyearfrom);
	commonframe.add(commondatetolabel);
	commonframe.add(commondatetofield);
	commonframe.add(commonmonthto);
	commonframe.add(commonyearto);
	commonframe.add(commontextarea);
	commonframe.add(commonbutton);
	
	commonframe.setSize(800, 600);
	commonframe.setVisible(true);
	commonframe.setLocationRelativeTo(null);

	
}
public void totallibfinecollected() {
	try

	{
		Date date1,date2;
		DateFormat formatter ;
		String datefrom, dateto;
		String result = "Total fines collected by bookmarkers = $ ";
		datefrom = commondatefromfield.getText() +"-" +commonmonthfrom.getSelectedItem().toString() + "-" +commonyearfrom.getText();
		dateto = commondatetofield.getText() +"-" +commonmonthto.getSelectedItem().toString() + "-" +commonyearto.getText();
		formatter = new SimpleDateFormat("dd-MMM-yy");	        
		date1 = formatter.parse(datefrom);
		date2=formatter.parse(dateto);
		//Added by meen
		bookmarkers=null;
    	bookmarkers=new Bookmarkers();
		double fine = bookmarkers.totalFinesCollected(date1,date2);
		result +=fine ;
		
		commontextarea.setText(result);
	}catch(Exception e){}
}

public void Mostpopularitem() {
	try

	{	
		Date date1,date2;
		DateFormat formatter ;
		String datefrom, dateto;
		String result = "Most popular item in bookmarkers is ";
		datefrom = commondatefromfield.getText() +"-" +commonmonthfrom.getSelectedItem().toString() + "-" +commonyearfrom.getText();
		dateto = commondatetofield.getText() +"-" +commonmonthto.getSelectedItem().toString() + "-" +commonyearto.getText();
		formatter = new SimpleDateFormat("dd-MMM-yy");	 
		
		date1 = formatter.parse(datefrom);
		date2=formatter.parse(dateto);
		
		bookmarkers=null;
    	bookmarkers=new Bookmarkers();
    	
		result += bookmarkers.mostPopularItem(date1,date2);
		commontextarea.setText(result);
		
	}catch(Exception e){}

	
}

public void createadminmostpopularitemcreen() {
	
	String [] monthfrom = { "Jan", "Feb", "Mar", "Apr","May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	String [] monthto = { "Jan", "Feb", "Mar", "Apr","May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	commonframe = new JFrame("Most Popular Item");
	commonlabel = new JLabel("Most Popular Item");
	commondatefromlabel = new JLabel("Date from(DDMMMYY)");
	commondatefromfield = new JTextField(5);
	commonmonthfrom = new JComboBox(monthfrom);
	commonyearfrom = new JTextField(5);
	commondatetolabel = new JLabel("Date to(DDMMMYY)");
	commondatetofield = new JTextField(5);
	commonmonthto = new JComboBox(monthto);
	commonyearto = new JTextField(5);
	commontextarea = new JTextArea(5, 20);
	commonbutton = new JButton("Show Results");
	commonbutton.setActionCommand("MostPopularitem");
	commonbutton.addActionListener(adminspecificoptionsActionhandler);
	
	commonframe.setLayout(new FlowLayout());
	
	commonframe.add(commonlabel);
	commonframe.add(commondatefromlabel);
	commonframe.add(commondatefromfield);
	commonframe.add(commonmonthfrom);
	commonframe.add(commonyearfrom);
	commonframe.add(commondatetolabel);
	commonframe.add(commondatetofield);
	commonframe.add(commonmonthto);
	commonframe.add(commonyearto);
	commonframe.add(commontextarea);
	commonframe.add(commonbutton);
	
	commonframe.setSize(800, 600);
	commonframe.setVisible(true);
	commonframe.setLocationRelativeTo(null);

}



/** 
 * creates the useroption handler for the library GUI
 * @param none
 */	
class UserOptionEventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String str = "";
			if(e.getActionCommand() == "s") {
				str = "Search Clicked";
				createSearchComponent();
					
			}
			if(e.getActionCommand() == "c") {
				str = "checkout clicked";
				
				createCheckoutScreen();
			}
			if(e.getActionCommand() == "ri") {
				
				createReturnItemScreen();
				
			}
			if(e.getActionCommand() == "bm") {
				createBecomeMemberScreen();
				str = "become member clicked";
			}
			if(e.getActionCommand() == "pf") {
				createPayfineScreen();
				str = "pay fine clicked";
			}
			if(e.getActionCommand() == "st") {
				str = "see transaction clicked";
				createtransactionHistroyScreen();
			}
			if(e.getActionCommand() == "r") {
				createReserveItemScreen();
				str = "reserve item clicked";
			}
			if(e.getActionCommand() == "claim_lost_item") {
				createclaimItemLostScreen();
			}
			
		}
	
	}
}
