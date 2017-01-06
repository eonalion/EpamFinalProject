package com.suboch.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ConnectionPool {
    private static final Logger LOG = LogManager.getLogger();
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("properties.database");
    private static final String URL = RESOURCE_BUNDLE.getString("db.url");
    private static final String DRIVER = RESOURCE_BUNDLE.getString("db.driver");
    private static final String LOGIN = RESOURCE_BUNDLE.getString("db.login");
    private static final String PASSWORD = RESOURCE_BUNDLE.getString("db.password");
    private int poolSize = Integer.parseInt(RESOURCE_BUNDLE.getString("db.poolsize"));

    private static Lock connectionPoolLock = new ReentrantLock();
    private static Lock connectionActionLock = new ReentrantLock();
    private static AtomicBoolean poolInstanceCreated = new AtomicBoolean(false);
    private static AtomicBoolean poolClosed = new AtomicBoolean(false);

    private BlockingQueue<ConnectionProxy> freeConnections;
    private BlockingQueue<ConnectionProxy> takenConnections;

    private static ConnectionPool poolInstance;

    private ConnectionPool() {
        freeConnections = new ArrayBlockingQueue<>(poolSize);
        takenConnections = new ArrayBlockingQueue<>(poolSize);

        try {
            Class.forName(DRIVER).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            LOG.error(e);
            //TODO:
        }

        try {
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                freeConnections.put(new ConnectionProxy(connection));
            }
        } catch (SQLException | InterruptedException e) {
            //TODO:
        }
    }

    public static ConnectionPool getInstance() {
        if (!poolInstanceCreated.get()) {
            connectionPoolLock.lock();
            try {
                if (poolInstance == null) {
                    poolInstance = new ConnectionPool();
                    poolInstanceCreated.set(true);
                }
            } finally {
                connectionPoolLock.unlock();
            }
        }
        return poolInstance;
    }

    public ConnectionProxy getConnection() {
        connectionActionLock.lock();
        ConnectionProxy connection = null;

        if(poolClosed.get()) {
            // FIXME: return null?
            return connection;
        }

        try {
            connection = freeConnections.take();
            takenConnections.put(connection);
        } catch (InterruptedException e) {
            //TODO:
        } finally {
            connectionActionLock.unlock();
        }

        return connection;
    }

    public void freeConnection(ConnectionProxy connection) {
        takenConnections.remove(connection);
        try {
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            //TODO:
        }
    }

    public void closePool() {
        connectionActionLock.lock();
        try {
            for (int i = 0; i < freeConnections.size(); i++) {
                freeConnections.take().finalClose();
            }
            for (int i = 0; i < takenConnections.size(); i++) {
                takenConnections.take().finalClose();
            }
            poolClosed.set(true);
        } catch (SQLException | InterruptedException e) {
            //TODO:
        } finally {
            connectionActionLock.unlock();
        }
    }
}
