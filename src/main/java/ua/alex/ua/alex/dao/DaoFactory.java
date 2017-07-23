package ua.alex.ua.alex.dao;


public abstract class DaoFactory {
    public static final int H2 = 1;

    public abstract UserDao getUserDao();

    public static DaoFactory getDaoFactory(int factoryNumber) {
        if (factoryNumber == 1) {
            return new H2DaoFactory();
        } else {
            return null;
        }


    }

    public abstract void init();
}
