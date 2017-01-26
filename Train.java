package cta;

public class Train {

    private int stationID;
    private String stationName;
    private int month;
    private int day;
    private String year;
    private String dayType;
    private int rides;
    
// DEFAULT CONSTRUCTOR
    public Train () {
        setStationID(0);
        setStationName("Unknown");
        setMonth(1);
        setDay(1);
        setYear("Unknown");
        setDayType("Unknown");
        setRides(0);
    }

// NONDEFAULT CONSTRUCTOR
    public Train (int stationID, String stationName, int month, int day, String year,  String dayType, int rides) {
        setStationID(stationID);
        setStationName(stationName);
    	setMonth(month);
        setDay(day);
        setYear(year);
        setDayType(dayType);
        setRides(rides);
    }

// ACCESSORS AND METHODS
	public int getStationID() {return stationID; }
	public void setStationID(int newStationID) {stationID = newStationID; }
	public String getStationName() {return stationName; }
	public void setStationName(String newStationName) {stationName = newStationName;}
	public int getMonth() {return month; }
	public void setMonth(int newMonth) {month = newMonth; }
	public int getDay() {return day; }
	public void setDay(int newDay) {day = newDay; }
	public String getYear() {return year; }
	public void setYear(String newYear) {year = newYear; }
	public String getDayType() {return dayType; }
	public void setDayType(String newDayType) {dayType = newDayType;}	
	public int getRides() {return rides; }
	public void setRides(int newRides) {rides = newRides; }

// STRING
	public String toString(){ 
		return "Station ID = " + getYear();
	}
	
}