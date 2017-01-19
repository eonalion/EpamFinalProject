package by.suboch.database;

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
    private static Lock connectionActionLock = new ReentrantLock();//TODO: ROMAN!!! Use boolean flag instead. Call me may be.
    private static AtomicBoolean poolInstanceCreated = new AtomicBoolean(false);
    private static AtomicBoolean poolClosed = new AtomicBoolean(false);

    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> takenConnections;

    private static ConnectionPool poolInstance;

    private ConnectionPool() {
        freeConnections = new ArrayBlockingQueue<>(poolSize);
        takenConnections = new ArrayBlockingQueue<>(poolSize);

        try {
            Class.forName(DRIVER).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            LOG.fatal("Error while initializing database driver.", e);
            throw new RuntimeException(e);
        }

        try {
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                freeConnections.put(new ProxyConnection(connection));
            }
        } catch (SQLException | InterruptedException e) {//TODO: ROMAN!!! See html.
            LOG.error("Error while getting new connection.", e);
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

    public ProxyConnection getConnection() {
        connectionActionLock.lock();
        ProxyConnection connection = null;

        if(poolClosed.get()) {
            return connection;
        }

        try {
            connection = freeConnections.take();
            takenConnections.put(connection);
        } catch (InterruptedException e) {
            LOG.error("Error while getting connection from pool.", e);
        } finally {
            connectionActionLock.unlock();
        }

        return connection;
    }

    public void freeConnection(ProxyConnection connection) {
        takenConnections.remove(connection);
        try {
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            LOG.error("Error while returning connection to pool.", e);
        }
    }

    public void closePool() {
        connectionActionLock.lock();
        try {
            poolClosed.set(true);
            for (int i = 0; i < poolSize; i++) {
                freeConnections.take().finalClose();
            }
//            for (int i = 0; i < takenConnections.size(); i++) {
//                takenConnections.take().finalClose();
//            }
        } catch (SQLException | InterruptedException e) {
            LOG.error("Error while closing pool.", e);
        } finally {
            connectionActionLock.unlock();
        }
    }
}
