package cs555;

import java.util.ArrayList;
import java.util.List;

public class Family {
	private String id;
	private String marr;
	private Person husb;
	private Person wife;
	private List<Person> chil;
	private String div;
    
    //test

	public Family() {
		this.chil = new ArrayList<>();
	}

	public Family(String id) {
		this.chil = new ArrayList<>();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarr() {
		return marr;
	}

	public void setMarr(String marr) {
		this.marr = marr;
	}

	public Person getHusb() {
		return husb;
	}

	public void setHusb(Person husb) {
		this.husb = husb;
	}

	public Person getWife() {
		return wife;
	}

	public void setWife(Person wife) {
		this.wife = wife;
	}

	public List<Person> getChil() {
		return chil;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public void addChild(Person person) {
		chil.add(person);
	}

	public String format() {
		return  id + ":\t" +"Husband: "+ husb.getName() + "\tWife: " + wife.getName();
	}
}
