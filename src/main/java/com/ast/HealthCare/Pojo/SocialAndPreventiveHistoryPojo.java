package com.ast.HealthCare.Pojo;

public class SocialAndPreventiveHistoryPojo {

	//CREATE TABLE  socialandpreventivehistory(SOCIALID SERIAL,PATIENTID TEXT,MARITALSTATUS TEXT,WORKSTATUS TEXT,OCCUPATION TEXT,SMOKING TEXT,PACKSPERDAY INTEGER,
	//SMOKINGDURATION TEXT,ALCOHOL TEXT,DRINKSPERWEEK INTEGER,DRINKSDURATION INTEGER,COFFEEORTEA TEXT,
	//CUPSPERDAY INTEGER,EXCERCISE TEXT,HOURSPERDAY INTEGER,SEATBELTS TEXT,HELMETS TEXT,
	//TWOWHEELERDRIVINGHOURSPERDAY INTEGER,FOURWHEELERDRIVINGHOURSPERDAY INTEGER);
	private int socialId;
	private String patientId;
	private String maritalStatus;
	private String workStatus;
	private String occupation;
	private String smoking;
	private int packsPerDay;
	private String smokingDuration;
	private String alcohol;
	private int drinksPerWeek;
	private int drinksDuration;
	private String coffeeOrTea;
	private int cupsPerDay;
	private String excercise;
	private int hoursPerDay;
	private String seatBelts;
	private String helmets;
	private int twoWheelerDrivingHoursPerDay;
	private int fourWheelerDrivingHoursPerDay;
	
	@Override
	public String toString() {
		return "SocialAndPreventiveHistoryPojo [socialId=" + socialId + ", patientId=" + patientId + ", maritalStatus="
				+ maritalStatus + ", workStatus=" + workStatus + ", occupation=" + occupation + ", smoking=" + smoking
				+ ", packsPerDay=" + packsPerDay + ", smokingDuration=" + smokingDuration + ", alcohol=" + alcohol
				+ ", drinksPerWeek=" + drinksPerWeek + ", drinksDuration=" + drinksDuration + ", coffeeOrTea="
				+ coffeeOrTea + ", cupsPerDay=" + cupsPerDay + ", excercise=" + excercise + ", hoursPerDay="
				+ hoursPerDay + ", seatBelts=" + seatBelts + ", helmets=" + helmets + ", twoWheelerDrivingHoursPerDay="
				+ twoWheelerDrivingHoursPerDay + ", fourWheelerDrivingHoursPerDay=" + fourWheelerDrivingHoursPerDay
				+ "]";
	}
	public int getSocialId() {
		return socialId;
	}
	public void setSocialId(int socialId) {
		this.socialId = socialId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getSmoking() {
		return smoking;
	}
	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}
	public int getPacksPerDay() {
		return packsPerDay;
	}
	public void setPacksPerDay(int packsPerDay) {
		this.packsPerDay = packsPerDay;
	}
	public String getSmokingDuration() {
		return smokingDuration;
	}
	public void setSmokingDuration(String smokingDuration) {
		this.smokingDuration = smokingDuration;
	}
	public String getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}
	public int getDrinksPerWeek() {
		return drinksPerWeek;
	}
	public void setDrinksPerWeek(int drinksPerWeek) {
		this.drinksPerWeek = drinksPerWeek;
	}
	public int getDrinksDuration() {
		return drinksDuration;
	}
	public void setDrinksDuration(int drinksDuration) {
		this.drinksDuration = drinksDuration;
	}
	public String getCoffeeOrTea() {
		return coffeeOrTea;
	}
	public void setCoffeeOrTea(String coffeeOrTea) {
		this.coffeeOrTea = coffeeOrTea;
	}
	public int getCupsPerDay() {
		return cupsPerDay;
	}
	public void setCupsPerDay(int cupsPerDay) {
		this.cupsPerDay = cupsPerDay;
	}
	public String getExcercise() {
		return excercise;
	}
	public void setExcercise(String excercise) {
		this.excercise = excercise;
	}
	public int getHoursPerDay() {
		return hoursPerDay;
	}
	public void setHoursPerDay(int hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}
	public String getSeatBelts() {
		return seatBelts;
	}
	public void setSeatBelts(String seatBelts) {
		this.seatBelts = seatBelts;
	}
	public String getHelmets() {
		return helmets;
	}
	public void setHelmets(String helmets) {
		this.helmets = helmets;
	}
	public int getTwoWheelerDrivingHoursPerDay() {
		return twoWheelerDrivingHoursPerDay;
	}
	public void setTwoWheelerDrivingHoursPerDay(int twoWheelerDrivingHoursPerDay) {
		this.twoWheelerDrivingHoursPerDay = twoWheelerDrivingHoursPerDay;
	}
	public int getFourWheelerDrivingHoursPerDay() {
		return fourWheelerDrivingHoursPerDay;
	}
	public void setFourWheelerDrivingHoursPerDay(int fourWheelerDrivingHoursPerDay) {
		this.fourWheelerDrivingHoursPerDay = fourWheelerDrivingHoursPerDay;
	}
	
}
