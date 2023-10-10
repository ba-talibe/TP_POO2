package serie04;

import util.Contract;

public enum Civ {
	UKN("") {
		@Override protected boolean test (Civ candidate) {
		return candidate != UKN;
		}
	},
	
	MR("M.") {
		@Override protected boolean test (Civ candidate) {
		return false;
		}
	},
	
	MRS("Mme") {
		@Override protected boolean test (Civ candidate) {
		return candidate == MS;
		}
	},
	
	MS("Mlle") {
		@Override protected boolean test (Civ candidate) {
		return candidate == MRS;
		}
	};
	
	private final String description;
	Civ(String description) {
		this.description = description;
	}
	
	public boolean canEvolveTo(Civ candidate) {
		Contract.checkCondition(candidate != null);
		return test(candidate);
	}
	
	@Override
	public String toString() {
		return description;
	}
	
	protected abstract boolean test(Civ candidate);
}
