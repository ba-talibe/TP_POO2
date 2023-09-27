package serie02.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		Contract.checkCondition(canSplit());
		Contract.checkCondition(getMinFragmentSize() <= size);
		Contract.checkCondition(size <= this.file.length());
		
		double k = Math.floor(this.file.length() / size);
		double r = this.file.length() % size;
		double splitsNb = (r == 0) ? k : k + 1;
		for (double i=0; i<splitsNb-1; i++){
			this.sizes.add(size);
		}
		this.sizes.add((int) splitsNb, (r == 0) ? size : (long) r);

	}

	private long sumSizes(long[] sizes){
		Contract.checkCondition(sizes != null);
		long sum=0;
		for (int i=0;i<sizes.length;i++){
			sum += sizes[i];
		}
		return sum;
	}

	@Override
	public void setSplitsSizes(long[] sizes) {
		Contract.checkCondition(canSplit());
		Contract.checkCondition(sizes != null);
		Contract.checkCondition(sizes.length >= 1);
		Contract.checkCondition(sizes.length < getMaxFragmentNb());
		for (int i=0;i<sizes.length;i++){
			Contract.checkCondition(sizes[i] >= getMinFragmentSize());
			Contract.checkCondition(sizes[i] < this.file.length());
		}
		long sum = sumSizes(sizes);
		long n = getFile().length();
		if ( sum <= n){
			for (int i=0;i<sizes.length;i++){
				this.sizes.add(sizes[i]);
			}
			long lastSize = sum - n;
			if (lastSize > 0)
				this.sizes.add(lastSize);
			
		}else{
			sum = 0;
			for (int i=0;i<sizes.length;i++){
				this.sizes.add(sizes[i]);
				sum += sizes[i];
				if (sum >= n){
					if (sum == n)
						break;
					this.sizes.add(i, n -(sum - sizes[i]));
					break;
				}
			}
		}
		
	}

	private InputStream createInputStream() throws FileNotFoundException{
		return new BufferedInputStream(
			new FileInputStream(getFile())
			);

	}
	private OutputStream createOutputstream(long n) throws FileNotFoundException{
		return new BufferedOutputStream(
			new FileOutputStream(getFile().getAbsolutePath() + "." + n)
		);
	}

	@Override
	public void split() throws IOException {
		Contract.checkCondition(canSplit());
		InputStream input = createInputStream();
		OutputStream output;
		
		for ( int i=0;i<this.sizes.size(); i++){
			long size = this.sizes.get(i);
			byte[] readBytes = new byte[(int) size];
			if (input.read(readBytes) != -1){
				output = createOutputstream(i);
				output.write(readBytes);
			}else{
				throw new IOException();
			}

		}
	}

}
