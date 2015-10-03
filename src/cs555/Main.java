package cs555;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		List<Family> listFam = new ArrayList<Family>();
		List<Person> listPerson = new ArrayList<Person>();
		redGEDByLine("test.ged", listPerson, listFam);
		printGEDById(listPerson, listFam);
	}

	private static void redGEDByLine(String file, List<Person> listPerson, List<Family> listFam) {
		// TODO Auto-generated method stub
		try (Stream<String> s = Files.lines(Paths.get("C:/Users/yumen_000/Desktop", file))) {
			Iterator<String> iter = s.iterator();
			String perId = null, famId = null;
			boolean isBirt = false, isDeath = false;

			while (iter.hasNext()) {
				Person person = new Person();
				Family family = new Family();

				String[] values = iter.next().trim().split(" ", 3);

				if (values.length == 3) {
					String level = values[0];
					if (level.equals("0")) {
						if (values[2].equals("INDI")) {
							perId = values[1];
							if (perId != null) {
								int perIndex = getPersonIndex(perId, listPerson);
								if (perIndex == -1) {
									person.setId(perId);
									listPerson.add(person);
								}
							}
						} else if (values[2].equals("FAM")) {
							famId = values[1];
							if (famId != null) {
								int famIndex = getFamilyIndex(famId, listFam);
								if (famIndex == -1) {
									family.setId(famId);
									listFam.add(family);
								}
							}
						}
					} else if (level.equals("1") || level.equals("2")) {
						String tag, arguments = null;
						tag = values[1];
						if (tag.equals("BIRT")) {
							isBirt = true;
							continue;
						} else if (tag.equals("DEAT")) {
							isDeath = true;
							continue;
						} else if (tag.equals("DATE")) {
							arguments = values[2];
							if (isBirt) {
								isBirt = false;
								saveInfo("BIRT", arguments, perId, famId, listPerson, listFam);
							}
							if (isDeath) {
								isDeath = false;
								saveInfo("DEAT", arguments, perId, famId, listPerson, listFam);
							}
						} else if (level.equals("1")) {
							arguments = values[2];
							saveInfo(tag, arguments, perId, famId, listPerson, listFam);
						}
					}
				}
			}

		} catch (IOException e) {
			System.out.printf("Error reading file %s\n", file);
		}
	}

	private static int getFamilyIndex(String famId, List<Family> listFam) {
		int index = -1;
		for (int i = 0; i < listFam.size(); i++) {
			if (listFam.get(i).getId().equals(famId)) {
				index = i;
			}
		}
		return index;
	}

	private static int getPersonIndex(String perId, List<Person> listPerson) {
		int index = -1;
		for (int i = 0; i < listPerson.size(); i++) {
			if (listPerson.get(i).getId().equals(perId)) {
				index = i;
				return index;
			}
		}
		return index;
	}

	private static void saveInfo(String tag, String arguments, String perId, String famId, List<Person> listPerson,
			List<Family> listFam) {
		// TODO Auto-generated method stub
		Person person = new Person();
		Family family = new Family();
		int perIndex = -1;
		int famIndex = -1;
		if (perId != null) {
			perIndex = getPersonIndex(perId, listPerson);
			if (perIndex != -1) {
				person = listPerson.get(perIndex);
			}
		}
		if (famId != null) {
			famIndex = getFamilyIndex(famId, listFam);
			if (famIndex != -1) {
				family = listFam.get(famIndex);
			}
		}
		if (tag.equals("NAME")) {
			person.setName(arguments);
		} else if (tag.equals("SEX")) {
			person.setSex(arguments);
		} else if (tag.equals("BIRT")) {
			person.setBirt(arguments);
		} else if (tag.equals("DEAT")) {
			person.setDeat(arguments);
		} else if (tag.equals("FAMC")) {
			Family fam;
			int fIndex = getFamilyIndex(arguments, listFam);
			if (fIndex != -1) {
				fam = listFam.get(fIndex);
			} else {
				fam = new Family(arguments);
				listFam.add(fam);
			}
			person.addFamc(fam);
		} else if (tag.equals("FAMS")) {
			Family fam;
			int fIndex = getFamilyIndex(arguments, listFam);
			if (fIndex != -1) {
				fam = listFam.get(fIndex);
			} else {
				fam = new Family(arguments);
				listFam.add(fam);
			}
			person.addFams(fam);
		} else if (tag.equals("MARR")) {
			family.setMarr(arguments);
		} else if (tag.equals("HUSB")) {
			Person husb;
			int pIndex = getPersonIndex(arguments, listPerson);
			if (pIndex != -1) {
				husb = listPerson.get(pIndex);
			} else {
				husb = new Person(arguments);
			}
			family.setHusb(husb);
		} else if (tag.equals("WIFE")) {
			Person wife;
			int pIndex1 = getPersonIndex(arguments, listPerson);
			if (pIndex1 != -1) {
				wife = listPerson.get(pIndex1);
			} else {
				wife = new Person(arguments);
			}
			family.setWife(wife);
		} else if (tag.equals("CHIL")) {

			Person chil;
			int pIndex = getPersonIndex(arguments, listPerson);
			if (pIndex != -1) {
				chil = listPerson.get(pIndex);
			} else {
				chil = new Person(arguments);
				listPerson.add(chil);
			}
			family.addChild(chil);
		} else if (tag.equals("DIV")) {
			family.setDiv(arguments);
		}
		if (perIndex != -1)
			listPerson.set(perIndex, person);
		if (famIndex != -1)
			listFam.set(famIndex, family);
	}

	private static void printGEDById(List<Person> listPerson, List<Family> listFam) {
		// TODO Auto-generated method stub
		Iterator<Person> iterPerson = listPerson.iterator();
		Iterator<Family> iterFamily = listFam.iterator();
		System.out.println("Person: " + listPerson.size());
		System.out.println("Family: " + listFam.size());
		while (iterPerson.hasNext()) {
			System.out.println(iterPerson.next().format());
		}
		while (iterFamily.hasNext()) {
			System.out.println(iterFamily.next().format());
		}
	}

}
