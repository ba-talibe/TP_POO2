package serie01.util;

import java.io.File;
import java.util.*;

public final class DBFactory {
    private DBFactory() {
       // rien 
    }
    
    public static CurrencyDB createInternalDB() {
        return new StdCurrencyDB();
    }
    
    public static CurrencyDB createLocalDB(File f) throws Exception {
        //Contract.checkCondition(f != null);
    	assert f != null;

//        return new LocalCurrencyDB(f);
        throw new UnsupportedOperationException("Type non implémenté !");
    }
    
    public static CurrencyDB createRemoteDB(String... data) throws Exception {
        throw new UnsupportedOperationException("Type non implémenté !");
    }
}
