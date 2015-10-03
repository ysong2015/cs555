package cs555;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String id;
	private String name;
	private String sex;
	private String birt;
	private String deat;
	private List<Family> famc;
	private List<Family> fams;

	public Person() {
		this.famc = new ArrayList<>();
		this.fams = new ArrayList<>();
	}

	public Person(String id) {
		this.famc = new ArrayList<>();
		this.fams = new ArrayList<>();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirt() {
		return birt;
	}

	public void setBirt(String birt) {
		this.birt = birt;
	}

	public String getDeat() {
		return deat;
	}

	public void setDeat(String deat) {
		this.deat = deat;
	}

	public List<Family> getFamc() {
		return famc;
	}

	public List<Family> getFams() {
		return fams;
	}

	public void addFamc(Family family) {
		famc.add(family);
	}

	public void addFams(Family family) {
		fams.add(family);
	}

	public String format() {
		return  id + ":\t" + name;
	}

}
