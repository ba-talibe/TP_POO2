package serie02.model;

import java.io.File;
import java.io.IOException;

public class StdSplitManager implements SplitManager {

	@Override
	public boolean canSplit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxFragmentNb() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getMinFragmentSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getSplitsNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long[] getSplitsSizes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeFor(File f) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSplitsNumber(int splitsNb) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSplitsSizes(long size) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSplitsSizes(long[] sizes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void split() throws IOException {
		// TODO Auto-generated method stub

	}

}
