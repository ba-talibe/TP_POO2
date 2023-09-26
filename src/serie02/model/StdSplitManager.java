package serie02.model;

import java.io.File;
import java.io.IOException;
import serie02.util.FileStateTester;
import java.math.*;
import java.util.*;
import util.Contract;

public class StdSplitManager implements SplitManager {

	private File file;
	private static final int EOF = -1; 
	private List<Long> sizes = new ArrayList<Long>();
	
	public StdSplitManager(){
		this.file = null;
	}

	
	public StdSplitManager(File file){
		Contract.checkCondition(file != null);
		this.file = file;
				
	}

	@Override
	public boolean canSplit() {
		return FileStateTester.isSplittable(this.file);
	}

	@Override
	public String getDescription() {
		
		return FileStateTester.describe(this.file);
	}

	@Override
	public File getFile() {
		
		return this.file;
	}

	@Override
	public int getMaxFragmentNb() {
		Contract.checkCondition(canSplit());
		return (int) Math.min(MAX_FRAGMENT_NB, Math.max(1, Math.floor(file.length()/MIN_FRAGMENT_SIZE)));
	}

	@Override
	public long getMinFragmentSize() {
		Contract.checkCondition(canSplit());
		return  (int) Math.max(MIN_FRAGMENT_SIZE, Math.ceil( file.length()/MIN_FRAGMENT_SIZE));
	}

	@Override
	public String[] getSplitsNames() {
		Contract.checkCondition(canSplit());
		return null;
	}

	@Override
	public long[] getSplitsSizes() {
		
		return null;
	}

	@Override
	public void changeFor(File f) {
		Contract.checkCondition(f != null);
		this.file = f;
	}

	@Override
	public void close() {
		

	}

	@Override
	public void setSplitsNumber(int splitsNb) {
		Contract.checkCondition(canSplit());
		Contract.checkCondition(1 <= splitsNb && splitsNb<= getMaxFragmentNb());
		long q = (long) Math.floor(this.file.length()/splitsNb);
		long r = (long) this.file.length() % splitsNb;
		for (int i=0; i<r; i++ ) {
			this.sizes.add(q+1) ;
		}
		for (int i=(int)r; i <splitsNb; i++) {
			this.sizes.add(q) ;
		}
		
	}

	@Override
	public void setSplitsSizes(long size) {
		

	}

	@Override
	public void setSplitsSizes(long[] sizes) {
		

	}

	@Override
	public void split() throws IOException {
		

	}

}
