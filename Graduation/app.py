import datetime

from flask import Flask

import route
from config import server_config as config


def main():
    """Main function.

    The main function to run the program.
    The information of host, debug and port are saved in the server_config file.
    """

    app = Flask(__name__)
    app.config['SECRET_KEY'] = config.SERVER['SECRET_KEY']
    app.permanent_session_lifetime = datetime.timedelta(minutes=30)
    route.Route(app)
    app.run(host=config.SERVER['HOST'], debug=config.SERVER['DEBUG'], port=config.SERVER['PORT'])


if __name__ == '__main__':
    main()
