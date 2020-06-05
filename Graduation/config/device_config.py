# Here is the configuration information about the camera.
#
# NUMBER: The number of camera you want to use. Default is 0.
# OUTPUT: The output information.
#   FPS: Video frame rate.
#   WIDTH: The width of the frame.
#   HEIGHT: The height of the frame.
#   VIDEO_PATH: Video file output path.
#   DATA_PATH: Data file output path.
# FACEPP: The config of facepp api.
#   API_KEY and API_SECRET: Check it in the console of facepp. This is a trial version.
CAMERA = {
    'NUMBER': 0,
    'OUTPUT': {
        'FPS': 20.0,
        'WIDTH': 640,
        'HEIGHT': 480,
        'VIDEO_PATH': '.\\output\\video\\',
        'DATA_PATH': '.\\output\\data\\camera_eye'
    },
    'FACEPP': {
        'API_KEY': '',
        'API_SECRET': ''
    }
}

# Deprecated
# Here is the configuration information about the eye tracker device.
#
# PATH: Path to call executable.
EYE_TRACKER = {
    'PATH': '\\device\\tobii\\eyetracker.exe'
}

# Here is the configuration information about the brain wave device.
#
# PATH: Path to call executable.
# OUTPUT: The output information.
#   DATA_PATH: Data file output path.
BRAIN_WAVE = {
    'PATH': '\\device\\sichiray\\BrainWave.exe',
    'OUTPUT': {
        'DATA_PATH': '.\\output\\data\\wave\\'
    }
}

OTHER = {
    "DEVICE": 3,
    "MEMBER": 34
}
