package ch.ullinger.dicexpression.ref;

public class NamedExpressionStoreFactory {

    private static NamedExpressionStore store = null;

    public static NamedExpressionStore getInstance() {
        return store;
    }

    public static NamedExpressionStore getStore() {
        return store;
    }

    public static void setStore(final NamedExpressionStore store) {
        NamedExpressionStoreFactory.store = store;
    }


}
