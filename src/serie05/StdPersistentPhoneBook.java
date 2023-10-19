package serie05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NavigableSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import serie04.Civ;
import serie04.Contact;
import serie04.StdPhoneBook;
import util.Contract;

public class StdPersistentPhoneBook<C extends Contact & Comparable<C>, N extends PhoneNumber> extends StdPhoneBook<C, N> implements PersistentPhoneBook<C, N> {

	File file;
	DataFactory<C, N> df;
	Matcher m = Pattern.compile(LINE_PATTERN).matcher("");
	public StdPersistentPhoneBook(DataFactory<C, N> df, File file){
		super();
		Contract.checkCondition(df != null);
		this.file = file;
		this.df = df;
	}

	public StdPersistentPhoneBook( DataFactory<C, N> df){
		super();
		Contract.checkCondition(df != null);
		this.df = df;
		file = null;
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return file;
	}

	@Override
	public void load() throws IOException, BadSyntaxException {
		// TODO Auto-generated method stub
		Contract.checkCondition(getFile() != null);
		try{
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while( (line = bf.readLine()) !=  null ){
				if (!m.reset(line).matches()) 
					throw new BadSyntaxException();
				String[] info = line.split(":");
				Civ civ =  Civ.values()[Integer.parseInt(info[0].trim())] ;
				String nom = info[1].trim();
				String prenom = info[2].trim();
				String[] nums = info[3].split(",");
				System.out.println(nom + " : " + prenom);
				C contact = df.createContact(civ, nom, prenom);
				
				if (this.contains(contact)){
					for (int i=0;i<nums.length;i++){
						this.addPhoneNumber(contact, df.createPhoneNumber(nums[i]));
					}
				}else{
					this.addEntry(contact, df.createPhoneNumber(nums[0]));
					for (int i=1;i<nums.length;i++){
						this.addPhoneNumber(contact, df.createPhoneNumber(nums[i]));
					}
				}
			}
		}catch (FileNotFoundException e){
			this.clear();
			throw new FileNotFoundException();
		}catch (IOException e){
			
			throw new IOException();
		}catch (BadSyntaxException e){
			this.clear();
			throw new BadSyntaxException();
		}catch( Error e){
			this.clear();
			throw e;
		}
		
	}

	@Override
	public void save() throws IOException {
		Contract.checkCondition(getFile() != null);
		
		try {
			
			BufferedWriter bw =new BufferedWriter(new FileWriter(file));
			for (C contact : super.contacts()){
				String line = "";
				line += contact.getCivility().ordinal() +  ":" + contact.getLastName() + ":" + contact.getFirstName() + ":";
				line += super.phoneNumbers(contact).get(0).national();

				int nNums = super.phoneNumbers(contact).size();
				for (int i=1; i< nNums; i++){
					line = line + "," + super.phoneNumbers(contact).get(i).national() ;
				}
				
				bw.write(line);
				bw.newLine();
			}
			bw.flush();
		}catch(FileNotFoundException e){
			throw e;
		} catch(IOException e){
			throw e;
		}
	}

	@Override
	public void setFile(File file) {
		// TODO Auto-generated method stub
		Contract.checkCondition(file != null);
		this.file = file;		
	}

	@Override
	public NavigableSet<C> contacts() {
		// TODO Auto-generated method stub
		return super.contacts();
	}

	@Override
	public boolean contains(C p) {
		// TODO Auto-generated method stub
		return super.contains(p);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return super.isEmpty();
	}

	@Override
	public List<N> phoneNumbers(C p) {
		// TODO Auto-generated method stub
		return super.phoneNumbers(p);
	}

	@Override
	public void addEntry(C p, N n) {
		// TODO Auto-generated method stub
		super.addEntry(p, n);
	}

	@Override
	public void addEntry(C p, List<N> nums) {
		// TODO Auto-generated method stub
		super.addEntry(p, nums);
	}

	@Override
	public void addPhoneNumber(C p, N n) {
		// TODO Auto-generated method stub
		super.addPhoneNumber(p, n);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}

	@Override
	public void deletePhoneNumber(C p, N n) {
		// TODO Auto-generated method stub
		super.deletePhoneNumber(p, n);
	}

	@Override
	public void removeEntry(C p) {
		// TODO Auto-generated method stub
		super.removeEntry(p);
	}

}
