package by.bsu.appAds.jdbc.service;

import by.bsu.appAds.jdbc.dao.DAOManager;

public abstract class Service {
    protected DAOManager daoManager;

    public Service(DAOManager daoManager) {
        super();
        this.daoManager = daoManager;
    };

    public void close() {
        daoManager.close();
    }
}
