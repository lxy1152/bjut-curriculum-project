# Here is the configuration information about the database.
# SQLite is selected for convenience.
#
# DB_NAME: The name of the database.
# CHECK_SAME_THREAD: Set to False, otherwise an error will be reported.
DATABASE = {
    'DB_NAME': 'concentration.db',
    'CHECK_SAME_THREAD': False
}

# Here is the configuration information about the special user.
#
# ADMIN: All eight digits are 0.
# TEACHER: From START to END must conform to FORMAT.
# STUDENT: No format requirements.
SPECIAL_USER = {
    'ADMIN': '00000000',
    'TEACHER': {
        'START': 2,
        'END': 4,
        'FORMAT': '00'
    }
}
