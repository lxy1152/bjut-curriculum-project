import sqlite3
import threading

from config import db_config as config


class Database(object):
    """Database object.

    Use this object to operate the database.

    Attributes:
        connection: The connection to database.
        cursor: Database cursor.
    """

    def __init__(self) -> None:
        """ Init method. """

        self.connection = sqlite3.connect(config.DATABASE['DB_NAME'],
                                          check_same_thread=config.DATABASE['CHECK_SAME_THREAD'])
        self.cursor = self.connection.cursor()
        self.lock = threading.Lock()

    def __del__(self) -> None:
        """Del method.

        Close the connection.
        """

        self.connection.close()

    def execute(self, sql: str) -> sqlite3.Cursor:
        """Execute function.

        Execute SQL statement and commit.

        Args:
            sql: SQL statement you want to execute.
        Returns:
            cursor: Database cursor.
        """
        self.lock.acquire(True)
        self.cursor.execute(sql)
        self.connection.commit()
        self.lock.release()
        return self.cursor
